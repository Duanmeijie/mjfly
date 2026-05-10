package com.dmj.fly.sdk

import android.util.Log
import dji.v5.common.error.IDJIError
import dji.v5.common.register.DJISDKInitEvent
import dji.v5.manager.SDKManager
import dji.v5.manager.interfaces.SDKManagerCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object DjiSdkManager {

    private const val TAG = "DjiSdkManager"

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered

    private val _activationState = MutableStateFlow<ActivationState>(ActivationState.NotActivated)
    val activationState: StateFlow<ActivationState> = _activationState

    fun initialize(context: android.content.Context) {
        Log.d(TAG, "Initializing DJI SDK V5...")

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
                Log.d(TAG, "Product connected: productId=$productId")
                _connectionState.value = ConnectionState.Connected(productId)
            }

            override fun onProductDisconnect(productId: Int) {
                Log.d(TAG, "Product disconnected: productId=$productId")
                _connectionState.value = ConnectionState.Disconnected
            }

            override fun onProductChanged(productId: Int) {
                Log.d(TAG, "Product changed: productId=$productId")
                _connectionState.value = ConnectionState.Connected(productId)
            }

            override fun onDatabaseDownloadProgress(current: Long, total: Long) {
                val progress = (current * 100 / total).toInt()
                Log.d(TAG, "Database download progress: $progress% ($current/$total)")
            }
        })
    }

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
        _connectionState.value = ConnectionState.Connected(productId)
        Log.d(TAG, "onProductConnect called: productId=$productId")
    }

    fun onProductChanged(productId: Int) {
        _connectionState.value = ConnectionState.Connected(productId)
        Log.d(TAG, "onProductChanged called: productId=$productId")
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

sealed class ConnectionState {
    data object Disconnected : ConnectionState()
    data class Connected(val productId: Int) : ConnectionState()
}

sealed class ActivationState {
    data object NotActivated : ActivationState()
    data object Activated : ActivationState()
    data class ActivationFailed(val error: String) : ActivationState()
}
