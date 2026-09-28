# 8-3 集合增删改查

**板块编号：** c8s3　|　**所属：** 第 8 章　|　**练习项目：** `ch08-s03-collection-crud`

## 🎯 练习目标
掌握 ArrayList 的增删改查（add/get/set/remove/contains/indexOf）并安全地按内容删除元素。

## 📋 练习步骤
1. 创建 ArrayList<String> 并 add A、B、C，打印初始内容确认顺序
2. 用 add(0, "插队的") 在头部插入，再用 set(1, "新名字") 改一个元素，打印观察下标变化
3. 用 indexOf("B") 找到下标后 remove(idx) 删除（找不到打印“查无此人”），再用 contains("C") 验证是否还在

## ✍️ 要做的事（TODO）
- [ ] TODO 1：用 add(0, "插队的") 指定位置插入，再用 set(index, 值) 修改一个元素并打印
- [ ] TODO 2：用 indexOf("B") 找到下标后 remove(idx) 删除，找不到就打印“查无此人”
- [ ] TODO 3：用 contains("C") 判断 C 是否还在集合里，打印 true/false

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
直接运行 → 参考输出：
初始：[A, B, C]
插入并修改后：[插队的, 新名字, B, C]
删除 B 后：[插队的, 新名字, C]
contains("C") = true
```

## 💡 提示
删除后后面的元素会自动前移、下标整体变化；不要正向边遍历边删，改成 indexOf 找下标后 remove 并立刻 break。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*