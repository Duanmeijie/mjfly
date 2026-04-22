package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper
import com.dmj.fly.domain.model.CameraMode
import com.dmj.fly.domain.model.CameraState
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.CameraRepository
import dji.sdk.keyvalue.key.CameraKey
import dji.sdk.keyvalue.key.GimbalKey
import dji.sdk.keyvalue.value.camera.CameraModeType
import dji.sdk.keyvalue.value.camera.CameraRecordingState
import dji.sdk.keyvalue.value.camera.CameraStorageInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CameraRepositoryImpl @Inject constructor() : CameraRepository {

    override suspend fun shootPhoto(): Result<Unit> {
        return KeyManagerHelper.actionKey(CameraKey.KeyShootPhotoAction)
            .onFailure { Timber.e("shootPhoto failed: ${it.message}") }
    }

    override suspend fun startRecord(): Result<Unit> {
        return KeyManagerHelper.actionKey(CameraKey.KeyStartRecordAction)
            .onFailure { Timber.e("startRecord failed: ${it.message}") }
    }

    override suspend fun stopRecord(): Result<Unit> {
        return KeyManagerHelper.actionKey(CameraKey.KeyStopRecordAction)
            .onFailure { Timber.e("stopRecord failed: ${it.message}") }
    }

    override suspend fun setMode(mode: CameraMode): Result<Unit> {
        val djiMode = when (mode) {
            CameraMode.PHOTO -> CameraModeType.PHOTO
            CameraMode.VIDEO -> CameraModeType.VIDEO
            CameraMode.UNKNOWN -> CameraModeType.UNKNOWN
        }
        return KeyManagerHelper.setKey(CameraKey.KeyCameraMode, djiMode)
            .onFailure { Timber.e("setMode failed: ${it.message}") }
    }

    override suspend fun setZoom(zoomFactor: Float): Result<Unit> {
        return KeyManagerHelper.setKey(CameraKey.KeyCameraZoomRatios, zoomFactor)
            .onFailure { Timber.e("setZoom failed: ${it.message}") }
    }

    override fun getCameraState(): Flow<CameraState> {
        val modeKey = CameraKey.KeyCameraMode
        val recordingKey = CameraKey.KeyRecordingState
        val storageKey = CameraKey.KeySDCardAvailableSpace

        val modeFlow = KeyManagerHelper.listenKey<CameraModeType>(modeKey).map { type ->
            when (type) {
                CameraModeType.PHOTO -> CameraMode.PHOTO
                CameraModeType.VIDEO -> CameraMode.VIDEO
                else -> CameraMode.UNKNOWN
            }
        }
        
        val recordingFlow = KeyManagerHelper.listenKey<CameraRecordingState>(recordingKey).map { state ->
            state.isRecording
        }

        val storageFlow = KeyManagerHelper.listenKey<CameraStorageInfo>(storageKey).map { info ->
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