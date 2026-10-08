<#
.SYNOPSIS
  Java Learning Center - One-way folder sync (D drive -> working copy)
.DESCRIPTION
  Incrementally syncs D:\java学习中心\java-course-site\ (source of truth)
  to d:\AI小游戏\新建文件夹\java-course-site\ (IDE working copy).
  Preserves target-only files (like .bak backups).
  Source path is hardcoded so the script works correctly no matter
  which copy (D drive or d drive) you launch it from.
.PARAMETER DryRun
  Preview only, do not copy anything.
.PARAMETER OpenLog
  Open the log file in notepad after completion.
.EXAMPLE
  .\sync-folders.ps1
  Perform incremental sync
.EXAMPLE
  .\sync-folders.ps1 -DryRun
  Preview without copying
#>
param(
  [switch]$DryRun,
  [switch]$OpenLog,
  [string]$Source = "D:\java学习中心\java-course-site",
  [string]$Target = "d:\AI小游戏\新建文件夹\java-course-site"
)

$ErrorActionPreference = "Stop"

try {
  [Console]::OutputEncoding = [System.Text.Encoding]::UTF8
  $OutputEncoding           = [System.Text.Encoding]::UTF8
} catch {}

# ---------- Pre-checks ----------
if (-not (Test-Path -LiteralPath $Source)) {
  Write-Host "[FAIL] Source dir does not exist: $Source" -ForegroundColor Red
  Write-Host "       Please confirm the project is deployed to D:\java学习中心" -ForegroundColor Yellow
  exit 1
}

if (-not (Test-Path -LiteralPath $Target)) {
  try {
    New-Item -ItemType Directory -Path $Target -Force | Out-Null
    Write-Host "[OK]   Created target dir: $Target" -ForegroundColor Green
  } catch {
    Write-Host "[FAIL] Cannot create target dir: $Target" -ForegroundColor Red
    exit 1
  }
}

# Refuse to sync if source == target (would happen if someone edits defaults)
if ((Resolve-Path -LiteralPath $Source).Path -eq (Resolve-Path -LiteralPath $Target).Path) {
  Write-Host "[FAIL] Source and Target resolve to the same path:" -ForegroundColor Red
  Write-Host "       $Source" -ForegroundColor Yellow
  exit 1
}

# ---------- Log dir ----------
$LogDir = Join-Path $env:USERPROFILE ".java-course-sync-logs"
if (-not (Test-Path $LogDir)) {
  New-Item -ItemType Directory -Path $LogDir -Force | Out-Null
}
$timestamp = Get-Date -Format "yyyyMMdd-HHmmss"
$logFile   = Join-Path $LogDir "sync-$timestamp.log"

# ---------- Sync strategy ----------
# /E      copy all subdirs (incl empty), do NOT delete target-only files
# /XO     only copy files newer in source than target (incremental)
# /XX     exclude target-extra files (preserve .bak etc.)
# /XF     exclude file name patterns
# /R:2    retry 2 times
# /W:3    wait 3s between retries
# /NP /NDL no progress percent, no dir list (cleaner log)
# /TEE    output to console AND log
$excludeFiles = @("*.bak-*", "*.bak", "*.tmp", "Thumbs.db", ".DS_Store")

$robocopyArgs = @(
  $Source, $Target,
  "/E", "/XO", "/XX",
  "/R:2", "/W:3",
  "/NP", "/NDL", "/TEE",
  "/LOG:$logFile"
)

foreach ($pat in $excludeFiles) {
  $robocopyArgs += "/XF"
  $robocopyArgs += $pat
}

if ($DryRun) {
  $robocopyArgs += "/L"   # list only, no copy
  $robocopyArgs += "/X"   # report all files (even same)
}

# ---------- Banner ----------
Write-Host ""
Write-Host "===========================================================" -ForegroundColor Cyan
Write-Host "  Java Learning Center - One-way Sync" -ForegroundColor Cyan
Write-Host "===========================================================" -ForegroundColor Cyan
Write-Host "  Source (truth): $Source"
Write-Host "  Target (copy) : $Target"
Write-Host "  Log file      : $logFile"
Write-Host "  Excluded      : $($excludeFiles -join ', ')"
if ($DryRun) {
  Write-Host "  Mode          : [PREVIEW] no files will be copied" -ForegroundColor Yellow
} else {
  Write-Host "  Mode          : [SYNC] actual copy" -ForegroundColor Green
}
Write-Host "===========================================================" -ForegroundColor Cyan
Write-Host ""

# ---------- Run robocopy ----------
& robocopy @robocopyArgs
$rc = $LASTEXITCODE

Write-Host ""
Write-Host "===========================================================" -ForegroundColor Cyan

if ($rc -lt 8) {
  Write-Host "[OK]   Sync succeeded (robocopy exit code: $rc)" -ForegroundColor Green
  switch ($rc) {
    0 { Write-Host "       No files needed copying - target already up to date" -ForegroundColor Gray }
    1 { Write-Host "       Copied some files successfully" -ForegroundColor Gray }
    2 { Write-Host "       Target has extra files (preserved)" -ForegroundColor Gray }
    3 { Write-Host "       Copied + extra files in target" -ForegroundColor Gray }
    4 { Write-Host "       Some mismatched files" -ForegroundColor Gray }
    5 { Write-Host "       Copied + mismatches" -ForegroundColor Gray }
    6 { Write-Host "       Extra files + mismatches" -ForegroundColor Gray }
    7 { Write-Host "       Copied + extra + mismatches" -ForegroundColor Gray }
    default { Write-Host "       Exit code $rc" -ForegroundColor Gray }
  }
} else {
  Write-Host "[FAIL] Sync failed (robocopy exit code: $rc)" -ForegroundColor Red
  Write-Host "       See log: $logFile" -ForegroundColor Yellow
  if ($OpenLog) { Start-Process notepad.exe $logFile }
  exit $rc
}

# ---------- MD5 verification ----------
Write-Host ""
Write-Host "---- MD5 checksum of key files ----" -ForegroundColor Cyan

$checkFiles = @("index.html", "java-center.ico", "tools\sync-folders.ps1")
$allMatch = $true
foreach ($f in $checkFiles) {
  $s = Join-Path $Source $f
  $t = Join-Path $Target $f
  if ((Test-Path -LiteralPath $s) -and (Test-Path -LiteralPath $t)) {
    try {
      $sh = (Get-FileHash -LiteralPath $s -Algorithm MD5).Hash
      $th = (Get-FileHash -LiteralPath $t -Algorithm MD5).Hash
      if ($sh -eq $th) {
        Write-Host ("  [OK]   {0,-28} match (MD5: {1})" -f $f, $sh.Substring(0,12)) -ForegroundColor Green
      } else {
        Write-Host ("  [FAIL] {0,-28} MISMATCH!" -f $f) -ForegroundColor Red
        Write-Host "         Source : $sh" -ForegroundColor Yellow
        Write-Host "         Target : $th" -ForegroundColor Yellow
        $allMatch = $false
      }
    } catch {
      Write-Host ("  [WARN] {0,-28} MD5 error: {1}" -f $f, $_.Exception.Message) -ForegroundColor Yellow
    }
  } elseif (Test-Path -LiteralPath $s) {
    Write-Host ("  [WARN] {0,-28} missing in target" -f $f) -ForegroundColor Yellow
    if (-not $DryRun) { $allMatch = $false }
  }
}

Write-Host ""
Write-Host "===========================================================" -ForegroundColor Cyan
if ($DryRun) {
  Write-Host "[INFO] Preview mode - no files were actually copied." -ForegroundColor Yellow
  Write-Host "       Drop -DryRun to perform the real sync."
} elseif ($allMatch) {
  Write-Host "[DONE] Sync complete, all key files match." -ForegroundColor Green
} else {
  Write-Host "[WARN] Sync done but some MD5 mismatches - see above." -ForegroundColor Yellow
}
Write-Host ""
Write-Host "Log : $logFile" -ForegroundColor Gray
Write-Host "===========================================================" -ForegroundColor Cyan

if ($OpenLog) { Start-Process notepad.exe $logFile }

if ($rc -lt 8 -and $allMatch) { exit 0 }
if ($rc -lt 8) { exit 2 }
exit $rc