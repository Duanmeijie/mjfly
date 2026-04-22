package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper
import com.dmj.fly.domain.model.CameraMode
import com.dmj.fly.domain.model.CameraState
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.CameraRepository
import dji.sdk.keyvalue.key.CameraKey
import dji.sdk.keyvalue.value.camera.CameraModeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraRepositoryImpl @Inject constructor(
    private val keyManagerHelper: KeyManagerHelper
) : CameraRepository {

    override suspend fun shootPhoto(): Result<Unit> {
        return keyManagerHelper.setKey(CameraKey.KeyStartShootPhoto, true)
            .onFailure { Timber.e("shootPhoto failed: ${it.message}") }
    }

    override suspend fun startRecord(): Result<Unit> {
        return keyManagerHelper.setKey(CameraKey.KeyStartRecord, true)
            .onFailure { Timber.e("startRecord failed: ${it.message}") }
    }

    override suspend fun stopRecord(): Result<Unit> {
        return keyManagerHelper.setKey(CameraKey.KeyStopRecord, true)
            .onFailure { Timber.e("stopRecord failed: ${it.message}") }
    }

    override suspend fun setMode(mode: CameraMode): Result<Unit> {
        val djiMode = when (mode) {
            CameraMode.PHOTO -> CameraModeType.SHOOT_PHOTO
            CameraMode.VIDEO -> CameraModeType.RECORD_VIDEO
            CameraMode.UNKNOWN -> CameraModeType.UNKNOWN
        }
        return keyManagerHelper.setKey(CameraKey.KeyCameraMode, djiMode)
            .onFailure { Timber.e("setMode failed: ${it.message}") }
    }

    override suspend fun setZoom(zoomFactor: Float): Result<Unit> {
        return keyManagerHelper.setKey(CameraKey.KeyCameraZoomRatios, zoomFactor)
            .onFailure { Timber.e("setZoom failed: ${it.message}") }
    }

    override fun getCameraState(): Flow<CameraState> {
        val modeKey = CameraKey.KeyCameraMode
        val recordingKey = CameraKey.KeyRecordingState
        val storageKey = CameraKey.KeySDCardAvailableSpace

        val modeFlow = keyManagerHelper.listenKey(modeKey).map { type ->
            when (type) {
                CameraModeType.SHOOT_PHOTO -> CameraMode.PHOTO
                CameraModeType.RECORD_VIDEO -> CameraMode.VIDEO
                else -> CameraMode.UNKNOWN
            }
        }

        val recordingFlow = keyManagerHelper.listenKey(recordingKey).map { state ->
            state.isRecording
        }

        val storageFlow = keyManagerHelper.listenKey(storageKey).map { info ->
            info.availableCapacity.toInt()
        }

        return combine(modeFlow, recordingFlow, storageFlow) { mode, isRecording, availableSpace ->
            CameraState(
                mode = mode,
                isRecording = isRecording,
                storageLocation = 0,
                availablePhotoCount = availableSpace,
                availableVideoDuration = availableSpace / 10
            )
        }
    }

    private fun Result<Unit>.onFailure(action: (Exception) -> Unit): Result<Unit> {
        if (this is com.dmj.fly.domain.model.Result.Error) {
            action(Exception(message))
        }
        return this
    }
}