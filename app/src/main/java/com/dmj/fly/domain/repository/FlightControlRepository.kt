package com.dmj.fly.domain.repository

import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.model.Waypoint
import kotlinx.coroutines.flow.Flow

interface FlightControlRepository {
    suspend fun takeOff(): Result<Unit>
    suspend fun land(): Result<Unit>
    suspend fun confirmLanding(): Result<Unit>
    suspend fun startRTH(): Result<Unit>
    suspend fun cancelRTH(): Result<Unit>
    suspend fun enableVirtualStick(): Result<Unit>
    suspend fun disableVirtualStick(): Result<Unit>
    suspend fun sendVirtualStickData(pitch: Float, roll: Float, yaw: Float, throttle: Float): Result<Unit>
    suspend fun uploadWayline(waypoints: List<Waypoint>): Result<Unit>
    suspend fun startWayline(): Result<Unit>
    suspend fun pauseWayline(): Result<Unit>
    suspend fun resumeWayline(): Result<Unit>
    suspend fun stopWayline(): Result<Unit>
    fun isLandingConfirmationNeeded(): Flow<Boolean>
}