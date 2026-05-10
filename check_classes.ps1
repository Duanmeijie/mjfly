$classesJar = "D:\Projects\mjfly\app\libs\dji-sdk-v5-aircraft-classes.jar"

Write-Host "=== classes.jar 中 dji/v5/ 的所有内容 ==="
& jar tf $classesJar | Where-Object { $_ -match "^dji/v5/" } | ForEach-Object { Write-Host $_ }

Write-Host ""
Write-Host "=== classes.jar 中 dji/ 的顶层包结构 ==="
& jar tf $classesJar | Where-Object { $_ -match "^dji/[^/]+/" } | Select-Object -Unique | ForEach-Object { Write-Host $_ }