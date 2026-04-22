package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper
import com.dmj.fly.domain.model.AircraftStatus
import com.dmj.fly.domain.model.FlightTelemetry
import com.dmj.fly.domain.repository.AircraftRepository
import dji.sdk.keyvalue.key.BatteryKey
import dji.sdk.keyvalue.key.FlightControllerKey
import dji.sdk.keyvalue.value.common.Attitude
import dji.sdk.keyvalue.value.common.LocationCoordinate3D
import dji.sdk.keyvalue.value.common.Velocity3D
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AircraftRepositoryImpl @Inject constructor() : AircraftRepository {

    override fun getAircraftStatus(): Flow<AircraftStatus> {
        val connectionKey = FlightControllerKey.KeyConnectionState
        val flightModeKey = FlightControllerKey.KeyAircraftFlightMode
        val gpsSignalKey = FlightControllerKey.KeyGPSSignalLevel
        val batteryKey = BatteryKey.KeyChargeRemainingInPercent
        val isFlyingKey = FlightControllerKey.KeyIsFlying
        val motorsOnKey = FlightControllerKey.KeyAreMotorsOn
        val flightTimeKey = FlightControllerKey.KeyFlightTimeInSeconds
        val locationKey = FlightControllerKey.KeyAircraftLocation3D
        val altitudeKey = FlightControllerKey.KeyRelativeAltitude

        val connectionFlow = KeyManagerHelper.listenKey(connectionKey).map { it }
        val flightModeFlow = KeyManagerHelper.listenKey(flightModeKey).map { it }
        val gpsSignalFlow = KeyManagerHelper.listenKey(gpsSignalKey).map { it }
        val batteryFlow = KeyManagerHelper.listenKey(batteryKey).map { it }
        val isFlyingFlow = KeyManagerHelper.listenKey(isFlyingKey).map { it }
        val motorsOnFlow = KeyManagerHelper.listenKey(motorsOnKey).map { it }
        val flightTimeFlow = KeyManagerHelper.listenKey(flightTimeKey).map { it }
        val locationFlow = KeyManagerHelper.listenKey(locationKey).map { it }
        val altitudeFlow = KeyManagerHelper.listenKey(altitudeKey).map { it }

        return combine(
            connectionFlow,
            flightModeFlow,
            gpsSignalFlow,
            batteryFlow,
            isFlyingFlow,
            motorsOnFlow,
            flightTimeFlow,
            locationFlow,
            altitudeFlow
        ) { values ->
            AircraftStatus(
                isConnected = values[0] as Boolean,
                flightMode = values[1] as String,
                gpsSignalLevel = values[2] as Int,
                batteryPercentage = values[3] as Int,
                isFlying = values[4] as Boolean,
                isMotorsOn = values[5] as Boolean,
                flightTime = values[6] as Long,
                latitude = (values[7] as? LocationCoordinate3D)?.latitude ?: 0.0,
                longitude = (values[7] as? LocationCoordinate3D)?.longitude ?: 0.0,
                altitude = values[8] as Double
            )
        }
    }

    override fun getTelemetry(): Flow<FlightTelemetry> {
        val locationKey = FlightControllerKey.KeyAircraftLocation3D
        val attitudeKey = FlightControllerKey.KeyAircraftAttitude
        val velocityKey = FlightControllerKey.KeyAircraftVelocity
        val ultrasonicKey = FlightControllerKey.KeyUltrasonicHeight
        val takeoffAltKey = FlightControllerKey.KeyTakeoffLocationAltitude

        val locationFlow = KeyManagerHelper.listenKey(locationKey).map { it }
        val attitudeFlow = KeyManagerHelper.listenKey(attitudeKey).map { it }
        val velocityFlow = KeyManagerHelper.listenKey(velocityKey).map { it }
        val ultrasonicFlow = KeyManagerHelper.listenKey(ultrasonicKey).map { it }
        val takeoffAltFlow = KeyManagerHelper.listenKey(takeoffAltKey).map { it }

        return combine(
            locationFlow,
            attitudeFlow,
            velocityFlow,
            ultrasonicFlow,
            takeoffAltFlow
        ) { location, attitude, velocity, ultrasonic, takeoffAlt ->
            FlightTelemetry(
                latitude = location.latitude,
                longitude = location.longitude,
                relativeAltitude = location.altitude.toFloat(),
                ultrasonicHeight = ultrasonic,
                takeoffAltitude = takeoffAlt,
                pitch = attitude.pitch.toFloat(),
                roll = attitude.roll.toFloat(),
                yaw = attitude.yaw.toFloat(),
                velocityX = velocity.x.toFloat(),
                velocityY = velocity.y.toFloat(),
                velocityZ = velocity.z.toFloat()
            )
        }
    }

    override fun getConnectionState(): Flow<Boolean> {
        return KeyManagerHelper.listenKey(FlightControllerKey.KeyConnectionState)
    }
}