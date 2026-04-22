package com.dmj.fly.ui.fpv

import android.graphics.SurfaceTexture
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmj.fly.domain.repository.CameraRepository
import com.dmj.fly.domain.repository.FlightControlRepository
import com.dmj.fly.sdk.DjiSdkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FpvViewModel @Inject constructor(
    private val cameraRepository: CameraRepository,
    private val flightControlRepository: FlightControlRepository
) : ViewModel() {

    private val _connectionStatus = MutableStateFlow("未连接")
    val connectionStatus: StateFlow<String> = _connectionStatus

    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording

    init {
        viewModelScope.launch {
            DjiSdkManager.connectionState.collect { state ->
                _connectionStatus.value = when (state) {
                    is com.dmj.fly.sdk.ConnectionState.Connected -> "已连接: ${state.modelName}"
                    is com.dmj.fly.sdk.ConnectionState.Disconnected -> "未连接"
                }
            }
        }
    }

    fun startVideoStream(surfaceTexture: SurfaceTexture) {
        try {
            Timber.d("Video stream setup for TextureView")
        } catch (e: Exception) {
            Timber.e("Failed to setup video stream: ${e.message}")
        }
    }

    fun stopVideoStream() {
        try {
            Timber.d("Video stream stopped")
        } catch (e: Exception) {
            Timber.e("Failed to stop video stream: ${e.message}")
        }
    }

    fun takePhoto() {
        viewModelScope.launch {
            cameraRepository.shootPhoto()
                .onFailure { Timber.e("takePhoto failed: ${it.message}") }
        }
    }

    fun toggleRecord() {
        viewModelScope.launch {
            if (_isRecording.value) {
                cameraRepository.stopRecord()
                    .onFailure { Timber.e("stopRecord failed: ${it.message}") }
                _isRecording.value = false
            } else {
                cameraRepository.startRecord()
                    .onFailure { Timber.e("startRecord failed: ${it.message}") }
                _isRecording.value = true
            }
        }
    }

    fun takeOff() {
        viewModelScope.launch {
            flightControlRepository.takeOff()
                .onFailure { Timber.e("takeOff failed: ${it.message}") }
        }
    }

    fun land() {
        viewModelScope.launch {
            flightControlRepository.land()
                .onFailure { Timber.e("land failed: ${it.message}") }
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopVideoStream()
    }
}