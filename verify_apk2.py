import zipfile, os

APK = r"D:\Projects\mjfly\app\build\outputs\apk\debug\app-debug.apk"

print("=== APK中所有dex文件 ===")
with zipfile.ZipFile(APK, 'r') as z:
    all_files = z.namelist()

    # 搜索dji相关
    dji_files = [n for n in all_files if 'dji' in n.lower()]
    print("\n=== APK中的dji相关文件 ===")
    for f in dji_files[:50]:
        info = z.getinfo(f)
        print("  %s (%d bytes)" % (f, info.file_size))
    if len(dji_files) > 50:
        print("  ... and %d more" % (len(dji_files) - 50))

    print("\n=== APK中是否有任何dji/v5/manager路径 ===")
    v5_mgr = [n for n in all_files if 'dji/v5/manager' in n]
    if v5_mgr:
        for f in v5_mgr:
            print("  %s" % f)
    else:
        print("  NOT FOUND")

    # 检查META-INF
    meta = [n for n in all_files if n.startswith('META-INF')]
    print("\n=== META-INF (前20个) ===")
    for f in meta[:20]:
        print("  %s" % f)