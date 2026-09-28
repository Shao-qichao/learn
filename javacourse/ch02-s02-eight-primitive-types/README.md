# 2-2 八种基本类型

**板块编号：** c2s2　|　**所属：** 第 2 章　|　**练习项目：** `ch02-s02-eight-primitive-types`

## 🎯 练习目标
记住常用的四种基本类型，会按类型给变量赋值并分清单引号和双引号。

## 📋 练习步骤
1. 声明 int 变量 count 表示班级人数 45，用 println 输出它
2. 声明 double 变量 price = 19.9 表示商品价格，用 println 输出它
3. 声明 char 变量 level = 'A' 和 boolean 变量 isVip = true，分别用 println 输出
4. 声明 int a = 10, b = 20，计算 sum = a + b 并用 println 输出 30

## ✍️ 要做的事（TODO）
- [ ] TODO 1：声明 int count = 45 和 double price = 19.9，分别输出
- [ ] TODO 2：声明 char level = 'A' 和 boolean isVip = true，分别输出
- [ ] TODO 3：声明 int a = 10, b = 20，算出 sum = a + b 并输出

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
=== 基本类型练习 ===
45
19.9
A
true
30
```

## 💡 提示
char 用单引号且只能装一个字符，String 用双引号；小数默认就是 double，别写成 float。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*