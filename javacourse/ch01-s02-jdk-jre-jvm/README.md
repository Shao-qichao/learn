# 1-2 JDK/JRE/JVM

**板块编号：** c1s2　|　**所属：** 第 1 章　|　**练习项目：** `ch01-s02-jdk-jre-jvm`

## 🎯 练习目标
用打印输出讲清 JDK、JRE、JVM 的套娃包含关系，记住写代码要装 JDK。

## 📋 练习步骤
1. 在 main 里用三行 println 分别输出 JVM、JRE、JDK 的名字
2. 把三行内容补全：JVM 负责运行程序、JRE 是运行环境、JDK 是开发工具包
3. 再用一行 println 输出包含关系：JDK 包含 JRE，JRE 包含 JVM
4. 用 // 注释写一句「写代码装 JDK，因为 javac 只在 JDK 里」

## ✍️ 要做的事（TODO）
- [ ] TODO 1：把 JVM、JRE、JDK 三行冒号后面的解释补全
- [ ] TODO 2：补一行 println，输出三者的包含关系
- [ ] TODO 3：用 // 注释写一句「javac 来自 JDK，所以写代码必须装 JDK」

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
=== JDK / JRE / JVM 小卡片 ===
JVM：Java 虚拟机，真正运行程序的「虚拟电脑」
JRE：运行环境，等于 JVM 加官方类库
JDK：开发工具包，等于 JRE 加编译器 javac
JDK 包含 JRE，JRE 包含 JVM
```

## 💡 提示
记不住就念口诀：大圈套小圈，JDK 大于 JRE 大于 JVM。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*