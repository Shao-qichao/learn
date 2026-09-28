# 6-2 成员变量与方法

**板块编号：** c6s2　|　**所属：** 第 6 章　|　**练习项目：** `ch06-s02-member-local-var`

## 🎯 练习目标
区分成员变量与局部变量，会写能直接使用成员变量的成员方法，并在 main 中通过对象调用。

## 📋 练习步骤
1. 运行初始程序，观察 deposit 方法直接使用成员变量 balance 完成存款
2. 在 Account 中新增 withdraw 方法，用 if-else 判断余额是否够扣
3. 在 main 中分别调用 acc.withdraw(200) 和 acc.withdraw(1000)，对比两次输出
4. 验证在 main 里直接写 balance 会编译报错，改用 acc.balance 访问后再运行

## ✍️ 要做的事（TODO）
- [ ] 在 Account 中新增 withdraw(double money) 方法：余额够就扣减并打印“XX 取款 YY，余额 ZZ”，不够则打印“余额不足”
- [ ] 在 main 中依次调用 acc.withdraw(200) 和 acc.withdraw(1000)，观察两次输出的不同
- [ ] 验证：在 main 里直接写 System.out.println(balance); 为什么会编译报错？改成用 acc.balance 访问后再运行

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
小明 存入 500.0，余额 500.0
余额：500.0
小明 取款 200.0，余额 300.0
余额不足
```

## 💡 提示
成员变量写在类里方法外、有默认值、随对象存活；局部变量写在方法内、没有默认值必须先赋值，方法结束就销毁。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Account.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*