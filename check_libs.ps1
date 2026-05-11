$ErrorActionPreference = "Stop"
$aar = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\dji-sdk-v5-aircraft-5.17.0.aar"
$tmp  = "C:\Users\14472\AppData\Local\Temp\djidebug"

Write-Host "=== Checking tmp/libs/ ==="
$libsPath = Join-Path $tmp "libs"
if (Test-Path $libsPath) {
    Get-ChildItem $libsPath -File | ForEach-Object { Write-Host "  $($_.Name) $([math]::Round($_.Length/1MB,2))MB" }
}

Write-Host "`n=== Checking tmp/libs/jars ==="
$jarPath = Get-ChildItem $libsPath -Filter "*.jar"
foreach ($j in $jarPath) {
    Write-Host "`nChecking $($j.Name):"
    & jar tf $j.FullName | Where-Object { $_ -match "SDKManager" }
}
