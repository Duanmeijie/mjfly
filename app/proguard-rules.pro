# ============================================================
# DJI MSDK V5 官方 ProGuard 规则 - 禁止优化/混淆关键类
# ============================================================

# 保留 DJI SDK 所有类、方法、字段
-keep class dji.** { *; }
-keep class com.dji.** { *; }
-keep class dji.v5.** { *; }
-keepclassmembers class dji.v5.** { *; }
-dontwarn dji.v5.**
-keepnames class dji.v5.** { *; }

# 保护 secneo 安全库（Helper.install 所在包 - 旧版）
-keep class com.secneo.** { *; }
-keepclassmembers class com.secneo.** { *; }
-dontwarn com.secneo.**

# 保护 cySdkyc 安全库（Helper.install 所在包 - V5 5.10.0+ 新版）
-keep class com.cySdkyc.** { *; }
-keepclassmembers class com.cySdkyc.** { *; }
-dontwarn com.cySdkyc.**

# 保护 EventBus（DJI SDK 内部使用）
-keep class org.greenrobot.eventbus.** { *; }
-dontwarn org.greenrobot.eventbus.**

# 保护 wire-runtime（DJI SDK Protobuf 依赖）
-keep class com.squareup.wire.** { *; }
-dontwarn com.squareup.wire.**

# 保护 Google Protobuf（DJI SDK 依赖）
-keep class com.google.protobuf.** { *; }
-dontwarn com.google.protobuf.**

# 特别保护 SDKManager 及其构造函数（解决 VerifyError 关键）
-keep class dji.v5.manager.SDKManager { *; }
-keepclassmembers class dji.v5.manager.SDKManager {
    <init>();
    public static *;
    public *;
}

# 保留所有 Native 方法
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留所有 JNI 库
-keep class * {
    static {
        java.lang.String[];
    }
}

# 保留所有枚举
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留 Parcelable 和 Serializable
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keep class * implements java.io.Serializable { *; }

# 保留 Retrofit 和 OkHttp
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class okio.** { *; }
-dontwarn okio.**

# 保留 Kotlin Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

# 禁用优化（解决 VerifyError 的关键）
-dontoptimize

# 禁用过度内联
-optimizations !code/allocation/variable

# 保留注解
-keepattributes *Annotation*,Signature,InnerClasses
