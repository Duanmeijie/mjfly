package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.model.Waypoint
import com.dmj.fly.domain.repository.FlightControlRepository
import dji.sdk.keyvalue.key.FlightControllerKey
import dji.sdk.keyvalue.value.flightcontroller.VirtualStickFlightControlData
import dji.sdk.keyvalue.value.flightcontroller.VirtualStickAdvancedSettings
import dji.sdk.keyvalue.value.flightcontroller.VirtualStickControlMode
import dji.sdk.keyvalue.value.flightcontroller.VirtualStickCoordinateSystem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightControlRepositoryImpl @Inject constructor() : FlightControlRepository {

    override suspend fun takeOff(): Result<Unit> {
        return KeyManagerHelper.actionKey(FlightControllerKey.KeyStartTakeoff)
            .onFailure { Timber.e("takeOff failed: ${it.message}") }
    }

    override suspend fun land(): Result<Unit> {
        return KeyManagerHelper.actionKey(FlightControllerKey.KeyStartAutoLanding)
            .onFailure { Timber.e("land failed: ${it.message}") }
    }

    override suspend fun confirmLanding(): Result<Unit> {
        return KeyManagerHelper.setKey(FlightControllerKey.KeyConfirmLanding, dji.sdk.keyvalue.value.common.BooleanValue(true))
            .onFailure { Timber.e("confirmLanding failed: ${it.message}") }
    }

    override suspend fun startRTH(): Result<Unit> {
        return KeyManagerHelper.actionKey(FlightControllerKey.KeyStartGoHome)
            .onFailure { Timber.e("startRTH failed: ${it.message}") }
    }

    override suspend fun cancelRTH(): Result<Unit> {
        return KeyManagerHelper.actionKey(FlightControllerKey.KeyCancelGoHome)
            .onFailure { Timber.e("cancelRTH failed: ${it.message}") }
    }

    override suspend fun enableVirtualStick(): Result<Unit> {
        val enabled = dji.sdk.keyvalue.value.common.BooleanValue(true)
        val settings = VirtualStickAdvancedSettings().apply {
            controlMode = VirtualStickControlMode.VELOCITY
            coordinateSystem = VirtualStickCoordinateSystem.NED
        }
        
        return KeyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickEnabled, enabled)
            .recoverWith { KeyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickAdvancedSettings, settings) }
            .onFailure { Timber.e("enableVirtualStick failed: ${it.message}") }
    }

    override suspend fun disableVirtualStick(): Result<Unit> {
        val disabled = dji.sdk.keyvalue.value.common.BooleanValue(false)
        return KeyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickEnabled, disabled)
            .onFailure { Timber.e("disableVirtualStick failed: ${it.message}") }
    }

    override suspend fun sendVirtualStickData(
        pitch: Float,
        roll: Float,
        yaw: Float,
        throttle: Float
    ): Result<Unit> {
        val data = VirtualStickFlightControlData().apply {
            pitch = pitch
            roll = roll
            yaw = yaw
            verticalThrottle = throttle
        }
        return KeyManagerHelper.setKey(FlightControllerKey.KeyVirtualStickFlightControlData, data)
            .onFailure { Timber.e("sendVirtualStickData failed: ${it.message}") }
    }

    override suspend fun uploadWayline(waypoints: List<Waypoint>): Result<Unit> {
        return Result.failure(Exception("Wayline upload not implemented"))
    }

    override suspend fun startWayline(): Result<Unit> {
        return KeyManagerHelper.actionKey(FlightControllerKey.KeyStartMission)
            .onFailure { Timber.e("startWayline failed: ${it.message}") }
    }

    override suspend fun pauseWayline(): Result<Unit> {
        return KeyManagerHelper.actionKey(FlightControllerKey.KeyPauseMission)
            .onFailure { Timber.e("pauseWayline failed: ${it.message}") }
    }

    override suspend fun resumeWayline(): Result<Unit> {
        return KeyManagerHelper.actionKey(FlightControllerKey.KeyResumeMission)
            .onFailure { Timber.e("resumeWayline failed: ${it.message}") }
    }

    override suspend fun stopWayline(): Result<Unit> {
        return KeyManagerHelper.actionKey(FlightControllerKey.KeyStopMission)
            .onFailure { Timber.e("stopWayline failed: ${it.message}") }
    }

    override fun isLandingConfirmationNeeded(): Flow<Boolean> {
        return KeyManagerHelper.listenKey<dji.sdk.keyvalue.value.common.BooleanValue>(FlightControllerKey.KeyIsLandingConfirmationNeeded)
            .map { it.value }
    }

    private fun Result<Unit>.recoverWith(block: () -> Result<Unit>): Result<Unit> {
        return when (this) {
            is com.dmj.fly.domain.model.Result.Success -> this
            is com.dmj.fly.domain.model.Result.Error -> block()
        }
    }

    private fun Result<Unit>.onFailure(action: (Exception) -> Unit): Result<Unit> {
        if (this is com.dmj.fly.domain.model.Result.Error) {
            action(Exception(message))
        }
        return this
    }
}