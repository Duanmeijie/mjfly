package com.dmj.fly.data.repository

import com.dmj.fly.domain.model.LiveStreamConfig
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.LiveStreamRepository
import dji.sdk.sdkmanager.DJISDKManager
import dji.sdk.livestream.LiveStreamManager
import dji.common.livestream.DJILiveStreamSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LiveStreamRepositoryImpl @Inject constructor() : LiveStreamRepository {

    private val _isStreaming = MutableStateFlow(false)
    private val _streamStatus = MutableStateFlow("Idle")
    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        registerLiveStreamListener()
    }

    private fun registerLiveStreamListener() {
        try {
            val liveStreamManager = DJISDKManager.getInstance().liveStreamManager
            liveStreamManager?.registerListener(object : LiveStreamManager.OnLiveStreamStatusListener {
                override fun onStatusChanged(status: LiveStreamManager.LiveStreamStatus?) {
                    when (status) {
                        LiveStreamManager.LiveStreamStatus.STARTED -> {
                            _isStreaming.value = true
                            _streamStatus.value = "Streaming"
                        }
                        LiveStreamManager.LiveStreamStatus.STOPPED -> {
                            _isStreaming.value = false
                            _streamStatus.value = "Stopped"
                        }
                        LiveStreamManager.LiveStreamStatus.ENCODE_ERROR -> {
                            _isStreaming.value = false
                            _streamStatus.value = "Encode Error"
                        }
                        LiveStreamManager.LiveStreamStatus.CONNECTING -> {
                            _streamStatus.value = "Connecting"
                        }
                        else -> {
                            _streamStatus.value = status?.name ?: "Unknown"
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
            val liveStreamManager = DJISDKManager.getInstance().liveStreamManager
            if (liveStreamManager == null) {
                return Result.failure(Exception("LiveStreamManager not available"))
            }

            when (config.type) {
                com.dmj.fly.domain.model.StreamType.RTMP -> {
                    val settings = DJILiveStreamSettings.SettingsBuilder()
                        .envType(DJILiveStreamSettings.LiveStreamEnvType.OVerseas)
                        .videoEncoderType(DJILiveStreamSettings.VideoEncoderType.H264)
                        .rtmpURL(config.url)
                        .audioEnabled(config.enabled)
                        .videoEnabled(config.enabled)
                        .build()
                    DJISDKManager.getInstance().liveStreamManager?.setLiveStreamSettings(settings)
                }
                com.dmj.fly.domain.model.StreamType.RTSP -> {
                    val settings = DJILiveStreamSettings.SettingsBuilder()
                        .envType(DJILiveStreamSettings.LiveStreamEnvType.OVerseas)
                        .videoEncoderType(DJILiveStreamSettings.VideoEncoderType.H264)
                        .rtspURL(config.url)
                        .audioEnabled(config.enabled)
                        .videoEnabled(config.enabled)
                        .build()
                    DJISDKManager.getInstance().liveStreamManager?.setLiveStreamSettings(settings)
                }
                com.dmj.fly.domain.model.StreamType.GB28181 -> {
                    val settings = DJILiveStreamSettings.SettingsBuilder()
                        .envType(DJILiveStreamSettings.LiveStreamEnvType.OVerseas)
                        .gb28181ServerIP(config.url)
                        .audioEnabled(config.enabled)
                        .videoEnabled(config.enabled)
                        .build()
                    DJISDKManager.getInstance().liveStreamManager?.setLiveStreamSettings(settings)
                }
                com.dmj.fly.domain.model.StreamType.AGORA -> {
                    val settings = DJILiveStreamSettings.SettingsBuilder()
                        .envType(DJILiveStreamSettings.LiveStreamEnvType.OVerseas)
                        .agoraAppId(config.url)
                        .audioEnabled(config.enabled)
                        .videoEnabled(config.enabled)
                        .build()
                    DJISDKManager.getInstance().liveStreamManager?.setLiveStreamSettings(settings)
                }
            }

            val result = liveStreamManager.startStream()
            if (result) {
                Timber.d("Live stream started successfully")
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to start live stream"))
            }
        } catch (e: Exception) {
            Timber.e("startStream failed: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun stopStream(): Result<Unit> {
        return try {
            val liveStreamManager = DJISDKManager.getInstance().liveStreamManager
            if (liveStreamManager == null) {
                return Result.failure(Exception("LiveStreamManager not available"))
            }

            val result = liveStreamManager.stopStream()
            if (result) {
                Timber.d("Live stream stopped successfully")
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to stop live stream"))
            }
        } catch (e: Exception) {
            Timber.e("stopStream failed: ${e.message}")
            Result.failure(e)
        }
    }

    override fun isStreaming(): Flow<Boolean> = _isStreaming

    override fun getStreamStatus(): Flow<String> = _streamStatus
}