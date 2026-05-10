$aar = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\dji-sdk-v5-aircraft-5.17.0.aar"
$tmp  = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\_tmp"
$nestedZip = "$tmp\dji.zip"

# 提取嵌套 dji.zip
$extractDir = "$tmp\extracted"
New-Item -ItemType Directory -Path $extractDir -Force | Out-Null
Expand-Archive -Path $nestedZip -DestinationPath $extractDir -Force

# 输出提取后的顶层结构
Write-Host "=== 提取后的顶层结构 ==="
Get-ChildItem $extractDir | ForEach-Object { Write-Host $_.Name }

# 复制 classes.jar
$classesJar = "$extractDir\classes.jar"
if (Test-Path $classesJar) {
    Copy-Item $classesJar "D:\Projects\mjfly\app\libs\dji-sdk-v5-aircraft-classes.jar" -Force
    Write-Host "已复制 classes.jar 到 libs 目录"
}

# 创建 jniLibs 目录并复制 so 文件
$arm64Dir = "$extractDir\jni\arm64-v8a"
if (Test-Path $arm64Dir) {
    $jniTarget = "D:\Projects\mjfly\app\src\main\jniLibs\arm64-v8a"
    New-Item -ItemType Directory -Path $jniTarget -Force | Out-Null
    Copy-Item "$arm64Dir\*.so" $jniTarget -Force
    $soFiles = Get-ChildItem $jniTarget -Filter "*.so"
    Write-Host "已复制 $($soFiles.Count) 个 so 文件到 jniLibs/arm64-v8a:"
    $soFiles | ForEach-Object { Write-Host "  $($_.Name)" }
}

# 也检查 classes.jar 中是否有 SDKManager
Write-Host ""
Write-Host "=== classes.jar 中 SDKManager 相关类 ==="
& jar tf $classesJar | Where-Object { $_ -match "SDKManager" } | ForEach-Object { Write-Host $_ }

Write-Host ""
Write-Host "=== 完成 ==="