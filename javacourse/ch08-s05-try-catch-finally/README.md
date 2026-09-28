# 8-5 try-catch

**板块编号：** c8s5　|　**所属：** 第 8 章　|　**练习项目：** `ch08-s05-try-catch-finally`

## 🎯 练习目标
掌握 try-catch-finally 语法、多个 catch 子类在前的顺序，以及 finally 必定执行的特点。

## 📋 练习步骤
1. 用 try-catch 包住 arr[5] 的访问，catch 里打印 e.getMessage()，观察具体越界信息
2. 再补一个 catch (ArithmeticException e) 处理 100 / 0，体会谁先匹配谁处理
3. 给其中一个 try 加 finally 块打印提示，分别让 try 出错和不换参数两种情况各跑一次，验证 finally 都输出

## ✍️ 要做的事（TODO）
- [ ] TODO 1：用 try-catch 接住 arr[5] 的 ArrayIndexOutOfBoundsException，并打印 e.getMessage()
- [ ] TODO 2：再写一个 try-catch 接住 100 / 0 的 ArithmeticException，打印“不能输入 0！”
- [ ] TODO 3：给其中一个 try 加 finally 块打印“finally 一定执行”，验证无论是否出错都会输出

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
运行后应看到（程序全程不崩溃）：
下标越界了：Index 5 out of bounds for length 3
不能输入 0！
finally 一定执行
try-catch 练习未完成，请补全 TODO 1~3 后重新运行
```

## 💡 提示
多个 catch 必须子类异常在前、Exception 兜底在后；catch 块里至少打印一条提示，空 catch 会把错误悄悄吞掉，最难查。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*