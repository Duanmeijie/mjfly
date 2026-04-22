package com.dmj.fly.domain.repository

import com.dmj.fly.domain.model.MediaFile
import com.dmj.fly.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    suspend fun refreshFileList(): Result<List<MediaFile>>
    suspend fun downloadFile(mediaFile: MediaFile): Result<String>
    suspend fun deleteFile(mediaFile: MediaFile): Result<Unit>
    fun getDownloadProgress(): Flow<Pair<String, Int>>
}