#!/usr/bin/env bash
# =====================================================================
#  Java 学习中心 · 一键环境搭建脚本（macOS / Linux 版）
#  功能：检测系统 → 安装 Git/JDK/VS Code → 克隆项目 → 路径适配 → 验证
#  用法：chmod +x setup-mac-linux.sh && ./setup-mac-linux.sh
# =====================================================================
set -euo pipefail

# ─────────────── 全局变量 ───────────────
LOG_DIR="$HOME/java-setup-logs"
TIMESTAMP=$(date +%Y%m%d-%H%M%S)
LOG_FILE="$LOG_DIR/setup-$TIMESTAMP.log"
REPO_URL="https://github.com/Shao-qichao/learn.git"
CLONE_DIR="$HOME/java学习中心"
JDK_VERSION="11"        # 可改为 17 / 21
GIT_USER=""
GIT_EMAIL=""
BACKUP_DIR="$LOG_DIR/backups-$TIMESTAMP"
ORIGINAL_USER="13053"   # index.html 中写死的 Windows 用户名

# ─────────────── 工具函数 ───────────────
log()      { local lvl="${3:-INFO}"; local msg="[$(date +%H:%M:%S)] [$lvl] $1"; echo "$msg"; echo "$msg" >> "$LOG_FILE"; }
step()     { echo ""; echo "========== $1 =========="; }
ok()       { echo "  [OK] $1"; echo "[$(date +%H:%M:%S)] [INFO] [OK] $1" >> "$LOG_FILE"; }
warn()     { echo "  [!]  $1" | tee -a "$LOG_FILE"; }
err()      { echo "  [X]  $1" | tee -a "$LOG_FILE"; }

has_cmd()  { command -v "$1" &>/dev/null; }

confirm() {
    echo ""
    echo "  $1" | tee -a "$LOG_FILE"
    read -p "  确认继续？(y/N) " reply
    [[ "$reply" =~ ^[Yy]$ ]]
}

# ─────────────── 1. 系统检测 ───────────────
system_check() {
    step "1/6  系统环境检测"

    local os_name os_arch

    if [[ "$OSTYPE" == "darwin"* ]]; then
        os_name="macOS $(sw_vers -productVersion)"
        os_name_human="macOS"
    elif [[ -f /etc/os-release ]]; then
        . /etc/os-release
        os_name="$PRETTY_NAME"
        os_name_human="Linux"
    else
        os_name="$OSTYPE"
        os_name_human="Unknown"
    fi

    os_arch=$(uname -m)
    log "操作系统: $os_name"
    ok "$os_name ($os_arch)"

    # 磁盘空间
    local home_free
    home_free=$(df -m "$HOME" 2>/dev/null | awk 'NR==2{print $4}')
    if [[ -n "$home_free" ]] && (( home_free < 500 )); then
        warn "可用空间不足 500MB（当前 ${home_free}MB）"
    else
        ok "可用空间: ${home_free:-未知}MB"
    fi

    # 网络连接
    log "检测网络连接..."
    if curl -sI --connect-timeout 10 "https://github.com" &>/dev/null; then
        ok "网络连接正常"
    else
        warn "GitHub 直连失败，请检查网络或代理设置"
        if [[ -n "$http_proxy" || -n "$HTTP_PROXY" || -n "$https_proxy" || -n "$HTTPS_PROXY" ]]; then
            ok "检测到代理: ${http_proxy:-$https_proxy:-$HTTP_PROXY:-$HTTPS_PROXY}"
        else
            err "无法连接 GitHub，请开启 VPN/代理后重试"
            return 1
        fi
    fi

    return 0
}

# ─────────────── 2. 软件安装 ───────────────
install_software() {
    step "2/6  软件安装检测与部署"

    # --- Git ---
    log "检测 Git..."
    if has_cmd git; then
        ok "Git 已安装: $(git --version)"
    else
        warn "Git 未安装，正在安装..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            if has_cmd brew; then
                brew install git 2>&1 | tee -a "$LOG_FILE"
            elif ! has_cmd xcode-select; then
                xcode-select --install 2>&1 | tee -a "$LOG_FILE"
                warn "请等待 Xcode Command Line Tools 安装完成后重新运行此脚本"
                return 1
            else
                git 2>/dev/null || { err "Git 安装失败，请手动安装"; return 1; }
            fi
        else
            # Linux: 检测包管理器
            if has_cmd apt-get; then
                sudo apt-get update -y && sudo apt-get install -y git 2>&1 | tee -a "$LOG_FILE"
            elif has_cmd yum; then
                sudo yum install -y git 2>&1 | tee -a "$LOG_FILE"
            elif has_cmd dnf; then
                sudo dnf install -y git 2>&1 | tee -a "$LOG_FILE"
            elif has_cmd pacman; then
                sudo pacman -S --noconfirm git 2>&1 | tee -a "$LOG_FILE"
            else
                err "无法自动安装 Git，请手动安装"
                return 1
            fi
        fi
        has_cmd git && ok "Git 安装成功: $(git --version)" || { err "Git 安装失败"; return 1; }
    fi

    # --- JDK ---
    log "检测 JDK..."
    if has_cmd java; then
        ok "JDK 已安装: $(java -version 2>&1 | head -1)"
    else
        warn "JDK 未安装，正在安装 JDK $JDK_VERSION ..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            brew install "openjdk@$JDK_VERSION" 2>&1 | tee -a "$LOG_FILE"
            brew link --force "openjdk@$JDK_VERSION" 2>&1 | tee -a "$LOG_FILE" || true
        else
            if has_cmd apt-get; then
                sudo apt-get install -y "openjdk-$JDK_VERSION-jdk" 2>&1 | tee -a "$LOG_FILE"
            elif has_cmd yum; then
                sudo yum install -y "java-$JDK_VERSION-openjdk-devel" 2>&1 | tee -a "$LOG_FILE"
            elif has_cmd dnf; then
                sudo dnf install -y "java-$JDK_VERSION-openjdk-devel" 2>&1 | tee -a "$LOG_FILE"
            elif has_cmd pacman; then
                sudo pacman -S --noconfirm "jdk-$JDK_VERSION-openjdk" 2>&1 | tee -a "$LOG_FILE"
            else
                err "无法自动安装 JDK，请手动安装: https://adoptium.net/"
                return 1
            fi
        fi
        has_cmd java && ok "JDK 安装成功: $(java -version 2>&1 | head -1)" || { err "JDK 安装失败"; return 1; }
    fi

    # --- VS Code ---
    log "检测 VS Code..."
    if has_cmd code; then
        ok "VS Code 已安装"
    else
        warn "VS Code 未安装，正在安装..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            if has_cmd brew; then
                brew install --cask visual-studio-code 2>&1 | tee -a "$LOG_FILE" || warn "VS Code 安装失败，请手动安装: https://code.visualstudio.com/"
            else
                warn "请先安装 Homebrew (https://brew.sh) 再安装 VS Code"
            fi
        else
            # Linux: 尝试 snap 或包管理器
            if has_cmd snap; then
                sudo snap install code --classic 2>&1 | tee -a "$LOG_FILE" || warn "VS Code 安装失败，请手动安装"
            elif has_cmd apt-get; then
                wget -qO- "https://packages.microsoft.com/keys/microsoft.asc" | gpg --dearmor > /tmp/packages.microsoft.gpg 2>/dev/null
                sudo install -o root -g root -m 644 /tmp/packages.microsoft.gpg /etc/apt/keyrings/ 2>/dev/null || true
                echo "deb [arch=amd64 signed-by=/etc/apt/keyrings/packages.microsoft.gpg] https://packages.microsoft.com/repos/code stable main" | sudo tee /etc/apt/sources.list.d/vscode.list 2>/dev/null || true
                sudo apt-get update -y && sudo apt-get install -y code 2>&1 | tee -a "$LOG_FILE" || warn "VS Code 安装失败"
            else
                warn "无法自动安装 VS Code，请手动安装: https://code.visualstudio.com/"
            fi
        fi
        has_cmd code && ok "VS Code 安装成功" || warn "VS Code 可能需要重启终端才能生效"
    fi

    return 0
}

# ─────────────── 3. Git 配置 ───────────────
git_config() {
    step "3/6  Git 用户信息配置"

    local existing_name existing_email
    existing_name=$(git config --global user.name 2>/dev/null || echo "")
    existing_email=$(git config --global user.email 2>/dev/null || echo "")

    if [[ -n "$existing_name" ]]; then
        ok "当前 Git 用户名: $existing_name"
        if ! confirm "是否修改？"; then GIT_USER="$existing_name"; fi
    fi
    if [[ -z "$GIT_USER" ]]; then
        read -p "  请输入 Git 用户名: " GIT_USER
    fi

    if [[ -n "$existing_email" ]]; then
        ok "当前 Git 邮箱: $existing_email"
        if ! confirm "是否修改？"; then GIT_EMAIL="$existing_email"; fi
    fi
    if [[ -z "$GIT_EMAIL" ]]; then
        read -p "  请输入 Git 邮箱: " GIT_EMAIL
    fi

    git config --global user.name  "$GIT_USER"
    git config --global user.email "$GIT_EMAIL"
    log "已配置 Git: name=$GIT_USER email=$GIT_EMAIL"
    ok "Git 用户名: $GIT_USER"
    ok "Git 邮箱:   $GIT_EMAIL"
    git config --global core.autocrlf false  # macOS/Linux 不做行尾转换
    ok "Git 行尾设置: autocrlf=false"
}

# ─────────────── 4. 项目克隆 ───────────────
clone_project() {
    step "4/6  克隆项目仓库"

    if [[ -d "$CLONE_DIR/.git" ]]; then
        ok "项目已存在: $CLONE_DIR"
        if confirm "是否拉取最新代码？"; then
            cd "$CLONE_DIR" && git pull origin main 2>&1 | tee -a "$LOG_FILE" || true
        fi
    else
        log "开始克隆: $REPO_URL -> $CLONE_DIR"
        git clone "$REPO_URL" "$CLONE_DIR" 2>&1 | tee -a "$LOG_FILE"
        [[ -d "$CLONE_DIR/.git" ]] && ok "克隆成功: $CLONE_DIR" || { err "克隆失败"; return 1; }
    fi

    # 创建 JavaLearning 目录
    local jl="$HOME/JavaLearning"
    if [[ ! -d "$jl" ]]; then
        mkdir -p "$jl"
        ok "已创建 Java 学习目录: $jl"
    else
        ok "Java 学习目录已存在: $jl"
    fi
    log "JavaLearning 路径: $jl"
    return 0
}

# ─────────────── 5. 路径适配 ───────────────
patch_paths() {
    step "5/6  路径适配（macOS/Linux 适配）"

    local html_path="$CLONE_DIR/java-course-site/index.html"
    if [[ ! -f "$html_path" ]]; then
        err "index.html 未找到: $html_path"
        return 1
    fi

    # 备份
    mkdir -p "$BACKUP_DIR"
    cp "$html_path" "$BACKUP_DIR/index.html.bak"
    log "已备份: $html_path -> $BACKUP_DIR/index.html.bak"
    ok "原文件已备份"

    # macOS/Linux 路径与 Windows 完全不同
    # 练习文件夹在 $CLONE_DIR/javacourse，需要把 Windows 路径替换为本地路径
    local practice_dir="$CLONE_DIR/javacourse"
    local jl_dir="$HOME/JavaLearning"

    # 读取文件
    local content
    content=$(cat "$html_path")

    # 替换练习文件夹路径（JSON 中的双反斜杠格式）
    # D:\\java学习中心\\javacourse → 本地路径（JSON 转义格式）
    local practice_json_old='D:\\java学习中心\\javacourse'
    local practice_json_new
    practice_json_new=$(echo "$practice_dir" | sed 's/\\/\\\\/g')
    local count
    count=$(echo "$content" | grep -o "$practice_json_old" | wc -l || echo 0)
    if (( count > 0 )); then
        content=$(echo "$content" | sed "s|$practice_json_old|$practice_json_new|g")
        log "替换练习路径(JSON): $count 处"
        ok "练习路径(JSON): 替换 $count 处"
    fi

    # 替换 JavaLearning 路径（各种格式）
    local patterns=(
        "c:\\Users\\$ORIGINAL_USER\\JavaLearning"
        "c:/Users/$ORIGINAL_USER/JavaLearning"
        "c%3A/Users/$ORIGINAL_USER/JavaLearning"
        "c:\\\\Users\\\\$ORIGINAL_USER\\\\JavaLearning"
    )
    local replacements=()
    for i in "${!patterns[@]}"; do
        replacements+=("$jl_dir")
    done

    local jl_json_old="c:\\\\Users\\\\$ORIGINAL_USER\\\\JavaLearning"
    local jl_json_new
    jl_json_new=$(echo "$jl_dir" | sed 's/\\/\\\\/g')

    total=0
    # 简单替换：把所有包含 13053\JavaLearning 的路径替换为本地路径
    content=$(echo "$content" | sed "s|c:\\\\Users\\\\$ORIGINAL_USER\\\\JavaLearning|$jl_json_new|g" || true)
    content=$(echo "$content" | sed "s|c:/Users/$ORIGINAL_USER/JavaLearning|$jl_dir|g" || true)
    content=$(echo "$content" | sed "s|c%3A/Users/$ORIGINAL_USER/JavaLearning|$(echo "$jl_dir" | sed 's|/|%2F|g; s|:|%3A|g')|g" || true)

    # Windows 练习路径的单反斜杠格式也替换
    content=$(echo "$content" | sed "s|D:\\\\java学习中心\\\\javacourse|$practice_json_new|g" || true)

    # 写回文件
    echo "$content" > "$html_path"

    ok "路径已适配为: $practice_dir"
    ok "JavaLearning: $jl_dir"

    # 生成报告
    local report="$BACKUP_DIR/replace-report.txt"
    {
        echo "Java 学习中心 · 路径替换报告"
        echo "时间: $(date)"
        echo "原始平台: Windows (用户 $ORIGINAL_USER)"
        echo "当前平台: $(uname -s) (用户 $USER)"
        echo "练习路径: $practice_dir"
        echo "JavaLearning: $jl_dir"
        echo "备份文件: $BACKUP_DIR/index.html.bak"
    } > "$report"
    ok "替换报告: $report"
    return 0
}

# ─────────────── 6. 验证 ───────────────
verify() {
    step "6/6  验证测试"
    local all_ok=true

    has_cmd git  && ok "Git:        $(git --version)" || { err "Git 不可用"; all_ok=false; }
    has_cmd java && ok "JDK:        $(java -version 2>&1 | head -1)" || { err "JDK 不可用"; all_ok=false; }
    has_cmd code && ok "VS Code:    可用" || warn "VS Code: 未检测到（可能需要重启终端）"

    local html_path="$CLONE_DIR/java-course-site/index.html"
    [[ -f "$html_path" ]] && ok "index.html: 存在" || { err "index.html 缺失"; all_ok=false; }

    local practice_dir="$CLONE_DIR/javacourse"
    if [[ -d "$practice_dir" ]]; then
        local dcount
        dcount=$(find "$practice_dir" -maxdepth 1 -type d | tail -n +2 | wc -l)
        ok "练习文件夹: $dcount 个"
    else
        err "练习文件夹缺失"; all_ok=false
    fi

    [[ -d "$HOME/JavaLearning" ]] && ok "JavaLearning: 已创建" || { err "JavaLearning 缺失"; all_ok=false; }

    local gn ge
    gn=$(git config --global user.name 2>/dev/null || echo "")
    ge=$(git config --global user.email 2>/dev/null || echo "")
    [[ -n "$gn" && -n "$ge" ]] && ok "Git 用户:   $gn <$ge>" || warn "Git 用户信息未配置"

    local old_count
    old_count=$(grep -c "Users\\\\$ORIGINAL_USER" "$html_path" 2>/dev/null || echo 0)
    if [[ "$old_count" -eq 0 ]]; then
        ok "路径替换:   无旧路径残留"
    else
        warn "路径替换:   仍有 $old_count 处旧路径"
    fi

    [[ "$all_ok" == "true" ]]
}

# ─────────────── 主流程 ───────────────
mkdir -p "$LOG_DIR"

echo "╔══════════════════════════════════════════════════╗"
echo "║  Java 学习中心 · 一键环境搭建（macOS/Linux）     ║"
echo "║  自动安装 Git + JDK + VS Code                    ║"
echo "║  自动克隆项目 + 适配本地路径                      ║"
echo "╚══════════════════════════════════════════════════╝"

log "===== 脚本启动 ====="

if ! confirm "本脚本将安装 Git、JDK $JDK_VERSION、VS Code，并克隆项目到 $CLONE_DIR
继续？"; then
    echo "  已取消。"
    exit 0
fi

system_check   || { err "系统检测未通过"; log "终止：系统检测失败"; exit 1; }
install_software || { err "软件安装失败"; log "终止：软件安装失败"; exit 1; }
git_config
clone_project  || { err "项目克隆失败"; log "终止：克隆失败"; exit 1; }
patch_paths    || { err "路径适配失败"; log "终止：路径替换失败"; }
verify_ok=verify

step "完成"
if $verify_ok; then
    echo "
  全部就绪！
  项目路径:   $CLONE_DIR
  网页入口:   $CLONE_DIR/java-course-site/index.html
  练习代码:   $HOME/JavaLearning
  日志文件:   $LOG_FILE

  在浏览器中打开 index.html 即可开始学习！
"
else
    echo "  部分检查未通过，详见日志: $LOG_FILE"
fi
log "===== 脚本结束 ====="
