$ErrorActionPreference = "Stop"
$tmp  = "C:\Users\14472\AppData\Local\Temp\djidebug"
Write-Host "=== Checking tmp/ ==="
Get-ChildItem $tmp -Recurse -File | ForEach-Object { 
    $rel = $_.FullName.Substring($tmp.Length)
    $sizeMB = [math]::Round($_.Length/1MB, 2)
    Write-Host "  $rel ($sizeMB MB)"
}
