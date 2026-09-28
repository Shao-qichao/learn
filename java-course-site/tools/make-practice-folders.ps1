# =====================================================================
#  Java 学习中心 · 动手练习项目生成器
#  作用：
#    1) 读取 tools\practice-data\*.json（页面上 48 个板块的练习数据）
#    2) 在 $WorkspaceRoot 下批量生成标准化练习项目文件夹（含初始代码）
#    3) 把同一份数据注入 index.html 的 <script id="practiceData"> 数据块
#  说明：默认只创建缺失文件，不覆盖学员已改过的代码；加 -Force 可强制覆盖。
#  注意：本文件必须保存为「UTF-8 带 BOM」，否则 Windows PowerShell 5.1 会读成乱码。
# =====================================================================
param(
  [string]$WorkspaceRoot = 'D:\java学习中心\javacourse',
  [switch]$Force,
  [switch]$SkipHtml
)

$ErrorActionPreference = 'Stop'
$Utf8NoBom = New-Object System.Text.UTF8Encoding($false)
$Utf8Bom   = New-Object System.Text.UTF8Encoding($true)

function Write-Text($path, $text, $withBom = $false) {
  $dir = Split-Path -Parent $path
  if (-not (Test-Path -LiteralPath $dir)) { New-Item -ItemType Directory -Force -Path $dir | Out-Null }
  $enc = if ($withBom) { $Utf8Bom } else { $Utf8NoBom }
  [System.IO.File]::WriteAllText($path, $text, $enc)
}
function Write-TextIfNeeded($path, $text, $withBom = $false) {
  if ((Test-Path -LiteralPath $path) -and -not $Force) { return $false }
  Write-Text $path $text $withBom
  return $true
}
# 统一成 Windows 换行（先归一化再转换，避免出现重复的 CR）
function Crlf($text) { return ($text -replace "`r`n", "`n") -replace "`n", "`r`n" }

$siteDir  = Split-Path -Parent $PSScriptRoot          # ...\java-course-site
$htmlPath = Join-Path $siteDir 'index.html'
$dataDir  = Join-Path $PSScriptRoot 'practice-data'

# ---------- 1. 读练习数据 ----------
$items = @()
foreach ($f in Get-ChildItem -LiteralPath $dataDir -Filter *.json | Sort-Object Name) {
  $items += (Get-Content -LiteralPath $f.FullName -Raw -Encoding UTF8 | ConvertFrom-Json)
}
$items = $items | Sort-Object { [int]$_.id.Substring(1,1) }, { [int]$_.id.Substring(3) }
Write-Host "读取练习数据：$($items.Count) 条" -ForegroundColor Cyan

# ---------- 2. 从 index.html 侧边栏取每个板块的中文标题 ----------
$html = Get-Content -LiteralPath $htmlPath -Raw -Encoding UTF8
$titles = @{}
foreach ($m in [regex]::Matches($html, 'data-page="(c\d+s\d+)"><span class="lab">([^<]+)</span>')) {
  $titles[$m.Groups[1].Value] = $m.Groups[2].Value
}

function Get-DirName($item) {
  $ch  = [int]$item.id.Substring(1,1)
  $sec = [int]$item.id.Substring(3)
  return ('ch{0:d2}-s{1:d2}-{2}' -f $ch, $sec, $item.slug)
}
function Normalize-Imports($imports) {
  $out = @()
  foreach ($i in $imports) {
    $t = "$i".Trim()
    $t = $t -replace '^import\s+', ''
    $t = $t.TrimEnd(';')
    if ($t) { $out += "import $t;" }
  }
  return $out
}
function Get-VsCodeCli() {
  # 1) 优先从 vscode:// 协议注册表里反推 Code.exe 位置
  $reg = (Get-ItemProperty 'HKCU:\Software\Classes\vscode\shell\open\command' -ErrorAction SilentlyContinue).'(default)'
  if ($reg -and $reg -match '"([^"]*Code\.exe)"') {
    $exe = $Matches[1]
    $cli = Join-Path (Split-Path -Parent $exe) 'bin\code.cmd'
    if (Test-Path -LiteralPath $cli) { return $cli }
    return $exe
  }
  # 2) 常见安装位置
  foreach ($p in @("$env:LOCALAPPDATA\Programs\Microsoft VS Code\bin\code.cmd",
                   "$env:ProgramFiles\Microsoft VS Code\bin\code.cmd",
                   "${env:ProgramFiles(x86)}\Microsoft VS Code\bin\code.cmd")) {
    if (Test-Path -LiteralPath $p) { return $p }
  }
  # 3) PATH 里的 code
  $cmd = Get-Command code -ErrorAction SilentlyContinue
  if ($cmd) { return $cmd.Source }
  # 4) 兜底：在常见盘符里找 “Microsoft VS Code” 目录
  foreach ($base in @($env:ProgramFiles, ${env:ProgramFiles(x86)}, 'D:\', 'C:\')) {
    $cand = Get-ChildItem -Path $base -Directory -Filter '*VS Code*' -ErrorAction SilentlyContinue | Select-Object -First 1
    if ($cand) {
      $cli = Join-Path $cand.FullName 'bin\code.cmd'
      if (Test-Path -LiteralPath $cli) { return $cli }
    }
  }
  return $null
}
$vsCodeCli = Get-VsCodeCli

# ---------- 3. 生成每个板块的练习项目 ----------
$created = 0; $skipped = 0
foreach ($it in $items) {
  $dirName = Get-DirName $it
  $dir     = Join-Path $WorkspaceRoot $dirName
  $srcDir  = Join-Path $dir 'src'
  if (-not (Test-Path -LiteralPath $srcDir)) { New-Item -ItemType Directory -Force -Path $srcDir | Out-Null }
  $title   = if ($titles[$it.id]) { $titles[$it.id] } else { $it.id }
  $ch      = [int]$it.id.Substring(1,1)

  # --- src\Main.java ---
  $imp = Normalize-Imports $it.imports
  $sb = New-Object System.Text.StringBuilder
  [void]$sb.AppendLine("/*")
  [void]$sb.AppendLine(" * ============================================================")
  [void]$sb.AppendLine(" *  $title   （板块 $($it.id) / 第 $ch 章）")
  [void]$sb.AppendLine(" *  练习项目：$dirName")
  [void]$sb.AppendLine(" *  目标：$($it.goal)")
  [void]$sb.AppendLine(" * ------------------------------------------------------------")
  [void]$sb.AppendLine(" *  只改本文件（和 src 里其它 .java），改完双击 run.bat 运行")
  [void]$sb.AppendLine(" *  完整练习要求看同目录 README.md")
  [void]$sb.AppendLine(" * ============================================================")
  [void]$sb.AppendLine(" */")
  foreach ($line in $imp) { [void]$sb.AppendLine($line) }
  if ($imp.Count -gt 0) { [void]$sb.AppendLine("") }
  [void]$sb.AppendLine("public class Main {")
  [void]$sb.AppendLine("    public static void main(String[] args) {")
  [void]$sb.AppendLine("        // 本板块 TODO（做完一条就删掉一条对应注释）：")
  foreach ($t in $it.todos) { [void]$sb.AppendLine("        // - $t") }
  [void]$sb.AppendLine("")
  foreach ($line in ("$($it.starter)" -split "`n")) {
    $l = $line.TrimEnd("`r")
    if ($l -ne '') { [void]$sb.AppendLine("        " + $l) } else { [void]$sb.AppendLine("") }
  }
  [void]$sb.AppendLine("    }")
  [void]$sb.AppendLine("}")
  $mainPath = Join-Path $srcDir 'Main.java'
  if ($Force -or -not (Test-Path -LiteralPath $mainPath)) { Write-Text $mainPath $sb.ToString(); $created++ }
  else { $skipped++ }

  # --- src 里其它类文件 ---
  foreach ($ex in $it.extra) {
    $p = Join-Path $srcDir $ex.file
    if ($Force -or -not (Test-Path -LiteralPath $p)) {
      $code = "$($ex.code)"
      if ($code -notmatch '\r?\n$') { $code += "`r`n" }
      Write-Text $p $code; $created++
    } else { $skipped++ }
  }

  # --- README.md ---
  $steps = ($it.steps | ForEach-Object -Begin { $i = 1 } -Process { "$($i). $_"; $i++ }) -join "`r`n"
  $todoLines = ($it.todos | ForEach-Object { "- [ ] $_" }) -join "`r`n"
  $fileLines = @("- ``src\Main.java`` —— 你唯一必须改的文件")
  foreach ($ex in $it.extra) { $fileLines += "- ``src\$($ex.file)`` —— 本节用到的类" }
  $fileLines += "- ``run.bat`` —— 一键编译并运行（双击）"
  $fileLines += "- ``open-in-vscode.bat`` —— 用 VS Code 打开本文件夹（双击）"
  $fileList = $fileLines -join "`r`n"
  $readme = @"
# $title

**板块编号：** $($it.id)　|　**所属：** 第 $ch 章　|　**练习项目：** ``$dirName``

## 🎯 练习目标
$($it.goal)

## 📋 练习步骤
$steps

## ✍️ 要做的事（TODO）
$todoLines

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 ``open-in-vscode.bat``）
2. 修改 ``src\Main.java``
3. 双击 ``run.bat`` 编译并运行（或按 Ctrl+` 打开终端，执行 ``java src\Main.java``）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
``````
$($it.expected)
``````

## 💡 提示
$($it.hint)

## 📁 文件说明
$fileList

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*
"@
  # README 是由练习数据生成的文档，每次运行都刷新（你的代码在 src 里，不受影响）
  Write-Text (Join-Path $dir 'README.md') (Crlf $readme)

  # --- run.bat ---
  $runBat = @"
@echo off
chcp 65001 >nul
cd /d "%~dp0"
echo ============================================
echo  $title   练习运行
echo ============================================
if not exist out mkdir out
javac -encoding UTF-8 -d out src\*.java
if errorlevel 1 (
    echo.
    echo [编译失败] 把上面标红的那几行发给我，我们一起改。
    pause
    exit /b 1
)
echo.
java -cp out Main
echo.
echo [运行结束] 按任意键关闭
pause >nul
"@
  Write-TextIfNeeded (Join-Path $dir 'run.bat') (Crlf $runBat) | Out-Null

  # --- open-in-vscode.bat ---
  $vscodeBat = @"
@echo off
rem 用 VS Code 打开本练习文件夹（双击即可）
setlocal
set "CLI=$vsCodeCli"
if not exist "%CLI%" set "CLI=code"
start "" /min "%CLI%" -n "%~dp0"
rem 等 VS Code 起来后，把 Main.java 也打开（复用刚开的那个窗口）
ping -n 4 127.0.0.1 >nul
if exist "%~dp0src\Main.java" start "" /min "%CLI%" -r "%~dp0src\Main.java"
"@
  Write-TextIfNeeded (Join-Path $dir 'open-in-vscode.bat') (Crlf $vscodeBat) | Out-Null

  # --- .vscode/settings.json（VS Code 工作区配置：UTF-8、Java 源码路径、格式化）---
  $vscodeDir = Join-Path $dir '.vscode'
  if (-not (Test-Path -LiteralPath $vscodeDir)) { New-Item -ItemType Directory -Force -Path $vscodeDir | Out-Null }
  $settingsJson = @'
{
  "files.encoding": "utf8",
  "files.autoGuessEncoding": false,
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.compile.nullAnalysis.mode": "automatic",
  "java.import.gradle.enabled": false,
  "java.import.maven.enabled": false,
  "[java]": {
    "editor.tabSize": 4,
    "editor.insertSpaces": true,
    "editor.formatOnSave": true
  }
}
'@
  Write-TextIfNeeded (Join-Path $vscodeDir 'settings.json') (Crlf $settingsJson) | Out-Null
}

# ---------- 4. 工作区根目录的公共文件 ----------
if (-not (Test-Path -LiteralPath $WorkspaceRoot)) { New-Item -ItemType Directory -Force -Path $WorkspaceRoot | Out-Null }

$rootReadme = @"
# javacourse · Java 基础强化练习工作区

一个板块一个练习项目文件夹，命名规范固定为 ``ch<章号两位>-s<节号两位>-<英文短名>``，
例如 ``ch01-s01-java-what``、``ch03-s06-atm-project``。

每个文件夹里：

| 文件 | 用途 |
| --- | --- |
| ``README.md`` | 本板块练习要求：目标、步骤、TODO、预期输出、提示 |
| ``src\Main.java`` | 初始代码，你在这里写 |
| ``src\*.java`` | 本节用到的其它类（第 5 章起出现） |
| ``run.bat`` | 一键编译运行（javac + java） |
| ``open-in-vscode.bat`` | 用 VS Code 打开本文件夹 |

## 一键生成 / 刷新全部练习项目

在 PowerShell 里执行：

``````powershell
powershell -ExecutionPolicy Bypass -File "D:\java学习中心\java-course-site\tools\make-practice-folders.ps1"
``````

默认**不会覆盖**你已经改过的 src 代码；确需重建时加 ``-Force``。

## 网页上的入口

打开 ``D:\java学习中心\java-course-site\index.html``，每个板块正文下方都有
「🖥️ 用 VS Code 打开」按钮，点一下自动用 VS Code 打开对应文件夹（首次点会问一次是否允许）。
如果按钮没反应，双击该文件夹里的 ``open-in-vscode.bat`` 即可。
"@
Write-Text (Join-Path $WorkspaceRoot 'README.md') (Crlf $rootReadme) | Out-Null

$editorConfig = @"
root = true

[*]
charset = utf-8
end_of_line = crlf
insert_final_newline = true
trim_trailing_whitespace = false

[*.java]
indent_style = space
indent_size = 4

[*.{md,json}]
indent_style = space
indent_size = 2
"@
Write-TextIfNeeded (Join-Path $WorkspaceRoot '.editorconfig') (Crlf $editorConfig) | Out-Null

# ---------- 5. 清理上一版（Visual Studio 版）的遗留文件 ----------
#  网页按钮现在直接用 VS Code 自带的 vscode:// 协议，不再需要自定义协议与启动脚本
foreach ($obsolete in @('_open-in-vs.ps1','install-open-in-vs.reg','_open-in-vscode.ps1','install-open-in-vscode.reg')) {
  $p = Join-Path $WorkspaceRoot $obsolete
  if (Test-Path -LiteralPath $p) { Remove-Item -LiteralPath $p -Force; Write-Host "已删除遗留文件：$obsolete" -ForegroundColor DarkGray }
}
Get-ChildItem -LiteralPath $WorkspaceRoot -Directory -ErrorAction SilentlyContinue | ForEach-Object {
  $old = Join-Path $_.FullName 'open-in-vs.bat'
  if (Test-Path -LiteralPath $old) { Remove-Item -LiteralPath $old -Force }
}

# ---------- 6. 注入 index.html 数据块 ----------
if (-not $SkipHtml) {
  $payload = @()
  foreach ($it in $items) {
    $dirName = Get-DirName $it
    $fileList = @('src\Main.java')
    foreach ($ex in $it.extra) { $fileList += "src\$($ex.file)" }
    $fileList += @('README.md','run.bat','open-in-vscode.bat')
    $payload += [ordered]@{
      id       = $it.id
      title    = if ($titles[$it.id]) { $titles[$it.id] } else { $it.id }
      dir      = $dirName
      path     = (Join-Path $WorkspaceRoot $dirName)
      goal     = $it.goal
      steps    = @($it.steps)
      todos    = @($it.todos)
      expected = $it.expected
      hint     = $it.hint
      files    = $fileList
    }
  }
  $json = ($payload | ConvertTo-Json -Depth 6)
  $blockStart = '<!-- PRACTICE-DATA-BEGIN（由 tools\make-practice-folders.ps1 生成，请勿手改） -->'
  $blockEnd   = '<!-- PRACTICE-DATA-END -->'
  $block = "$blockStart`r`n<script type=""application/json"" id=""practiceData"">`r`n$json`r`n</script>`r`n$blockEnd"
  $pattern = [regex]::Escape($blockStart) + '[\s\S]*?' + [regex]::Escape($blockEnd)
  if ([regex]::IsMatch($html, $pattern)) {
    $html = [regex]::Replace($html, $pattern, { param($m) $block })
    Write-Host "index.html：已刷新练习数据块" -ForegroundColor Green
  } else {
    $anchor = '<canvas class="confetti" id="confettiCanvas"></canvas>'
    if ($html.IndexOf($anchor) -lt 0) { throw "index.html 里找不到注入锚点：$anchor" }
    $html = $html.Replace($anchor, "$anchor`r`n`r`n$block")
    Write-Host "index.html：已插入练习数据块" -ForegroundColor Green
  }
  Write-Text $htmlPath $html
}

# ---------- 7. 汇总 ----------
Write-Host ""
Write-Host "练习工作区：$WorkspaceRoot" -ForegroundColor Cyan
Write-Host "板块数量：$($items.Count)" -ForegroundColor Cyan
Write-Host "新建文件：$created 个；已存在跳过：$skipped 个" -ForegroundColor Cyan
if ($vsCodeCli) { Write-Host "VS Code：$vsCodeCli" -ForegroundColor Cyan }
else { Write-Host "警告：未找到 VS Code（网页按钮仍可用 vscode:// 协议，.bat 会退回 PATH 里的 code）" -ForegroundColor Yellow }
