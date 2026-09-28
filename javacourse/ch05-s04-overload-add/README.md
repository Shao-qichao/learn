# 5-4 方法重载

**板块编号：** c5s4　|　**所属：** 第 5 章　|　**练习项目：** `ch05-s04-overload-add`

## 🎯 练习目标
理解方法重载：同名方法只要参数列表不同，调用时会由 Java 按实参自动选择对应版本。

## 📋 练习步骤
1. 运行初始程序，观察 add(1, 2) 自动匹配了 int 版本并输出 3
2. 补全 add(double a, double b) 的方法体，看调用 add(1.5, 2.5) 选中的是哪一个版本
3. 补全 add(int a, int b, int c) 的方法体，实现三数相加
4. 在 main 中把三个 add 调用都打印出来，对照输出确认 Java 的选择结果

## ✍️ 要做的事（TODO）
- [ ] 补全 Tools.add(double a, double b)，返回两数之和（注意返回类型是 double）
- [ ] 补全 Tools.add(int a, int b, int c)，返回三个数之和
- [ ] 在 main 中依次打印 Tools.add(1.5, 2.5) 和 Tools.add(1, 2, 3)，观察 Java 分别自动选择了哪个版本

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
3
4.0
6
```

## 💡 提示
重载只认“方法名相同 + 参数列表不同”；只改返回类型或只改参数名都不算重载，会报方法已定义的冲突错误。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Tools.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*