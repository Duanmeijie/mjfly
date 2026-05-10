$aar = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\dji-sdk-v5-aircraft-5.17.0.aar"
$tmp  = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\_tmp"
$nestedZip = "$tmp\dji.zip"
$extractDir = "$tmp\extracted"

Write-Host "=== 解压后的 dji.zip 顶层结构 ==="
Get-ChildItem $extractDir | ForEach-Object { Write-Host "$($_.Name) $(if($_.PSIsContainer){'[DIR]'}else{'[FILE]'})" }

Write-Host ""
Write-Host "=== libs/ 目录内容 ==="
$libsDir = "$extractDir\libs"
if (Test-Path $libsDir) {
    Get-ChildItem $libsDir -Recurse | ForEach-Object { Write-Host $_.FullName }

    Write-Host ""
    Write-Host "=== libs/ 下 jar 中的 dji/v5/SDKManager ==="
    Get-ChildItem $libsDir -Filter "*.jar" -Recurse | ForEach-Object {
        Write-Host "--- $($_.Name) ---"
        & jar tf $_.FullName | Where-Object { $_ -match "SDKManager" } | ForEach-Object { Write-Host "  $_" }
    }
} else {
    Write-Host "libs/ 目录不存在"
}

Write-Host ""
Write-Host "=== classes.jar 中 dji/ 的顶层包（前50行） ==="
$clsJar = "$extractDir\classes.jar"
if (Test-Path $clsJar) {
    & jar tf $clsJar | Where-Object { $_ -match "^dji/" } | Select-Object -First 50 | ForEach-Object { Write-Host $_ }
}
Write-Host ""
Write-Host "=== classes.jar 总大小 ==="
$fi = Get-Item $clsJar
Write-Host "$($fi.Name): $([math]::Round($fi.Length/1KB,1)) KB"