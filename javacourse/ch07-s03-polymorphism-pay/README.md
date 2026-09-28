# 7-3 向上转型与多态

**板块编号：** c7s3　|　**所属：** 第 7 章　|　**练习项目：** `ch07-s03-polymorphism-pay`

## 🎯 练习目标
理解向上转型与多态：父类引用指向子类对象，调用被重写的方法时按真实对象执行。

## 📋 练习步骤
1. 声明 Employee[] staff = new Employee[2]，把全职员工对象放进第 0 个格子
2. 把兼职员工对象放进第 1 个格子，体会左边父类引用、右边子类对象
3. 用增强 for 遍历数组，对每个元素调用 showPay()，观察输出随真实对象变化
4. 用静态初始化 Employee[] staff2 = { new ..., new ... }; 再写一遍并遍历
5. 用 instanceof 判断元素的真实类型，向下转型后调用子类特有方法

## ✍️ 要做的事（TODO）
- [ ] TODO 1 用父类引用把兼职员工放进 staff[1]
- [ ] TODO 2 用增强 for 遍历数组并调用 showPay()，观察多态效果
- [ ] TODO 3 用 instanceof 判断真实类型，向下转型后打印兼职员工的月工时

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
小明（全职）月薪：8000.0 元
数组长度：2
小明（全职）月薪：8000.0 元
小红（兼职）时薪 50.0 元，本月 20 小时，合计 1000.0 元
小红的月工时：20 小时
```

## 💡 提示
成员方法编译看左边、运行看右边；向上转型后不能直接调用子类特有方法，要先 instanceof 判断再向下转型。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Employee.java` —— 本节用到的类
- `src\FullTimeEmployee.java` —— 本节用到的类
- `src\PartTimeEmployee.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*