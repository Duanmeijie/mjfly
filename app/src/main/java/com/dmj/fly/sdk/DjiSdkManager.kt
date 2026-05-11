package com.dmj.fly.sdk

import android.util.Log
import com.dmj.fly.FlyApplication
import dji.v5.common.error.IDJIError
import dji.v5.common.register.DJISDKInitEvent
import dji.v5.manager.SDKManager
import dji.v5.manager.interfaces.SDKManagerCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

object DjiSdkManager {

    private const val TAG = "DjiSdkManager"
    private const val CHECK_INTERVAL_MS = 2000L

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    // WiFi 连接监控协程
    private var wifiMonitorJob: Job? = null

    fun initialize(context: android.content.Context) {
        Log.d(TAG, "Initializing DJI SDK V5 (WiFi mode)...")

        // 启动 WiFi 连接状态监控
        startWifiMonitoring()

        SDKManager.getInstance().init(context, object : SDKManagerCallback {
            override fun onInitProcess(event: DJISDKInitEvent, totalProcess: Int) {
                Log.d(TAG, "Init process: $event, progress: $totalProcess%")
                if (event == DJISDKInitEvent.INITIALIZE_COMPLETE) {
                    Log.d(TAG, "SDK initialized, registering app...")
                    SDKManager.getInstance().registerApp()
                }
            }

            override fun onRegisterSuccess() {
                Log.d(TAG, "App registered successfully!")
                _isRegistered.value = true
                _activationState.value = ActivationState.Activated
            }

            override fun onRegisterFailure(error: IDJIError) {
                Log.e(TAG, "App registration failed: ${error.description()}")
                Log.e(TAG, "Check: 1. Network 2. App Key 3. Package name")
                _isRegistered.value = false
                _activationState.value = ActivationState.ActivationFailed(error.description())
            }

            override fun onProductConnect(productId: Int) {
                val ssid = FlyApplication.instance.wifiConnectionDetector.getCurrentWifiSsid()
                Log.d(TAG, "Product connected via WiFi: productId=$productId, ssid=$ssid")
                _connectionState.value = ConnectionState.Connected(productId, ssid)
            }

            override fun onProductDisconnect(productId: Int) {
                Log.d(TAG, "Product disconnected: productId=$productId")
                _connectionState.value = ConnectionState.Disconnected
            }

            override fun onProductChanged(productId: Int) {
                val ssid = FlyApplication.instance.wifiConnectionDetector.getCurrentWifiSsid()
                Log.d(TAG, "Product changed: productId=$productId, ssid=$ssid")
                _connectionState.value = ConnectionState.Connected(productId, ssid)
            }

            override fun onDatabaseDownloadProgress(current: Long, total: Long) {
                val progress = (current * 100 / total).toInt()
                Log.d(TAG, "Database download progress: $progress% ($current/$total)")
            }
        })
    }

    /**
     * 启动 WiFi 连接状态监控
     * 每 2 秒检测一次是否连接到 DJI 无人机 WiFi
     */
    private fun startWifiMonitoring() {
        wifiMonitorJob?.cancel()
        val scope = FlyApplication.applicationScope
        wifiMonitorJob = scope.launch(Dispatchers.IO) {
            while (isActive) {
                checkWifiConnection()
                delay(CHECK_INTERVAL_MS)
            }
        }
    }

    private fun checkWifiConnection() {
        val wifiDetector = FlyApplication.instance.wifiConnectionDetector
        val isWifiConnected = wifiDetector.isConnected.value
        val currentSsid = wifiDetector.getCurrentWifiSsid()

        if (isWifiConnected && currentSsid != null) {
            // 仅在当前未连接时更新状态（避免覆盖 SDK 回调的 productId）
            if (_connectionState.value is ConnectionState.Disconnected) {
                _connectionState.value = ConnectionState.Connected(
                    productId = 0,
                    ssid = currentSsid
                )
                Log.i(TAG, "WiFi drone detected: $currentSsid")
            }
        } else if (_connectionState.value is ConnectionState.Connected) {
            // WiFi 断开，恢复为未连接
            _connectionState.value = ConnectionState.Disconnected
            Log.i(TAG, "WiFi drone disconnected")
        }
    }

    // 注册状态
    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered

    private val _activationState = MutableStateFlow<ActivationState>(ActivationState.NotActivated)
    val activationState: StateFlow<ActivationState> = _activationState

    fun onRegisterSuccess() {
        _isRegistered.value = true
        _activationState.value = ActivationState.Activated
        Log.d(TAG, "onRegisterSuccess called")
    }

    fun onRegisterFailure(error: IDJIError) {
        _isRegistered.value = false
        _activationState.value = ActivationState.ActivationFailed(error.description())
        Log.d(TAG, "onRegisterFailure called: ${error.description()}")
    }

    fun onProductConnect(productId: Int) {
        val ssid = FlyApplication.instance.wifiConnectionDetector.getCurrentWifiSsid()
        _connectionState.value = ConnectionState.Connected(productId, ssid)
        Log.d(TAG, "onProductConnect called: productId=$productId, ssid=$ssid")
    }

    fun onProductChanged(productId: Int) {
        val ssid = FlyApplication.instance.wifiConnectionDetector.getCurrentWifiSsid()
        _connectionState.value = ConnectionState.Connected(productId, ssid)
        Log.d(TAG, "onProductChanged called: productId=$productId, ssid=$ssid")
    }

    fun onProductDisconnect() {
        _connectionState.value = ConnectionState.Disconnected
        Log.d(TAG, "onProductDisconnect called")
    }

    fun getConnectedAircraft(): Any? {
        return try {
            SDKManager.getInstance().javaClass.getMethod("getAircraft").invoke(SDKManager.getInstance())
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get aircraft: ${e.message}")
            null
        }
    }
}

/**
 * 连接状态
 * WiFi 直连模式：手机连接无人机 WiFi 热点进行控制
 */
sealed class ConnectionState {
    data object Disconnected : ConnectionState()
    data class Connected(
        val productId: Int,
        val ssid: String? = null
    ) : ConnectionState() {
        override fun toString(): String {
            return "WiFi(${ssid ?: "Unknown"})"
        }
    }
}

sealed class ActivationState {
    data object NotActivated : ActivationState()
    data object Activated : ActivationState()
    data class ActivationFailed(val error: String) : ActivationState()
}
