$ErrorActionPreference = "Stop"
$tmp  = "C:\Users\14472\AppData\Local\Temp\djidebug"
$projectDir = "D:\Projects\mjfly\app"

# Find the classes.jar in Gradle cache that has SDKManager
# Let's look at the provided AAR maybe?
$providedAar = "C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft-provided\5.17.0\bcfb09194684460f7b1c7bd32727e37b853a4a0\dji-sdk-v5-aircraft-provided-5.17.0.aar"

if (Test-Path $providedAar) {
    Write-Host "Checking aircraft-provided AAR"
    $tmpProvided = "C:\Users\14472\AppData\Local\Temp\djiprovided"
    if (Test-Path $tmpProvided) { Remove-Item $tmpProvided -Recurse -Force }
    New-Item -ItemType Directory -Path $tmpProvided -Force | Out-Null
    
    Copy-Item $providedAar (Join-Path $tmpProvided "provided.zip")
    Expand-Archive -Path (Join-Path $tmpProvided "provided.zip") -DestinationPath $tmpProvided -Force
    
    # Check contents
    Get-ChildItem $tmpProvided
    
    $providedClasses = Join-Path $tmpProvided "classes.jar"
    if (Test-Path $providedClasses) {
        Write-Host "provided classes.jar size: $([math]::Round((Get-Item $providedClasses).Length/1MB,2))MB"
        & jar tf $providedClasses | Where-Object { $_ -match "SDKManager" }
    }
}

# Now: Use the original AAR's classes? Wait let's check if original Gradle AAR has any other JARs
# Wait: Let's just use the provided AAR's classes.jar which likely has the API!
if (Test-Path $providedClasses) {
    $libsDir = Join-Path $projectDir "libs"
    New-Item -ItemType Directory -Path $libsDir -Force | Out-Null
    Copy-Item $providedClasses (Join-Path $libsDir "dji-sdk-v5-aircraft-classes.jar") -Force
    Write-Host "Copied provided classes to app/libs"
}

# Also copy the native libs
$jniLibsDir = Join-Path $projectDir "src\main\jniLibs\arm64-v8a"
New-Item -ItemType Directory -Path $jniLibsDir -Force | Out-Null
$soSource = Join-Path $tmp "jni\arm64-v8a"
if (Test-Path $soSource) {
    Copy-Item "$soSource\*.so" $jniLibsDir -Force
    $count = (Get-ChildItem $jniLibsDir -Filter "*.so").Count
    Write-Host "Copied $count .so files"
}
