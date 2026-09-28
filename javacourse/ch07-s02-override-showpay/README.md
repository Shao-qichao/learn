# 7-2 方法重写

**板块编号：** c7s2　|　**所属：** 第 7 章　|　**练习项目：** `ch07-s02-override-showpay`

## 🎯 练习目标
掌握方法重写的写法与 @Override 的作用，会在子类里改写继承来的方法并复用 super。

## 📋 练习步骤
1. 阅读 Employee.java 里 showPay() 的通用版本，记住它的方法名和参数列表
2. 阅读两个子类的 showPay()，对比方法名、参数是否与父类完全一致，@Override 有没有加上
3. 在 main 中创建全职员工并调用 showPay()，确认执行的是子类版本
4. 创建兼职员工并调用 showPay()，确认工资按时薪乘工时计算
5. 观察 PartTimeEmployee 里 super.showPay() 的效果，说明什么是先复用父类再追加内容

## ✍️ 要做的事（TODO）
- [ ] TODO 1 创建全职员工（姓名、月薪）并调用 showPay()，确认执行子类重写版本
- [ ] TODO 2 创建兼职员工（姓名、时薪、工时）并调用 showPay()，确认工资 = 时薪 × 工时
- [ ] TODO 3 修改全职员工月薪后再次调用 showPay()，观察同一方法名在不同类里的不同表现

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
普通员工 的工资按通用规则计算
小明（全职）月薪：8000.0 元
小红 的工资按通用规则计算
小红（兼职）时薪 50.0 元，本月 20 小时，合计 1000.0 元
小明（全职）月薪：12000.0 元
```

## 💡 提示
重写必须方法名和参数列表与父类完全一致；@Override 能让编译器帮你抓住名字或参数打错的情况。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Employee.java` —— 本节用到的类
- `src\FullTimeEmployee.java` —— 本节用到的类
- `src\PartTimeEmployee.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*