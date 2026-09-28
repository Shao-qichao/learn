# javacourse · Java 基础强化练习工作区

一个板块一个练习项目文件夹，命名规范固定为 `ch<章号两位>-s<节号两位>-<英文短名>`，
例如 `ch01-s01-java-what`、`ch03-s06-atm-project`。

每个文件夹里：

| 文件 | 用途 |
| --- | --- |
| `README.md` | 本板块练习要求：目标、步骤、TODO、预期输出、提示 |
| `src\Main.java` | 初始代码，你在这里写 |
| `src\*.java` | 本节用到的其它类（第 5 章起出现） |
| `run.bat` | 一键编译运行（javac + java） |
| `open-in-vscode.bat` | 用 VS Code 打开本文件夹 |

## 一键生成 / 刷新全部练习项目

在 PowerShell 里执行：

```powershell
powershell -ExecutionPolicy Bypass -File "d:\AI小游戏\新建文件夹\java-course-site\tools\make-practice-folders.ps1"
```

默认**不会覆盖**你已经改过的 src 代码；确需重建时加 `-Force`。

## 网页上的入口

打开 `d:\AI小游戏\新建文件夹\java-course-site\index.html`，每个板块正文下方都有
「🖥️ 用 VS Code 打开」按钮，点一下自动用 VS Code 打开对应文件夹（首次点会问一次是否允许）。
如果按钮没反应，双击该文件夹里的 `open-in-vscode.bat` 即可。