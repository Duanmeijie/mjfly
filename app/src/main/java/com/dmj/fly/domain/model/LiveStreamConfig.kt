package com.dmj.fly.domain.model

enum class StreamType {
    RTMP,
    RTSP,
    GB28181,
    AGORA
}

data class LiveStreamConfig(
    val type: StreamType = StreamType.RTMP,
    val url: String = "",
    val enabled: Boolean = false,
    val width: Int = 1920,
    val height: Int = 1080,
    val bitrate: Int = 4000,
    val frameRate: Int = 30
)