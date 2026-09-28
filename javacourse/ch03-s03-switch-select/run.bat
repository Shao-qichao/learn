@echo off
chcp 65001 >nul
cd /d "%~dp0"
echo ============================================
echo  3-3 switch 选择   练习运行
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