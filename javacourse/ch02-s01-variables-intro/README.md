# 2-1 变量是什么

**板块编号：** c2s1　|　**所属：** 第 2 章　|　**练习项目：** `ch02-s01-variables-intro`

## 🎯 练习目标
会声明 int 变量、赋值与重新赋值，并用 println 输出变量的值。

## 📋 练习步骤
1. 在 main 里声明 int 变量 age 并赋值 18，用 println 输出它
2. 声明 int 变量 nextYearAge = age + 1，用 println 输出，确认结果是 19
3. 把 age 重新赋值为 19（不要再写 int），再用 println 输出一次
4. 声明 int 变量 count 并赋值 0，执行 count = count + 1 后用 println 输出

## ✍️ 要做的事（TODO）
- [ ] TODO 1：声明 int nextYearAge = age + 1 并用 println 输出
- [ ] TODO 2：把 age 重新赋值为 19，再输出一次 age
- [ ] TODO 3：声明 int count = 0，执行 count = count + 1 后输出它

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
18
19
19
1
```

## 💡 提示
重新赋值时不要再写 int；变量没赋值就使用会报「可能尚未初始化变量」。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*