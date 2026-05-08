package com.dmj.fly.sdk

import android.content.Context
import com.dji.sdk.keyvalue.key.DJIKey
import com.dji.v5.common.callback.CommonCallbacks
import com.dji.v5.common.error.IDJIError
import com.dji.v5.manager.KeyManager
import com.dji.v5.manager.SDKManager
import com.dji.v5.manager.datacenter.ProductConnectionState
import com.dji.v5.manager.datacenter.ProductType
import com.dmj.fly.domain.model.Result
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * DJI SDK 管理单例
 * 包名:com.dmj.fly
 * App Key:EE45A36E38A16E49C8CF38A8
 */
object DjiSdkManager {

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered

    private val keyManager = KeyManager.getInstance()

    /**
     * 初始化 SDK（由 FlyApplication 调用）
     */
    fun onRegisterSuccess() {
        _isRegistered.value = true
        startConnectionToProduct()
    }

    /**
     * 注册失败
     */
    fun onRegisterFailure(error: IDJIError) {
        _isRegistered.value = false
    }

    /**
     * 产品连接
     */
    fun onProductConnect(productType: ProductType) {
        _connectionState.value = ConnectionState.Connected(productType.name)
    }

    /**
     * 产品断开
     */
    fun onProductDisconnect() {
        _connectionState.value = ConnectionState.Disconnected
    }

    fun startConnectionToProduct() {
        SDKManager.getInstance().startConnection()
    }

    fun stopConnectionToProduct() {
        SDKManager.getInstance().stopConnection()
    }

    fun isProductConnected(): Boolean {
        return SDKManager.getInstance().productConnectionState == ProductConnectionState.CONNECTED
    }

    /**
     * 监听 Key 变化
     * ⚠️ 真机测试警告:此功能需在连接真实 DJI 设备且 SDK 注册成功后测试
     */
    fun <T> listenKey(key: DJIKey<T>): Flow<T> = callbackFlow {
        val tag = Object()
        val listener = object : CommonCallbacks.KeyListener<T>() {
            override fun onValueChange(oldValue: T, newValue: T) {
                newValue?.let { trySend(it) }
            }
        }
        keyManager.startListening(key, tag, listener)
        awaitClose { keyManager.stopListening(key, tag) }
    }

    /**
     * 获取 Key 值
     * ⚠️ 真机测试警告:此功能需在连接真实 DJI 设备且 SDK 注册成功后测试
     */
    suspend fun <T> getKey(key: DJIKey<T>): T? = suspendCoroutine { continuation ->
        keyManager.getValue(key, object : CommonCallbacks.CompletionCallbackWithParam<T> {
            override fun onSuccess(value: T) {
                continuation.resume(value)
            }
            override fun onFailure(error: IDJIError) {
                continuation.resume(null)
            }
        })
    }

    /**
     * 设置 Key 值
     * ⚠️ 真机测试警告:此功能需在连接真实 DJI 设备、GPS 信号良好、环境安全的条件下测试
     */
    suspend fun <T> setKey(key: DJIKey<T>, value: T): Result<Unit> = suspendCoroutine { continuation ->
        keyManager.setValue(key, value, object : CommonCallbacks.CompletionCallback {
            override fun onSuccess() {
                continuation.resume(Result.Success(Unit))
            }
            override fun onFailure(error: IDJIError) {
                continuation.resume(Result.Error(error.description()))
            }
        })
    }
}

/**
 * 产品连接状态
 */
sealed class ConnectionState {
    data object Disconnected : ConnectionState()
    data class Connected(val productType: String) : ConnectionState()
}
