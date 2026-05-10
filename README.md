# MJFLY - 大疆无人机控制应用

<div align="center">

![无人机](https://img.shields.io/badge/无人机-DJI%20SDK%20V5-blue)
![平台](https://img.shields.io/badge/平台-Android-green)
![语言](https://img.shields.io/badge/语言-Kotlin-orange)
![版本](https://img.shields.io/badge/版本-1.0.0-red)

**一款基于 DJI SDK V5 的真实无人机控制应用，让你的手机成为无人机的指挥中心**

[English](README_EN.md) | 中文

</div>

---

## 📖 目录

- [🎯 项目介绍](#-项目介绍)
- [✨ 能做什么](#-能做什么)
- [🛠️ 开发环境配置](#️-开发环境配置)
- [📱 真机使用教程](#-真机使用教程)
- [🐛 常见问题](#-常见问题)
- [🏗️ 项目架构](#️-项目架构)
- [� 功能清单](#-功能清单)
- [� 后续拓展](#-后续拓展)

---

## 🎯 项目介绍

### 这是什么？

**MJFLY** 是一款基于 **DJI SDK V5** 开发的 Android 无人机控制应用。它不是模拟器，而是能够**真实控制大疆无人机**的移动端应用。

### 项目信息

| 项目 | 内容 |
|------|------|
| **项目名称** | MJFLY |
| **包名** | com.dmj.fly |
| **SDK版本** | DJI SDK V5 5.17.0 |
| **目标平台** | Android 7.0+ (API 24+) |
| **开发语言** | Kotlin |
| **UI框架** | Jetpack Compose |

---

## ✨ 能做什么

### 🚀 已实现功能

| 功能 | 说明 | 状态 |
|------|------|------|
| **起飞** | 一键自动起飞 | ✅ 已完成 |
| **降落** | 一键自动降落 | ✅ 已完成 |
| **返航(RTH)** | 一键返回起飞点 | ✅ 已完成 |
| **取消返航** | 取消正在执行的返航 | ✅ 已完成 |
| **虚拟摇杆** | 屏幕双摇杆控制飞行姿态 | ✅ 已完成 |
| **SDK初始化** | 自动初始化 DJI SDK | ✅ 已完成 |
| **设备监听** | 实时监听无人机连接状态 | ✅ 已完成 |

### ⚠️ 重要说明

> **这是一个真实的无人机控制应用！**
>
> - ❌ 不支持模拟器调试
> - ❌ 不支持模拟飞行
> - ✅ **必须在真机上运行**
> - ✅ **必须连接真实无人机**

---

## 🛠️ 开发环境配置

### 📋 需要准备什么？

| 软件 | 版本要求 | 下载地址 |
|------|----------|----------|
| Android Studio | Ladybug (2024.2.1) 或更高 | [下载地址](https://developer.android.com/studio) |
| JDK | JDK 17 | [下载地址](https://adoptium.net/) |
| Android SDK | API 35 | 通过 Android Studio 安装 |
| Gradle | 8.10.2 | 项目已包含 Wrapper |

### 🔧 详细安装步骤

#### 第一步：安装 Android Studio

1. 访问 [Android Studio 下载页面](https://developer.android.com/studio)
2. 下载最新版本的 Android Studio
3. 安装时选择"Custom"安装，勾选：
   - ✅ Android SDK
   - ✅ Android Virtual Device
   - ✅ Performance (Intel HAXM)
4. 完成安装向导

#### 第二步：配置 JDK

> 💡 **提示**：Android Studio 2024.x 已内置 JDK 17，通常不需要单独安装

1. 打开 Android Studio
2. 菜单栏：**File** → **Project Structure** → **SDK Location**
3. 检查 **JDK location** 是否指向 JDK 17
4. 如果没有，安装 JDK 17：
   ```bash
   # Windows (使用 winget)
   winget install EclipseAdoptium.Temurin17JDK

   # macOS
   brew install openjdk@17
   ```

#### 第三步：克隆项目

```bash
# 使用 Git 克隆
git clone <项目地址>
cd mjfly

# 或直接下载 ZIP 包解压
```

#### 第四步：导入 Android Studio

1. 打开 Android Studio
2. 选择 **Open** 或 **Import Project**
3. 选择项目根目录 `mjfly`
4. 等待 Gradle 同步完成（约5-10分钟）

#### 第五步：配置 DJI App Key

> ⚠️ **重要**：每个应用需要唯一的 App Key，必须使用自己的！

1. 访问 [DJI 开发者平台](https://developer.dji.com/)
2. 注册账号并完成**实名认证**
3. 创建新应用：
   - 应用名称：MJFLY
   - 应用平台：Android
   - 包名：`com.dmj.fly`
4. 获取 **App Key**

5. 编辑 `app/src/main/AndroidManifest.xml`，找到：
   ```xml
   <meta-data
       android:name="com.dji.sdk.API_KEY"
       android:value="EE45A36E38A16E49C8CF38A8" />
   ```

6. 将 `value` 替换为你的 App Key：
   ```xml
   <meta-data
       android:name="com.dji.sdk.API_KEY"
       android:value="你的App Key" />
   ```

#### 第六步：执行 SDK 提取脚本

> ⚠️ **必须执行**：DJI SDK 有嵌套问题，需要手动提取

1. 打开 PowerShell 终端（在项目根目录按住 Shift + 右键，点击"在此处打开 PowerShell"）

2. 运行脚本：
   ```powershell
   .\extract_v3.ps1
   ```

3. 等待脚本执行完成，看到 `=== All Done ===` 即成功

4. 如果遇到脚本执行错误，运行：
   ```powershell
   Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
   ```

#### 第七步：同步 Gradle

1. Android Studio 右上角点击 **Sync Now**
2. 或在终端运行：
   ```bash
   ./gradlew sync
   ```

#### 第八步：编译打包

```bash
# Debug 构建
./gradlew assembleDebug

# Release 构建（需要签名配置）
./gradlew assembleRelease
```

#### 第九步：安装到手机

```bash
# 通过 USB 安装
adb install app/build/outputs/apk/debug/app-debug.apk

# 或直接将 APK 文件传到手机安装
```

---

## 📱 真机使用教程

### 🔧 手机设置

#### Android 7.0+ 通用设置

1. **开启开发者模式**：
   - 设置 → 关于手机 → 连续点击"版本号"7次
   - 返回设置，看到"开发者选项"

2. **开启 USB 调试**：
   - 设置 → 开发者选项 → 开启 **USB 调试**
   - 开启 **USB 调试（安全设置）**

3. **授予 USB 权限**：
   - 用 USB 连接电脑后，手机弹出"允许 USB 调试"，点击**允许**

4. **保持后台运行**：
   - 设置 → 应用 → MJFLY → 电池 → 设为"不受限制"

### 🔌 遥控器连接方式

#### 方式一：USB OTG 直连（推荐）

```
手机 ─── USB OTG 线 ─── 遥控器 ─── 无人机
```

1. 准备一根 **USB OTG 数据线**
2. 手机通过 OTG 线连接遥控器的 USB 接口
3. 确保遥控器已开启
4. 确保无人机已开机

#### 方式二：WiFi 连接（部分设备）

```
手机 ─── WiFi ─── 遥控器热点 ─── 无人机
```

1. 遥控器进入 WiFi 配置模式
2. 手机连接遥控器发出的 WiFi 热点
3. 打开 App，等待连接

### 🚀 设备开机顺序

> ⚠️ **严格按顺序操作，否则可能导致连接失败**

#### 标准开机顺序

```
1️⃣ 先开遥控器
   └── 等待遥控器启动完成（约30秒）
   └── 确认遥控器屏幕显示正常

2️⃣ 再开无人机
   └── 等待无人机完成自检（约10秒）
   └── 确认无人机桨叶未转动、状态灯正常

3️⃣ 最后开手机 App
   └── 确保 USB 已连接
   └── 打开 MJFLY App
   └── 等待 SDK 初始化完成
```

#### 关机顺序（相反）

```
1️⃣ 先关无人机
2️⃣ 再关遥控器
3️⃣ 最后拔掉手机
```

### 📡 SDK 激活流程

#### 首次使用

1. 打开 App，等待看到日志：
   ```
   开始初始化 DJI MSDK V5...
   初始化进度: INITIALIZE_IN_PROGRESS, 10%
   初始化进度: INITIALIZE_IN_PROGRESS, 30%
   ...
   初始化完成，正在注册...
   ```

2. 如果看到：
   ```
   App 注册成功！
   ```
   ✅ SDK 激活成功，可以飞行！

3. 如果看到错误：
   ```
   App 注册失败: xxx
   ```
   请参考 [常见问题](#-常见问题) 解决

#### 连接成功标志

App 界面显示：
- ✅ 连接状态：已连接
- ✅ 注册状态：已注册
- ✅ 产品信息：显示无人机型号

### 🎮 起飞、降落、返航使用教程

#### 🚁 起飞

1. 确保无人机在**平坦地面**
2. 确保周围**无障碍物**
3. 确保人员**远离无人机**
4. 点击 App 中的 **起飞按钮**
5. 等待无人机自动起飞（约3-5秒）
6. 看到日志：`takeOff success`

#### 🛬 降落

1. 确保降落区域**无障碍物**
2. 点击 App 中的 **降落按钮**
3. 等待无人机自动降落
4. 落地后桨叶停止转动

#### 🏠 返航（RTH）

1. 点击 App 中的 **返航按钮**
2. 无人机会自动飞回起飞点
3. 如需取消，点击 **取消返航**

#### �️ 虚拟摇杆控制

| 摇杆 | 控制轴 | 说明 |
|------|--------|------|
| **左侧** | 油门(Y) + 偏航(X) | 上升/下降 + 原地转向 |
| **右侧** | 俯仰(Y) + 横滚(X) | 前进/后退 + 左移/右移 |

**使用技巧**：
- 轻推摇杆：微调姿态
- 重推摇杆：快速响应
- 松开摇杆：保持当前悬停

---

## 🐛 常见问题

### ❌ 问题：SDK 注册失败

**错误日志**：
```
App 注册失败: Invalid App Key.
```

**原因**：
1. App Key 不正确
2. 包名与 App Key 不匹配
3. 网络连接失败

**解决方案**：
1. 确认 App Key 正确（与 DJI 开发者平台一致）
2. 确认包名是 `com.dmj.fly`
3. 确保手机网络连接正常
4. 确保 DJI 开发者账号已完成实名认证

---

### ❌ 问题：USB 设备未授权

**错误日志**：
```
USB device not authorized.
```

**解决方案**：
1. 断开 USB，重新连接
2. 手机弹出授权窗口时，点击"允许"
3. 如果没有弹出，手动授权：
   - 设置 → 开发者选项 → USB 调试 → 确认授权

---

### ❌ 问题：找不到 DJIService

**错误日志**：
```
ClassNotFoundException: dji.v5.common.core.DJIService
```

**解决方案**：
1. 运行 `extract_v3.ps1` 脚本
2. 确认 `app/libs/` 下有 `dji-sdk-v5-aircraft-classes.jar`
3. 确认 `app/src/main/jniLibs/arm64-v8a/` 下有 `.so` 文件
4. 重新编译：`./gradlew clean assembleDebug`

---

### ❌ 问题：模拟器不支持

**错误日志**：
```
DJI SDK requires real device.
```

**说明**：
DJI SDK V5 **不支持模拟器**，必须在真机上运行。

---

### ❌ 问题：Gradle 同步失败

**解决方案**：
1. 检查网络连接
2. 检查 JDK 版本（JDK 17）
3. 清理缓存：
   ```bash
   ./gradlew clean
   rm -rf ~/.gradle/caches
   ```
4. 重新同步

---

### ❌ 问题：Build 失败，显示重复类

**错误日志**：
```
Duplicate class com.xxx.xxx found in:
- dji-sdk-v5-aircraft-5.17.0.aar
- dji-sdk-v5-aircraft-classes.jar
```

**解决方案**：
删除重复的 jar 文件：
```bash
rm app/libs/dji-sdk-v5-aircraft-classes.jar
```

---

### ❌ 问题：无人机无法连接

**排查步骤**：
1. ✅ 确认遥控器已开启
2. ✅ 确认无人机已开启
3. ✅ 确认 USB 线连接正常
4. ✅ 确认 App Key 正确
5. ✅ 尝试重启所有设备

---

## 🏗️ 项目架构

### � 目录结构

```
mjfly/
├── app/
│   ├── src/main/
│   │   ├── java/com/dmj/fly/
│   │   │   ├── FlyApplication.kt      # 应用入口
│   │   │   ├── sdk/
│   │   │   │   └── DjiSdkManager.kt   # SDK 管理器
│   │   │   ├── data/
│   │   │   │   └── repository/         # 数据仓库
│   │   │   ├── domain/
│   │   │   │   └── repository/        # 领域接口
│   │   │   ├── di/
│   │   │   │   └── AppModule.kt       # Hilt 模块
│   │   │   └── ui/
│   │   │       ├── MainActivity.kt    # 主界面
│   │   │       └── control/           # 飞控页面
│   │   ├── res/                       # 资源文件
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── docs/requirement/                   # 需求文档
├── build.gradle.kts                   # 根构建配置
├── settings.gradle.kts                # 项目设置
└── gradle.properties                  # Gradle 属性
```

### 🏛️ 架构分层

```
┌─────────────────────────────────────┐
│           UI Layer                  │
│    MainActivity, ControlFragment    │
│        Compose 声明式界面            │
└─────────────────────────────────────┘
                  ↓ ↑
┌─────────────────────────────────────┐
│       ViewModel Layer                │
│    ControlViewModel, FpvViewModel   │
│         状态管理与业务逻辑           │
└─────────────────────────────────────┘
                  ↓ ↑
┌─────────────────────────────────────┐
│       Repository Layer               │
│    FlightControlRepository          │
│         飞行控制命令封装            │
└─────────────────────────────────────┘
                  ↓ ↑
┌─────────────────────────────────────┐
│         SDK Layer                    │
│        DjiSdkManager                │
│       DJI SDK V5 封装               │
└─────────────────────────────────────┘
                  ↓ ↑
┌─────────────────────────────────────┐
│         DJI SDK V5                  │
│    FlightController, VirtualStick   │
│         底层飞行控制 API            │
└─────────────────────────────────────┘
```

---

## � 功能清单

### ✅ 核心功能

| 功能 | 文件位置 | 说明 |
|------|----------|------|
| SDK 初始化 | `FlyApplication.kt` | 应用启动自动初始化 |
| SDK 注册 | `DjiSdkManager.kt` | 自动注册并处理回调 |
| 起飞 | `FlightControlRepositoryImpl.kt` | `startTakeoff()` |
| 降落 | `FlightControlRepositoryImpl.kt` | `startLanding()` |
| 确认降落 | `FlightControlRepositoryImpl.kt` | `confirmLanding()` |
| 返航 | `FlightControlRepositoryImpl.kt` | `startGoHome()` |
| 取消返航 | `FlightControlRepositoryImpl.kt` | `cancelGoHome()` |
| 虚拟摇杆 | `FlightControlRepositoryImpl.kt` | `sendPositionControlData()` |

### 📊 状态管理

| 状态 | 类型 | 说明 |
|------|------|------|
| `connectionState` | StateFlow | 连接状态 |
| `isRegistered` | StateFlow | 注册状态 |
| `activationState` | StateFlow | 激活状态 |

---

## 🚀 后续拓展

### 🔮 可拓展功能

| 功能 | 难度 | 说明 |
|------|------|------|
| 航点飞行 | ⭐⭐⭐ | 需要 DJI Waypoints SDK |
| 相机控制 | ⭐⭐ | 拍摄、录像、云台 |
| 图传显示 | ⭐⭐ | FPV 画面解码 |
| 飞行数据记录 | ⭐ | 轨迹、日志存储 |
| 实时数据监控 | ⭐ | 高度、速度、电量 |
| 自动化任务 | ⭐⭐⭐ | 任务规划执行 |

---

## 📞 技术支持

- **问题反馈**：提交 Issue
- **开发者论坛**：DJI Developer Forum
- **官方文档**：[DJI Mobile SDK Documentation](https://developer.dji.com/doc/mobile-sdk-tutorial/cn/)

---

## 📄 许可证

本项目仅供学习研究使用，请遵守 DJI 开发者协议。

---

<div align="center">

**让每一次飞行都安全可控** ✈️

</div>
