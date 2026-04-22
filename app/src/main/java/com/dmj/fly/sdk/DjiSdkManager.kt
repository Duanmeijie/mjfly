package com.dmj.fly.sdk

import android.content.Context
import dji.sdk.products.Aircraft
import dji.sdk.base.BaseProduct
import dji.v5.common.error.IDJIError
import dji.v5.manager.SDKManager
import dji.v5.manager.interfaces.SDKManagerCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class ConnectionState {
    data object Disconnected : ConnectionState()
    data class Connected(val modelName: String) : ConnectionState()
}

object DjiSdkManager {

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    fun initialize(context: Context) {
        SDKManager.getInstance().init(context, object : SDKManagerCallback {
            override fun onRegisterSuccess() {
                SDKManager.getInstance().startConnectionToProduct()
            }

            override fun onRegisterFailure(error: IDJIError) {
            }

            override fun onProductConnect(product: BaseProduct) {
                _connectionState.value = ConnectionState.Connected(product.modelName)
            }

            override fun onProductDisconnect() {
                _connectionState.value = ConnectionState.Disconnected
            }

            override fun onProductChanged(product: BaseProduct) {
            }
        })
    }

    fun getAircraft(): Aircraft? {
        return SDKManager.getInstance().product as? Aircraft
    }
}