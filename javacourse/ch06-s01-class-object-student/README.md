# 6-1 类与对象

**板块编号：** c6s1　|　**所属：** 第 6 章　|　**练习项目：** `ch06-s01-class-object-student`

## 🎯 练习目标
理解类与对象的关系，会定义类、用 new 创建对象并通过点访问属性和成员方法。

## 📋 练习步骤
1. 运行初始程序，观察 Student.java 这张“图纸”造出的小明对象完成了自我介绍
2. 在 main 中再 new 一个学生对象 s2，分别给它设置姓名和年龄并调用 study()
3. 在 Student 类中新增 introduce() 方法，用成员变量拼出一句自我介绍
4. 在 main 中让两个对象都调用 introduce()，确认它们各自打印自己的数据

## ✍️ 要做的事（TODO）
- [ ] 在 main 中再 new 一个 Student 对象 s2，把 name 设为“小红”、age 设为 17，并调用 s2.study()
- [ ] 在 Student 类中新增方法 introduce()，打印“我叫 XX，今年 XX 岁”（XX 用成员变量 name、age 拼接）
- [ ] 在 main 中分别调用 s1.introduce() 和 s2.introduce()，确认每个对象打印的是自己的数据

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
小明 正在学习
小红 正在学习
我叫 小明，今年 18 岁
我叫 小红，今年 17 岁
```

## 💡 提示
成员方法前面不写 static；main 是 static，必须先 new 造出对象，再用“对象.属性 / 对象.方法()”访问，s1 和 s2 互不影响。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Student.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*