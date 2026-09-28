# 8-4 异常是什么

**板块编号：** c8s4　|　**所属：** 第 8 章　|　**练习项目：** `ch08-s04-exception-basics`

## 🎯 练习目标
认识常见运行时异常与红色堆栈信息，会用 if 防御避免除零和下标越界导致崩溃。

## 📋 练习步骤
1. 运行 starter 看两处防御式 if 如何拦住除零和越界，程序全程不崩溃
2. 临时取消注释制造一次 ArithmeticException，读第一行异常类型、信息和出错行号，再注释回去
3. 确认除零和越界两种情况都改成了“先判断再执行”，输出与预期一致

## ✍️ 要做的事（TODO）
- [ ] TODO 1：用 if (b != 0) 做除零防御，分别打印结果或“除数不能为 0”
- [ ] TODO 2：用 index >= 0 && index < arr.length 判断下标合法后再取值，非法打印“下标越界”
- [ ] TODO 3：临时制造一次 ArithmeticException，记录报错类型、信息和自己代码的行号，然后恢复注释

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
运行后应看到：
除数不能为 0
下标越界
异常练习未完成，请补全 TODO 1~3 后重新运行
（取消注释那行时会看到 Exception in thread "main" java.lang.ArithmeticException: / by zero）
```

## 💡 提示
能靠 if 预防的就先 if（除零、下标范围、空对象）；读红色报错先看第一行的异常类型和信息，再找堆栈里自己写的类名和行号。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*