# 8-7 章项目：通讯录 v1

**板块编号：** c8s7　|　**所属：** 第 8 章　|　**练习项目：** `ch08-s07-contact-book`

## 🎯 练习目标
综合封装类、ArrayList 增删改查、String 模糊搜索和 try-catch 容错，独立完成通讯录 v1。

## 📋 练习步骤
1. 先单独 new 一个 Contact 对象并调用 showInfo()，确认 JavaBean 的无参/全参构造、getter/setter 和打印都正常
2. 在 main 里创建 ArrayList<Contact>，把菜单循环 + 添加 + 查看先跑通（暂不做输入容错）
3. 加搜索：输入关键字后用 getName().contains(keyword) 模糊匹配打印所有命中项，一个都没有则提示“无匹配结果”
4. 加修改和删除：遍历找同名下标的对象，用 set 更新信息 / remove 删除并立即 break，找不到提示“查无此人”
5. 最后把菜单输入改成 while(true)+try-catch 的健壮写法，故意输入字母和 9，测到“怎么输都不崩”

## ✍️ 要做的事（TODO）
- [ ] TODO 1：把菜单选择换成 while(true)+try-catch 的健壮输入（catch InputMismatchException 时提示并 sc.next()），并校验数字在 1~6 之间
- [ ] TODO 2：实现 1 添加（姓名重复用 equals 判断提示“已存在”）和 2 查看（带序号遍历，空通讯录提示“还没有联系人”）
- [ ] TODO 3：实现 3 搜索（getName().contains(keyword) 模糊匹配，无命中提示“无匹配结果”）、4 修改（找下标后 set）、5 删除（找下标后 remove 并立刻 break，找不到提示“查无此人”）

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
输入 1 → 依次录入 张三 / 13800001111 / zs@xx.com，输出“添加成功！”；再次输入同名 → 输出“已存在”；输入 3 再输入关键字“张”→ 输出“-------- 搜索结果 --------” 和 “张三  电话：13800001111  邮箱：zs@xx.com”；输入 5 输入“李四”→ 输出“查无此人”；输入菜单时敲字母 → 提示“输入无效，请输入整数！”并重新显示菜单（不崩溃）；输入 6 → 输出“再见！”
```

## 💡 提示
ArrayList 的 contains/remove(对象) 默认靠 equals 判断，自定义对象现阶段一律“遍历找下标再操作”；删完记得 break，输入容错的关键是 catch 里的 sc.next()。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `src\Contact.java` —— 本节用到的类
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*