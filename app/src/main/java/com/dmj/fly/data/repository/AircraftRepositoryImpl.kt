package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.tello.TelloStateReceiver
import com.dmj.fly.domain.model.AircraftStatus
import com.dmj.fly.domain.model.FlightTelemetry
import com.dmj.fly.domain.repository.AircraftRepository
import com.dmj.fly.sdk.ConnectionState
import com.dmj.fly.sdk.DjiSdkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AircraftRepositoryImpl @Inject constructor(
    private val telloStateReceiver: TelloStateReceiver
) : AircraftRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    // 使用真实连接状态
    private val _connectionState: Flow<Boolean> = DjiSdkManager.connectionState.map { state ->
        state is ConnectionState.Connected
    }

    init {
        // 启动 Tello 状态接收
        telloStateReceiver.startReceiving(scope)
    }

    override fun getAircraftStatus(): Flow<AircraftStatus> {
        return combine(
            _connectionState,
            telloStateReceiver.state
        ) { connected, telloState ->
            AircraftStatus(
                isConnected = connected,
                flightMode = if (telloState.height > 0) "Flying" else "Idle",
                gpsSignalLevel = 0,
                batteryPercentage = telloState.battery,
                temperature = telloState.temperature,
                isFlying = telloState.height > 0,
                isMotorsOn = telloState.height > 0,
                flightTime = telloState.flightTime.toLong(),
                altitude = telloState.height.toDouble(),
                latitude = 0.0,
                longitude = 0.0
            )
        }
    }

    override fun getTelemetry(): Flow<FlightTelemetry> {
        return telloStateReceiver.state.map { telloState ->
            FlightTelemetry(
                latitude = 0.0,
                longitude = 0.0,
                relativeAltitude = telloState.height.toFloat(),
                ultrasonicHeight = telloState.tofDistance.toFloat(),
                takeoffAltitude = 0f,
                pitch = telloState.pitch.toFloat(),
                roll = telloState.roll.toFloat(),
                yaw = telloState.yaw.toFloat(),
                velocityX = telloState.speedX.toFloat(),
                velocityY = telloState.speedY.toFloat(),
                velocityZ = telloState.speedZ.toFloat()
            )
        }
    }

    override fun getConnectionState(): Flow<Boolean> = _connectionState
}
