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
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        ndk {
            abiFilters += listOf("arm64-v8a")
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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

    packaging {
        jniLibs {
            useLegacyPackaging = true
        }
        resources {
            pickFirsts.add("lib/arm64-v8a/libc++_shared.so")
            pickFirsts.add("lib/arm64-v8a/libdjiv5.so")
        }
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
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

    // DJI SDK V5
    implementation("com.dji:dji-sdk-v5-aircraft:5.17.0")
    compileOnly("com.dji:dji-sdk-v5-aircraft-provided:5.17.0")
    runtimeOnly("com.dji:dji-sdk-v5-networkImp:5.17.0")

    // Also include local jars if any (optional fallback)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // 第三方库 - 注意：不要显式声明 wire-runtime，DJI SDK 内部依赖 wire 2.2.0
    // 显式声明高版本会覆盖导致 NoSuchMethodError
    implementation("com.squareup.okio:okio:1.17.6")
}

kapt {
    correctErrorTypes = true
}
