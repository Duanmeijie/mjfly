package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper
import com.dmj.fly.domain.model.CameraMode
import com.dmj.fly.domain.model.CameraState
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.CameraRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraRepositoryImpl @Inject constructor(
    private val keyManagerHelper: KeyManagerHelper
) : CameraRepository {

    private val _mockMode = MutableStateFlow(CameraMode.UNKNOWN)
    private val _mockIsRecording = MutableStateFlow(false)
    private val _mockIsShootingPhoto = MutableStateFlow(false)

    override suspend fun shootPhoto(): Result<Unit> {
        Timber.d("shootPhoto called")
        _mockIsShootingPhoto.value = true
        return Result.Success(Unit)
    }

    override suspend fun startRecord(): Result<Unit> {
        Timber.d("startRecord called")
        _mockIsRecording.value = true
        return Result.Success(Unit)
    }

    override suspend fun stopRecord(): Result<Unit> {
        Timber.d("stopRecord called")
        _mockIsRecording.value = false
        return Result.Success(Unit)
    }

    override suspend fun setMode(mode: CameraMode): Result<Unit> {
        Timber.d("setMode called: $mode")
        _mockMode.value = mode
        return Result.Success(Unit)
    }

    override suspend fun setZoom(zoomFactor: Float): Result<Unit> {
        Timber.d("setZoom called: $zoomFactor")
        return Result.Success(Unit)
    }

    override fun getCameraState(): Flow<CameraState> {
        return combine(_mockMode, _mockIsRecording, _mockIsShootingPhoto) { mode, isRecording, isShooting ->
            CameraState(
                mode = mode,
                isRecording = isRecording,
                isShootingPhoto = isShooting,
                storageLocation = 0,
                availablePhotoCount = 0,
                availableVideoDuration = 0
            )
        }
    }
}
