package com.dmj.fly.domain.repository

import com.dmj.fly.domain.model.CameraMode
import com.dmj.fly.domain.model.CameraState
import com.dmj.fly.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface CameraRepository {
    suspend fun shootPhoto(): Result<Unit>
    suspend fun startRecord(): Result<Unit>
    suspend fun stopRecord(): Result<Unit>
    suspend fun setMode(mode: CameraMode): Result<Unit>
    suspend fun setZoom(zoomFactor: Float): Result<Unit>
    fun getCameraState(): Flow<CameraState>
}