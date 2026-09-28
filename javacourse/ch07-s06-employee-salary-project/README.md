# 7-6 章项目：薪资系统

**板块编号：** c7s6　|　**所属：** 第 7 章　|　**练习项目：** `ch07-s06-employee-salary-project`

## 🎯 练习目标
用抽象类、继承、方法重写和多态数组实现员工薪资系统，理解面向对象三大特性。

## 📋 练习步骤
1. 读懂并确认抽象父类 Employee：protected 属性 name/id、构造方法、getter、showInfo() 与抽象方法 calcSalary()
2. 补全 FullTimeEmployee 和 PartTimeEmployee 的 calcSalary()：一个返回月薪，一个返回时薪 × 月工时
3. 补全 Manager：继承 FullTimeEmployee，构造用 super(...) 链式调用，calcSalary 用 super.calcSalary() + bonus
4. 在 main 中用父类数组 Employee[] 装 3 个不同员工，遍历时只写一套 showInfo() + calcSalary() 打印代码
5. 累计 total 并打印公司本月总薪资和人数，确认输出与参考效果一致

## ✍️ 要做的事（TODO）
- [ ] TODO 1：补全三个子类的 calcSalary()——FullTimeEmployee 返回 monthlySalary，PartTimeEmployee 返回 hourSalary * hours，Manager 返回 super.calcSalary() + bonus
- [ ] TODO 2：在 main 的 for 循环里把占位行换成 System.out.println("工资：" + e.calcSalary())，不要写 instanceof 分支
- [ ] TODO 3：循环结束后打印 公司本月总薪资：total 元，共 staff.length 人

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
直接运行 Main → 控制台输出：
====== 本月薪资表 ======
小明(E001) 全职员工  工资：8000.0
小红(E002) 兼职员工  工资：1000.0
老张(E003) 经理  工资：13000.0
------------------------
公司本月总薪资：22000.0 元，共 3 人
```

## 💡 提示
只用一套打印代码的关键：把数组声明成 Employee[]，循环里不写任何类型判断，calcSalary 靠重写自动选对算法；Manager 别忘了 super(...) 和 super.calcSalary()。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Employee.java` —— 本节用到的类
- `src\FullTimeEmployee.java` —— 本节用到的类
- `src\PartTimeEmployee.java` —— 本节用到的类
- `src\Manager.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*