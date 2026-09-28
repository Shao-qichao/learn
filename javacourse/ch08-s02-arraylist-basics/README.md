# 8-2 ArrayList 入门

**板块编号：** c8s2　|　**所属：** 第 8 章　|　**练习项目：** `ch08-s02-arraylist-basics`

## 🎯 练习目标
会创建 ArrayList 集合并用 add、get、size 与增强 for 存取和遍历一组对象。

## 📋 练习步骤
1. 创建 ArrayList<String>，add 三个名字，直接打印集合本身并打印 size() 看长度
2. 再创建 ArrayList<Book>，add 两本 Book 对象，用 size() 和 get(i) 验证元素位置
3. 分别用普通 for + get(i) 和增强 for 遍历 books，都调用 showInfo() 输出每本书信息

## ✍️ 要做的事（TODO）
- [ ] TODO 1：再 add 一本 new Book("数据结构", 59.0)，并打印集合内容和 books.size()
- [ ] TODO 2：用增强 for（for (Book b : books)）遍历调用 showInfo()
- [ ] TODO 3：用普通 for + get(i) 打印每本书的书名

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
直接运行 → 参考输出：
[小明, 小红, 小刚]
人数：3
books.size() = 2
书名：Java入门  价格：69.9
书名：数据结构  价格：59.0
```

## 💡 提示
取长度是 size()（有括号）、取元素是 get(i)（不能写 []）；泛型里只能放引用类型，装整数要写 ArrayList<Integer>。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Book.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*