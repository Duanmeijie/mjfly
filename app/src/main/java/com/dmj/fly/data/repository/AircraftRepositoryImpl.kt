package com.dmj.fly.data.repository

import com.dmj.fly.data.datasource.msdk.KeyManagerHelper
import com.dmj.fly.domain.model.AircraftStatus
import com.dmj.fly.domain.model.FlightTelemetry
import com.dmj.fly.domain.repository.AircraftRepository
import dji.sdk.keyvalue.key.FlightControllerKey
import dji.sdk.keyvalue.key.KeyConnection
import dji.sdk.keyvalue.value.flightcontroller.FlightMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AircraftRepositoryImpl @Inject constructor() : AircraftRepository {

    override fun getAircraftStatus(): Flow<AircraftStatus> {
        val connectionKey = KeyConnection.keyForProduct()
        val flightModeKey = FlightControllerKey.KeyFlightModeString
        val gpsSignalKey = FlightControllerKey.KeyGPSSignalLevel
        val batteryKey = FlightControllerKey.KeyChargeRemainingInPercent
        val isFlyingKey = FlightControllerKey.KeyIsFlying
        val motorsOnKey = FlightControllerKey.KeyAreMotorsOn
        val flightTimeKey = FlightControllerKey.KeyFlightTimeInSeconds
        val locationKey = FlightControllerKey.KeyAircraftLocation3D
        val altitudeKey = FlightControllerKey.KeyRelativeAltitude

        val connectionFlow = KeyManagerHelper.listenKey<Boolean>(connectionKey).map { it }
        val flightModeFlow = KeyManagerHelper.listenKey<String>(flightModeKey).map { it }
        val gpsSignalFlow = KeyManagerHelper.listenKey<Int>(gpsSignalKey).map { it }
        val batteryFlow = KeyManagerHelper.listenKey<Int>(batteryKey).map { it }
        val isFlyingFlow = KeyManagerHelper.listenKey<Boolean>(isFlyingKey).map { it }
        val motorsOnFlow = KeyManagerHelper.listenKey<Boolean>(motorsOnKey).map { it }
        val flightTimeFlow = KeyManagerHelper.listenKey<Int>(flightTimeKey).map { it.toLong() }
        val locationFlow = KeyManagerHelper.listenKey<dji.sdk.keyvalue.value.location.LocationCoordinate3D>(locationKey).map { it }
        val altitudeFlow = KeyManagerHelper.listenKey<Double>(altitudeKey).map { it }

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
                latitude = (values[7] as? dji.sdk.keyvalue.value.location.LocationCoordinate3D)?.latitude ?: 0.0,
                longitude = (values[7] as? dji.sdk.keyvalue.value.location.LocationCoordinate3D)?.longitude ?: 0.0,
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

        val locationFlow = KeyManagerHelper.listenKey<dji.sdk.keyvalue.value.location.LocationCoordinate3D>(locationKey).map { it }
        val attitudeFlow = KeyManagerHelper.listenKey<dji.sdk.keyvalue.value.flightcontroller.Attitude>(attitudeKey).map { it }
        val velocityFlow = KeyManagerHelper.listenKey<dji.sdk.keyvalue.value.flightcontroller.Velocity>(velocityKey).map { it }
        val ultrasonicFlow = KeyManagerHelper.listenKey<Float>(ultrasonicKey).map { it }
        val takeoffAltFlow = KeyManagerHelper.listenKey<Float>(takeoffAltKey).map { it }

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
        return KeyManagerHelper.listenKey<Boolean>(KeyConnection.keyForProduct())
    }
}