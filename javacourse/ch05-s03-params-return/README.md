# 5-3 参数与返回值

**板块编号：** c5s3　|　**所属：** 第 5 章　|　**练习项目：** `ch05-s03-params-return`

## 🎯 练习目标
掌握带参数与带返回值方法的定义和调用，会写形参、实参并用 return 把结果交回调用处。

## 📋 练习步骤
1. 运行初始程序，观察 printWelcome 用同一段逻辑换数据打印两组欢迎语
2. 补全 Tools.add 的方法体，让它返回两个整数之和，并运行核对结果
3. 补全 Tools.isAdult，让它按年龄返回 true 或 false，并在 main 中当布尔条件使用
4. 在 main 中把 add 的返回值用 int 变量接住并打印，确认 return 的值能被赋值给变量

## ✍️ 要做的事（TODO）
- [ ] 补全 Tools.add(int a, int b) 的方法体，让它返回两个数的和（把 return 0 改成正确结果）
- [ ] 补全 Tools.isAdult(int age)：age 大于等于 18 返回 true，否则返回 false
- [ ] 在 main 中把 add 的返回值用 int 变量接住再打印，并加一句：当 Tools.isAdult(20) 成立时打印“可以进入”

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
欢迎你，小明
欢迎你，小明
欢迎你，小明
欢迎你，小红
8
可以进入
```

## 💡 提示
返回类型不是 void 的方法，所有分支都必须有 return，且返回值类型要和声明的返回类型匹配（声明 int 就不要返回带小数点的数）。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Tools.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*