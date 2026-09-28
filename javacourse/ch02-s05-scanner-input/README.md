# 2-5 键盘输入 Scanner

**板块编号：** c2s5　|　**所属：** 第 2 章　|　**练习项目：** `ch02-s05-scanner-input`

## 🎯 练习目标
学会 Scanner 的三步用法，用键盘输入整数、小数和文字并打印结果。

## 📋 练习步骤
1. 在文件最顶部导入 java.util.Scanner。
2. 在 main 里用 new Scanner(System.in) 领取一个扫描器并起名 sc。
3. 用 System.out.print 写提示语，用 sc.nextInt() 接收整数年龄并打印明年年龄。
4. 用 sc.nextDouble() 接收身高、用 sc.next() 接收姓名，拼成一句话打印。

## ✍️ 要做的事（TODO）
- [ ] 把 age 的固定值 18 改成 sc.nextInt()，让程序停下来等用户输入年龄。
- [ ] 用 sc.nextDouble() 接收身高并存进 double 变量，打印“你的身高是…米”。
- [ ] 用 sc.next() 接收姓名并存进 String 变量，拼成自我介绍的句子打印。

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
请输入你的年龄：18
你明年 19 岁
请输入你的身高：1.75
你的身高是 1.75 米
请输入你的姓名：小明
你好，小明
```

## 💡 提示
import java.util.Scanner; 必须写在 class 外面、文件最顶部；要整数用 nextInt()，要小数用 nextDouble()，输入类型不匹配会报 InputMismatchException。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*