pluginManagement {
    repositories {
        // 插件仓库也建议使用国内镜像加速
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/gradle-plugins/") } // 腾讯云插件
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") } // 阿里云插件
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // 1. 大疆官方仓库（必须第一顺位，且必须直连，镜像站通常没有这个）
        maven { url = uri("https://dl.djicdn.com/repo/") }

        // 2. 腾讯云 Maven 中央仓库镜像（替换原本的 mavenCentral）
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }

        // 3. 腾讯云 Google 镜像（替换原本的 google）
        maven { url = uri("https://mirrors.cloud.tencent.com/nexus/repository/google/") }

        // 4. 阿里云（作为腾讯云的备用）
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }

        // 5. 官方源（如果上面都失败，才尝试连这个，通常很慢）
        google()
        mavenCentral()
    }
}

rootProject.name = "mjfly"
include(":app")