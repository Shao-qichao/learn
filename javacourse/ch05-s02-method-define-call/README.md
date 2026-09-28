# 5-2 定义与调用

**板块编号：** c5s2　|　**所属：** 第 5 章　|　**练习项目：** `ch05-s02-method-define-call`

## 🎯 练习目标
掌握无参无返回值方法的定义与调用，用方法复用打印分割线，理解方法必须与 main 平级。

## 📋 练习步骤
1. 运行初始程序，观察 Tools.printLine() 打印出的分割线只有两个等号
2. 把 Tools.java 里 printLine() 的方法体改成打印一行 20 个等号并运行
3. 在 main 最前面补一句 Tools.printLine();，让标题上方也出现分割线
4. 在 Tools 中新增 printHeader()（内部两次调用 printLine()），并在 main 中调用它
5. 确认只改一处方法体、所有调用处输出都跟着变，体会方法的复用价值

## ✍️ 要做的事（TODO）
- [ ] 把 Tools.printLine() 的方法体改成打印一行 20 个等号（即 20 个 = 号）
- [ ] 在 main 最前面补一句 Tools.printLine();，让标题上方也有分割线
- [ ] 在 Tools 中新增 printHeader()：先调用 printLine()、再打印“学生管理系统”、再调用 printLine()，并在 main 中调用它

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
====================
成绩单标题
====================
小明：90分
====================
学生管理系统
====================
```

## 💡 提示
方法定义要写在类里、与 main 平级（本练习放在 Tools 类里）；调用时别忘了小括号，要写 Tools.printLine(); 而不是 Tools.printLine。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Tools.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*