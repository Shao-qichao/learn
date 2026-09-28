# 1-4 HelloWorld 精讲

**板块编号：** c1s4　|　**所属：** 第 1 章　|　**练习项目：** `ch01-s04-hello-world-detail`

## 🎯 练习目标
逐行读懂 HelloWorld，能改输出内容并分清 print 与 println 的换行差别。

## 📋 练习步骤
1. 在 main 里用 println 输出 Hello,World!，再补一行输出 Hello,Java!
2. 把其中一行改成 print，运行后观察它和下一行是否挤在同一行，再改回 println
3. 用三行 println 分三行输出你的姓名、年龄、城市
4. 用 // 注释说明某一行 println 输出的是什么，并检查代码里的大括号是否成对

## ✍️ 要做的事（TODO）
- [ ] TODO 1：补一行 println 输出 Hello,Java!
- [ ] TODO 2：把其中一个 println 改成 print，对比输出有没有换行
- [ ] TODO 3：补三行 println，分三行输出姓名、年龄、城市

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
Hello,World!
Hello,Java!
小明
18
广州
（改成 print 后 Hello,World! 会和下一行挤在同一行）
```

## 💡 提示
println 里的 ln 就是 line（换行）；大小写敏感，System 写成 system 立刻报错。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*