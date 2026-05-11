package com.dmj.fly

import android.app.Application
import android.content.Context
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlyApplication : Application() {

    companion object {
        private const val TAG = "MJFLY"
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // MSDK V5 必须首先调用 Helper.install 解密 SDK
        // V5 5.10.0+ 使用 com.cySdkyc.clx.Helper
        installDjiHelper()
    }

    private fun installDjiHelper() {
        // 尝试手动预加载 native 库
        try {
            System.loadLibrary("Sdkyclx_clx")
            Log.d(TAG, "预加载 libSdkyclx_clx.so 成功")
        } catch (e: Throwable) {
            Log.w(TAG, "预加载 libSdkyclx_clx.so 失败: ${e.message}")
        }

        val helperClasses = listOf(
            "com.cySdkyc.clx.Helper",   // V5 5.10.0+
            "com.secneo.sdk.Helper"       // V5 5.9.x 及更早
        )
        for (className in helperClasses) {
            try {
                val clazz = Class.forName(className)
                val method = clazz.getDeclaredMethod("install", Application::class.java)
                method.invoke(null, this)
                Log.d(TAG, "Helper.install() 完成: $className")
                return
            } catch (e: ClassNotFoundException) {
                Log.d(TAG, "未找到 Helper 类: $className")
            } catch (e: Exception) {
                Log.e(TAG, "Helper.install() 失败: $className", e)
            }
        }
        Log.w(TAG, "Helper.install() 未成功")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "==============================")
        Log.d(TAG, "FlyApplication onCreate()")
        Log.d(TAG, "==============================")
        // 通过反射延迟初始化 DJI SDK
        android.os.Handler(android.os.Looper.getMainLooper()).post {
            initDjiSdkByReflection()
        }
    }

    private fun initDjiSdkByReflection() {
        try {
            val clazz = Class.forName("com.dmj.fly.sdk.DjiSdkInitializer")
            val instance = clazz.getDeclaredField("INSTANCE").get(null)
            val method = clazz.getDeclaredMethod("init", Application::class.java)
            method.invoke(instance, this)
            Log.d(TAG, "DJI SDK 初始化已触发")
        } catch (e: Exception) {
            Log.e(TAG, "DJI SDK 初始化失败", e)
        }
    }
}
