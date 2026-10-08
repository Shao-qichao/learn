/**
 * 更新管理模块（主进程）
 *
 * 流程（对标 Chrome / VS Code 的静默更新体验）：
 *  1. 应用启动 3.5 秒后，后台静默 checkForUpdates() —— 发现新版本不弹窗，直接下载
 *  2. update-downloaded（安装包下载并校验完成）后，才弹出非模态提示窗
 *  3. 提示窗三个按钮：
 *       以后更新   → 关窗，仅本次启动不再打扰；下次启动继续提示（安装包已缓存）
 *       忽略此版本 → 写入 userData/update-prefs.json，永久跳过该版本（更高版本照常提示）
 *       立即更新   → autoUpdater.quitAndInstall()，退出并安装、装完自动重启
 *  4. 帮助菜单「检查更新…」走手动模式：会给出检查结果反馈，且无视「忽略/稍后」强制弹窗
 *
 * 设计约束：
 *  - 更新的任何环节失败都只记日志 / 在窗口内提示，绝不影响应用正常功能
 *  - 开发态（!app.isPackaged）不访问网络；设置环境变量 SIMULATE_UPDATE=1
 *    可在本地完整演示提示窗与按钮交互（安装动作同样为模拟，不会真退出）
 */

'use strict';

const fs = require('fs');
const path = require('path');
const { app, BrowserWindow, ipcMain, dialog, shell, screen } = require('electron');
const core = require('./updater-core');

/* ---------------- 轻量文件日志（排查「没弹提示」类问题） ----------------
   写 userData/updater.log；超过 200KB 时裁掉旧行，只保留最近约 400 行。
   任何 IO 失败都静默，绝不影响更新流程本身。 */
let _logFile = null;
function getLogFile() {
  if (_logFile === null) {
    try { _logFile = path.join(app.getPath('userData'), 'updater.log'); }
    catch (_e) { _logFile = ''; }
  }
  return _logFile;
}
function logLine(msg) {
  try {
    const f = getLogFile();
    if (!f) return;
    const line = new Date().toISOString() + ' ' + String(msg).replace(/\s*\n\s*/g, ' ').slice(0, 500) + '\n';
    try {
      if (fs.statSync(f).size > 200 * 1024) {
        const kept = fs.readFileSync(f, 'utf8').split('\n').slice(-400).join('\n');
        fs.writeFileSync(f, kept + (kept.endsWith('\n') ? '' : '\n') + line, 'utf8');
        return;
      }
    } catch (_e) { /* 首次写入时文件不存在，直接 append */ }
    fs.appendFileSync(f, line);
  } catch (_e) { /* 日志失败永远静默 */ }
}

/** 模拟模式：开发态 + SIMULATE_UPDATE=1 */
function isSimulate() {
  return !app.isPackaged && process.env.SIMULATE_UPDATE === '1';
}

/** 错误信息翻译成人话 */
function friendlyError(err) {
  const msg = (err && (err.message || err.toString())) || String(err);
  if (/net::|ERR_|ENOTFOUND|ETIMEDOUT|ECONNRESET|socket hang up|timeout/i.test(msg)) {
    return '网络连接失败，暂时无法检查更新。请确认网络后通过「帮助 → 检查更新」重试。';
  }
  if (/signature|certificate|sign/i.test(msg)) {
    return '安装包签名校验失败，已中止安装以保证安全。请前往 GitHub 发布页手动下载。';
  }
  if (/checkForUpdates|cannot|not packed/i.test(msg)) {
    return '当前环境暂不支持自动更新（开发模式）。';
  }
  return '更新过程中出现问题：' + msg;
}

/**
 * 创建更新管理器并注册 IPC。
 * @param {() => BrowserWindow|null} getMainWindow 取主窗口的函数（惰性，避免闭包旧值）
 */
function createUpdateManager(getMainWindow) {
  // electron-updater 仅在打包后真正使用；用惰性 require，保证缺依赖时主进程仍可启动
  let autoUpdater = null;
  try {
    ({ autoUpdater } = require('electron-updater'));
  } catch (e) {
    console.warn('[updater] electron-updater 不可用，自动更新功能关闭：', e.message);
  }

  const prefsFile = path.join(app.getPath('userData'), 'update-prefs.json');
  const prefs = core.createPrefsStore(prefsFile);

  let info = null;                 // 已下载完成的 UpdateInfo
  let notifierWin = null;          // 提示窗
  let sessionDismissed = false;    // 本次启动是否已「以后更新」
  let mode = 'auto';               // 'auto' | 'manual'
  let installing = false;          // 防止重复触发安装
  let checking = false;            // 防止手动检查重入

  /* ---------------- 窗口状态推送 ---------------- */

  function sendState(state) {
    if (notifierWin && !notifierWin.isDestroyed()) {
      notifierWin.webContents.send('update:state', state);
    }
  }

  /* ---------------- 窗口定位与尺寸（适配各种分辨率/缩放） ---------------- */

  /** 以「主窗口中心点」所在显示器为准（多显示器/高 DPI 下比用鼠标位置可靠） */
  function targetArea(parent) {
    if (parent && !parent.isDestroyed()) {
      const b = parent.getBounds();
      const center = { x: Math.round(b.x + b.width / 2), y: Math.round(b.y + b.height / 2) };
      return { area: screen.getDisplayNearestPoint(center).workArea, bounds: b };
    }
    const area = screen.getPrimaryDisplay().workArea;
    return { area, bounds: { x: area.x, y: area.y, width: area.width, height: area.height } };
  }

  function fitSize(parent) {
    const { area, bounds } = targetArea(parent);
    // 宽：340~440，且不超过工作区 92%；高：460~600，且同时不超过工作区/主窗口高度
    const heightCap = Math.min(area.height - 20, bounds.height - 16);
    return {
      width: core.clamp(Math.round(area.width * 0.92), 340, 440),
      height: core.clamp(Math.round(heightCap), 460, 600)
    };
  }

  function positionOver(win, parent) {
    const { area, bounds } = targetArea(parent);
    const [w, h] = win.getSize();
    // 在主窗口可视区域内居中，再夹取到显示器工作区内，保证按钮绝不越出屏幕
    let x = Math.round(bounds.x + (bounds.width - w) / 2);
    let y = Math.round(bounds.y + (bounds.height - h) / 2);
    x = core.clamp(x, area.x + 8, area.x + area.width - w - 8);
    y = core.clamp(y, area.y + 8, area.y + area.height - h - 8);
    win.setBounds({ x, y, width: w, height: h });
  }

  /* ---------------- 提示窗 ---------------- */

  /**
   * 自定义提示窗渲染失败（透明无边框窗在个别显卡/远程桌面下可能不可见）时，
   * 用系统原生对话框兜底——更新已下载完成，至少要让用户知道可以安装。
   */
  function nativeFallbackPrompt() {
    const parent = getMainWindow();
    const owner = parent && !parent.isDestroyed() ? parent : undefined;
    const ver = info && info.version ? 'v' + info.version : '新版本';
    dialog.showMessageBox(owner, {
      type: 'info',
      buttons: ['立即更新', '以后再说'],
      defaultId: 0,
      cancelId: 1,
      title: '发现新版本',
      message: '新版本 ' + ver + ' 已下载完成',
      detail: '点击「立即更新」将退出并安装新版本，安装完成后自动重启；也可以稍后通过「帮助 → 检查更新」再装。'
    }).then((r) => {
      if (r.response === 0) {
        actInstall();
      } else {
        sessionDismissed = true;
      }
    }).catch((e) => logLine('原生兜底对话框异常：' + e));
  }

  /**
   * @param {object} [opts]
   * @param {boolean} [opts.standalone] 不挂父窗口（主窗口最小化时使用，
   *                   否则子窗口会跟着最小化、用户完全看不到）
   */
  function openNotifier(opts) {
    opts = opts || {};
    if (notifierWin) {
      if (notifierWin.isMinimized()) notifierWin.restore();
      notifierWin.focus();
      return notifierWin;
    }
    const parent = getMainWindow();
    /* 主窗口最小化时，必须以独立窗口弹出——这是「下载完却没提示」的主要场景 */
    const standalone = !!opts.standalone ||
      !!(parent && !parent.isDestroyed() && parent.isMinimized());
    const size = fitSize(parent);
    const win = new BrowserWindow({
      width: size.width,
      height: size.height,
      minWidth: 340,
      minHeight: 460,
      resizable: false,
      minimizable: false,
      maximizable: false,
      fullscreenable: false,
      frame: false,
      transparent: true,
      show: false,
      parent: !standalone && parent && !parent.isDestroyed() ? parent : undefined,
      modal: false,                 // 关键：非模态，主窗口仍可正常使用
      alwaysOnTop: true,
      backgroundColor: '#00000000',
      title: '发现新版本',
      webPreferences: {
        contextIsolation: true,
        nodeIntegration: false,
        sandbox: true,
        preload: path.join(__dirname, 'update-preload.js')
      }
    });
    notifierWin = win;
    win.setAlwaysOnTop(true, 'screen-saver');

    win.loadFile(path.join(__dirname, 'update-notifier.html'));
    win.once('ready-to-show', () => {
      positionOver(win, parent);
      win.show();
      win.focus();
    });

    /* 兜底：5 秒后窗口仍不可见（ready-to-show 未触发或透明窗渲染失败），
       放弃自定义窗，改用系统原生对话框，保证用户一定收到更新通知 */
    const visibleFallback = setTimeout(() => {
      try {
        if (notifierWin === win && (!win.isVisible() || win.isMinimized())) {
          logLine('自定义提示窗 5 秒未显示，回退原生对话框');
          win.destroy();
          notifierWin = null;
          nativeFallbackPrompt();
        }
      } catch (e) {
        logLine('可见性兜底检查异常：' + e);
      }
    }, 5000);

    // 更新说明里的外链交给系统浏览器
    win.webContents.setWindowOpenHandler(({ url }) => {
      shell.openExternal(url);
      return { action: 'deny' };
    });
    win.webContents.on('will-navigate', (e, navUrl) => {
      if (!navUrl.startsWith('file://')) {
        e.preventDefault();
        shell.openExternal(navUrl);
      }
    });

    win.on('closed', () => {
      clearTimeout(visibleFallback);
      if (notifierWin === win) notifierWin = null;
    });
    return win;
  }

  /**
   * 在当前可用状态下弹出提示：
   * 主窗口最小化时不再死等 restore（用户若一直不还原、直接退出就永远看不到提示），
   * 而是以独立置顶窗在主窗口所在显示器弹出。
   */
  function showWhenUsable() {
    const parent = getMainWindow();
    if (parent && !parent.isDestroyed() && parent.isMinimized()) {
      logLine('下载完成时主窗口最小化：以独立置顶窗弹出提示');
      openNotifier({ standalone: true });
    } else {
      openNotifier();
    }
  }

  /* ---------------- 三个按钮的动作 ---------------- */

  function closeNotifier() {
    if (notifierWin && !notifierWin.isDestroyed()) notifierWin.close();
    notifierWin = null;
  }

  /** 以后更新：仅本会话记忆 */
  function actLater() {
    sessionDismissed = true;
    closeNotifier();
  }

  /** 忽略此版本：持久化跳过该版本 */
  function actIgnore() {
    if (info && info.version) prefs.ignoreVersion(info.version);
    sessionDismissed = true;
    closeNotifier();
  }

  /** 立即更新：退出并安装已下载的包 */
  function actInstall() {
    if (installing) return;

    // 开发模拟：不真退出
    if (isSimulate()) {
      installing = true;
      sendState({ phase: 'installing', message: '（模拟）正在退出并安装新版本…' });
      setTimeout(() => {
        installing = false;
        sendState({ phase: 'error', message: '模拟环境不会真正退出安装。打包后此处会执行 quitAndInstall()。' });
      }, 1200);
      return;
    }

    installing = true;
    sendState({ phase: 'installing', message: '正在退出并安装新版本，安装完成后将自动重启…' });
    logLine('用户点击立即更新，执行 quitAndInstall(false, true)');
    try {
      // isSilent=false：Windows 下走安装向导；isForceRunAfter=true：装完自动启动新版本
      autoUpdater.quitAndInstall(false, true);
      // 正常情况下进程会立刻退出；8 秒后仍在说明安装未拉起，恢复可操作状态
      setTimeout(() => {
        if (!installing) return;
        installing = false;
        sendState({ phase: 'error', message: '安装未能自动开始，可以重试，或前往 GitHub 发布页手动下载。' });
      }, 8000);
    } catch (err) {
      installing = false;
      console.error('[updater] quitAndInstall 失败：', err);
      sendState({ phase: 'error', message: friendlyError(err) });
    }
  }

  /* ---------------- IPC（只暴露最小接口） ---------------- */

  ipcMain.removeHandler('update:get-info');
  ipcMain.removeHandler('update:later');
  ipcMain.removeHandler('update:ignore');
  ipcMain.removeHandler('update:install');

  ipcMain.handle('update:get-info', () => ({
    currentVersion: app.getVersion(),
    version: info ? info.version : '',
    releaseDate: info && info.releaseDate ? String(info.releaseDate).slice(0, 10) : '',
    notes: core.truncate(core.notesToText(info ? info.releaseNotes : ''), 1400)
  }));
  ipcMain.handle('update:later', () => actLater());
  ipcMain.handle('update:ignore', () => actIgnore());
  ipcMain.handle('update:install', () => actInstall());

  /* ---------------- electron-updater 事件 ---------------- */

  function bindAutoUpdater() {
    autoUpdater.autoDownload = true;          // 发现后静默下载（默认即 true，显式声明）
    autoUpdater.autoInstallOnAppQuit = true;  // 用户正常退出时顺带安装已下载的包

    autoUpdater.on('update-available', (ev) => {
      logLine('发现新版本 ' + (ev && ev.version) + '，开始后台静默下载（mode=' + mode + '）');
      console.log('[updater] 发现新版本，开始后台静默下载');
      if (mode === 'manual') {
        dialog.showMessageBox(getMainWindow(), {
          type: 'info',
          title: '发现新版本',
          message: '发现新版本，将在后台静默下载。',
          detail: '下载完成后会弹出提示窗，你可以选择立即安装或稍后再装，不影响当前使用。'
        });
      }
    });

    autoUpdater.on('download-progress', (p) => {
      // 提示窗通常尚未打开（设计为下载完成后才弹）；若已打开则同步进度
      sendState({ phase: 'downloading', percent: Math.round(p.percent || 0) });
    });

    autoUpdater.on('update-downloaded', (ev) => {
      info = ev || {};
      const parentNow = getMainWindow();
      const minimizedNow = !!(parentNow && !parentNow.isDestroyed() && parentNow.isMinimized());
      const willPrompt = mode === 'manual' ||
        core.shouldAutoPrompt(info, prefs.all(), sessionDismissed, !!notifierWin);
      logLine('更新包下载完成：' + info.version +
        '，mode=' + mode + '，忽略版本=' + (prefs.ignoredVersion || '无') +
        '，本会话已稍后=' + sessionDismissed + '，主窗口最小化=' + minimizedNow +
        '，将弹窗=' + willPrompt);
      console.log('[updater] 更新包下载完成：', info.version);
      if (willPrompt) {
        showWhenUsable();
      }
      mode = 'auto';
      checking = false;
    });

    autoUpdater.on('update-not-available', () => {
      logLine('当前已是最新版本 v' + app.getVersion());
      console.log('[updater] 当前已是最新版本');
      if (mode === 'manual') {
        dialog.showMessageBox(getMainWindow(), {
          type: 'info',
          title: '已是最新版本',
          message: '当前已是最新版本 v' + app.getVersion(),
          detail: '新版本发布后会在后台静默下载，完成后提醒你安装。'
        });
      }
      mode = 'auto';
      checking = false;
    });

    autoUpdater.on('error', (err) => {
      logLine('更新流程出错（mode=' + mode + '）：' + ((err && err.stack) ? err.stack : err));
      console.warn('[updater] 更新流程出错：', err);
      if (notifierWin && !notifierWin.isDestroyed()) {
        sendState({ phase: 'error', message: friendlyError(err) });
      } else if (mode === 'manual') {
        dialog.showMessageBox(getMainWindow(), {
          type: 'error',
          title: '检查更新失败',
          message: '无法完成更新检查',
          detail: friendlyError(err)
        });
      }
      mode = 'auto';
      checking = false;
    });
  }

  /* ---------------- 对外接口 ---------------- */

  /** 启动后静默自检（打包环境才真正联网） */
  function start() {
    logLine('启动更新检查：app.isPackaged=' + app.isPackaged +
      '，当前版本 v' + app.getVersion() + '，electron-updater=' + (autoUpdater ? '可用' : '不可用') +
      '，SIMULATE_UPDATE=' + (process.env.SIMULATE_UPDATE || '未设置'));
    if (isSimulate()) {
      console.log('[updater] 模拟模式：1.5 秒后推送一个假的“下载完成”事件');
      setTimeout(() => {
        info = {
          version: '9.9.9-demo',
          releaseDate: new Date().toISOString(),
          releaseNotes:
            '## 模拟更新 v9.9.9-demo\n\n' +
            '- 这是一条用于演示提示窗的模拟更新，不会真的退出应用。\n' +
            '- 可依次测试三个按钮：以后更新 / 忽略此版本 / 立即更新。\n' +
            '- 「忽略此版本」会写入 update-prefs.json，重启后该版本不再自动提示。'
        };
        logLine('（模拟）更新包下载完成：9.9.9-demo，准备弹窗');
        showWhenUsable();
      }, 1500);
      return;
    }
    if (!app.isPackaged || !autoUpdater) return;
    setTimeout(() => {
      logLine('3.5 秒后自动检查开始');
      autoUpdater.checkForUpdates().then((r) => {
        logLine('自动检查请求完成：' + (r && r.updateInfo ? r.updateInfo.version : '(无返回)'));
      }).catch((err) => {
        // 后台自检失败静默处理，绝不能打扰学生上课
        logLine('启动自动检查失败（已忽略）：' + (err && err.message));
        console.warn('[updater] 启动自动检查失败（已忽略）：', err.message);
      });
    }, 3500);
  }

  /** 帮助菜单手动检查更新 */
  async function checkNowWithUi() {
    if (isSimulate()) {
      info = {
        version: '9.9.9-demo',
        releaseDate: new Date().toISOString(),
        releaseNotes: '## 模拟更新 v9.9.9-demo\n\n手动检查触发的模拟弹窗。'
      };
      openNotifier();
      return;
    }
    if (!app.isPackaged || !autoUpdater) {
      dialog.showMessageBox(getMainWindow(), {
        type: 'info',
        title: '检查更新',
        message: '当前为开发环境',
        detail: '自动更新仅在安装版中启用。正式版启动后会自动检查，也可随时在此手动检查。'
      });
      return;
    }
    if (checking) return;
    checking = true;
    mode = 'manual';
    try {
      const result = await autoUpdater.checkForUpdates();
      // update-downloaded 已缓存时可能不经过 download-progress，直接兜底弹窗
      if (result && result.downloadPromise) {
        result.downloadPromise.then(() => {
          if (mode === 'manual' && info) showWhenUsable();
        }).catch(() => {});
      }
    } catch (err) {
      mode = 'auto';
      checking = false;
      dialog.showMessageBox(getMainWindow(), {
        type: 'error',
        title: '检查更新失败',
        message: '无法完成更新检查',
        detail: friendlyError(err)
      });
    }
  }

  if (autoUpdater) bindAutoUpdater();

  return { start, checkNowWithUi, openNotifier, _core: core, _prefsFile: prefsFile };
}

module.exports = { createUpdateManager, isSimulate, friendlyError };
