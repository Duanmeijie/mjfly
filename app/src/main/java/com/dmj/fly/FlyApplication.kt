package com.dmj.fly

import android.app.Application
import android.content.Context
import android.util.Log
import com.dji.v5.common.error.IDJIError
import com.dji.v5.manager.SDKManager
import com.dji.v5.manager.datacenter.ProductType
import com.dji.v5.manager.interfaces.SDKManagerCallback
import com.secneo.sdk.Helper

/**
 * MJFLY 应用入口类
 * 包名:com.dmj.fly
 * App Key:EE45A36E38A16E49C8CF38A8
 */
class FlyApplication : Application() {

    companion object {
        private const val TAG = "MJFLY"
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        // MSDK V5 必须首先调用 install 方法
        // 注意:V5.10.0 之前使用 com.secneo.sdk.Helper.install
        // V5.10.0+ 可能需要使用其他助手类，请根据实际 SDK 版本确认
        Helper.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initializeSDK()
    }

    private fun initializeSDK() {
        Log.d(TAG, "开始初始化 DJI MSDK...")

        SDKManager.getInstance().register(this, object : SDKManagerCallback {
            override fun onRegisterSuccess() {
                Log.d(TAG, "✅ SDK 注册成功！App Key 验证通过")
                // SDK 注册成功，可以开始连接产品
                startProductConnection()
            }

            override fun onRegisterFailure(error: IDJIError) {
                Log.e(TAG, "❌ SDK 注册失败: ${error.description()}")
                Log.e(TAG, "请检查:1. 网络连接 2. App Key 是否正确 3. 包名是否匹配")
            }

            override fun onProductConnect(product: com.dji.sdk.base.BaseProduct?) {
                product?.let {
                    Log.d(TAG, "✅ 产品已连接: ${it.modelName} (${it.productType})")
                    // 可以在这里更新 UI 或启动业务逻辑
                    notifyProductConnected(it.productType)
                }
            }

            override fun onProductDisconnect() {
                Log.d(TAG, "⚠️ 产品已断开连接")
                notifyProductDisconnected()
            }

            override fun onProductChanged(product: com.dji.sdk.base.BaseProduct?) {
                product?.let {
                    Log.d(TAG, "📡 产品变更: ${it.modelName}")
                }
            }

            override fun onDatabaseDownloadProgress(current: Int, total: Int) {
                Log.d(TAG, "📥 数据库下载进度: $current / $total")
            }

            override fun onDatabaseDownloadSuccess() {
                Log.d(TAG, "✅ 数据库下载完成")
            }

            override fun onDatabaseDownloadFailure(error: IDJIError) {
                Log.e(TAG, "❌ 数据库下载失败: ${error.description()}")
            }
        })
    }

    private fun startProductConnection() {
        Log.d(TAG, "开始连接产品...")
        SDKManager.getInstance().startConnection()
    }

    private fun notifyProductConnected(productType: ProductType) {
        // 通知 DjiSdkManager 产品已连接
        com.dmj.fly.sdk.DjiSdkManager.onProductConnect(productType)
    }

    private fun notifyProductDisconnected() {
        // 通知 DjiSdkManager 产品已断开
        com.dmj.fly.sdk.DjiSdkManager.onProductDisconnect()
    }
}
