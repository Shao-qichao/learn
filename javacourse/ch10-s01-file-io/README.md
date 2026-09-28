# 10-1 文件读写 BufferedReader

**板块编号：** c10s1　|　**所属：** 第 10 章　|　**练习项目：** `ch10-s01-file-io`

## 🎯 练习目标
用 `FileWriter` 写文件、用 `BufferedReader` 逐行读文件，掌握 try-with-resources 自动关闭，以及 `readLine()` 返回 `null` 表示读到文件末尾。

## 📋 练习步骤
1. 双击 `run.bat`，程序会自动生成 `notes.txt` 并逐行读回打印
2. 把 `new FileWriter(FILE)` 改成 `new FileWriter(FILE, true)`，**连续运行两次**，观察文件内容是被覆盖还是追加
3. 在 `readFile` 里加变量统计所有行的总字符数并打印
4. 用 `//` 注释回答：为什么用 `try(...)` 包流，而不是自己写 `close()`

## ✍️ 要做的事（TODO）
- [ ] TODO 1：改追加模式 true，跑两遍观察覆盖/追加
- [ ] TODO 2：统计文件总字符数
- [ ] TODO 3：注释回答 try-with-resources 的好处

## 👀 预期效果
```
== 第一次写入并读取 ==
第 1 行：第一行：学 Java 的第 10 章
第 2 行：第二行：今天练习文件读写
第 3 行：第三行：读完记得关闭文件
一共 3 行
```
改成追加模式后，再跑一遍文件会变成 6 行；不加 true 则永远只有 3 行（被重写）。

## 💡 提示
- `readLine()` 读到文件末尾返回 `null`，所以经典写法是 `while((line=br.readLine())!=null)`。
- `new FileWriter(path, true)` 第二个参数 `true` = 在文件末尾追加，`false`/不写 = 覆盖。
- `try(...)` 里声明的流无论正常还是异常都会自动关闭，不用手写 finally，也不怕忘记 close 造成文件被占用。
- `notes.txt` 生成在练习文件夹根目录（和 run.bat 同级）。

## 📁 文件说明
- `src\Main.java` —— 你唯一要改的文件
- `notes.txt` —— 运行后自动生成
- `run.bat` / `open-in-vscode.bat` —— 运行 / 用 VS Code 打开
