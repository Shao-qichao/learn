# 1-3 环境与命令

**板块编号：** c1s3　|　**所属：** 第 1 章　|　**练习项目：** `ch01-s03-env-and-commands`

## 🎯 练习目标
亲手用 javac 编译、java 运行，弄清两个命令后面分别跟什么名字。

## 📋 练习步骤
1. 打开终端输入 java -version，记下显示的版本号
2. 进入 JavaLearning 目录执行 javac HelloWorld.java，观察文件夹里新生成的 .class 文件
3. 执行 java HelloWorld（后面只写类名）看到输出，再故意写一次 java HelloWorld.class 看报错
4. 回到代码里，用 println 把这次用到的两条命令打印成一张小抄

## ✍️ 要做的事（TODO）
- [ ] TODO 1：把第一行 println 补成 javac HelloWorld.java
- [ ] TODO 2：把第二行 println 补成 java HelloWorld，注意不带后缀
- [ ] TODO 3：先在终端里真的敲一遍 java -version、javac HelloWorld.java、java HelloWorld

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
终端里能看到 JDK 版本号和 Hello,World! 输出；程序运行后输出：
=== 编译运行小抄 ===
javac HelloWorld.java
java HelloWorld
```

## 💡 提示
运行时 java 后面只写类名，多写 .class 或 .java 都会报「找不到或无法加载主类」。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*