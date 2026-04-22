# DJI MSDK V5 无人机地面站 🚁

基于 DJI Mobile SDK V5 开发的高性能无人机控制应用，采用 MVVM + Clean Architecture 架构，实现了第一人称视角视频流、虚拟摇杆控制、实时遥测、相机控制及媒体文件管理功能。

## ✨ 核心功能特性

- 📡 **FPV 实时视频流** - 通过 TextureView 展示无人机实时画面
- 🎮 **20Hz 虚拟摇杆控制** - 自定义双摇杆控件，支持俯仰/横滚/偏航/油门
- 📊 **全量飞行数据遥测** - 实时显示位置、高度、姿态、速度、电量等信息
- 📷 **相机参数调节** - 支持拍照/录像模式切换、光学变焦控制
- 💾 **媒体文件管理** - 浏览、下载、删除无人机存储的媒体文件
- 📺 **直播推流支持** - 支持 RTMP/RTSP/GB28181/Agora 多种协议

## 🛠️ 技术栈

| 分类 | 技术 |
|------|------|
| 语言 | Kotlin 100% |
| 架构 | MVVM + Clean Architecture |
| 异步 | Kotlin Coroutines + Flow |
| 依赖注入 | Hilt |
| UI | Jetpack Navigation, Fragment, ViewBinding |
| SDK | DJI MSDK V5 (5.9.0) |
| 日志 | Timber |

## 🏗️ 架构图解

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                        │
│  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐        │
│  │  FPV    │  │ Control │  │ Telemetry│  │ Camera  │        │
│  │Fragment │  │Fragment │  │Fragment │  │Fragment │        │
│  └────┬────┘  └────┬────┘  └────┬────┘  └────┬────┘        │
│       └────────────┴────────────┴────────────┘              │
│                          │                                   │
│                    ViewModel                                 │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────┴──────────────────────────────────┐
│                      Domain Layer                             │
│  ┌──────────────────┐  ┌─────────────────────┐              │
│  │   Models         │  │   Repositories      │              │
│  │ AircraftStatus   │  │ AircraftRepository  │              │
│  │ FlightTelemetry  │  │ FlightControlRepo   │              │
│  │ CameraState      │  │ CameraRepository   │              │
│  │ MediaFile        │  │ MediaRepository    │              │
│  └──────────────────┘  └─────────────────────┘              │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────┴──────────────────────────────────┐
│                       Data Layer                              │
│  ┌────────────────────────┐  ┌─────────────────────────┐     │
│  │ KeyManagerHelper       │  │ RepositoryImpl          │     │
│  │ (MSDK → Flow)         │  │ AircraftRepositoryImpl  │     │
│  └────────────────────────┘  │ FlightControlRepoImpl   │     │
│                              │ CameraRepositoryImpl     │     │
│                              │ MediaRepositoryImpl      │     │
│                              └─────────────────────────┘     │
└─────────────────────────────────────────────────────────────┘
```

## 📦 模块说明

| 模块 | 职责 |
|------|------|
| `domain/model` | 纯 Kotlin 数据类，与 Android/MSDK 解耦 |
| `domain/repository` | 仓储接口定义，业务逻辑抽象 |
| `data/datasource/msdk` | MSDK KeyManager 封装，异步转 Flow |
| `data/repository` | 仓储实现，调用 MSDK API |
| `di` | Hilt 依赖注入模块 |
| `sdk` | SDK 初始化管理 |
| `ui` | 表现层：Fragment + ViewModel |
| `util` | 工具类：权限、日志、常量 |

## 📋 前置条件与运行

### 开发环境
- Android Studio Hedgehog 或更高版本
- JDK 17+
- Gradle 8.2+

### 配置步骤

1. **配置 DJI App Key**

   在 `app/src/main/java/com/dmj/fly/util/Constants.kt` 中修改：
   ```kotlin
   const val DJI_APP_KEY = "YOUR_VALID_DJI_APP_KEY"
   ```

   同时修改 `AndroidManifest.xml` 中的 meta-data：
   ```xml
   <meta-data
       android:name="com.dji.sdk.API_KEY"
       android:value="YOUR_VALID_DJI_APP_KEY" />
   ```

2. **构建项目**
   ```bash
   ./gradlew assembleDebug
   ```

3. **真机调试**
   - 使用 DJI 真机（如 Mavic 3, Mini 3 Pro 等）
   - 开启开发者模式
   - 安装并运行 APK

> ⚠️ 注意：DJI MSDK 需要真机调试，模拟器无法使用

## 📂 代码结构

```
com.dmj.fly/
├── DmjFlyApplication.kt          # 应用入口
├── di/
│   └── AppModule.kt             # Hilt 模块
├── domain/
│   ├── model/
│   │   ├── Result.kt            # 结果封装
│   │   ├── AircraftStatus.kt    # 飞行器状态
│   │   ├── FlightTelemetry.kt   # 飞行遥测
│   │   ├── CameraState.kt       # 相机状态
│   │   ├── MediaFile.kt         # 媒体文件
│   │   ├── LiveStreamConfig.kt  # 直播配置
│   │   └── Waypoint.kt          # 航点任务
│   └── repository/
│       ├── AircraftRepository.kt
│       ├── FlightControlRepository.kt
│       ├── CameraRepository.kt
│       ├── MediaRepository.kt
│       └── LiveStreamRepository.kt
├── data/
│   ├── datasource/msdk/
│   │   └── KeyManagerHelper.kt  # MSDK Key 封装
│   └── repository/
│       ├── AircraftRepositoryImpl.kt
│       ├── FlightControlRepositoryImpl.kt
│       ├── CameraRepositoryImpl.kt
│       ├── MediaRepositoryImpl.kt
│       └── LiveStreamRepositoryImpl.kt
├── sdk/
│   └── DjiSdkManager.kt         # SDK 初始化管理
├── ui/
│   ├── MainActivity.kt
│   ├── fpv/
│   │   ├── FpvFragment.kt
│   │   └── FpvViewModel.kt
│   ├── control/
│   │   ├── ControlFragment.kt
│   │   ├── ControlViewModel.kt
│   │   └── VirtualStickView.kt
│   ├── telemetry/
│   │   ├── TelemetryFragment.kt
│   │   └── TelemetryViewModel.kt
│   ├── camera/
│   │   ├── CameraFragment.kt
│   │   └── CameraViewModel.kt
│   ├── media/
│   │   ├── MediaFragment.kt
│   │   ├── MediaViewModel.kt
│   │   └── MediaAdapter.kt
│   └── widget/
│       └── VirtualStickView.kt
└── util/
    ├── Constants.kt             # 常量定义
    ├── Logger.kt                # 日志封装
    ├── Extension.kt             # 扩展函数
    └── PermissionHelper.kt     # 权限管理
```

## 📄 开源协议

```
MIT License

Copyright (c) 2024 DMJ Fly

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

⭐ 如果这个项目对你有帮助，欢迎 Star！
