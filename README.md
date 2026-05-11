# MJFLY - 大疆无人机控制应用

<div align="center">

![平台](https://img.shields.io/badge/平台-Android%207.0+-green)
![语言](https://img.shields.io/badge/语言-Kotlin-orange)
![DJI SDK](https://img.shields.io/badge/DJI%20SDK-V5%205.17.0-blue)
![AGP](https://img.shields.io/badge/AGP-8.8.0-purple)
![Gradle](https://img.shields.io/badge/Gradle-8.10.2-6B3FA0)
![版本](https://img.shields.io/badge/版本-1.0.0-red)

**基于 DJI SDK V5 5.17.0 的真实大疆无人机移动端控制应用**

</div>

---

## 项目概述

MJFLY 是一款基于 **DJI SDK V5** 开发的 Android 无人机控制应用，非模拟器。通过手机 USB OTG 连接遥控器，可直接控制真实的大疆无人机执行起飞、降落、返航、虚拟摇杆等操作。

采用 **MVVM + Repository** 分层架构，使用 Kotlin 协程 + Flow 进行异步数据处理，Hilt 进行依赖注入。

### 技术栈总览

| 项 | 值 |
|---|---|
| **开发语言** | Kotlin |
| **包名** | `com.dmj.fly` |
| **最低系统** | Android 7.0 (API 24) |
| **编译系统** | API 35 (Android 15) |
| **目标系统** | API 34 |
| **AGP 版本** | 8.8.0 |
| **Gradle** | 8.10.2 |
| **JDK** | 17 |
| **Kotlin** | 2.1.0 |
| **DJI SDK V5** | 5.17.0 |
| **Hilt** | 2.52 |
| **构建方式** | 全命令行 (无需 Android Studio) |

---

## 核心功能

| 功能模块 | 功能 | 说明 |
|---------|------|------|
| **飞行控制** | 一键起飞 | 自动起飞至安全高度 |
| | 一键降落 | 自动降落 |
| | 返航 (RTH) | 返回起飞点 |
| | 取消返航 | 中止正在执行的返航 |
| | 虚拟摇杆 | 屏幕双摇杆控制飞行姿态 |
| | 紧急停止 | 禁用摇杆并立即降落 |
| **相机控制** | 拍照 | 拍摄照片 |
| | 录像 | 开始/停止录像 |
| | 变焦控制 | 1x~10x 数字变焦 |
| | 模式切换 | 拍照/录像模式切换 |
| **遥测监测** | 飞行器状态 | 连接状态、飞行模式、电池、GPS |
| | 飞行数据 | 位置、高度、姿态、速度 |
| **媒体管理** | 文件浏览 | 查看机载媒体文件列表 |
| | 文件下载 | 下载媒体文件到手机 |
| | 文件删除 | 删除机载媒体文件 |
| **直播功能** | 多协议支持 | RTMP/RTSP/GB28181/Agora |

---

## 快速开始

### 环境要求

- **JDK 17**
- **Android SDK API 35**
- **USB OTG 数据线**
- **支持 USB Host 的 Android 手机**（Android 7.0+）
- **DJI 无人机 + 遥控器**
- **有效的 DJI App Key**

### 命令行构建与安装

无需 Android Studio，全程命令行：

```bash
# 1. 构建
cd d:\Projects\mjfly
.\gradlew.bat clean assembleDebug

# 2. 安装到已连接的手机
adb install -r app\build\outputs\apk\debug\app-debug.apk

# 3. 启动应用
adb shell am start -n com.dmj.fly/.ui.MainActivity
```

---

## 项目架构

```
┌─────────────────────────────────────────────┐
│              UI Layer                       │
│  MainActivity + 5 Fragments + Custom Views  │
│  (ViewBinding, Navigation Component)        │
└──────────────────────────┬──────────────────┘
                           │
┌──────────────────────────▼──────────────────┐
│           ViewModel Layer                   │
│  FpvViewModel, ControlViewModel,            │
│  CameraViewModel, TelemetryViewModel,       │
│  MediaViewModel                             │
│  (StateFlow, Coroutine, MVVM)               │
└──────────────────────────┬──────────────────┘
                           │
┌──────────────────────────▼──────────────────┐
│         Repository Layer                    │
│  AircraftRepository  →  AircraftRepositoryImpl
│  FlightControlRepository → FlightControlRepositoryImpl
│  CameraRepository  →  CameraRepositoryImpl  │
│  MediaRepository   →  MediaRepositoryImpl   │
│  LiveStreamRepository → LiveStreamRepositoryImpl
│  (接口 + 实现分离, 反射调用 DJI SDK)        │
└──────────────────────────┬──────────────────┘
                           │
┌──────────────────────────▼──────────────────┐
│          SDK Layer                          │
│  DjiSdkManager (Object 单例)                │
│  DjiSdkInitializer (初始化器)               │
│  KeyManagerHelper (SDK Key 管理)            │
│  (Flow 状态流, 事件回调)                    │
└──────────────────────────┬──────────────────┘
                           │
┌──────────────────────────▼──────────────────┐
│          DJI SDK V5 5.17.0                  │
│  SDKManager, FlightController,              │
│  VirtualStick, CameraController,            │
│  MediaManager, FlySafe                      │
└─────────────────────────────────────────────┘
```

---

## 项目结构

```
mjfly/
├── app/
│   ├── src/main/
│   │   ├── java/com/dmj/fly/
│   │   │   ├── FlyApplication.kt              # 应用入口，SDK 初始化
│   │   │   ├── sdk/
│   │   │   │   ├── DjiSdkManager.kt           # SDK 状态管理
│   │   │   │   └── DjiSdkInitializer.kt       # SDK 初始化器
│   │   │   ├── di/
│   │   │   │   └── AppModule.kt               # Hilt 依赖注入
│   │   │   ├── data/
│   │   │   │   ├── datasource/msdk/
│   │   │   │   │   └── KeyManagerHelper.kt    # SDK Key 管理
│   │   │   │   └── repository/
│   │   │   │       ├── AircraftRepositoryImpl.kt
│   │   │   │       ├── FlightControlRepositoryImpl.kt
│   │   │   │       ├── CameraRepositoryImpl.kt
│   │   │   │       ├── MediaRepositoryImpl.kt
│   │   │   │       └── LiveStreamRepositoryImpl.kt
│   │   │   ├── domain/
│   │   │   │   ├── model/                     # 数据模型
│   │   │   │   │   ├── AircraftStatus.kt
│   │   │   │   │   ├── FlightTelemetry.kt
│   │   │   │   │   ├── CameraState.kt
│   │   │   │   │   ├── MediaFile.kt
│   │   │   │   │   ├── LiveStreamConfig.kt
│   │   │   │   │   ├── Waypoint.kt
│   │   │   │   │   └── Result.kt
│   │   │   │   └── repository/                # 领域接口
│   │   │   │       ├── AircraftRepository.kt
│   │   │   │       ├── FlightControlRepository.kt
│   │   │   │       ├── CameraRepository.kt
│   │   │   │       ├── MediaRepository.kt
│   │   │   │       └── LiveStreamRepository.kt
│   │   │   ├── ui/
│   │   │   │   ├── MainActivity.kt            # 主界面，权限管理
│   │   │   │   ├── fpv/
│   │   │   │   │   ├── FpvFragment.kt         # FPV 第一人称视角
│   │   │   │   │   └── FpvViewModel.kt
│   │   │   │   ├── control/
│   │   │   │   │   ├── ControlFragment.kt     # 飞行控制面板
│   │   │   │   │   └── ControlViewModel.kt
│   │   │   │   ├── telemetry/
│   │   │   │   │   ├── TelemetryFragment.kt   # 遥测数据
│   │   │   │   │   └── TelemetryViewModel.kt
│   │   │   │   ├── camera/
│   │   │   │   │   ├── CameraFragment.kt      # 相机控制
│   │   │   │   │   └── CameraViewModel.kt
│   │   │   │   ├── media/
│   │   │   │   │   ├── MediaFragment.kt       # 媒体管理
│   │   │   │   │   ├── MediaViewModel.kt
│   │   │   │   │   └── MediaAdapter.kt        # 列表适配器
│   │   │   │   └── widget/
│   │   │   │       └── VirtualStickView.kt    # 自定义虚拟摇杆
│   │   │   └── util/
│   │   │       ├── Constants.kt               # 常量定义
│   │   │       ├── Extension.kt               # 扩展函数
│   │   │       ├── Logger.kt                  # 日志封装
│   │   │       └── PermissionHelper.kt        # 权限管理
│   │   ├── res/
│   │   │   ├── layout/                        # XML 布局文件
│   │   │   ├── navigation/                    # Navigation 图
│   │   │   ├── menu/                          # 底部导航菜单
│   │   │   ├── values/                        # 字符串、主题
│   │   │   ├── drawable/                      # 图形资源
│   │   │   └── xml/                           # USB 过滤器、网络安全
│   │   └── AndroidManifest.xml
│   ├── proguard-rules.pro                     # 混淆规则
│   └── build.gradle.kts
├── docs/                                      # 项目文档
│   ├── 需求分析文档.md
│   ├── 测试文档.md
│   └── 项目使用说明书.md
├── build.gradle.kts                           # 根构建配置
├── settings.gradle.kts                        # Gradle 设置
├── gradle.properties                          # Gradle 属性
└── gradle/wrapper/                            # Gradle Wrapper
```

---

## 构建与依赖配置

### 关键构建配置

| 配置项 | 值 | 说明 |
|--------|---|---|
| `minSdk` | 24 | Android 7.0 |
| `compileSdk` | 35 | Android 15 |
| `targetSdk` | 34 | |
| `abiFilters` | arm64-v8a | 仅 64 位 ARM |
| `viewBinding` | 启用 | |
| `dataBinding` | 启用 | |
| `useLegacyPackaging` | true | JNI 库打包 |
| `extractNativeLibs` | true | 原生库提取 |

### DJI SDK 依赖

```kotlin
implementation("com.dji:dji-sdk-v5-aircraft:5.17.0")
compileOnly("com.dji:dji-sdk-v5-aircraft-provided:5.17.0")
runtimeOnly("com.dji:dji-sdk-v5-networkImp:5.17.0")
```

### 仓库配置

- `google()`
- `mavenCentral()`
- `https://maven.aliyun.com/repository/public`
- `https://dl.djicdn.com/repo/`

### 已修复的兼容性问题

| 问题 | 原因 | 修复 |
|------|------|------|
| VerifyError: SDKManager 构造函数 | Wire 4.9.2 与 DJI SDK 的 Wire 2.2.0 冲突 | 移除 wire-runtime:4.9.2 |
| NoSuchMethodError: newEnumAdapter | wire-runtime 版本过高 | 使用 DJI SDK 传递的 2.2.0 |
| 混淆导致 SDK 类损坏 | ProGuard 优化加密的 SDK 类 | proguard-rules.pro 完整保护 dji.** |
| Debug 构建闪退 | minifyEnabled 在 debug 中开启 | debug 设为 false |

---

## 真机使用

### 连接方式

```
手机 ── USB OTG ── 遥控器 USB 口 ── 无人机
```

### 开机顺序（严格遵循）

```
1. 先开遥控器（等待 30 秒启动完成）
2. 再开无人机（等待 10 秒自检完成）
3. 最后打开手机 App
```

### 关机顺序

```
1. 先关无人机
2. 再关遥控器
3. 最后关闭手机 App
```

### 安全须知

> 本应用直接控制真实无人机，所有飞行操作存在安全风险。
> 请在空旷场地操作，远离人群和建筑物，遵守当地无人机管理法规。

---

## 文档导航

| 文档 | 路径 | 用途 |
|------|------|------|
| 需求分析文档 | `docs/需求分析文档.md` | 功能需求、架构设计、技术决策 |
| 测试文档 | `docs/测试文档.md` | 可逐项执行的测试用例 |
| 项目使用说明书 | `docs/项目使用说明书.md` | 用户使用指南、操作说明 |

---

## 技术支持

- DJI 官方文档：https://developer.dji.com/doc/mobile-sdk-tutorial/cn/
- 问题反馈：提交 Issue

---

<div align="center">

**让每一次飞行都安全可控**

</div>
