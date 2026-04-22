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
        google()
        mavenCentral()
        maven {
            url = uri("https://dl.djicdn.com/repo/")
        }
        maven {
            url = uri("https://maven.aliyun.com/repository/public")
        }
    }
}

rootProject.name = "mjfly"
include(":app")