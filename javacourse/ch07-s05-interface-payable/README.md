# 7-5 接口

**板块编号：** c7s5　|　**所属：** 第 7 章　|　**练习项目：** `ch07-s05-interface-payable`

## 🎯 练习目标
掌握接口的定义与 implements 实现，理解接口引用多态以及一个类可实现多个接口。

## 📋 练习步骤
1. 阅读 Payable.java 和 Meeting.java，确认接口方法只有声明、没有方法体
2. 阅读 Manager.java，确认它用逗号同时实现了两个接口
3. 在 main 中把 FullTimeEmployee 对象赋给 Payable 变量并调用 calcSalary()
4. 用 Payable[] 数组装入不同实现类的对象，遍历统一调用 calcSalary()
5. 把同一个 Manager 对象分别赋给 Payable 与 Meeting 变量，调用不同接口的方法

## ✍️ 要做的事（TODO）
- [ ] TODO 1 创建 Manager 对象
- [ ] TODO 2 用 Payable[] 数组装入两个实现类对象，遍历调用 calcSalary()
- [ ] TODO 3 把 Manager 对象赋给 Meeting 变量，调用 meeting()

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
接口引用调用的工资：8000.0 元
第一位工资：8000.0 元
第二位工资：15000.0 元
老张 通知：下午 3 点开部门会议
```

## 💡 提示
接口只是行为契约、不能 new；一个类可以实现多个接口（逗号分隔），正好弥补 Java 只能单继承的局限。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Payable.java` —— 本节用到的类
- `src\Meeting.java` —— 本节用到的类
- `src\FullTimeEmployee.java` —— 本节用到的类
- `src\Manager.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*