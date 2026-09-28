# 1-1 Java 是什么

**板块编号：** c1s1　|　**所属：** 第 1 章　|　**练习项目：** `ch01-s01-java-what`

## 🎯 练习目标
认识 Java 是什么、能做什么，并亲手运行一段会打印文字的 Java 程序。

## 📋 练习步骤
1. 在 main 方法里用 System.out.println 输出一行 "Hello,World!"
2. 把引号里的文字换成一句自我介绍（姓名 + 为什么学 Java），重新运行确认输出变了
3. 再补一行 System.out.println，说出 Java 的一个真实用途，比如网站后台或安卓 App
4. 用 // 注释在代码里写下「一次编写，到处运行，靠 JVM 跨平台」

## ✍️ 要做的事（TODO）
- [ ] TODO 1：把第一行 println 里的文字改成你的自我介绍
- [ ] TODO 2：补一行 println，写出 Java 的一个真实用途
- [ ] TODO 3：用 // 写一条注释，说明跨平台靠 JVM

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
运行后屏幕上出现两行以上文字，例如：
Hello,World!
我是小明，我想学 Java 做后台开发
```

## 💡 提示
双引号里写什么屏幕就显示什么，双引号本身不显示；句末的分号最容易漏。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*