# 7-4 抽象类

**板块编号：** c7s4　|　**所属：** 第 7 章　|　**练习项目：** `ch07-s04-abstract-employee`

## 🎯 练习目标
掌握抽象类与抽象方法的规则，理解父类定规矩、子类兑现的设计，并用多态数组做统计。

## 📋 练习步骤
1. 阅读抽象类 Employee.java，找出 abstract 类声明和没有方法体的 calcSalary()
2. 阅读两个子类，确认它们都用 @Override 重写了 calcSalary()
3. 在 main 中用抽象父类数组装入全职与兼职两种员工对象
4. 遍历数组打印姓名与工资，并累加 calcSalary() 的返回值
5. 在数组里加入第三位员工，重新统计工资合计与平均工资

## ✍️ 要做的事（TODO）
- [ ] TODO 1 往 staff[2] 再放入一位兼职员工对象
- [ ] TODO 2 用增强 for 遍历数组，调用 showInfo() 并累加 calcSalary()
- [ ] TODO 3 输出工资合计与平均工资（注意平均工资是小数）

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
第 1 位：小明，工资 8000.0 元
姓名：小明，工号：E001
姓名：小红，工号：E002
姓名：小刚，工号：E003
工资合计：10200.0 元
平均工资：3400.0 元
```

## 💡 提示
抽象类不能 new；子类必须重写父类所有抽象方法，否则该子类自己也要声明为 abstract，否则编译不通过。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Employee.java` —— 本节用到的类
- `src\FullTimeEmployee.java` —— 本节用到的类
- `src\PartTimeEmployee.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*