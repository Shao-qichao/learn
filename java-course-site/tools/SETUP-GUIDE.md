# Java 学习中心 · 一键环境搭建指南

## 快速开始

### Windows 用户

1. 下载本项目（或 git clone）
2. 打开 PowerShell，运行：

```powershell
powershell -ExecutionPolicy Bypass -File "java-course-site\tools\setup-windows.ps1"
```

3. 按提示输入 Git 用户名和邮箱，等待安装完成
4. 双击 `java-course-site\index.html` 开始学习

### macOS / Linux 用户

```bash
chmod +x java-course-site/tools/setup-mac-linux.sh
./java-course-site/tools/setup-mac-linux.sh
```

## 脚本功能

| 步骤 | Windows 脚本 | macOS/Linux 脚本 |
|------|-------------|-------------------|
| 系统检测 | OS/架构/磁盘/网络 | OS/架构/磁盘/网络 |
| 安装 Git | winget → Git.Git | brew/apt/yum/dnf/pacman |
| 安装 JDK | winget → Temurin 11/17/21 | brew/apt/yum → OpenJDK |
| 安装 VS Code | winget → VSCode | brew cask / snap / apt |
| Git 配置 | 交互输入用户名邮箱 | 同左 |
| 克隆项目 | → D:\java学习中心 | → ~/java学习中心 |
| 路径适配 | 替换 index.html 中 13053 → 当前用户 | 替换 Windows 路径 → 本地路径 |
| 验证测试 | 6 项验证 | 6 项验证 |
| 日志记录 | %TEMP%\java-setup-logs\ | ~/java-setup-logs/ |
| 备份机制 | 备份 index.html + 替换报告 | 同左 |

## 自定义 JDK 版本

### Windows
编辑 `setup-windows.ps1`，修改：
```powershell
$JdkVersion = "17"   # 改为 11 / 17 / 21
```

### macOS / Linux
编辑 `setup-mac-linux.sh`，修改：
```bash
JDK_VERSION="17"     # 改为 11 / 17 / 21
```

## 日志与备份

- **日志目录**：`%TEMP%\java-setup-logs\`（Windows）/ `~/java-setup-logs/`（macOS/Linux）
- **备份目录**：日志目录下的 `backups-时间戳/`
- **备份内容**：原始 `index.html` + 替换报告 `replace-report.txt`
- **替换报告**：记录每处替换的旧值、新值、数量

## 脚本架构（可扩展性）

脚本采用模块化设计，按功能分 6 个阶段：

```
1. SystemCheck     → 系统检测
2. InstallSoftware → 软件安装（支持 winget/brew/apt/yum/dnf/pacman/snap）
3. GitConfig       → Git 用户配置
4. CloneProject    → 项目克隆
5. PatchPaths      → 路径适配
6. Verify          → 验证测试
```

### 添加新软件安装

**Windows**：在 `Install-Software` 函数中添加：
```powershell
# --- 你的软件 ---
if (Test-Command yourapp) { Write-OK "已安装" }
else {
    winget install --id YourApp.Id -e --accept-source-agreements --accept-package-agreements --silent
}
```

**macOS/Linux**：在 `install_software()` 函数中添加：
```bash
if has_cmd yourapp; then ok "已安装"
else
    # macOS
    brew install yourapp
    # Linux
    sudo apt-get install -y yourapp
fi
```

## 常见问题

### Q: winget 不可用（旧版 Windows）
A: 手动下载安装：
- Git: https://git-scm.com/download/win
- JDK: https://adoptium.net/
- VS Code: https://code.visualstudio.com/

### Q: GitHub 连接超时
A: 脚本会自动检测系统代理。如果用了 VPN/代理软件，请先开启再运行脚本。
也可以手动配置 Git 代理：
```powershell
git config --global http.proxy "http://127.0.0.1:7897"
git config --global https.proxy "http://127.0.0.1:7897"
```

### Q: VS Code 安装后命令行不可用
A: 重启终端或电脑即可，不影响脚本其他步骤。

### Q: 用户名不是 13053 怎么办
A: 脚本会自动检测并替换，无需手动操作。备份和替换报告在日志目录中可查。
