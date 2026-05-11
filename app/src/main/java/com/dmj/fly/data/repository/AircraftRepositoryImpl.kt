package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper
import com.dmj.fly.domain.model.AircraftStatus
import com.dmj.fly.domain.model.FlightTelemetry
import com.dmj.fly.domain.repository.AircraftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AircraftRepositoryImpl @Inject constructor(
    private val keyManagerHelper: KeyManagerHelper
) : AircraftRepository {

    private val _mockConnectionState = MutableStateFlow(false)
    private val _mockFlightMode = MutableStateFlow("P-GPS")
    private val _mockGpsSignal = MutableStateFlow(5)
    private val _mockBattery = MutableStateFlow(100)
    private val _mockIsFlying = MutableStateFlow(false)
    private val _mockMotorsOn = MutableStateFlow(false)
    private val _mockFlightTime = MutableStateFlow(0L)
    private val _mockLatitude = MutableStateFlow(0.0)
    private val _mockLongitude = MutableStateFlow(0.0)
    private val _mockAltitude = MutableStateFlow(0.0)

    override fun getAircraftStatus(): Flow<AircraftStatus> {
        return combine(
            _mockConnectionState,
            _mockFlightMode,
            _mockGpsSignal,
            _mockBattery,
            _mockIsFlying,
            _mockMotorsOn,
            _mockFlightTime,
            _mockLatitude,
            _mockLongitude,
            _mockAltitude
        ) { values ->
            AircraftStatus(
                isConnected = values[0] as Boolean,
                flightMode = values[1] as String,
                gpsSignalLevel = values[2] as Int,
                batteryPercentage = values[3] as Int,
                isFlying = values[4] as Boolean,
                isMotorsOn = values[5] as Boolean,
                flightTime = values[6] as Long,
                latitude = values[7] as Double,
                longitude = values[8] as Double,
                altitude = values[9] as Double
            )
        }
    }

    override fun getTelemetry(): Flow<FlightTelemetry> {
        return combine(
            _mockLatitude,
            _mockLongitude,
            _mockAltitude
        ) { lat, lon, alt ->
            FlightTelemetry(
                latitude = lat,
                longitude = lon,
                relativeAltitude = alt.toFloat(),
                ultrasonicHeight = 0f,
                takeoffAltitude = 0f,
                pitch = 0f,
                roll = 0f,
                yaw = 0f,
                velocityX = 0f,
                velocityY = 0f,
                velocityZ = 0f
            )
        }
    }

    override fun getConnectionState(): Flow<Boolean> = _mockConnectionState

    fun updateConnectionState(connected: Boolean) {
        _mockConnectionState.value = connected
    }

    fun updateFlightMode(mode: String) {
        _mockFlightMode.value = mode
    }

    fun updateBattery(percentage: Int) {
        _mockBattery.value = percentage
    }

    fun updateLocation(lat: Double, lon: Double, alt: Double) {
        _mockLatitude.value = lat
        _mockLongitude.value = lon
        _mockAltitude.value = alt
    }
}
