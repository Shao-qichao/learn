@echo off
rem 用 VS Code 打开本练习文件夹（双击即可）
setlocal
set "CLI=D:\C语言\Microsoft VS Code\bin\code.cmd"
if not exist "%CLI%" set "CLI=code"
start "" /min "%CLI%" -n "%~dp0"
rem 等 VS Code 起来后，把 Main.java 也打开（复用刚开的那个窗口）
ping -n 4 127.0.0.1 >nul
if exist "%~dp0src\Main.java" start "" /min "%CLI%" -r "%~dp0src\Main.java"