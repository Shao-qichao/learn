/**
 * Java 学习中心 · 桌面版主进程
 *
 * 核心职责：
 *  1. 把课程网站（index.html）与练习模板（javacourse/）作为应用资源打包
 *  2. 首次启动时把练习模板复制到「文档\Java学习中心」（用户可写、可保存代码）
 *  3. 运行时生成 index.html 副本，把写死的开发机路径替换为本机真实路径
 *  4. vscode:// 与 http(s) 链接交给系统外部程序打开
 */

const { app, BrowserWindow, Menu, shell, dialog, session } = require('electron');
const fs = require('fs');
const path = require('path');
const { createUpdateManager } = require('./updater');

const APP_NAME = 'Java学习中心';
const isDev = !app.isPackaged;

// 资源目录：开发态在仓库根目录，打包后在 process.resourcesPath
function resDir(name) {
  return isDev
    ? path.join(__dirname, '..', name)
    : path.join(process.resourcesPath, name);
}

// 用户工作区（学生代码长期保存的位置）
const workspace = path.join(app.getPath('documents'), APP_NAME);
const practiceDir = path.join(workspace, 'javacourse');   // 48 个动手练习
const projectDir = path.join(workspace, 'JavaLearning');  // 10 个章项目
const runtimeDir = path.join(app.getPath('userData'), 'runtime');

function ensureDir(d) { fs.mkdirSync(d, { recursive: true }); }

/**
 * 首次启动：把练习模板同步到用户文档。
 * 只复制「不存在」的练习文件夹 —— 学生已修改的代码绝不覆盖。
 */
function syncPractice() {
  const src = resDir(isDev ? 'javacourse' : 'practice');
  ensureDir(practiceDir);
  for (const name of fs.readdirSync(src)) {
    const s = path.join(src, name);
    const d = path.join(practiceDir, name);
    if (fs.statSync(s).isDirectory() && !fs.existsSync(d)) {
      fs.cpSync(s, d, { recursive: true });
    }
  }
}

/** 在用户工作区放一份目录说明（仅首次） */
function writeWorkspaceReadme() {
  const f = path.join(workspace, '目录说明.txt');
  if (fs.existsSync(f)) return;
  const note =
    'Java 学习中心 · 我的代码目录\r\n' +
    '================================\r\n\r\n' +
    '【javacourse】48 个随课程配套的动手练习（只读模板的可写副本，可随意修改）\r\n' +
    '【JavaLearning】10 个章节项目的代码写在这里（如个人名片、收银台、图书馆系统）\r\n\r\n' +
    '这两个文件夹会在软件启动时自动补齐缺失内容，已有的代码不会被覆盖。\r\n';
  fs.writeFileSync(f, note, 'utf8');
}

/** JSON 字符串里的反斜杠需要转义 */
function escJson(p) { return p.replace(/\\/g, '\\\\'); }

/** 本地路径 -> vscode://file URL（与课程网页的编码规则一致） */
function vscodeUrl(p) {
  return 'vscode://file/' + p.split(/[\\/]/).map(encodeURIComponent).join('/');
}

/**
 * 读取打包的 index.html，替换全部写死路径，输出到 userData 运行时副本。
 * 返回运行时 html 的绝对路径。
 */
function buildRuntimeHtml() {
  const siteDir = resDir(isDev ? 'java-course-site' : 'site');
  let html = fs.readFileSync(path.join(siteDir, 'index.html'), 'utf8');

  // 1) 练习数据 JSON 中的路径（文件里是 JSON 转义形式：两个反斜杠）
  html = html.split('D:\\\\java学习中心\\\\javacourse').join(escJson(practiceDir));

  // 2) 章项目静态卡片的 vscode://file/c%3A/Users/13053/JavaLearning
  html = html
    .split('vscode://file/c%3A/Users/13053/JavaLearning')
    .join(vscodeUrl(projectDir));

  // 3) 课文讲解中出现的示例路径（正斜杠形式）
  html = html.split('c:/Users/13053/JavaLearning').join(projectDir.split('\\').join('/'));

  ensureDir(runtimeDir);
  // 复制图标等网页相对引用的资源
  const ico = path.join(siteDir, 'java-center.ico');
  if (fs.existsSync(ico)) fs.copyFileSync(ico, path.join(runtimeDir, 'java-center.ico'));

  const out = path.join(runtimeDir, 'index.html');
  fs.writeFileSync(out, html, 'utf8');
  return out;
}

let mainWindow = null;
let updateManager = null;   // 更新管理器（app ready 后创建）

function createWindow() {
  const page = buildRuntimeHtml();
  mainWindow = new BrowserWindow({
    width: 1320,
    height: 880,
    minWidth: 1024,
    minHeight: 680,
    backgroundColor: '#f5f3ff',
    title: APP_NAME,
    icon: path.join(__dirname, 'build', process.platform === 'win32' ? 'icon.ico' : 'icon.png'),
    webPreferences: { contextIsolation: true, nodeIntegration: false }
  });

  mainWindow.loadFile(page);

  // 所有外链（vscode://、http(s)://、mailto:）交给系统，不在应用内导航
  mainWindow.webContents.setWindowOpenHandler(({ url }) => {
    shell.openExternal(url);
    return { action: 'deny' };
  });
  mainWindow.webContents.on('will-navigate', (e, url) => {
    if (!url.startsWith('file://')) {
      e.preventDefault();
      shell.openExternal(url);
    }
  });

  const menu = Menu.buildFromTemplate([
    {
      label: '学习',
      submenu: [
        { label: '刷新页面', accelerator: 'F5', click: () => mainWindow.reload() },
        { type: 'separator' },
        { label: '打开练习文件夹（javacourse）', click: () => shell.openPath(practiceDir) },
        { label: '打开章项目文件夹（JavaLearning）', click: () => shell.openPath(projectDir) },
        { type: 'separator' },
        {
          label: '清空学习进度并重新开始',
          click: async () => {
            const r = await dialog.showMessageBox(mainWindow, {
              type: 'warning',
              buttons: ['取消', '确认清空'],
              defaultId: 0,
              cancelId: 0,
              title: '重置进度',
              message: '将清除所有通关记录、测验成绩和解锁状态。',
              detail: '不会删除你写的 Java 代码。此操作不可撤销。'
            });
            if (r.response === 1) {
              await session.defaultSession.clearData({ storages: ['localstorage', 'shadercache'] });
              mainWindow.reload();
            }
          }
        }
      ]
    },
    { role: 'editMenu', label: '编辑' },
    {
      label: '帮助',
      submenu: [
        {
          label: '关于 Java 学习中心',
          click: () => dialog.showMessageBox(mainWindow, {
            type: 'info',
            title: '关于',
            message: 'Java 学习中心 v' + app.getVersion(),
            detail:
              '闯关式 Java 零基础学习桌面软件\n\n' +
              '我的代码目录：\n' + workspace + '\n\n' +
              '仓库：https://github.com/Shao-qichao/learn'
          })
        },
        { type: 'separator' },
        { label: '检查更新…', click: () => updateManager && updateManager.checkNowWithUi() },
        { label: '访问 GitHub 仓库', click: () => shell.openExternal('https://github.com/Shao-qichao/learn') }
      ]
    }
  ]);
  Menu.setApplicationMenu(menu);
}

// 单实例：再次启动时聚焦已有窗口
if (!app.requestSingleInstanceLock()) {
  app.quit();
} else {
  app.on('second-instance', () => {
    if (mainWindow) { if (mainWindow.isMinimized()) mainWindow.restore(); mainWindow.focus(); }
  });

  app.whenReady().then(() => {
    // 自检模式：node main.js --selfcheck（CI/打包前验证路径替换）
    if (process.argv.includes('--selfcheck')) {
      ensureDir(workspace); ensureDir(projectDir);
      syncPractice();
      const page = buildRuntimeHtml();
      const html = fs.readFileSync(page, 'utf8');
      // 注意：本机用户名可能正好就是 13053，所以只比对“旧的硬编码完整串”
      const bad = [
        'vscode://file/c%3A/Users/13053/JavaLearning',
        'D:\\\\java学习中心\\\\javacourse',
        'c:/Users/13053/JavaLearning'
      ].filter(s => html.includes(s));
      if (bad.length) { console.error('SELFCHECK FAIL 残留路径:', bad.join(', ')); app.exit(1); }
      console.log('SELFCHECK OK ->', page);
      console.log('练习目录:', practiceDir);
      console.log('章项目目录:', projectDir);
      app.exit(0);
      return;
    }

    ensureDir(workspace);
    ensureDir(projectDir);
    syncPractice();
    writeWorkspaceReadme();
    createWindow();

    // 更新系统：打包后启动 3.5 秒静默检查，下载完成后弹非模态提示窗；
    // 开发态可设置环境变量 SIMULATE_UPDATE=1 演示完整交互。
    updateManager = createUpdateManager(() => mainWindow);
    updateManager.start();

    app.on('activate', () => { if (BrowserWindow.getAllWindows().length === 0) createWindow(); });
  });

  app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') app.quit();
  });
}
