# 8-6 让输入更健壮

**板块编号：** c8s6　|　**所属：** 第 8 章　|　**练习项目：** `ch08-s06-robust-input`

## 🎯 练习目标
用 while 循环加 try-catch 封装 inputInt/inputChoice，让菜单输入怎么输错都不崩溃。

## 📋 练习步骤
1. 补全 InputHelper.inputInt：while(true) 里 try 直接 return sc.nextInt()，catch 里提示并 sc.next()
2. 补全 InputHelper.inputChoice：循环调用 inputInt，数字落在 min~max 之间才 return，否则提示重输
3. 把 main 里写死的 choice 换成 inputChoice(sc, 1, 4)，运行后分别输入字母、9、4 验证效果

## ✍️ 要做的事（TODO）
- [ ] TODO 1：补全 InputHelper.inputInt——while(true) 里 try { return sc.nextInt(); }，catch (InputMismatchException e) 里提示后必须 sc.next()
- [ ] TODO 2：补全 InputHelper.inputChoice——循环调用 inputInt，数字在 min~max 之间才 return，否则提示重输
- [ ] TODO 3：把 main 里写死的 choice 换成 InputHelper.inputChoice(sc, 1, 4)，并故意输入字母和 9 测试

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
输入 1 → 输出“执行 添加”；输入 9 → 提示“没有这个选项，请重新输入”并重新显示菜单；输入 abc → 提示“输入无效，请输入整数！”后重新选择；输入 4 → 输出“再见”并退出，全程不崩溃
```

## 💡 提示
catch 里那一行 sc.next() 千万别漏：非法输入留在缓冲区里，不消费掉下一轮 nextInt 读到的还是它，会无限重复报错。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\InputHelper.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*