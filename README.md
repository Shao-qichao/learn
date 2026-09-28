# Java 学习中心

闯关式 Java 零基础学习课程：10 章 57 个知识板块，图文讲解 + 选择题测验 + 动手练习 + 章节项目实战，完成一章才能解锁下一章。

## 📦 下载桌面版（推荐新手）

无需配置环境，安装后双击即用：

**👉 [前往 Releases 下载安装包](https://github.com/Shao-qichao/learn/releases)**

| Windows | macOS | Linux |
|---------|-------|-------|
| `Java学习中心-Setup-x.x.x.exe` | `.dmg`（Intel / Apple 芯片） | `.AppImage` / `.deb` |

安装步骤见 [下载安装指南](docs/下载安装指南.md)。

> 写代码仍需安装 **JDK 11+** 和 **VS Code**，可用 `java-course-site/tools/` 下的一键脚本安装。

## 🌐 网页版（开发者）

```powershell
git clone https://github.com/Shao-qichao/learn.git
# 直接双击 java-course-site/index.html
```

网页版需克隆到 `D:\java学习中心` 并按 [新用户操作指南](java-course-site/新用户操作指南.md) 适配本机路径。

## 📂 目录说明

| 目录 | 内容 |
|------|------|
| `java-course-site/` | 课程网站（单文件 index.html）、环境安装脚本、新用户指南 |
| `javacourse/` | 55 个随堂练习模板（含 Main.java 骨架、run.bat、README） |
| `desktop/` | Electron 桌面应用（打包配置见 [开发者文档](docs/开发者文档.md)） |
| `docs/` | 下载安装指南、开发者文档 |

## 🛠️ 从源码构建桌面版

```powershell
cd desktop
npm install
npm run selfcheck      # 路径替换自检
npm run dist:win       # 产出 dist/Java学习中心-Setup-x.x.x.exe
```

发布新版本：推送 `v*` tag 后，GitHub Actions 自动构建三平台安装包并发布 Release。

## 🗝️ 章节解锁口令

| 章 | 口令 | 章 | 口令 |
|----|------|----|------|
| 1→2 | COFFEE-02 | 6→7 | EXTEND-0707 |
| 2→3 | LOOP-0303 | 7→8 | FINAL-0808 |
| 3→4 | ARRAY-0404 | 8→9 | DSALGO-09 |
| 4→5 | METHOD-0505 | 9→10 | MUSIC-10 |
| 5→6 | OBJECT-0606 | | |

## License

MIT
