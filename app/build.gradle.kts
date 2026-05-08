plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.dmj.fly"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dmj.fly"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        ndk {
            // DJI SDK V5 仅支持 arm64-v8a
            abiFilters += listOf("arm64-v8a")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    packaging {
        resources {
            // 处理重复的 so 文件
            pickFirsts.add("lib/arm64-v8a/libc++_shared.so")
            pickFirsts.add("lib/arm64-v8a/libdjiv5.so")
        }
        jniLibs {
            // 保留调试符号（可选，防止部分日志丢失）
            keepDebugSymbols += listOf(
                "**/libconstants.so",
                "**/libdji_innertools.so",
                "**/libdjibase.so",
                "**/libdjiarchos.so",
                "**/libdjiglass.so",
                "**/libdjiupgrade.so",
                "**/libDJIFlySafe.so",
                "**/libDJIGimbal.so",
                "**/libDJICamera.so",
                "**/libDJIVision.so",
                "**/libDJIVideoStream.so"
            )
        }
    }

    lint {
        disable += listOf("StringFormatInvalid", "MissingTranslation", "ExtraTranslation")
        abortOnError = false
        checkReleaseBuilds = false
    }
}

dependencies {
    // --- AndroidX & Kotlin ---
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // --- Coroutines ---
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // --- Hilt ---
    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-android-compiler:2.51")

    // --- Timber ---
    implementation("com.jakewharton.timber:timber:5.0.1")

    // --- DJI SDK V5 核心依赖 ---

    // 飞机 SDK (包含具体的无人机控制逻辑)
    implementation("com.dji:dji-sdk-v5-aircraft:5.17.0")

    // 网络依赖 (必须)
    runtimeOnly("com.dji:dji-sdk-v5-networkImp:5.17.0")

    // 编译时依赖 (可选，用于类型检查)
    compileOnly("com.dji:dji-sdk-v5-aircraft-provided:5.17.0")

    // --- 第三方库 (DJI 依赖) ---
    implementation("com.squareup.okio:okio:3.6.0")
    implementation("com.squareup.wire:wire-runtime:4.9.2")
}

kapt {
    correctErrorTypes = true
}