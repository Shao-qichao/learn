# =====================================================================
#  Java 学习中心 · 一键环境搭建脚本（Windows PowerShell 版）
#  功能：检测系统 → 安装 Git/JDK/VS Code → 克隆项目 → 用户名替换 → 验证
#  用法：右键 → 使用 PowerShell 运行
#  或：  powershell -ExecutionPolicy Bypass -File setup-windows.ps1
# =====================================================================

# ─────────────── 全局变量 ───────────────
$LogDir    = Join-Path $env:TEMP "java-setup-logs"
$TimeStamp = Get-Date -Format 'yyyyMMdd-HHmmss'
$LogFile   = Join-Path $LogDir "setup-$TimeStamp.log"
$RepoUrl   = "https://github.com/Shao-qichao/learn.git"
$CloneDir  = "D:\java学习中心"
$JdkVersion = "11"          # 可改为 17 / 21
$GitUser   = ""             # 留空则交互输入
$GitEmail  = ""

# 备份目录（用户名替换时备份原文件）
$BackupDir = Join-Path $LogDir "backups-$TimeStamp"

# ─────────────── 工具函数 ───────────────

function Write-Log {
    param([string]$Msg, [string]$Level = "INFO")
    $line = "[$(Get-Date -Format 'HH:mm:ss')] [$Level] $Msg"
    Write-Host $line
    if (-not (Test-Path $LogDir)) { New-Item -ItemType Directory -Path $LogDir -Force | Out-Null }
    Add-Content -Path $LogFile -Value $line -Encoding UTF8
}

function Write-Step  { param([string]$Msg) Write-Host "`n========== $Msg ==========" -ForegroundColor Cyan }
function Write-OK    { param([string]$Msg) Write-Host "  [OK] $Msg" -ForegroundColor Green }
function Write-Warn  { param([string]$Msg) Write-Host "  [!]  $Msg" -ForegroundColor Yellow }
function Write-Err   { param([string]$Msg) Write-Host "  [X]  $Msg" -ForegroundColor Red }

function Test-Command {
    param([string]$Name)
    $c = Get-Command $Name -ErrorAction SilentlyContinue
    return [bool]$c
}

function Refresh-Path {
    $mp = [Environment]::GetEnvironmentVariable('Path','Machine')
    $up = [Environment]::GetEnvironmentVariable('Path','User')
    $env:Path = "$mp;$up"
}

function Confirm-Action {
    param([string]$Prompt)
    Write-Host "`n  $Prompt" -ForegroundColor Yellow
    $reply = Read-Host "  确认继续？(y/N)"
    return ($reply -eq 'y' -or $reply -eq 'Y')
}

# ─────────────── 1. 系统检测 ───────────────

function Invoke-SystemCheck {
    Write-Step "1/6  系统环境检测"

    # 操作系统
    $os = (Get-CimInstance Win32_OperatingSystem).Caption
    Write-Log "操作系统: $os"
    Write-OK $os

    # 架构
    $arch = if ([Environment]::Is64BitOperatingSystem) { "64-bit" } else { "32-bit" }
    Write-Log "架构: $arch"
    Write-OK $arch

    # PowerShell 版本
    $psv = $PSVersionTable.PSVersion.ToString()
    Write-Log "PowerShell: $psv"
    if ($PSVersionTable.PSVersion.Major -lt 5) {
        Write-Err "PowerShell 版本过低（$psv），需要 5.1 以上"
        return $false
    }
    Write-OK "PowerShell $psv"

    # 磁盘空间（D盘至少 500MB）
    $d = Get-PSDrive D -ErrorAction SilentlyContinue
    if ($d) {
        $freeMB = [math]::Round($d.Free / 1MB)
        Write-Log "D盘可用空间: ${freeMB}MB"
        if ($freeMB -lt 500) { Write-Warn "D盘空间不足 500MB（当前 ${freeMB}MB）" }
        else { Write-OK "D盘可用 ${freeMB}MB" }
    } else {
        Write-Warn "未检测到 D 盘，项目将克隆到桌面"
        $script:CloneDir = Join-Path $env:USERPROFILE "Desktop\java学习中心"
    }

    # 网络连接
    Write-Log "检测网络连接..."
    try {
        $resp = Invoke-WebRequest -Uri "https://github.com" -Method Head -TimeoutSec 10 -UseBasicParsing
        Write-OK "网络连接正常"
    } catch {
        Write-Warn "GitHub 直连失败，尝试检测代理..."
        $ie = Get-ItemProperty "HKCU:\Software\Microsoft\Windows\CurrentVersion\Internet Settings"
        if ($ie.ProxyEnable -eq 1 -and $ie.ProxyServer) {
            Write-OK "检测到系统代理: $($ie.ProxyServer)"
            $script:Proxy = $ie.ProxyServer
        } else {
            Write-Err "无法连接 GitHub，请检查网络或代理设置"
            Write-Err "如果你使用 VPN/代理，请先开启后再运行此脚本"
            return $false
        }
    }

    return $true
}

# ─────────────── 2. 软件安装 ───────────────

function Install-Software {
    Write-Step "2/6  软件安装检测与部署"

    # --- Git ---
    Write-Log "检测 Git..."
    if (Test-Command git) { Write-OK "Git 已安装: $(git --version)" }
    else {
        Write-Warn "Git 未安装，正在通过 winget 安装..."
        try {
            winget install --id Git.Git -e --accept-source-agreements --accept-package-agreements --silent 2>&1 | Out-Null
            Refresh-Path
            if (Test-Command git) { Write-OK "Git 安装成功: $(git --version)" }
            else { Write-Err "Git 安装失败，请手动安装: https://git-scm.com/download/win"; return $false }
        } catch {
            Write-Err "Git 安装异常: $($_.Exception.Message)"
            return $false
        }
    }

    # --- JDK ---
    Write-Log "检测 JDK..."
    if (Test-Command java) {
        $jv = (java -version 2>&1 | Select-Object -First 1)
        Write-OK "JDK 已安装: $jv"
    } else {
        Write-Warn "JDK 未安装，正在通过 winget 安装 JDK $JdkVersion ..."
        $jdkId = if ($JdkVersion -eq "11") { "EclipseAdoptium.Temurin.11.JDK" }
                 elseif ($JdkVersion -eq "17") { "EclipseAdoptium.Temurin.17.JDK" }
                 elseif ($JdkVersion -eq "21") { "EclipseAdoptium.Temurin.21.JDK" }
                 else { "EclipseAdoptium.Temurin.11.JDK" }
        try {
            winget install --id $jdkId -e --accept-source-agreements --accept-package-agreements --silent 2>&1 | Out-Null
            Refresh-Path
            if (Test-Command java) { Write-OK "JDK 安装成功: $(java -version 2>&1 | Select-Object -First 1)" }
            else { Write-Err "JDK 安装失败，请手动安装: https://adoptium.net/"; return $false }
        } catch {
            Write-Err "JDK 安装异常: $($_.Exception.Message)"
            return $false
        }
    }

    # --- VS Code ---
    Write-Log "检测 VS Code..."
    $vscode = Get-Command code -ErrorAction SilentlyContinue
    if ($vscode) { Write-OK "VS Code 已安装: $($vscode.Source)" }
    else {
        # 也检查常见安装路径
        $vsPaths = @(
            "$env:LOCALAPPDATA\Programs\Microsoft VS Code\bin\code.cmd",
            "C:\Program Files\Microsoft VS Code\bin\code.cmd",
            "C:\Program Files (x86)\Microsoft VS Code\bin\code.cmd"
        )
        $found = $false
        foreach ($p in $vsPaths) { if (Test-Path $p) { $found = $true; Write-OK "VS Code 已安装: $p"; break } }
        if (-not $found) {
            Write-Warn "VS Code 未安装，正在通过 winget 安装..."
            try {
                winget install --id Microsoft.VisualStudioCode -e --accept-source-agreements --accept-package-agreements --silent 2>&1 | Out-Null
                Refresh-Path
                $vscode = Get-Command code -ErrorAction SilentlyContinue
                if ($vscode) { Write-OK "VS Code 安装成功" }
                else { Write-Warn "VS Code 安装可能需要重启终端才能生效，不影响后续步骤" }
            } catch {
                Write-Warn "VS Code 安装异常: $($_.Exception.Message)，请手动安装: https://code.visualstudio.com/"
            }
        }
    }

    return $true
}

# ─────────────── 3. Git 配置 ───────────────

function Invoke-GitConfig {
    Write-Step "3/6  Git 用户信息配置"

    # 交互获取用户名和邮箱
    if (-not $GitUser) {
        $existing = git config --global user.name 2>$null
        if ($existing) {
            Write-OK "当前 Git 用户名: $existing"
            if (-not (Confirm-Action "是否修改为新的用户名？")) { $GitUser = $existing }
        }
        if (-not $GitUser) { $GitUser = Read-Host "  请输入 Git 用户名（如：张三）" }
    }
    if (-not $GitEmail) {
        $existing = git config --global user.email 2>$null
        if ($existing) {
            Write-OK "当前 Git 邮箱: $existing"
            if (-not (Confirm-Action "是否修改为新的邮箱？")) { $GitEmail = $existing }
        }
        if (-not $GitEmail) { $GitEmail = Read-Host "  请输入 Git 邮箱（如：zhangsan@gmail.com）" }
    }

    git config --global user.name  $GitUser
    git config --global user.email $GitEmail
    Write-Log "已配置 Git: name=$GitUser email=$GitEmail"
    Write-OK "Git 用户名: $GitUser"
    Write-OK "Git 邮箱:   $GitEmail"

    # 配置代理（如果检测到）
    if ($script:Proxy) {
        $proxyUrl = "http://$($script:Proxy)"
        git config --global http.proxy  $proxyUrl
        git config --global https.proxy $proxyUrl
        Write-OK "Git 代理已配置: $proxyUrl"
    }

    # 行尾设置
    git config --global core.autocrlf true
    Write-OK "Git 行尾设置为 autocrlf=true（Windows 适配）"
}

# ─────────────── 4. 项目克隆 ───────────────

function Invoke-Clone {
    Write-Step "4/6  克隆项目仓库"

    if (Test-Path (Join-Path $CloneDir ".git")) {
        Write-OK "项目已存在: $CloneDir"
        if (Confirm-Action "项目已存在，是否重新拉取最新代码？") {
            Push-Location $CloneDir
            git pull origin main 2>&1 | ForEach-Object { Write-Log $_ }
            Pop-Location
        }
    } else {
        Write-Log "开始克隆: $RepoUrl -> $CloneDir"
        git clone $RepoUrl $CloneDir 2>&1 | ForEach-Object { Write-Log $_ }
        if (Test-Path (Join-Path $CloneDir ".git")) {
            Write-OK "克隆成功: $CloneDir"
        } else {
            Write-Err "克隆失败，请检查网络或代理"
            return $false
        }
    }

    # 创建 JavaLearning 目录
    $jl = Join-Path $env:USERPROFILE "JavaLearning"
    if (-not (Test-Path $jl)) {
        New-Item -ItemType Directory -Path $jl -Force | Out-Null
        Write-OK "已创建 Java 学习目录: $jl"
    } else {
        Write-OK "Java 学习目录已存在: $jl"
    }
    Write-Log "JavaLearning 路径: $jl"

    return $true
}

# ─────────────── 5. 用户名替换 ───────────────

function Invoke-UsernamePatch {
    Write-Step "5/6  路径适配（用户名替换）"

    $htmlPath = Join-Path $CloneDir "java-course-site\index.html"
    if (-not (Test-Path $htmlPath)) {
        Write-Err "index.html 未找到: $htmlPath"
        return $false
    }

    # 备份原文件
    if (-not (Test-Path $BackupDir)) { New-Item -ItemType Directory -Path $BackupDir -Force | Out-Null }
    $backupFile = Join-Path $BackupDir "index.html.bak"
    Copy-Item $htmlPath $backupFile -Force
    Write-Log "已备份: $htmlPath -> $backupFile"
    Write-OK "原文件已备份: $backupFile"

    # 读取内容
    $content = [System.IO.File]::ReadAllText($htmlPath, [System.Text.Encoding]::UTF8)

    # 替换：把 c:\Users\13053\JavaLearning → 当前用户的 JavaLearning
    $oldUser = "13053"
    $currentUser = $env:USERNAME
    $userProfile = $env:USERPROFILE

    # 需要替换的模式列表（可扩展）
    $patterns = @(
        @{ Old = 'c:\Users\13053\JavaLearning';       New = "c:\Users\$currentUser\JavaLearning";       Desc = "JavaLearning 路径（单反斜杠）" },
        @{ Old = 'c:\\Users\\13053\\JavaLearning';     New = "c:\\Users\\$currentUser\\JavaLearning";     Desc = "JavaLearning 路径（双反斜杠）" },
        @{ Old = 'c%3A/Users/13053/JavaLearning';      New = "c%3A/Users/$currentUser/JavaLearning";     Desc = "vscode URL 路径" },
        @{ Old = 'c:/Users/13053/JavaLearning';        New = "c:/Users/$currentUser/JavaLearning";        Desc = "正斜杠路径" }
    )

    $totalReplaced = 0
    foreach ($p in $patterns) {
        $count = ([regex]::Matches($content, [regex]::Escape($p.Old))).Count
        if ($count -gt 0) {
            $content = $content.Replace($p.Old, $p.New)
            Write-Log "替换: $($p.Desc) - 替换 $count 处"
            Write-OK "$($p.Desc): 替换 $count 处"
            $totalReplaced += $count
        }
    }

    # 写回文件
    $utf8NoBom = New-Object System.Text.UTF8Encoding($false)
    [System.IO.File]::WriteAllText($htmlPath, $content, $utf8NoBom)
    Write-Log "用户名替换完成，共替换 $totalReplaced 处，当前用户: $currentUser"
    Write-OK "路径已适配当前用户: $currentUser"
    Write-OK "替换日志: $LogFile"

    # 生成替换报告
    $report = Join-Path $BackupDir "replace-report.txt"
    "Java 学习中心 · 用户名替换报告" | Set-Content $report -Encoding UTF8
    "生成时间: $(Get-Date)" | Add-Content $report -Encoding UTF8
    "原用户: $oldUser" | Add-Content $report -Encoding UTF8
    "新用户: $currentUser" | Add-Content $report -Encoding UTF8
    "共替换: $totalReplaced 处" | Add-Content $report -Encoding UTF8
    "" | Add-Content $report -Encoding UTF8
    "替换详情:" | Add-Content $report -Encoding UTF8
    foreach ($p in $patterns) {
        "  $($p.Desc): $($p.Old) -> $($p.New)" | Add-Content $report -Encoding UTF8
    }
    Write-OK "替换报告: $report"

    return $true
}

# ─────────────── 6. 验证测试 ───────────────

function Invoke-Verify {
    Write-Step "6/6  验证测试"

    $allOK = $true

    # Git
    if (Test-Command git) { Write-OK "Git:        $(git --version)" }
    else { Write-Err "Git 不可用"; $allOK = $false }

    # JDK
    if (Test-Command java) { Write-OK "JDK:        $(java -version 2>&1 | Select-Object -First 1)" }
    else { Write-Err "JDK 不可用"; $allOK = $false }

    # VS Code
    $code = Get-Command code -ErrorAction SilentlyContinue
    if ($code) { Write-OK "VS Code:    可用" }
    else {
        $vsPaths = @("$env:LOCALAPPDATA\Programs\Microsoft VS Code\bin\code.cmd","C:\Program Files\Microsoft VS Code\bin\code.cmd")
        $found = $false; foreach ($p in $vsPaths) { if (Test-Path $p) { $found = $true; break } }
        if ($found) { Write-OK "VS Code:    已安装（需重启终端）" }
        else { Write-Warn "VS Code:    未检测到，可能需要重启终端"; }
    }

    # 项目文件
    $htmlPath = Join-Path $CloneDir "java-course-site\index.html"
    if (Test-Path $htmlPath) { Write-OK "index.html: 存在" }
    else { Write-Err "index.html 缺失"; $allOK = $false }

    # 练习文件夹
    $practiceDir = Join-Path $CloneDir "javacourse"
    if (Test-Path $practiceDir) {
        $dirCount = (Get-ChildItem $practiceDir -Directory).Count
        Write-OK "练习文件夹: $dirCount 个"
    } else { Write-Err "练习文件夹缺失"; $allOK = $false }

    # JavaLearning
    $jl = Join-Path $env:USERPROFILE "JavaLearning"
    if (Test-Path $jl) { Write-OK "JavaLearning: 已创建" }
    else { Write-Err "JavaLearning 目录缺失"; $allOK = $false }

    # Git 用户配置
    $gn = git config --global user.name 2>$null
    $ge = git config --global user.email 2>$null
    if ($gn -and $ge) { Write-OK "Git 用户:   $gn [$ge]" }
    else { Write-Warn "Git 用户信息未配置" }

    # 路径替换验证
    $content = [System.IO.File]::ReadAllText($htmlPath, [System.Text.Encoding]::UTF8)
    $oldCount = ([regex]::Matches($content, 'Users\\13053')).Count
    if ($oldCount -eq 0) { Write-OK "路径替换:   无旧路径残留" }
    else { Write-Warn "路径替换:   仍有 $oldCount 处旧路径" }

    Write-Log "验证结果: allOK=$allOK"
    return $allOK
}

# ─────────────── 主流程 ───────────────

Write-Host "==================================================" -ForegroundColor Cyan
Write-Host "  Java 学习中心 - 一键环境搭建 (Windows)" -ForegroundColor Cyan
Write-Host "  自动安装 Git + JDK + VS Code" -ForegroundColor Cyan
Write-Host "  自动克隆项目 + 适配用户路径" -ForegroundColor Cyan
Write-Host "==================================================" -ForegroundColor Cyan

Write-Log "===== 脚本启动 ====="

# 用户确认
if (-not (Confirm-Action "本脚本将安装 Git、JDK $JdkVersion、VS Code，并克隆项目到 $CloneDir`n继续？")) {
    Write-Host "  已取消。" -ForegroundColor Yellow
    exit 0
}

# 1. 系统检测
if (-not (Invoke-SystemCheck)) { Write-Err "系统检测未通过，脚本终止"; Write-Log "终止：系统检测失败"; exit 1 }

# 2. 软件安装
if (-not (Install-Software)) { Write-Err "软件安装失败，脚本终止"; Write-Log "终止：软件安装失败"; exit 1 }

# 3. Git 配置
Invoke-GitConfig

# 4. 克隆项目
if (-not (Invoke-Clone)) { Write-Err "项目克隆失败，脚本终止"; Write-Log "终止：克隆失败"; exit 1 }

# 5. 用户名替换
if (-not (Invoke-UsernamePatch)) { Write-Err "路径适配失败"; Write-Log "终止：路径替换失败" }

# 6. 验证
$verifyOK = Invoke-Verify

# ─────────────── 总结 ───────────────
Write-Step "完成"
if ($verifyOK) {
    Write-Host "  全部就绪！" -ForegroundColor Green
    Write-Host "  项目路径:   $CloneDir" -ForegroundColor Green
    $entryPath = Join-Path $CloneDir 'java-course-site\index.html'
    Write-Host "  网页入口:   $entryPath" -ForegroundColor Green
    Write-Host "  练习代码:   $env:USERPROFILE\JavaLearning" -ForegroundColor Green
    Write-Host "  日志文件:   $LogFile" -ForegroundColor Green
    Write-Host "  双击 index.html 即可开始学习！" -ForegroundColor Green
} else {
    Write-Host "  部分检查未通过，详见日志: $LogFile" -ForegroundColor Yellow
}
Write-Log "===== 脚本结束 ====="
