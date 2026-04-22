package com.dmj.fly.domain.repository

import com.dmj.fly.domain.model.LiveStreamConfig
import com.dmj.fly.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface LiveStreamRepository {
    suspend fun startStream(config: LiveStreamConfig): Result<Unit>
    suspend fun stopStream(): Result<Unit>
    fun isStreaming(): Flow<Boolean>
    fun getStreamStatus(): Flow<String>
}