# 9-4 哈希表 HashSet/HashMap

**板块编号：** c9s4　|　**所属：** 第 9 章　|　**练习项目：** `ch09-s04-hash-table`

## 🎯 练习目标
用 `HashMap` 的 `getOrDefault` 统计字符出现次数，再用“空间换时间”的哈希表一趟解决两数之和（O(n)）。

## 📋 练习步骤
1. 双击 `run.bat`，看 `banana`、`hello java` 的字符频次，以及两组两数之和结果
2. 再调用一次 `countChars`，传一个你自己的字符串
3. 在 `countChars` 里遍历 `count.entrySet()`，找出出现次数最多的字符并打印
4. 用 `//` 注释回答：哈希表两数之和为什么是 O(n)，牺牲了什么

## ✍️ 要做的事（TODO）
- [ ] TODO 1：自定义一个字符串做频次统计
- [ ] TODO 2：遍历 entrySet 找出次数最多的字符
- [ ] TODO 3：注释回答 O(n) 与“空间换时间”

## 👀 预期效果
```
banana 的字符频次：{a=3, b=1, n=2}      （a 最多）
目标 9：下标 0 和 1（2 + 7）
目标 18：下标 2 和 3（11 + 7 之类，以实际为准）
目标 100：没找到
```

## 💡 提示
- 统计频次背下这一句：`map.put(k, map.getOrDefault(k, 0) + 1);`
- 哈希表两数之和的思路：每走到一个数，就去 map 里问“我要配的另一个数 `target-当前值` 见没见过”，见过就成了。每个数只看一次所以 O(n)，代价是多用了一个 map 的空间。
- 参考片段：`for (var e : count.entrySet()) { e.getKey(); e.getValue(); }`

## 📁 文件说明
- `src\Main.java` —— 你唯一要改的文件
- `run.bat` / `open-in-vscode.bat` —— 运行 / 用 VS Code 打开
