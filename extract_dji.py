import zipfile, os, shutil

AAR = r'C:\Users\14472\.gradle\caches\modules-2\files-2.1\com.dji\dji-sdk-v5-aircraft\5.17.0\dbdb979eda74de02d08ad17d91e5c256511f4ae0\dji-sdk-v5-aircraft-5.17.0.aar'
TMPDIR = r'C:\Users\14472\.tmp_dji_extract'
PROJECT = r'D:\Projects\mjfly\app'

# 清理
if os.path.exists(TMPDIR):
    shutil.rmtree(TMPDIR)
os.makedirs(TMPDIR)

# Step 1: 解压 AAR
print('=== Step 1: 解压 AAR ===')
with zipfile.ZipFile(AAR, 'r') as z:
    z.extractall(TMPDIR)
for f in os.listdir(TMPDIR):
    p = os.path.join(TMPDIR, f)
    if os.path.isfile(p):
        print('  FILE: %s (%d bytes)' % (f, os.path.getsize(p)))
    else:
        print('  DIR:  %s' % f)

# Step 2: 解压嵌套的 dji.zip
dji_zip = os.path.join(TMPDIR, 'dji.zip')
if os.path.exists(dji_zip):
    print('\n=== Step 2: 解压嵌套 dji.zip ===')
    nested_dir = os.path.join(TMPDIR, 'nested')
    os.makedirs(nested_dir, exist_ok=True)
    with zipfile.ZipFile(dji_zip, 'r') as z:
        z.extractall(nested_dir)

    # 用嵌套的内容覆盖
    nested_classes = os.path.join(nested_dir, 'classes.jar')
    root_classes = os.path.join(TMPDIR, 'classes.jar')
    if os.path.exists(nested_classes):
        shutil.copy2(nested_classes, root_classes)
        print('  覆盖 classes.jar (%d bytes)' % os.path.getsize(root_classes))

    nested_jni = os.path.join(nested_dir, 'jni')
    root_jni = os.path.join(TMPDIR, 'jni')
    if os.path.exists(nested_jni) and not os.path.exists(root_jni):
        shutil.copytree(nested_jni, root_jni)
        print('  复制 jni 目录')

# Step 3: 验证 SDKManager
print('\n=== Step 3: 验证 SDKManager ===')
classes_jar = os.path.join(TMPDIR, 'classes.jar')
with zipfile.ZipFile(classes_jar, 'r') as z:
    sdk_classes = [n for n in z.namelist() if 'SDKManager' in n]
    for s in sdk_classes:
        print('  FOUND: %s' % s)
    v5_count = len([n for n in z.namelist() if n.startswith('dji/v5/')])
    print('  dji/v5 类总数: %d' % v5_count)
    print('  classes.jar 总大小: %d bytes' % os.path.getsize(classes_jar))

# Step 4: 复制到项目
print('\n=== Step 4: 复制到项目 ===')
libs_dir = os.path.join(PROJECT, 'libs')
os.makedirs(libs_dir, exist_ok=True)
dst_jar = os.path.join(libs_dir, 'dji-sdk-v5-aircraft-classes.jar')
shutil.copy2(classes_jar, dst_jar)
print('  复制 classes.jar -> %s (%d bytes)' % (dst_jar, os.path.getsize(dst_jar)))

# 复制 .so 文件
jni_src = os.path.join(TMPDIR, 'jni', 'arm64-v8a')
jni_dst = os.path.join(PROJECT, 'src', 'main', 'jniLibs', 'arm64-v8a')
os.makedirs(jni_dst, exist_ok=True)
so_count = 0
for f in os.listdir(jni_src):
    if f.endswith('.so'):
        shutil.copy2(os.path.join(jni_src, f), os.path.join(jni_dst, f))
        so_count += 1
print('  复制 %d 个 .so 文件 -> %s' % (so_count, jni_dst))

print('\n=== 完成 ===')