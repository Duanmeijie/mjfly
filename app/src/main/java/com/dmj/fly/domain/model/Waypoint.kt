package com.dmj.fly.domain.model

enum class WaypointAction {
    NONE,
    TAKE_PHOTO,
    START_RECORD,
    STOP_RECORD
}

data class Waypoint(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val altitude: Float = 0f,
    val heading: Float = 0f,
    val action: WaypointAction = WaypointAction.NONE,
    val actionTime: Long = 0L,
    val turnMode: Int = 0,
    val speed: Float = 0f,
    val gimbalPitch: Float = 0f
)