/**
 * 预加载脚本：以最小暴露面把主进程的 AI 评分代理能力带给渲染进程。
 * 页面里 window.electronAI.aiCall(payload) → 主进程 https 转发（无跨域）。
 * 只暴露 aiCall 一个方法，不暴露 ipcRenderer / node 能力。
 */
const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('electronAI', {
  aiCall: (payload) => ipcRenderer.invoke('ai-score-call', payload)
});
