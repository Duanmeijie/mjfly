$ErrorActionPreference = "Stop"

$aar = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\dji-sdk-v5-aircraft-5.17.0.aar"
$tmp  = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\_tmp4"
$projectDir = "D:\Projects\mjfly\app"

# Cleanup
if (Test-Path $tmp) { Remove-Item $tmp -Recurse -Force }
New-Item -ItemType Directory -Path $tmp -Force | Out-Null

# Step 1: Extract AAR (rename to .zip first)
$zipFile = Join-Path $tmp "outer_aircraft.zip"
Copy-Item $aar $zipFile -Force
Expand-Archive -Path $zipFile -DestinationPath $tmp -Force
Write-Host "=== Outer AAR Contents ==="
Get-ChildItem $tmp -File | ForEach-Object { Write-Host "  $($_.Name) $([math]::Round($_.Length/1KB,1))KB" }

# Step 2: Find and extract nested zip (可能叫 aircraft.zip 或 dji.zip)
$possibleNestedNames = @("aircraft.zip", "dji.zip")
$nestedZip = $null
foreach ($name in $possibleNestedNames) {
    $testPath = Join-Path $tmp $name
    if (Test-Path $testPath) {
        $nestedZip = $testPath
        break
    }
}

if ($null -ne $nestedZip) {
    $nestedDir = Join-Path $tmp "nested_dji"
    Write-Host "`n=== Found nested zip: $([System.IO.Path]::GetFileName($nestedZip)) ($([math]::Round((Get-Item $nestedZip).Length/1MB,2))MB), extracting ==="
    New-Item -ItemType Directory -Path $nestedDir -Force | Out-Null
    Expand-Archive -Path $nestedZip -DestinationPath $nestedDir -Force
    $entries = @(Get-ChildItem $nestedDir)
    Write-Host "Nested zip root contents: $($entries.Count) items"
    $entries | ForEach-Object { Write-Host "  $($_.Name)" }

    # Copy nested classes.jar
    $nestedClasses = Join-Path $nestedDir "classes.jar"
    $rootClasses = Join-Path $tmp "classes.jar"
    if (Test-Path $nestedClasses) {
        Write-Host "`nnested classes.jar size: $([math]::Round((Get-Item $nestedClasses).Length/1MB,2))MB"
        & jar tf $nestedClasses | Where-Object { $_ -match "SDKManager" } | ForEach-Object { Write-Host "  FOUND: $_" }
        Copy-Item $nestedClasses $rootClasses -Force
        Write-Host "Overwrote classes.jar"
    }

    # Copy jni
    $nestedJni = Join-Path $nestedDir "jni"
    $rootJni = Join-Path $tmp "jni"
    if (Test-Path $nestedJni -and !(Test-Path $rootJni)) {
        Write-Host "`nCopying jni"
        Copy-Item $nestedJni $rootJni -Recurse -Force
    }
}

# Step 3: Verify final classes.jar
$finalJar = Join-Path $tmp "classes.jar"
Write-Host "`n=== Final classes.jar Verification ==="
Write-Host "Size: $([math]::Round((Get-Item $finalJar).Length/1MB,2))MB"
Write-Host "SDKManager class check:"
& jar tf $finalJar | Where-Object { $_ -match "SDKManager" } | ForEach-Object { Write-Host "  $_" }

# Step 4: Copy to project
$libsDir = Join-Path $projectDir "libs"
New-Item -ItemType Directory -Path $libsDir -Force | Out-Null
Copy-Item $finalJar (Join-Path $libsDir "dji-sdk-v5-aircraft-classes.jar") -Force
Write-Host "`n=== Copied to app/libs/ ==="

# Step 5: Copy .so files
$jniLibsDir = Join-Path $projectDir "src\main\jniLibs\arm64-v8a"
New-Item -ItemType Directory -Path $jniLibsDir -Force | Out-Null
$soSource = Join-Path $tmp "jni\arm64-v8a"
if (Test-Path $soSource) {
    Copy-Item "$soSource\*.so" $jniLibsDir -Force
    $count = (Get-ChildItem $jniLibsDir -Filter "*.so").Count
    Write-Host "`n=== $count .so files copied to jniLibs/arm64-v8a ==="
}

Write-Host "`n=== All Done ==="
