package com.dmj.fly.ui.control

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmj.fly.domain.repository.AircraftRepository
import com.dmj.fly.domain.repository.FlightControlRepository
import com.dmj.fly.sdk.ConnectionState
import com.dmj.fly.sdk.DjiSdkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class ControlUiState(
    val connectionStatus: String = "未连接",
    val battery: Int = 0,
    val isFlying: Boolean = false,
    val isVirtualStickEnabled: Boolean = false,
    val needLandingConfirmation: Boolean = false
)

@HiltViewModel
class ControlViewModel @Inject constructor(
    private val flightControlRepository: FlightControlRepository,
    private val aircraftRepository: AircraftRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ControlUiState())
    val uiState: StateFlow<ControlUiState> = _uiState.asStateFlow()

    private var virtualStickJob: Job? = null

    private var currentPitch = 0f
    private var currentRoll = 0f
    private var currentYaw = 0f
    private var currentThrottle = 0f

    init {
        observeAircraftStatus()
        observeLandingConfirmation()
    }

    private fun observeAircraftStatus() {
        viewModelScope.launch {
            combine(
                DjiSdkManager.connectionState,
                aircraftRepository.getAircraftStatus()
            ) { connectionState, status ->
                Pair(connectionState, status)
            }.collect { (connectionState, status) ->
                val statusText = when (connectionState) {
                    is ConnectionState.Connected -> "已连接 (ID: ${connectionState.productId})"
                    is ConnectionState.Disconnected -> "未连接"
                }
                _uiState.value = _uiState.value.copy(
                    connectionStatus = statusText,
                    battery = status.batteryPercentage,
                    isFlying = status.isFlying
                )
            }
        }
    }

    private fun observeLandingConfirmation() {
        viewModelScope.launch {
            flightControlRepository.isLandingConfirmationNeeded().collect { needed ->
                _uiState.value = _uiState.value.copy(needLandingConfirmation = needed)
            }
        }
    }

    fun updateVirtualStickData(pitch: Float, roll: Float, yaw: Float, throttle: Float) {
        currentPitch = pitch
        currentRoll = roll
        currentYaw = yaw
        currentThrottle = throttle
    }

    fun enableVirtualStick() {
        viewModelScope.launch {
            flightControlRepository.enableVirtualStick()
            _uiState.value = _uiState.value.copy(isVirtualStickEnabled = true)
            startVirtualStickLoop()
        }
    }

    fun disableVirtualStick() {
        viewModelScope.launch {
            stopVirtualStickLoop()
            flightControlRepository.disableVirtualStick()
            _uiState.value = _uiState.value.copy(isVirtualStickEnabled = false)
        }
    }

    private fun startVirtualStickLoop() {
        virtualStickJob?.cancel()
        virtualStickJob = viewModelScope.launch {
            while (isActive) {
                flightControlRepository.sendVirtualStickData(
                    currentPitch, currentRoll, currentYaw, currentThrottle
                )
                delay(50)
            }
        }
    }

    private fun stopVirtualStickLoop() {
        virtualStickJob?.cancel()
        virtualStickJob = null
    }

    fun takeOff() {
        viewModelScope.launch {
            flightControlRepository.takeOff()
        }
    }

    fun land() {
        viewModelScope.launch {
            flightControlRepository.land()
        }
    }

    fun startRth() {
        viewModelScope.launch {
            flightControlRepository.startRTH()
        }
    }

    fun confirmLanding() {
        viewModelScope.launch {
            flightControlRepository.confirmLanding()
            _uiState.value = _uiState.value.copy(needLandingConfirmation = false)
        }
    }

    fun emergencyStop() {
        viewModelScope.launch {
            disableVirtualStick()
            flightControlRepository.land()
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopVirtualStickLoop()
    }
}
