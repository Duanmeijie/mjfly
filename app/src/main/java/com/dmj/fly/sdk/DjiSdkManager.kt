package com.dmj.fly.sdk

import dji.v5.common.error.IDJIError
import dji.v5.manager.SDKManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object DjiSdkManager {

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: StateFlow<ConnectionState> = _connectionState

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered

    fun onRegisterSuccess() {
        _isRegistered.value = true
    }

    fun onRegisterFailure(error: IDJIError) {
        _isRegistered.value = false
    }

    fun onProductConnect(productId: Int) {
        _connectionState.value = ConnectionState.Connected(productId)
    }

    fun onProductChanged(productId: Int) {
        _connectionState.value = ConnectionState.Connected(productId)
    }

    fun onProductDisconnect() {
        _connectionState.value = ConnectionState.Disconnected
    }
}

sealed class ConnectionState {
    data object Disconnected : ConnectionState()
    data class Connected(val productId: Int) : ConnectionState()
}