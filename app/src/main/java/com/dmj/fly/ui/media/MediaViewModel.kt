package com.dmj.fly.ui.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmj.fly.domain.model.MediaFile
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class MediaUiState(
    val files: List<MediaFile> = emptyList(),
    val isLoading: Boolean = false,
    val downloadProgress: Map<String, Int> = emptyMap()
)

@HiltViewModel
class MediaViewModel @Inject constructor(
    private val mediaRepository: MediaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MediaUiState())
    val uiState: StateFlow<MediaUiState> = _uiState.asStateFlow()

    init {
        observeDownloadProgress()
    }

    private fun observeDownloadProgress() {
        viewModelScope.launch {
            mediaRepository.getDownloadProgress().collect { (fileName, progress) ->
                _uiState.value = _uiState.value.copy(
                    downloadProgress = _uiState.value.downloadProgress + (fileName to progress)
                )
            }
        }
    }

    fun loadFiles() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            when (val result = mediaRepository.refreshFileList()) {
                is Result.Success -> {
                    _uiState.value = _uiState.value.copy(
                        files = result.data,
                        isLoading = false
                    )
                }
                is Result.Error -> {
                    Timber.e("loadFiles failed: ${result.message}")
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            }
        }
    }

    fun downloadFile(file: MediaFile) {
        viewModelScope.launch {
            when (val result = mediaRepository.downloadFile(file)) {
                is Result.Success -> {
                    Timber.d("Download success: ${result.data}")
                }
                is Result.Error -> {
                    Timber.e("downloadFile failed: ${result.message}")
                }
            }
        }
    }

    fun deleteFile(file: MediaFile) {
        viewModelScope.launch {
            when (val result = mediaRepository.deleteFile(file)) {
                is Result.Success -> {
                    loadFiles()
                }
                is Result.Error -> {
                    Timber.e("deleteFile failed: ${result.message}")
                }
            }
        }
    }
}