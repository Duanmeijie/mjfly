$ErrorActionPreference = "Stop"
$aar = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\dji-sdk-v5-aircraft-5.17.0.aar"
$tmp  = "C:\Users\14472\AppData\Local\Temp\djidebug"

if (Test-Path $tmp) { Remove-Item $tmp -Recurse -Force }
New-Item -ItemType Directory -Path $tmp -Force | Out-Null

# Extract AAR
Copy-Item $aar (Join-Path $tmp "aar.zip")
Expand-Archive -Path (Join-Path $tmp "aar.zip") -DestinationPath $tmp -Force
Write-Host "=== AAR Root Files ==="
Get-ChildItem $tmp -File | ForEach-Object { Write-Host "  $($_.Name) $([math]::Round($_.Length/1MB,2))MB" }
Get-ChildItem $tmp -Directory | ForEach-Object { Write-Host "  [DIR] $($_.Name)" }

Write-Host "`n=== Looking for possible zip files inside ==="
Get-ChildItem $tmp -File | Where-Object { $_.Extension -eq ".zip" } | ForEach-Object {
    $zipFile = $_.FullName
    Write-Host "`n==== $($_.Name) contents ===="
    & jar tf $zipFile | Select-Object -First 50
}

Write-Host "`n=== Done ==="
