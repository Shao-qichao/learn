# 3-5 for 与 break/continue

**板块编号：** c3s5　|　**所属：** 第 3 章　|　**练习项目：** `ch03-s05-for-break-continue`

## 🎯 练习目标
掌握 for 循环的三要素写法，并会用 break 提前结束、用 continue 跳过本轮。

## 📋 练习步骤
1. 用 for 循环把 1~10 依次打印出来，确认循环体执行了 10 次
2. 在循环体开头加 if (i == 5) { continue; }，观察 5 不再被打印
3. 再加 if (i > 8) { break; }，观察循环打印完 8 就提前结束
4. 另写一个 for 循环，用 if (i % 2 == 0) 累加出 1~100 的偶数和并打印

## ✍️ 要做的事（TODO）
- [ ] 在循环 1 里加 if (i == 5) { continue; }，让 5 不被打印
- [ ] 在循环 1 里加 if (i > 8) { break; }，让循环打印完 8 就提前结束
- [ ] 在循环 2 里写 if (i % 2 == 0) { sum += i; }，算出偶数和 2550

## ▶️ 怎么运行
1. 在 VS Code 里打开本文件夹（网页上点「用 VS Code 打开」按钮，或双击 `open-in-vscode.bat`）
2. 修改 `src\Main.java`
3. 双击 `run.bat` 编译并运行（或按 Ctrl+ 打开终端，执行 `java src\Main.java`）
4. 对照下面「预期效果」自查，然后回网页完成测验

## 👀 预期效果
```
1
2
3
4
6
7
8
1~100 偶数和 = 2550
```

## 💡 提示
continue 只跳过本轮、break 立即结束整个循环；for 里执行 continue 后仍会走 i++，不用怕死循环。

## 📁 文件说明
- `src\Main.java` —— 你唯一必须改的文件
- `run.bat` —— 一键编译并运行（双击）
- `open-in-vscode.bat` —— 用 VS Code 打开本文件夹（双击）

---
*本文件由网页练习数据自动生成；重新运行 tools\make-practice-folders.ps1 可刷新（不会覆盖你改过的 src 代码）。*