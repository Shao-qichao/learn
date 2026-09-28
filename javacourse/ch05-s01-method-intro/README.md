# 5-1 方法是什么

**板块编号：** c5s1　|　**所属：** 第 5 章　|　**练习项目：** `ch05-s01-method-intro`

## 🎯 练习目标
理解方法就是有名字的一段代码，能把重复代码抽成方法并反复调用。

## 📋 练习步骤
1. 观察 main 里重复粘贴的欢迎语，感受复制代码的麻烦
2. 在类里面、main 外面定义 public static void printWelcome()
3. 在 main 里调用 printWelcome() 两次，替换掉重复的六行
4. 再定义 printLine() 打印一行分隔线，并在 main 里调用

## ✍️ 要做的事（TODO）
- [ ] 把重复的两行欢迎语抽成方法：在类里面、main 外面写 public static void printWelcome() { ... }
- [ ] 在 main 里用 printWelcome(); 调用两次，把原来的六行 println 替换掉
- [ ] 再定义 printLine() 只负责打印一行分隔线，并在 main 里调用它

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
欢迎光临！
今天也要加油哦
----------
欢迎光临！
今天也要加油哦
----------
```

## 💡 提示
方法定义必须写在类里面、main 的外面（和 main 平级）；调用时别忘写小括号，现阶段自定义方法都要加 static。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*