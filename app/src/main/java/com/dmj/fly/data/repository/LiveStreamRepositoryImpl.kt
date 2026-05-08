package com.dmj.fly.data.repository

import com.dmj.fly.domain.model.LiveStreamConfig
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.LiveStreamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LiveStreamRepositoryImpl @Inject constructor() : LiveStreamRepository {

    private val _isStreaming = MutableStateFlow(false)
    private val _streamStatus = MutableStateFlow("Idle")

    override suspend fun startStream(config: LiveStreamConfig): Result<Unit> {
        Timber.d("startStream called: ${config.type}")
        _isStreaming.value = true
        _streamStatus.value = "Streaming"
        return Result.Success(Unit)
    }

    override suspend fun stopStream(): Result<Unit> {
        Timber.d("stopStream called")
        _isStreaming.value = false
        _streamStatus.value = "Stopped"
        return Result.Success(Unit)
    }

    override fun isStreaming(): Flow<Boolean> = _isStreaming

    override fun getStreamStatus(): Flow<String> = _streamStatus
}
