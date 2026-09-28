# 8-1 String 常用方法

**板块编号：** c8s1　|　**所属：** 第 8 章　|　**练习项目：** `ch08-s01-string-toolbox`

## 🎯 练习目标
熟练使用 String 的查找、截取、替换、分割等常用方法，牢记内容比较必须用 equals。

## 📋 练习步骤
1. 先运行 starter，确认 s.length() 的返回值是 18（首尾空格也算长度）
2. 用 trim() 去掉首尾空格后重新赋值给 s，对比长度从 18 变成 16
3. 用 indexOf("Java") 找到起始下标，再用 substring(6,10) 截取出 Java，体会含头不含尾
4. 用 split(",") 把 "小明,18,广州" 拆成字符串数组，打印第一段并用 Integer.parseInt 把年龄转成 int 后打印 age + 1

## ✍️ 要做的事（TODO）
- [ ] TODO 1：用 trim() 去掉 s 首尾空格并打印新内容和长度，验证 18 → 16
- [ ] TODO 2：用 indexOf + substring 从 s 里截取出 Java 并打印
- [ ] TODO 3：用 split(",") 拆分 line，打印第一段，并用 Integer.parseInt 把年龄转成 int 后打印 age + 1

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
直接运行 → 参考输出：
原字符串：[ Hello Java World ] 长度：18
trim 后：[Hello Java World] 长度：16
截取出：Java
拆分得到 3 段，第一段：小明
age + 1 = 19
```

## 💡 提示
比较内容永远用 s1.equals(s2) 而不是 ==；substring 含头不含尾，下标算不清就先在纸上点一遍；parseInt 常配 trim() 防止意外空格。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*