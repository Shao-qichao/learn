# Java 学习中心 · AI 评分功能接入文档

> 版本：v1.3.6 功能模块 · 文档更新日期：2026-10-10
> 适用范围：课程站全部 10 个章项目（个人名片 → 留言分析器）的 AI 深度评分

---

## 1. 功能概述

### 1.1 评分场景与对象

| 项 | 说明 |
|---|---|
| 评分场景 | 10 个章项目代码审查（`.proj-review` 区域内的「🤖 AI 深度评分」按钮） |
| 评分对象 | 学生提交的 Java 源代码文本（`textarea[data-review]` 内容） |
| 触发方式 | 章项目页点击按钮，单次调用，结果持久化到 localStorage |
| 分数用途 | **仅展示建议**，不改变现有「规则评分 ≥60 解锁下一章口令」的通关闸口 |

10 个项目与目标类名映射（引擎内 `AI_GOAL_CLASS`）：

| 页面 ID | 项目 | 目标类名 |
|---|---|---|
| c1s6 | 个人名片 | NameCard |
| c2s6 | 收银台 | Cashier |
| c3s6 | 迷你 ATM | MiniATM |
| c4s5 | 成绩分析器 | ScoreAnalyzer |
| c5s6 | 计算器 | Calculator |
| c6s6 | 图书管理 | Book |
| c7s6 | 工资系统 | SalaryApp |
| c8s7 | 通讯录 | ContactApp |
| c9s5 | 算法练习 | AlgoPractice |
| c10s4 | 留言分析器 | CommentAnalyzer |

### 1.2 评分维度与标准（总分 100）

| 维度 | 满分 | 含义 |
|---|---|---|
| 正确性 | 30 | 逻辑正确、输出符合项目要求 |
| 规范性 | 20 | 语法规范、命名合法、无明显语法隐患 |
| 结构 | 20 | 方法拆分、控制流组织、代码分层 |
| 可读性 | 15 | 缩进、命名可读、注释适量 |
| 习惯 | 15 | Scanner 关闭、无占位符残留、资源管理等 |

- 总分 = Σ(维度得分) 加权归一化到 0–100。
- **60 分线**：提示词中告知 AI「≥60 为达标」，但 AI 分数不参与解锁逻辑，仅供学生参考。
- 模拟引擎（mock）按同一套维度/权重本地确定性打分，保证无 Key 时体验一致。

---

## 2. 架构与数据流

```
┌─ 渲染进程（index.html）─────────────────────────────┐
│ aiRunScore(pid, code)                                │
│   ├─ provider=mock 或无 Key → aiMockScore 本地评分   │
│   └─ provider=deepseek → aiBuildPrompt 生成消息      │
│        ├─ Electron 环境 → window.electronAI.aiCall   │
│        │    （IPC → 主进程 HTTPS 代理）              │
│        └─ 网页环境 → fetch(baseUrl/chat/completions) │
│   失败重试(≤2次, 退避1s/4s/9s) → 仍失败降级 mock     │
│   aiParseAIJson 解析校验 → aiRecAdd 存储 → 渲染卡片  │
└──────────────────────────────────────────────────────┘
          │ IPC 'ai-score-call'（仅 Electron）
┌─ 主进程（desktop/main.js）───────────────────────────┐
│ https.request POST → Bearer 认证 → 30s 超时          │
│ 返回 choices[0].message.content 字符串               │
└──────────────────────────────────────────────────────┘
```

- Electron 渲染进程 `contextIsolation:true / nodeIntegration:false`，跨域请求必须经主进程代理（`ipcMain.handle('ai-score-call')` + `preload.js` 暴露 `window.electronAI.aiCall`）。
- 纯网页（浏览器直接打开 index.html）走 `fetch` 分支，逻辑相同，受浏览器 CORS 限制。

---

## 3. 配置说明

### 3.1 设置面板（结果卡内「⚙ 评分设置」按钮）

配置持久化在 localStorage：`java-course-ai-cfg-v1`

| 字段 | 默认值 | 说明 |
|---|---|---|
| provider | `mock` | `mock`=本地模拟；`deepseek`=真实 AI 调用 |
| apiKey | 空 | DeepSeek 平台申请的 API Key（`sk-` 开头），仅存本机 |
| baseUrl | `https://api.deepseek.com` | OpenAI 兼容接口地址，可换其他兼容服务 |
| model | `deepseek-chat` | 模型名（OpenAI 兼容服务可改） |
| timeout | 30000 | 单次请求超时毫秒（主进程侧强制 ≥3000） |

### 3.2 DeepSeek Key 申请步骤

1. 访问 https://platform.deepseek.com 注册并登录；
2. 左侧「API Keys」→「创建 API Key」，复制 `sk-...`；
3. 账户充值后即可调用（`deepseek-chat` 按 token 计费，单次评分约 1000–2000 token）；
4. 在课程软件任意章项目评分卡的「⚙ 评分设置」里：供应商选 **DeepSeek**、粘贴 Key、保存。

### 3.3 模拟 / 真实切换

- 供应商切回 `mock` 即恢复本地模拟（不需要 Key，秒出分，结果确定性可复现）。
- DeepSeek 模式下若 Key 为空，保存时会被拦截；已有 Key 但调用失败会**自动降级为模拟评分**（卡片徽标显示「已降级为模拟评分」）。

---

## 4. 调用示例

### 4.1 请求 Payload（OpenAI 兼容 chat/completions）

由 `aiBuildPrompt(pid, ck, code)` 生成，经 `aiCallDeepSeek` 发出：

```json
{
  "model": "deepseek-chat",
  "messages": [
    {
      "role": "system",
      "content": "你是严格而友善的 Java 助教。只输出 JSON，格式：{\"dims\":{\"正确性\":0-30,\"规范性\":0-20,\"结构\":0-20,\"可读性\":0-15,\"习惯\":0-15},\"total\":0-100,\"summary\":\"总评(≤200字)\",\"suggestions\":[\"建议\",...]}。总分≥60为达标…"
    },
    {
      "role": "user",
      "content": "项目：收银台\n目标类名：Cashier\n客观要求：…（来自 PROJECT_CHECKS 的 keywords/minPrints/minComments/minLines）\n主观参考：…\n学生代码：\n```java\n<预处理后的代码>\n```"
    }
  ],
  "response_format": { "type": "json_object" },
  "temperature": 0.2,
  "max_tokens": 1200
}
```

### 4.2 期望响应（AI 返回的 content 即该 JSON）

```json
{
  "dims": { "正确性": 28, "规范性": 18, "结构": 16, "可读性": 13, "习惯": 14 },
  "total": 89,
  "summary": "整体完成度高，收银逻辑正确…",
  "suggestions": ["输入价格前先提示单位（元）", "Scanner 用完建议 sc.close()"]
}
```

解析时：维度缺失直接报错；维度越界按满分钳制；`total` 缺失按维度平均重算；`suggestions` 最多保留 6 条、每条 ≤120 字。

### 4.3 curl 手工验证

```bash
curl https://api.deepseek.com/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $DEEPSEEK_API_KEY" \
  -d '{
    "model": "deepseek-chat",
    "messages": [{"role":"user","content":"只输出JSON：{\"total\":0-100,\"summary\":\"...\"}，评测这段Java代码：public class T { public static void main(String[] a){ System.out.println(1+1); } }"}],
    "response_format": {"type":"json_object"},
    "temperature": 0.2,
    "max_tokens": 1200
  }'
```

---

## 5. 输入预处理（`aiNormalizeCode`）

发送给 AI 前，代码统一做如下清洗，返回 `{text, truncated, chars}`：

1. 去除 BOM 与零宽字符（U+FEFF、U+200B 等）；
2. CRLF → LF 统一换行；
3. 连续 3 个以上空行压缩为 1 个空行；
4. 超 `AI_CODE_MAX`（12000 字符）截断，`truncated=true` 会体现在评分卡追溯行，提示词中也会告知 AI「代码被截断」。

---

## 6. 结果解析、校验与结构化存储

### 6.1 解析校验（`aiParseAIJson`）

- 剥离 ```json 围栏 → 正则抽取第一个 `{...}` → `JSON.parse`；
- 5 个维度逐一校验（缺失即解析失败，触发重试/降级）；
- 数值钳制到各维度满分、总分钳制 0–100（`aiClampScore` 四舍五入）。

### 6.2 存储（localStorage：`java-course-ai-v1`）

```json
{
  "records": {
    "c2s6": {
      "history": [
        {
          "dims": {"正确性":28,"规范性":18,"结构":16,"可读性":13,"习惯":14},
          "total": 89,
          "summary": "…",
          "suggestions": ["…"],
          "provider": "deepseek",   // mock | deepseek
          "degraded": false,        // 真实调用失败降级时为 true
          "ms": 3120,               // 本次耗时
          "model": "deepseek-chat",
          "codeChars": 614,
          "truncated": false,
          "at": 1760000000000,
          "pid": "c2s6",
          "review": {               // 人工复核后追加（可选）
            "total": 75,
            "dims": {"正确性":25,"规范性":15,"结构":15,"可读性":10,"习惯":10},
            "note": "缺少异常处理，扣分",
            "at": 1760000100000
          }
        }
      ]
    }
  }
}
```

每个项目最多保留 **10 条历史**（`aiRecAdd` unshift 后截断），「🕘 评分历史」面板可查看。

---

## 7. 异常处理与降级

| 场景 | 表现 | 处理 |
|---|---|---|
| 网络/超时错误 | 请求异常 | 重试 ≤2 次，退避 1s → 4s → 9s（`attempt²` 秒） |
| HTTP ≥400 | 主进程 reject `HTTP xxx` | 同上重试；401（Key 错）等也会重试后降级 |
| 返回非 JSON / 维度缺失 | 解析失败 | 追加一条「修复消息」让模型重新只输出 JSON，1s 后重试 |
| 全部重试失败 | — | **降级为模拟评分**，`degraded:true`，总评前缀写明失败原因 |
| Electron 主进程超时 | `req.destroy` | 与上同（页面侧统一兜底） |
| 代码 <30 字符 | 按钮点击即拦截 | hlToast 提示「请先粘贴完整代码」，不发起调用 |

> 重试次数为代码常量（2 次），不从配置读取，避免误配导致费用放大。

---

## 8. 人工复核（⚖ 复核校准）

- 入口：每张评分卡右侧「⚖ 复核校准」按钮，弹窗含 5 维分数 + 总分 + 备注；
- AI 原评**永久保留**：卡片总分旁显示「（AI 原评 X）」，历史面板标注「复核 75」；
- 复核记录随该条历史一并存储（`review` 字段），用于校准 AI 与人工标准的偏差；
- 复核不参与解锁闸口，仅作展示与追溯。

---

## 9. 测试

### 9.1 单元测试（27 断言，node 直接跑）

引擎纯函数全部位于 index.html 中 `/* ==AI-ENGINE-START== */` 与 `/* ==AI-ENGINE-END== */` 标记之间，测试脚本用正则提取源码后 `new Function` 注入执行，**不依赖 DOM/Electron**。覆盖：

- 预处理：BOM/零宽清除、CRLF 转换、空行压缩、超长截断；
- 解析：围栏剥离、维度缺失报错、越界钳制、total 重算、suggestions 清洗；
- 模拟评分：好代码 ≥60 且各项分配合理、坏代码低分、类名不匹配扣分、确定性（同输入同输出）；
- 加权正确性：满分代码应接近 100（防止维度权重错加）。

### 9.2 Electron e2e 探针（7 项）

开发期以探针脚本验证（已通过）：按钮注入 ×10、mock 评分卡渲染、好代码 ≥60、DeepSeek 失败降级、人工复核覆盖、历史面板、短代码拦截与按钮状态恢复。

---

## 10. 维护注意事项

1. **标记不可删**：`/* ==AI-ENGINE-START== */` 与 `/* ==AI-ENGINE-END== */` 是单元测试提取源码的锚点，改动引擎时保持标记完整。
2. **新增章项目需三处同步**：页面 `PROJECT_CHECKS` 加客观检查项 → `AI_GOAL_CLASS` 加目标类名 → （若有新页面 ID）无需改引擎，`aiInjectButtons` 自动遍历 `.proj-review` 注入。
3. **改维度/权重**：同时改 `AI_DIMS`、`AI_DIM_MAX` 与 mock 打分逻辑、提示词 system 部分，三处必须一致，否则解析校验会钳制掉新维度。
4. **ipcMain 频道名** `ai-score-call` 与 preload 暴露的 `window.electronAI.aiCall` 成对出现，改名需同步 main.js / preload.js / aiCallDeepSeek 三处。
5. **Key 安全**：Key 只存学生本机 localStorage，不上传；文档/截图勿粘贴真实 Key；若泄露在 DeepSeek 平台立即删除重建。
6. **三份 index.html 副本**：IDE 源（`新建文件夹\java-course-site`）→ learn 仓库（打包源）→ `D:\java学习中心\java-course-site`（本机运行），改动后必须同步并校验 MD5 一致。
7. **发版**：改 `desktop/package.json` 版本号 → 打 `v*` 标签推送，GitHub Actions 约 4 分钟出三平台安装包。
8. **超时调优**：DeepSeek 偶发慢响应，默认 30s；若频繁超时可在设置面板调大到 60000，主进程侧无上限（仅下限 3s）。
