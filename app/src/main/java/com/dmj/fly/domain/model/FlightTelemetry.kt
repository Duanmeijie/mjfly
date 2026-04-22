package com.dmj.fly.domain.model

data class FlightTelemetry(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val relativeAltitude: Float = 0f,
    val ultrasonicHeight: Float = 0f,
    val takeoffAltitude: Float = 0f,
    val pitch: Float = 0f,
    val roll: Float = 0f,
    val yaw: Float = 0f,
    val velocityX: Float = 0f,
    val velocityY: Float = 0f,
    val velocityZ: Float = 0f
)