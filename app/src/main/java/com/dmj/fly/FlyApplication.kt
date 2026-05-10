package com.dmj.fly

import android.app.Application
import android.content.Context
import android.util.Log
import dji.v5.common.error.IDJIError
import dji.v5.common.register.DJISDKInitEvent
import dji.v5.manager.SDKManager
import dji.v5.manager.interfaces.SDKManagerCallback
import com.cySdkyc.clx.Helper

class FlyApplication : Application() {

    companion object {
        private const val TAG = "MJFLY"
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Helper.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initializeSDK()
    }

    private fun initializeSDK() {
        Log.d(TAG, "开始初始化 DJI MSDK V5...")

        SDKManager.getInstance().init(this, object : SDKManagerCallback {
            override fun onInitProcess(event: DJISDKInitEvent, totalProcess: Int) {
                Log.d(TAG, "初始化进度: $event, $totalProcess")
                if (event == DJISDKInitEvent.INITIALIZE_COMPLETE) {
                    Log.d(TAG, "初始化完成，正在注册...")
                    SDKManager.getInstance().registerApp()
                }
            }

            override fun onRegisterSuccess() {
                Log.d(TAG, "SDK 注册成功！App Key 验证通过")
                onRegisterSuccess()
            }

            override fun onRegisterFailure(error: IDJIError) {
                Log.e(TAG, "SDK 注册失败: ${error.description()}")
                Log.e(TAG, "请检查:1. 网络连接 2. App Key 是否正确 3. 包名是否匹配")
                onRegisterFailure(error)
            }

            override fun onProductConnect(productId: Int) {
                Log.d(TAG, "产品已连接: productId=$productId")
                onProductConnected(productId)
            }

            override fun onProductDisconnect(productId: Int) {
                Log.d(TAG, "产品已断开连接: productId=$productId")
                onProductDisconnected()
            }

            override fun onProductChanged(productId: Int) {
                Log.d(TAG, "产品变更: productId=$productId")
                onProductChanged(productId)
            }

            override fun onDatabaseDownloadProgress(current: Long, total: Long) {
                Log.d(TAG, "数据库下载进度: $current / $total")
            }
        })
    }

    private fun onRegisterSuccess() {
        com.dmj.fly.sdk.DjiSdkManager.onRegisterSuccess()
    }

    private fun onRegisterFailure(error: IDJIError) {
        com.dmj.fly.sdk.DjiSdkManager.onRegisterFailure(error)
    }

    private fun onProductConnected(productId: Int) {
        com.dmj.fly.sdk.DjiSdkManager.onProductConnect(productId)
    }

    private fun onProductChanged(productId: Int) {
        com.dmj.fly.sdk.DjiSdkManager.onProductChanged(productId)
    }

    private fun onProductDisconnected() {
        com.dmj.fly.sdk.DjiSdkManager.onProductDisconnect()
    }
}