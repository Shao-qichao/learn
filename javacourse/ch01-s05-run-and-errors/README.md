# 1-5 运行原理与报错

**板块编号：** c1s5　|　**所属：** 第 1 章　|　**练习项目：** `ch01-s05-run-and-errors`

## 🎯 练习目标
看懂 .java 到 .class 再到输出的流程，会按报错行号和关键词定位问题。

## 📋 练习步骤
1. 用四行 println 从上到下画出流程：HelloWorld.java、javac 编译、HelloWorld.class、java 启动 JVM 输出结果
2. 故意删掉某一行 println 末尾的分号运行一次，抄下报错里的行号和关键词，再把分号补回来
3. 把 System 写成 system 试一次，记下「找不到符号」这类报错关键词，再改回正确大小写
4. 用 // 注释写下「改了 .java 必须重新 javac」和「报错先看行号，再看关键词」

## ✍️ 要做的事（TODO）
- [ ] TODO 1：补一行 println 输出中间产物 HelloWorld.class
- [ ] TODO 2：再补一行 println，输出程序运行后屏幕上的文字
- [ ] TODO 3：用 // 注释写下「改了代码必须重新 javac」

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
HelloWorld.java
   ↓ javac 编译
HelloWorld.class
   ↓ java 启动 JVM
Hello,World!
```

## 💡 提示
报错先看「文件名:第几行」，再看「错误: 需要';'」这类关键词，别被整段红字吓到。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*