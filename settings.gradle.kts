pluginManagement {
    repositories {
        // 国内镜像（提高下载速度）
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/gradle-plugins/") }
        
        // 官方仓库
        google()
        mavenCentral()
        gradlePluginPortal()
        
        // DJI 私有仓库
        maven { url = uri("https://dl.djicdn.com/repo/") }
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // 国内镜像（提高下载速度）
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        
        // 官方仓库
        google()
        mavenCentral()
        
        // DJI 私有仓库
        maven { url = uri("https://dl.djicdn.com/repo/") }
    }
}

rootProject.name = "mjfly"
include(":app")
