$providedJar = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft-provided\5.17.0\88ad88cc0fd52f3dbdf69855f562a161d052420f\dji-sdk-v5-aircraft-provided-5.17.0.jar"
Write-Host "Checking provided JAR..."
Write-Host "Size: $([math]::Round((Get-Item $providedJar).Length/1MB, 2)) MB"

$tmpDir = "C:\Users\14472\AppData\Local\Temp\dji_provided_check"
if (Test-Path $tmpDir) { Remove-Item $tmpDir -Recurse -Force }
New-Item -ItemType Directory -Path $tmpDir -Force | Out-Null

Copy-Item $providedJar (Join-Path $tmpDir "provided.zip")
Expand-Archive -Path (Join-Path $tmpDir "provided.zip") -DestinationPath $tmpDir -Force

Write-Host "`n=== Classes in JAR ==="
& jar tf $providedJar | Where-Object { $_ -match "\.class$" } | ForEach-Object { Write-Host "  $_" }
