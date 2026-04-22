package com.dmj.fly.domain.model

data class AircraftStatus(
    val isConnected: Boolean = false,
    val flightMode: String = "",
    val gpsSignalLevel: Int = 0,
    val batteryPercentage: Int = 0,
    val isFlying: Boolean = false,
    val isMotorsOn: Boolean = false,
    val flightTime: Long = 0L,
    val altitude: Double = 0.0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)