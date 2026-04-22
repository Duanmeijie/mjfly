package com.dmj.fly.data.repository

import com.dmj.fly.domain.model.LiveStreamConfig
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.LiveStreamRepository
import dji.v5.common.error.IDJIError
import dji.v5.manager.livestream.LiveStreamManager
import dji.v5.manager.livestream.LiveStreamSettings
import dji.v5.manager.livestream.enums.LiveStreamMode
import dji.v5.manager.livestream.enums.LiveStreamEnvType
import dji.v5.manager.livestream.enums.VideoEncoderType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LiveStreamRepositoryImpl @Inject constructor() : LiveStreamRepository {

    private val _isStreaming = MutableStateFlow(false)
    private val _streamStatus = MutableStateFlow("Idle")
    private val scope = CoroutineScope(Dispatchers.Main)

    private val liveStreamManager: LiveStreamManager? by lazy {
        LiveStreamManager.getInstance()
    }

    init {
        registerLiveStreamListener()
    }

    private fun registerLiveStreamListener() {
        try {
            liveStreamManager?.registerListener(object : dji.v5.manager.livestream.LiveStreamStatusListener {
                override fun onStatusChanged(status: dji.v5.manager.livestream.LiveStreamStatus) {
                    when (status) {
                        dji.v5.manager.livestream.LiveStreamStatus.STARTED -> {
                            _isStreaming.value = true
                            _streamStatus.value = "Streaming"
                        }
                        dji.v5.manager.livestream.LiveStreamStatus.STOPPED -> {
                            _isStreaming.value = false
                            _streamStatus.value = "Stopped"
                        }
                        dji.v5.manager.livestream.LiveStreamStatus.ENCODE_ERROR -> {
                            _isStreaming.value = false
                            _streamStatus.value = "Encode Error"
                        }
                        dji.v5.manager.livestream.LiveStreamStatus.CONNECTING -> {
                            _streamStatus.value = "Connecting"
                        }
                        else -> {
                            _streamStatus.value = status.name
                        }
                    }
                }
            })
        } catch (e: Exception) {
            Timber.e("Failed to register live stream listener: ${e.message}")
        }
    }

    override suspend fun startStream(config: LiveStreamConfig): Result<Unit> {
        return try {
            val manager = liveStreamManager
            if (manager == null) {
                return Result.Error("LiveStreamManager not available")
            }

            val settings = LiveStreamSettings().apply {
                envType = LiveStreamEnvType.OVERSEAS
                videoEncoderType = VideoEncoderType.H264
                videoEnabled = config.enabled
                audioEnabled = config.enabled
                
                when (config.type) {
                    com.dmj.fly.domain.model.StreamType.RTMP -> {
                        liveStreamMode = LiveStreamMode.RTMP
                        rtmpURL = config.url
                    }
                    com.dmj.fly.domain.model.StreamType.RTSP -> {
                        liveStreamMode = LiveStreamMode.RTSP
                        rtspURL = config.url
                    }
                    com.dmj.fly.domain.model.StreamType.GB28181 -> {
                        liveStreamMode = LiveStreamMode.GB28181
                        gb28181ServerIP = config.url
                    }
                    com.dmj.fly.domain.model.StreamType.AGORA -> {
                        liveStreamMode = LiveStreamMode.AGORA
                        agoraAppId = config.url
                    }
                }
            }
            
            manager.setLiveStreamSettings(settings)
            val started = manager.startStream()
            
            if (started) {
                Timber.d("Live stream started successfully")
                Result.Success(Unit)
            } else {
                Result.Error("Failed to start live stream")
            }
        } catch (e: Exception) {
            Timber.e("startStream failed: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun stopStream(): Result<Unit> {
        return try {
            val manager = liveStreamManager
            if (manager == null) {
                return Result.Error("LiveStreamManager not available")
            }

            val stopped = manager.stopStream()
            
            if (stopped) {
                Timber.d("Live stream stopped successfully")
                Result.Success(Unit)
            } else {
                Result.Error("Failed to stop live stream")
            }
        } catch (e: Exception) {
            Timber.e("stopStream failed: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override fun isStreaming(): Flow<Boolean> = _isStreaming

    override fun getStreamStatus(): Flow<String> = _streamStatus
}