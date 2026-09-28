# 7-1 继承 extends

**板块编号：** c7s1　|　**所属：** 第 7 章　|　**练习项目：** `ch07-s01-extends-employee`

## 🎯 练习目标
理解继承的含义与语法，掌握 extends、protected 以及 super(...) 在子类初始化中的作用。

## 📋 练习步骤
1. 阅读 Employee.java，找出 protected 属性、构造方法与 clockIn() 方法
2. 阅读 FullTimeEmployee.java，确认它只写了 extends 就拥有了父类的属性与方法
3. 在 main 中用无参构造创建全职员工，用 setter 赋值后调用 clockIn() 打卡
4. 用三参构造创建第二位员工，注意子类构造内部通过 super(name, id) 初始化父类
5. 打印两位员工的姓名、工号、月薪，确认姓名工号在子类里一次都没有重复定义

## ✍️ 要做的事（TODO）
- [ ] TODO 1 用三参构造 new FullTimeEmployee("小红", "E002", 9000) 创建第二位员工
- [ ] TODO 2 调用继承来的 clockIn() 打卡，并打印姓名、工号、月薪
- [ ] TODO 3 用 setMonthlySalary(12000) 修改小明月薪并打印验证

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
小明(E001) 打卡成功
姓名：小明，工号：E001
小红(E002) 打卡成功
姓名：小红，工号：E002，月薪：9000.0 元
小明 的月薪改为：12000.0 元
```

## 💡 提示
子类构造默认先调 super()；父类只有有参构造时，必须在子类构造第一行显式写 super(name, id)，否则编译报错。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Employee.java` —— 本节用到的类
- `src\FullTimeEmployee.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*