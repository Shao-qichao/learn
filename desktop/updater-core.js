/**
 * 更新系统 · 纯逻辑核心（不依赖 Electron，可直接 Node 单测）
 *
 * 负责：
 *  - 更新偏好的持久化（userData/update-prefs.json）
 *  - 「是否自动弹窗」决策（忽略版本 / 本会话已稍后）
 *  - releaseNotes 归一化为纯文本
 */

'use strict';

const fs = require('fs');

/** 默认偏好：未忽略任何版本 */
function defaultPrefs() {
  return { ignoredVersion: null };
}

/**
 * 读取偏好。文件缺失 / JSON 损坏 / 字段非法时一律回退默认值，绝不抛错，
 * 保证更新偏好文件出问题也不会影响应用启动。
 */
function loadPrefs(file) {
  try {
    const p = JSON.parse(fs.readFileSync(file, 'utf8'));
    return {
      ignoredVersion: typeof p.ignoredVersion === 'string' && p.ignoredVersion
        ? p.ignoredVersion
        : null
    };
  } catch (_e) {
    return defaultPrefs();
  }
}

/** 原子写：先写 .tmp 再重命名，避免写入中途崩溃产生半截 JSON */
function savePrefs(file, prefs) {
  const tmp = file + '.tmp';
  fs.writeFileSync(tmp, JSON.stringify(prefs, null, 2), 'utf8');
  fs.renameSync(tmp, file);
}

/** 偏好存储：内存缓存 + 落盘持久化 */
function createPrefsStore(file) {
  let prefs = loadPrefs(file);
  return {
    /** 当前偏好副本 */
    all() { return { ...prefs }; },
    get ignoredVersion() { return prefs.ignoredVersion; },
    /** 永久忽略某个版本（之后启动不再自动提示该版本） */
    ignoreVersion(v) {
      prefs = { ...prefs, ignoredVersion: String(v) };
      savePrefs(file, prefs);
      return prefs;
    }
  };
}

/**
 * 自动检查流程下，下载完成时是否应弹出提示窗。
 *
 * @param {object|null} info           electron-updater 的 UpdateInfo（至少含 version）
 * @param {object} prefs               偏好 { ignoredVersion }
 * @param {boolean} sessionDismissed   本次启动后用户是否点过「以后更新」
 * @param {boolean} windowOpen         提示窗是否已经开着
 * @returns {boolean}
 *
 * 规则：
 *  - 没有有效更新信息 → 不弹
 *  - 窗口已开 / 本会话已选择稍后 → 不弹（不反复打扰）
 *  - 该版本被「忽略此版本」永久跳过 → 不弹（更高版本不受影响）
 */
function shouldAutoPrompt(info, prefs, sessionDismissed, windowOpen) {
  if (!info || !info.version) return false;
  if (windowOpen || sessionDismissed) return false;
  if (prefs && prefs.ignoredVersion === info.version) return false;
  return true;
}

/**
 * 把 electron-updater 的 releaseNotes 归一化成纯文本。
 * GitHub provider 返回值可能是：string | null | Array<{note?:string}|string>
 */
function notesToText(notes) {
  if (notes == null) return '';
  if (Array.isArray(notes)) {
    return notes
      .map((n) => {
        if (typeof n === 'string') return n;
        if (n && typeof n === 'object') return n.note || n.text || n.title || '';
        return '';
      })
      .filter(Boolean)
      .join('\n\n');
  }
  return String(notes);
}

/** 截断过长的更新说明，避免提示窗内容失控 */
function truncate(text, max) {
  const t = String(text || '').trim();
  if (t.length <= max) return t;
  return t.slice(0, max).replace(/\s+\S*$/, '') + '\n\n…（完整说明见 GitHub Release 页面）';
}

/** 数值夹取 */
function clamp(v, min, max) {
  return Math.max(min, Math.min(max, v));
}

module.exports = {
  defaultPrefs,
  loadPrefs,
  savePrefs,
  createPrefsStore,
  shouldAutoPrompt,
  notesToText,
  truncate,
  clamp
};
