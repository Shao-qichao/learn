/**
 * 更新提示窗的预加载脚本（沙箱安全模型）
 *
 * 渲染进程只暴露最小 API，所有动作都经主进程 IPC 处理，
 * 页面本身拿不到 Node / 文件系统能力。
 */

'use strict';

const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('updateAPI', {
  /** 获取当前版本 / 新版本号 / 更新日期 / 更新说明 */
  getInfo: () => ipcRenderer.invoke('update:get-info'),
  /** 以后更新：关窗，本次会话不再提示，下次启动继续 */
  later: () => ipcRenderer.invoke('update:later'),
  /** 忽略此版本：持久化，不再自动提示该版本 */
  ignore: () => ipcRenderer.invoke('update:ignore'),
  /** 立即更新：退出并安装 */
  install: () => ipcRenderer.invoke('update:install'),
  /** 订阅主进程状态（downloading / installing / error） */
  onState: (cb) => {
    const listener = (_event, state) => {
      try { cb(state); } catch (_e) { /* 页面回调异常不能影响主进程 */ }
    };
    ipcRenderer.on('update:state', listener);
    return () => ipcRenderer.removeListener('update:state', listener);
  }
});
