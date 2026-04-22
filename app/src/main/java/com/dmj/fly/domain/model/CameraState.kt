package com.dmj.fly.domain.model

enum class CameraMode {
    PHOTO,
    VIDEO,
    UNKNOWN
}

data class CameraState(
    val mode: CameraMode = CameraMode.UNKNOWN,
    val isRecording: Boolean = false,
    val isShootingPhoto: Boolean = false,
    val storageLocation: Int = 0,
    val availablePhotoCount: Int = 0,
    val availableVideoDuration: Int = 0
)