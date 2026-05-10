plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
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
            pickFirsts.add("lib/arm64-v8a/libc++_shared.so")
            pickFirsts.add("lib/arm64-v8a/libdjiv5.so")
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }

    lint {
        disable += listOf("StringFormatInvalid", "MissingTranslation", "ExtraTranslation")
        abortOnError = false
        checkReleaseBuilds = false
    }
}

dependencies {
    // AndroidX & Kotlin
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // DJI SDK V5 依赖配置（官方示例）
    // api: 核心包，暴露 API 给其他模块
    // compileOnly: provided 包，仅编译时使用，运行时不打包
    // runtimeOnly: 网络实现包，运行时动态加载
    api("com.dji:dji-sdk-v5-aircraft:5.17.0")
    compileOnly("com.dji:dji-sdk-v5-aircraft-provided:5.17.0")
    runtimeOnly("com.dji:dji-sdk-v5-networkImp:5.17.0")

    // 第三方库
    implementation("com.squareup.okio:okio:3.6.0")
    implementation("com.squareup.wire:wire-runtime:4.9.2")

    // 本地 libs 目录
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))
}

kapt {
    correctErrorTypes = true
}