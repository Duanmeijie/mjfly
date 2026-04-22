package com.dmj.fly.data.repository

import com.dmj.fly.domain.model.MediaFile
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.MediaRepository
import com.dmj.fly.sdk.DjiSdkManager
import dji.sdk.keyvalue.value.camera.StorageLocation
import dji.v5.manager.media.MediaFileInfo
import dji.v5.manager.media.MediaManager
import dji.v5.manager.media.MediaStorageInfo
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
        try {
            val mediaManager = MediaManager.getInstance()
            if (mediaManager == null) {
                return@withContext Result.Error("MediaManager not available")
            }

            val fileList = mutableListOf<MediaFile>()
            val storageInfos = mediaManager.getSDCardStorageInfos()
            
            for (storageInfo: MediaStorageInfo in storageInfos) {
                val fileListSnapshot = storageInfo.fileListSnapshot
                for (mediaFileInfo: MediaFileInfo in fileListSnapshot) {
                    fileList.add(
                        MediaFile(
                            fileId = mediaFileInfo.fileIndex,
                            fileName = mediaFileInfo.fileName,
                            fileSize = mediaFileInfo.fileSize.toLong(),
                            createTime = mediaFileInfo.createdDate.time,
                            duration = mediaFileInfo.durationInSeconds,
                            thumbnailPath = null,
                            isVideo = mediaFileInfo.mediaType == dji.v5.manager.media.MediaType.MP4 || 
                                      mediaFileInfo.mediaType == dji.v5.manager.media.MediaType.MOV
                        )
                    )
                }
            }
            Result.Success(fileList)
        } catch (e: Exception) {
            Timber.e("refreshFileList failed: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun downloadFile(mediaFile: MediaFile): Result<String> = withContext(Dispatchers.IO) {
        try {
            val mediaManager = MediaManager.getInstance()
            if (mediaManager == null) {
                return@withContext Result.Error("MediaManager not available")
            }

            val storageInfos = mediaManager.getSDCardStorageInfos()
            var targetFile: MediaFileInfo? = null
            
            for (storageInfo: MediaStorageInfo in storageInfos) {
                val fileListSnapshot = storageInfo.fileListSnapshot
                targetFile = fileListSnapshot.find { it.fileIndex == mediaFile.fileId }
                if (targetFile != null) break
            }
            
            if (targetFile == null) {
                return@withContext Result.Error("File not found")
            }

            val outputPath = "/storage/emulated/0/Download/${mediaFile.fileName}"
            
            targetFile.downloadMediaFile(object : dji.v5.manager.media.DownloadListener {
                override fun onProgress(total: Int, current: Int) {
                    _downloadProgress.value = Pair(mediaFile.fileName, current * 100 / total)
                }

                override fun onStart() {
                    Timber.d("Download started: ${mediaFile.fileName}")
                }

                override fun onSuccess() {
                    Timber.d("Download success: ${mediaFile.fileName}")
                }

                override fun onFailure(error: dji.v5.common.error.IDJIError) {
                    Timber.e("Download failed: ${error.description()}")
                }
            }, outputPath, false)

            Result.Success(outputPath)
        } catch (e: Exception) {
            Timber.e("downloadFile failed: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun deleteFile(mediaFile: MediaFile): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val mediaManager = MediaManager.getInstance()
            if (mediaManager == null) {
                return@withContext Result.Error("MediaManager not available")
            }

            val storageInfos = mediaManager.getSDCardStorageInfos()
            var targetFile: MediaFileInfo? = null
            
            for (storageInfo: MediaStorageInfo in storageInfos) {
                val fileListSnapshot = storageInfo.fileListSnapshot
                targetFile = fileListSnapshot.find { it.fileIndex == mediaFile.fileId }
                if (targetFile != null) break
            }
            
            if (targetFile == null) {
                return@withContext Result.Error("File not found")
            }

            targetFile.deleteMediaFile(object : dji.v5.manager.media.DeleteListener {
                override fun onSuccess() {
                    Timber.d("Delete success: ${mediaFile.fileName}")
                }

                override fun onFailure(error: dji.v5.common.error.IDJIError) {
                    Timber.e("Delete failed: ${error.description()}")
                }
            })

            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e("deleteFile failed: ${e.message}")
            Result.Error(e.message ?: "Unknown error")
        }
    }

    override fun getDownloadProgress(): Flow<Pair<String, Int>> = _downloadProgress
}