package com.dmj.fly.sdk

import android.content.Context
import dji.sdk.sdkmanager.DJISDKManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

enum class ConnectionState {
    DISCONNECTED,
    CONNECTED,
    UNKNOWN
}

object DjiSdkManager {

    private var sdkManager: DJISDKManager? = null
    private var aircraft: Aircraft? = null

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered

    private val _connectionState = MutableStateFlow(ConnectionState.DISCONNECTED)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    fun initialize(context: Context) {
        sdkManager = DJISDKManager.getInstance()
        sdkManager?.init(context, object : DJISDKManager.SDKManagerCallback {
            override fun onRegisterSuccess() {
                _isRegistered.value = true
                Timber.d("DJI SDK registered successfully")
            }

            override fun onRegisterFailure(errorCode: Int) {
                _isRegistered.value = false
                Timber.e("DJI SDK registration failed: $errorCode")
            }

            override fun onProductConnect(product: Any?) {
                aircraft = product as? Aircraft
                _connectionState.value = ConnectionState.CONNECTED
                Timber.d("Product connected: ${product?.javaClass?.simpleName}")
            }

            override fun onProductDisconnect() {
                aircraft = null
                _connectionState.value = ConnectionState.DISCONNECTED
                Timber.d("Product disconnected")
            }

            override fun onDatabaseError(errorCode: Int) {
                Timber.e("Database error: $errorCode")
            }
        })
    }

    fun startConnectionToProduct() {
        sdkManager?.startConnectionToProduct()
    }

    fun getAircraft(): Aircraft? = aircraft
}