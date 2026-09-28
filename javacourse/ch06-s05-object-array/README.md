# 6-5 对象数组

**板块编号：** c6s5　|　**所属：** 第 6 章　|　**练习项目：** `ch06-s05-object-array`

## 🎯 练习目标
掌握对象数组的两步创建：先开格子再逐个 new 对象，并能遍历统计数组里的对象。

## 📋 练习步骤
1. 观察 Phone[] phones = new Phone[3]; 之后打印 phones[0]，理解格子里装的是 null 门牌
2. 给三个格子分别放入 new 出来的 Phone 对象
3. 用普通 for 循环遍历数组，调用每台手机的 show() 打印信息
4. 用增强 for 循环再遍历一次，累加价格并输出总价与平均价格
5. 确认遍历前每个格子都已 new，避免调用方法时出现空指针

## ✍️ 要做的事（TODO）
- [ ] TODO 1 给 phones[0]、phones[1]、phones[2] 分别放入 new 出来的 Phone 对象
- [ ] TODO 2 用普通 for 循环遍历数组，调用 show() 打印每台手机的信息
- [ ] TODO 3 用增强 for 累加价格，输出总价和平均价格

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
===== 对象数组练习 =====
数组长度：3
还没放对象时 phones[0] = null
小米 价格：1999.0 元
荣耀 价格：2499.0 元
苹果 价格：5999.0 元
总价：10497.0 元
平均价格：3499.0 元
```

## 💡 提示
对象数组只 new 数组还不够，每个格子都要单独 new，否则调用方法时会抛 NullPointerException。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Phone.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*