package com.dmj.fly.sdk

import android.app.Application
import android.util.Log

/**
 * DJI SDK 初始化器
 * 通过反射从 FlyApplication 调用，避免 FlyApplication 类加载时直接引用 DJI 类
 */
object DjiSdkInitializer {

    private const val TAG = "DjiSdkInitializer"

    fun init(app: Application) {
        try {
            Log.d(TAG, "开始初始化 DJI SDK...")
            DjiSdkManager.initialize(app)
            Log.d(TAG, "DJI SDK 初始化已提交")
        } catch (e: Throwable) {
            Log.e(TAG, "DJI SDK 初始化异常", e)
            // 捕获所有异常包括 VerifyError、NoClassDefFoundError 等
            if (e is VerifyError || e is NoClassDefFoundError) {
                Log.e(TAG, "SDK 类验证失败，可能 Helper.install() 未正确执行或 SDK 版本不兼容")
            }
        }
    }
}
