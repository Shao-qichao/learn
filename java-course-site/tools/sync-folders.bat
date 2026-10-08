@echo off
REM =============================================================
REM  Java 学习中心 - 双路径同步（双击运行）
REM  把 D:\java学习中心\java-course-site 同步到
REM      d:\AI小游戏\新建文件夹\java-course-site
REM
REM  用法：
REM    双击本文件 = 实际同步
REM    sync-folders.bat preview   = 仅预览不复制
REM    sync-folders.bat openlog   = 同步后打开日志
REM    sync-folders.bat preview openlog = 预览 + 打开日志
REM =============================================================

chcp 65001 >nul
cd /d "%~dp0"

set ARGS=
if /i "%~1"=="preview"   set ARGS=-DryRun
if /i "%~2"=="preview"   set ARGS=-DryRun
if /i "%~3"=="preview"   set ARGS=-DryRun

if /i "%~1"=="openlog"   set ARGS=%ARGS% -OpenLog
if /i "%~2"=="openlog"   set ARGS=%ARGS% -OpenLog
if /i "%~3"=="openlog"   set ARGS=%ARGS% -OpenLog

echo.
echo Java 学习中心 - 双路径同步脚本
echo ========================================
echo.
echo 命令行参数: %ARGS%
echo 如果出现权限提示，请允许 PowerShell 执行
echo.
pause

powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0sync-folders.ps1" %ARGS%

echo.
echo ========================================
echo 同步过程已结束。按任意键关闭窗口...
pause >nul
