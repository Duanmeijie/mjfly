import zipfile

APK = r"D:\Projects\mjfly\app\build\outputs\apk\debug\app-debug.apk"
print("=== APK大小 ===")
print("  %.1f MB" % (os.path.getsize(APK) / 1024 / 1024) if False else "")

with zipfile.ZipFile(APK, 'r') as z:
    # 在所有dex文件中搜索"SDKManager"关键字
    print("\n=== 在dex中搜索 SDKManager ===")
    found = False
    for name in z.namelist():
        if name.endswith('.dex'):
            data = z.read(name)
            if b'SDKManager' in data:
                found = True
                print("  FOUND in %s (%d bytes)" % (name, len(data)))
    if not found:
        print("  NOT FOUND in any dex")

    # 搜索dji/v5
    print("\n=== 在dex中搜索 dji/v5 ===")
    found = False
    for name in z.namelist():
        if name.endswith('.dex'):
            data = z.read(name)
            if b'dji/v5/manager' in data:
                found = True
                print("  FOUND dji/v5/manager in %s" % name)
            if b'Ldji/v5/manager/SDKManager' in data:
                print("  FOUND SDKManager descriptor in %s" % name)
    if not found:
        print("  NOT FOUND dji/v5/manager")

import os
print("\n=== APK大小 ===")
print("  %.1f MB" % (os.path.getsize(APK) / 1024 / 1024))