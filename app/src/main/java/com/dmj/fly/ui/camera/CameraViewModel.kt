package com.dmj.fly.ui.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmj.fly.domain.model.CameraMode
import com.dmj.fly.domain.model.CameraState
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.CameraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class CameraUiState(
    val cameraState: CameraState = CameraState(),
    val isRecording: Boolean = false,
    val zoomMin: Float = 1f,
    val zoomMax: Float = 1f,
    val currentZoom: Float = 1f
)

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraRepository: CameraRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CameraUiState())
    val uiState: StateFlow<CameraUiState> = _uiState.asStateFlow()

    init {
        observeCameraState()
    }

    private fun observeCameraState() {
        viewModelScope.launch {
            cameraRepository.getCameraState().collect { state ->
                _uiState.value = _uiState.value.copy(
                    cameraState = state,
                    isRecording = state.isRecording
                )
            }
        }
    }

    fun setMode(mode: CameraMode) {
        viewModelScope.launch {
            val result = cameraRepository.setMode(mode)
            if (result is Result.Error) {
                Timber.e("setMode failed: ${result.message}")
            }
        }
    }

    fun shootPhoto() {
        viewModelScope.launch {
            val result = cameraRepository.shootPhoto()
            if (result is Result.Error) {
                Timber.e("shootPhoto failed: ${result.message}")
            }
        }
    }

    fun startRecord() {
        viewModelScope.launch {
            val result = cameraRepository.startRecord()
            if (result is Result.Error) {
                Timber.e("startRecord failed: ${result.message}")
            }
        }
    }

    fun stopRecord() {
        viewModelScope.launch {
            val result = cameraRepository.stopRecord()
            if (result is Result.Error) {
                Timber.e("stopRecord failed: ${result.message}")
            }
        }
    }

    fun toggleRecord() {
        if (_uiState.value.isRecording) {
            stopRecord()
        } else {
            startRecord()
        }
    }

    fun setZoom(zoomFactor: Float) {
        viewModelScope.launch {
            val result = cameraRepository.setZoom(zoomFactor)
            if (result is Result.Error) {
                Timber.e("setZoom failed: ${result.message}")
            }
        }
    }
}