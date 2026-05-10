$ErrorActionPreference = "Stop"

$aar = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\dji-sdk-v5-aircraft-5.17.0.aar"
$tmp  = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\_tmp2"
$projectDir = "D:\Projects\mjfly\app"

# 清理旧提取
if (Test-Path $tmp) { Remove-Item $tmp -Recurse -Force }
New-Item -ItemType Directory -Path $tmp -Force | Out-Null

# Step 1: 将 .aar 复制为 .zip 然后解压
$zipFile = Join-Path $tmp "aircraft.zip"
Copy-Item $aar $zipFile -Force
Expand-Archive -Path $zipFile -DestinationPath $tmp -Force
Write-Host "=== Step 1: AAR 解压完成 ==="
Get-ChildItem $tmp | ForEach-Object { Write-Host "  $($_.Name)" }

# Step 2: 检查是否有嵌套的 dji.zip
$nestedZip = Join-Path $tmp "dji.zip"
if (Test-Path $nestedZip) {
    Write-Host "`n=== 发现嵌套 dji.zip，正在解压 ==="
    $nestedDir = Join-Path $tmp "nested"
    New-Item -ItemType Directory -Path $nestedDir -Force | Out-Null
    # 复制为 zip 再解压
    $nestedZip2 = Join-Path $tmp "nested.zip"
    Copy-Item $nestedZip $nestedZip2 -Force
    Expand-Archive -Path $nestedZip2 -DestinationPath $nestedDir -Force

    # 合并 classes
    $nestedClasses = Join-Path $nestedDir "classes.jar"
    $topClasses = Join-Path $tmp "classes.jar"

    if (Test-Path $nestedClasses) {
        Write-Host "复制嵌套 classes.jar 到 tmp 根目录"
        Copy-Item $nestedClasses $topClasses -Force
    }

    # 合并 jni so
    $nestedJni = Join-Path $nestedDir "jni"
    $topJni = Join-Path $tmp "jni"
    if (Test-Path $nestedJni -and !(Test-Path $topJni)) {
        Write-Host "复制嵌套 jni 到 tmp 根目录"
        Copy-Item $nestedJni $topJni -Recurse -Force
    }
}

# Step 3: 复制 classes.jar 到项目 libs
$libsDir = Join-Path $projectDir "libs"
New-Item -ItemType Directory -Path $libsDir -Force | Out-Null
$classesJar = Join-Path $tmp "classes.jar"
if (Test-Path $classesJar) {
    Copy-Item $classesJar (Join-Path $libsDir "dji-sdk-v5-aircraft-classes.jar") -Force
    Write-Host "`n=== classes.jar 已复制到 libs ==="
    & jar tf $classesJar | Where-Object { $_ -match "SDKManager" }
} else {
    Write-Host "ERROR: classes.jar 不存在！"
}

# Step 4: 复制 .so 文件到 jniLibs
$jniLibsDir = Join-Path $projectDir "src\main\jniLibs\arm64-v8a"
New-Item -ItemType Directory -Path $jniLibsDir -Force | Out-Null
$soSource = Join-Path $tmp "jni\arm64-v8a"
if (Test-Path $soSource) {
    Copy-Item "$soSource\*.so" $jniLibsDir -Force
    $count = (Get-ChildItem $jniLibsDir -Filter "*.so").Count
    Write-Host "`n=== $count 个 .so 文件已复制到 jniLibs/arm64-v8a ==="
    Get-ChildItem $jniLibsDir -Filter "*.so" | ForEach-Object { Write-Host "  $($_.Name)" }
} else {
    Write-Host "ERROR: jni/arm64-v8a 不存在！"
}

Write-Host "`n=== 完成 ==="