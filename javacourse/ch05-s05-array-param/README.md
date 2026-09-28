# 5-5 数组作为参数

**板块编号：** c5s5　|　**所属：** 第 5 章　|　**练习项目：** `ch05-s05-array-param`

## 🎯 练习目标
掌握数组作为参数与返回值，理解方法内修改数组元素会改变外部传入的同一个原数组。

## 📋 练习步骤
1. 运行初始程序，观察 printArray 把整个数组交给方法后一次性打印
2. 补全 Tools.addFive，用 for 循环给数组每个元素加 5
3. 在 main 中调用 addFive(scores) 后再打印一次 scores，观察原数组有没有被改变
4. 补全 Tools.makeArray，让它返回一个装着 1 到 n 的数组，并在 main 中打印该数组

## ✍️ 要做的事（TODO）
- [ ] 补全 Tools.addFive(int[] arr)：用 for 循环给数组每个元素加 5（在方法内直接改 arr[i]，不要改方法签名）
- [ ] 补全 Tools.makeArray(int n)：新建长度为 n 的数组，填入 1 到 n 后返回（返回类型是 int[]）
- [ ] 在 main 中调用 Tools.addFive(scores) 后再次 Tools.printArray(scores)，观察原数组是否被改变；再打印 Tools.makeArray(5)

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
88 92 56
总分：236
93 97 61
1 2 3 4 5
```

## 💡 提示
调用时实参只写数组名，不加中括号；数组传给方法传的是同一组数据的位置，方法里改元素会改到外面的原数组。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Tools.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*