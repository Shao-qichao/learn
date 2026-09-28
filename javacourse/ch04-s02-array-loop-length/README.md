# 4-2 遍历与 length

**板块编号：** c4s2　|　**所属：** 第 4 章　|　**练习项目：** `ch04-s02-array-loop-length`

## 🎯 练习目标
学会用 for 配合 arr.length 遍历数组，并分得清普通 for 和增强 for 的用途。

## 📋 练习步骤
1. 用普通 for 配合 arr.length 打印数组的每个元素
2. 打印 arr.length，确认长度是 5 而且没有小括号
3. 在循环里把每个元素加 5，再遍历一次打印修改后的数组
4. 用增强 for 再遍历一遍，体会它拿不到下标

## ✍️ 要做的事（TODO）
- [ ] 在遍历循环里把每个元素加 5（arr[i] = arr[i] + 5），循环后再遍历一次打印新数组
- [ ] 用增强 for（for (int x : arr)）再打印一遍数组，观察它拿不到下标
- [ ] 写一行注释说明为什么条件是 i < arr.length 而不是 i <= arr.length

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
10
20
30
40
50
数组长度：5
15
25
35
45
55
15 25 35 45 55
```

## 💡 提示
数组长度是 arr.length（没有小括号）；想修改元素必须用带下标的普通 for，增强 for 改不动原数组。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*