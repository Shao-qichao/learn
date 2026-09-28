# 3-3 switch 选择

**板块编号：** c3s3　|　**所属：** 第 3 章　|　**练习项目：** `ch03-s03-switch-select`

## 🎯 练习目标
用 switch 按菜单编号做多路选择，并记住每个 case 都要写 break 防穿透。

## 📋 练习步骤
1. 用 switch 判断整型变量 choice，为每个 case 打印对应的操作名称。
2. 在每个 case 结尾补上 break，保证只执行命中的那一个分支。
3. 故意删掉一个 break 重新运行，观察 case 穿透现象后再补回去。
4. 用 switch 判断星期几，让“周六”和“周日”共用同一段打印代码。

## ✍️ 要做的事（TODO）
- [ ] 补上 case 2 打印“存款”、case 3 打印“取款”，每个 case 结尾都要写 break。
- [ ] 声明 String day = "周六"，用 switch 让“周六”“周日”都打印“睡个懒觉”，其余打印“早起上课”。
- [ ] 把 choice 改成 4 运行一次，确认输出“无效选项”（走 default）。

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
choice = 2 输出“存款”，choice = 4 输出“无效选项”；day = "周六" 输出“睡个懒觉”，day = "周三" 输出“早起上课”。
```

## 💡 提示
每个 case 结尾都要写 break，漏写会发生 case 穿透，从命中的 case 一路执行到下一个 break；case 后面只能写固定值，范围判断要用 else if。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*