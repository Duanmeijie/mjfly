# MJFLY - 大疆无人机控制应用

<div align="center">

![平台](https://img.shields.io/badge/平台-Android%207.0+-green)
![语言](https://img.shields.io/badge/语言-Kotlin-orange)
![DJI SDK](https://img.shields.io/badge/DJI%20SDK-V5%205.17.0-blue)
![Tello](https://img.shields.io/badge/Tello-UDP%20WiFi-cyan)
![AGP](https://img.shields.io/badge/AGP-8.8.0-purple)
![Gradle](https://img.shields.io/badge/Gradle-8.10.2-6B3FA0)
![版本](https://img.shields.io/badge/版本-1.1.0-red)

**支持 DJI Tello WiFi 直连控制的移动端无人机应用**

</div>

---

## 项目概述

MJFLY 是一款基于 Android 平台的无人机控制应用，支持通过 **WiFi 直连** 控制 DJI Tello 无人机，同时保留 DJI SDK V5 框架支持。手机连接无人机 WiFi 热点后，即可直接控制起飞、降落、虚拟摇杆操控，并实时接收电池电量、温度、高度等状态数据。

采用 **MVVM + Repository** 分层架构，使用 Kotlin 协程 + Flow 进行异步数据处理，Hilt 进行依赖注入。通过 UDP 协议与 Tello 通信，支持实时状态监测和双摇杆飞行控制。

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
| **Tello 协议** | UDP (8889 命令 / 8890 状态) |
| **Hilt** | 2.52 |
| **连接方式** | WiFi 直连 (无需遥控器) |
| **构建方式** | 全命令行 (无需 Android Studio) |

---

## 核心功能

| 功能模块 | 功能 | 说明 |
|---------|------|------|
| **飞行控制** | 一键起飞 | UDP 发送 `takeoff` 命令 |
| | 一键降落 | UDP 发送 `land` 命令 |
| | 返航 (RTH) | Tello 不支持，降落代替 |
| | 虚拟摇杆 | 屏幕双摇杆，`rc` 命令 20Hz 发送 |
| | 紧急停止 | 禁用摇杆并立即降落 |
| **实时状态** | 电池电量 | UDP 8890 端口实时接收 |
| | 温度监测 | 芯片高低温实时显示 |
| | 飞行高度 | ToF 传感器 + 气压计数据 |
| | 飞行姿态 | 俯仰/横滚/偏航角度 |
| | 速度信息 | XYZ 三轴速度 |
| | 飞行时间 | 本次飞行累计时间 |
| **连接管理** | WiFi 自动检测 | 每 2 秒检测 SSID 是否匹配 |
| | 连接状态显示 | 实时显示连接的无人机名称 |
| | 多机型 SSID 支持 | TELLO/Mini/Mavic/Air 等前缀识别 |

---

## 快速开始

### 环境要求

- **JDK 17**
- **Android SDK API 35**
- **DJI Tello 无人机**（或其他支持 WiFi 直连的 DJI 无人机）
- **支持 WiFi 的 Android 手机**（Android 7.0+）

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
│  MainActivity + Fragments + VirtualStickView│
│  (ViewBinding, Navigation Component)        │
└──────────────────────────┬──────────────────┘
                           │
┌──────────────────────────▼──────────────────┐
│           ViewModel Layer                   │
│  ControlViewModel, FpvViewModel             │
│  (StateFlow, Coroutine, MVVM)               │
└──────────────────────────┬──────────────────┘
                           │
┌──────────────────────────▼──────────────────┐
│         Repository Layer                    │
│  AircraftRepositoryImpl (Tello状态 → 统一模型)
│  TelloFlightControlRepository (UDP命令发送)  │
│  (接口 + 实现分离)                           │
└──────────────────────────┬──────────────────┘
                           │
┌──────────────────────────▼──────────────────┐
│          数据源层                            │
│  TelloStateReceiver (UDP 8890 状态监听解析)  │
│  WifiConnectionDetector (SSID 检测)         │
│  DjiSdkManager (SDK 注册 + WiFi 监控)       │
└──────────────────────────┬──────────────────┘
                           │
┌──────────────────────────▼──────────────────┐
│       通信协议层                             │
│  Tello UDP 文本协议                          │
│  命令端口：192.168.10.1:8889                 │
│  状态端口：0.0.0.0:8890                      │
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
│   │   │   │   └── DjiSdkManager.kt           # SDK 状态管理 + WiFi 监控
│   │   │   ├── di/
│   │   │   │   └── AppModule.kt               # Hilt 依赖注入
│   │   │   ├── data/
│   │   │   │   ├── datasource/
│   │   │   │   │   ├── tello/
│   │   │   │   │   │   └── TelloStateReceiver.kt  # Tello UDP 状态接收
│   │   │   │   │   └── msdk/
│   │   │   │   │       └── KeyManagerHelper.kt    # SDK Key 管理
│   │   │   │   └── repository/
│   │   │   │       ├── AircraftRepositoryImpl.kt      # 飞行器状态（Tello 数据）
│   │   │   │       ├── TelloFlightControlRepository.kt # Tello UDP 飞行控制
│   │   │   │       ├── FlightControlRepositoryImpl.kt  # DJI SDK 飞行控制
│   │   │   │       ├── CameraRepositoryImpl.kt
│   │   │   │       ├── MediaRepositoryImpl.kt
│   │   │   │       └── LiveStreamRepositoryImpl.kt
│   │   │   ├── domain/
│   │   │   │   ├── model/                     # 数据模型
│   │   │   │   │   ├── AircraftStatus.kt      # 飞行器状态（电量/温度/高度）
│   │   │   │   │   ├── FlightTelemetry.kt     # 飞行遥测
│   │   │   │   │   └── Result.kt             # 结果包装
│   │   │   │   └── repository/                # 领域接口
│   │   │   │       ├── AircraftRepository.kt
│   │   │   │       └── FlightControlRepository.kt
│   │   │   ├── ui/
│   │   │   │   ├── MainActivity.kt            # 主界面，权限管理
│   │   │   │   ├── fpv/
│   │   │   │   │   ├── FpvFragment.kt         # FPV 页面
│   │   │   │   │   └── FpvViewModel.kt
│   │   │   │   ├── control/
│   │   │   │   │   ├── ControlFragment.kt     # 飞行控制面板
│   │   │   │   │   └── ControlViewModel.kt
│   │   │   │   └── widget/
│   │   │   │       └── VirtualStickView.kt    # 自定义虚拟摇杆
│   │   │   └── util/
│   │   │       ├── WifiConnectionDetector.kt  # WiFi SSID 检测
│   │   │       ├── Constants.kt
│   │   │       └── PermissionHelper.kt
│   │   ├── res/
│   │   │   ├── layout/                        # XML 布局文件
│   │   │   ├── navigation/                    # Navigation 图
│   │   │   └── values/                        # 字符串、主题
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── docs/                                      # 项目文档
│   ├── 需求分析文档.md
│   ├── 测试文档.md
│   └── 项目使用说明书.md
├── build.gradle.kts                           # 根构建配置
├── settings.gradle.kts                        # Gradle 设置
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

---

## 真机使用

### 连接方式 (WiFi 直连)

```
手机 ── WiFi ── 无人机热点 (如 TELLO-XXXXXX)
```

> 手机连接 Tello 无人机的 WiFi 热点即可，无需遥控器、无需 USB 线。

### 使用步骤

```
1. 开启 Tello 无人机（等待指示灯闪烁）
2. 手机 WiFi 设置中连接 "TELLO-XXXXXX" 热点
3. 打开 MJFLY App
4. App 自动检测到无人机连接（显示"已连接"）
5. 开始控制飞行
```

### 支持的 WiFi SSID 前缀

| 无人机型号 | SSID 前缀 |
|-----------|-----------|
| DJI Tello | `TELLO-` |
| DJI Mini 3/4 Pro | `DJI Mini 3 Pro-` / `DJI Mini 4 Pro-` |
| DJI Mavic Air | `Mavic Air-` |
| DJI Air 2S | `Air2S_` |
| DJI Avata | `Avata_` |
| DJI Neo | `DJI Neo-` |

### 安全须知

> 本应用直接控制真实无人机，所有飞行操作存在安全风险。
> 请在空旷场地操作，远离人群和建筑物，遵守当地无人机管理法规。

---

## 通信协议

### Tello UDP 协议

| 端口 | 方向 | 用途 |
|------|------|------|
| **8889** | 手机 → Tello (192.168.10.1) | 发送控制命令 |
| **8890** | Tello → 手机 (0.0.0.0) | 接收状态数据 |

### 命令列表

| 命令 | 说明 |
|------|------|
| `command` | 进入 SDK 模式（首次发送） |
| `takeoff` | 起飞 |
| `land` | 降落 |
| `emergency` | 紧急停止电机 |
| `rc a b c d` | 虚拟摇杆 (左右/前后/上下/偏航, -100~100) |

### 状态数据格式

```
pitch:0;roll:0;yaw:0;vgx:0;vgy:0;vgz:0;templ:62;temph:65;tof:10;h:0;bat:87;baro:170.49;time:0;agx:-9.00;agy:-5.00;agz:-1002.00;
```

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
