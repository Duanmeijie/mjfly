package com.dmj.fly.data.repository

import com.dmj.fly.domain.model.MediaFile
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepositoryImpl @Inject constructor() : MediaRepository {

    private val _downloadProgress = MutableStateFlow<Pair<String, Int>>(Pair("", 0))

    override suspend fun refreshFileList(): Result<List<MediaFile>> = withContext(Dispatchers.IO) {
        Timber.d("refreshFileList called")
        Result.Success(emptyList())
    }

    override suspend fun downloadFile(mediaFile: MediaFile): Result<String> = withContext(Dispatchers.IO) {
        val outputPath = "/storage/emulated/0/Download/${mediaFile.fileName}"
        Timber.d("downloadFile called: ${mediaFile.fileName}")
        _downloadProgress.value = Pair(mediaFile.fileName, 0)
        Result.Success(outputPath)
    }

    override suspend fun deleteFile(mediaFile: MediaFile): Result<Unit> = withContext(Dispatchers.IO) {
        Timber.d("deleteFile called: ${mediaFile.fileName}")
        Result.Success(Unit)
    }

    override fun getDownloadProgress(): Flow<Pair<String, Int>> = _downloadProgress
}
