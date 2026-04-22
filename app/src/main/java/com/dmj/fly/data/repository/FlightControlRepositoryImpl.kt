package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.model.Waypoint
import com.dmj.fly.domain.repository.FlightControlRepository
import dji.sdk.keyvalue.key.FlightControllerKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightControlRepositoryImpl @Inject constructor(
    private val keyManagerHelper: KeyManagerHelper
) : FlightControlRepository {

    override suspend fun takeOff(): Result<Unit> {
        return keyManagerHelper.setKey(FlightControllerKey.KeyStartTakeoff, true)
            .onFailure { Timber.e("takeOff failed: ${it.message}") }
    }

    override suspend fun land(): Result<Unit> {
        return keyManagerHelper.setKey(FlightControllerKey.KeyStartAutoLanding, true)
            .onFailure { Timber.e("land failed: ${it.message}") }
    }

    override suspend fun confirmLanding(): Result<Unit> {
        return keyManagerHelper.setKey(FlightControllerKey.KeyConfirmLanding, true)
            .onFailure { Timber.e("confirmLanding failed: ${it.message}") }
    }

    override suspend fun startRTH(): Result<Unit> {
        return keyManagerHelper.setKey(FlightControllerKey.KeyStartGoHome, true)
            .onFailure { Timber.e("startRTH failed: ${it.message}") }
    }

    override suspend fun cancelRTH(): Result<Unit> {
        return keyManagerHelper.setKey(FlightControllerKey.KeyCancelGoHome, true)
            .onFailure { Timber.e("cancelRTH failed: ${it.message}") }
    }

    override suspend fun enableVirtualStick(): Result<Unit> {
        return keyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickEnabled, true)
            .onFailure { Timber.e("enableVirtualStick failed: ${it.message}") }
    }

    override suspend fun disableVirtualStick(): Result<Unit> {
        return keyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickEnabled, false)
            .onFailure { Timber.e("disableVirtualStick failed: ${it.message}") }
    }

    override suspend fun sendVirtualStickData(
        pitch: Float,
        roll: Float,
        yaw: Float,
        throttle: Float
    ): Result<Unit> {
        return try {
            keyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickLeftVertical, throttle)
            keyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickLeftHorizontal, yaw)
            keyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickRightVertical, pitch)
            keyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickRightHorizontal, roll)
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e("sendVirtualStickData failed: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun uploadWayline(waypoints: List<Waypoint>): Result<Unit> {
        return Result.Error("Wayline upload not implemented for MSDK V5 5.17")
    }

    override suspend fun startWayline(): Result<Unit> {
        return Result.Error("Wayline control not implemented for MSDK V5 5.17")
    }

    override suspend fun pauseWayline(): Result<Unit> {
        return Result.Error("Wayline control not implemented for MSDK V5 5.17")
    }

    override suspend fun resumeWayline(): Result<Unit> {
        return Result.Error("Wayline control not implemented for MSDK V5 5.17")
    }

    override suspend fun stopWayline(): Result<Unit> {
        return Result.Error("Wayline control not implemented for MSDK V5 5.17")
    }

    override fun isLandingConfirmationNeeded(): Flow<Boolean> {
        return keyManagerHelper.listenKey(FlightControllerKey.KeyIsLandingConfirmationNeeded)
            .map { it }
    }

    private fun Result<Unit>.onFailure(action: (Exception) -> Unit): Result<Unit> {
        if (this is com.dmj.fly.domain.model.Result.Error) {
            action(Exception(message))
        }
        return this
    }
}