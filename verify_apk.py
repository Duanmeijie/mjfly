import zipfile, os

APK = r"D:\Projects\mjfly\app\build\outputs\apk\debug\app-debug.apk"

print("=== 检查APK中的SDKManager类 ===")
with zipfile.ZipFile(APK, 'r') as z:
    sdk = [n for n in z.namelist() if 'SDKManager' in n]
    if sdk:
        print("FOUND SDKManager classes:")
        for s in sdk:
            print("  %s (%d bytes)" % (s, z.getinfo(s).file_size))
    else:
        print("ERROR: SDKManager NOT FOUND!")

    print("\n=== 检查.so文件 ===")
    sos = [n for n in z.namelist() if n.startswith('lib/arm64') and n.endswith('.so')]
    print("arm64-v8a so files: %d" % len(sos))
    for s in sos[:10]:
        print("  %s (%d bytes)" % (s, z.getinfo(s).file_size))
    if len(sos) > 10:
        print("  ... and %d more" % (len(sos) - 10))

    dex = [n for n in z.namelist() if n.startswith('classes') and n.endswith('.dex')]
    print("\n=== classes.dex ===")
    for d in dex:
        print("  %s (%d bytes)" % (d, z.getinfo(d).file_size))

print("\n=== 完成 ===")