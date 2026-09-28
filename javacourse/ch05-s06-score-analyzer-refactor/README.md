# 5-6 章项目：重构+计算器

**板块编号：** c5s6　|　**所属：** 第 5 章　|　**练习项目：** `ch05-s06-score-analyzer-refactor`

## 🎯 练习目标
用方法把成绩分析器重构：录入、打印、求和、平均、最高分、分段统计各写一个方法各司其职。

## 📋 练习步骤
1. 运行初始程序，依次输入成绩个数 5 和成绩 90、85、77、59、100，观察当前输出
2. 补全 ScoreTools.getSum 与 getAvg，让总分和平均分计算正确（平均分要用 double 计算）
3. 补全 ScoreTools.getMax，用循环找出并返回数组中的最高分
4. 补全 ScoreTools.printLevelCount，统计并打印四个分数段的人数
5. 对照第 4 章的成绩分析器，确认每项结果一致，main 只负责按顺序调用各方法

## ✍️ 要做的事（TODO）
- [ ] 补全 ScoreTools.getSum 与 getAvg，分别返回总分和平均分（平均分要用 double 计算，避免整数相除丢小数）
- [ ] 补全 ScoreTools.getMax，用循环遍历数组找出最大值并返回
- [ ] 补全 ScoreTools.printLevelCount，统计并打印 90 以上、80~89、60~79、60 以下四个分数段的人数

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
所有成绩：90 85 77 59 100
总分：411
平均分：82.2
最高分：100
90 以上：2 人
80~89：1 人
60~79：1 人
60 以下：1 人
```

## 💡 提示
每个方法只做一件事，main 只负责按顺序调用；注意 getAvg 的返回类型是 double，求和后除以长度时不要写成整数除法。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\ScoreTools.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*