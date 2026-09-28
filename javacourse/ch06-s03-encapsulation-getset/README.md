# 6-3 封装

**板块编号：** c6s3　|　**所属：** 第 6 章　|　**练习项目：** `ch06-s03-encapsulation-getset`

## 🎯 练习目标
掌握封装：用 private 藏起属性，通过公开的 get/set 方法访问，并在 setter 里做合法性校验。

## 📋 练习步骤
1. 运行初始程序，观察 setAge(-100) 与 setPrice(-5) 目前的输出情况
2. 补全 Student.setAge，用 if 判断合法范围，非法值打印提示而不写进属性
3. 补全 Student.setPrice，只允许大于等于 0 的价格通过校验
4. 尝试在 main 中写 s.age = 20;，观察 private access 编译错误，改回用 setAge 赋值、getAge 读取

## ✍️ 要做的事（TODO）
- [ ] 补全 Student.setAge(int a)：只有 0 到 150 之间才把 a 赋给 age，否则打印“年龄不合法：”加 a
- [ ] 补全 Student.setPrice(double p)：只有 p 大于等于 0 才赋值，否则打印“价格不合法”
- [ ] 尝试在 main 中写 s.age = 20; 观察 private access 编译错误，改回用 setAge 赋值、getAge 读取

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
年龄不合法：-100
小明，18
价格不合法
价格：0.0
```

## 💡 提示
属性私有（private）、方法公开（public），读用 get、写用 set，set 里做校验；私有属性在类外连读带写都不行，编译期就会报错。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Student.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*