$provided = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft-provided\5.17.0\88ad88cc0fd52f3dbdf69855f562a161d052420f\dji-sdk-v5-aircraft-provided-5.17.0.jar"

Write-Host "=== provided jar 中 dji/v5/ 的所有内容 ==="
& jar tf $provided | Where-Object { $_ -match "^dji/v5/" } | ForEach-Object { Write-Host $_ }

Write-Host ""
Write-Host "=== provided jar 中所有顶层包 ==="
& jar tf $provided | Where-Object { $_ -match "^dji/[^/]+/" } | Select-Object -Unique | ForEach-Object { Write-Host $_ }

Write-Host ""
Write-Host "=== SDKManager 类 ==="
& jar tf $provided | Where-Object { $_ -match "SDKManager" } | ForEach-Object { Write-Host $_ }

Write-Host ""
Write-Host "=== 是否存在 dji/v5/ 开头的类 ==="
$djiV5Count = (& jar tf $provided | Where-Object { $_ -match "^dji/v5/" }).Count
Write-Host "dji/v5/ 类数量: $djiV5Count"

Write-Host ""
Write-Host "=== provided jar 文件大小 ==="
$file = Get-Item $provided
Write-Host "$($file.Name): $([math]::Round($file.Length / 1MB, 2)) MB"