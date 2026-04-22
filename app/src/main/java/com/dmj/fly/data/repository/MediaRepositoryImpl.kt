package com.dmj.fly.data.repository

import com.dmj.fly.domain.model.MediaFile
import com.dmj.fly.domain.model.Result
import com.dmj.fly.domain.repository.MediaRepository
import dji.sdk.sdkmanager.DJISDKManager
import dji.sdk.sdkmanager.ProductManager
import dji.common.camera.DJIMedia
import dji.common.camera.storage.DJICameraStorageInfo
import dji.common.error.DJICameraError
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
class MediaRepositoryImpl @Inject constructor() : MediaRepository {

    private val _downloadProgress = MutableStateFlow<Pair<String, Int>>(Pair("", 0))

    override suspend fun refreshFileList(): Result<List<MediaFile>> {
        val productManager = DJISDKManager.getInstance().productManager
        if (productManager == null) {
            return Result.failure(Exception("Product not connected"))
        }

        return try {
            val mediaManager = productManager.mediaManager
            if (mediaManager == null) {
                return Result.failure(Exception("MediaManager not available"))
            }

            val fileList = mutableListOf<MediaFile>()
            for (storageInfo: DJICameraStorageInfo in mediaManager.storageInfos) {
                val fileListSnapshot = storageInfo.fileListSnapshot
                for (media: DJIMedia in fileListSnapshot) {
                    fileList.add(
                        MediaFile(
                            fileId = media.fileId,
                            fileName = media.fileName,
                            fileSize = media.fileSize,
                            createTime = media.createdDate.time,
                            duration = media.durationInSeconds,
                            thumbnailPath = media.thumbnail?.absolutePath,
                            isVideo = media.mediaType == DJIMedia.DJIMediaType.MP4 || media.mediaType == DJIMedia.DJIMediaType.MOV
                        )
                    )
                }
            }
            Result.success(fileList)
        } catch (e: Exception) {
            Timber.e("refreshFileList failed: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun downloadFile(mediaFile: MediaFile): Result<String> {
        return try {
            val productManager = DJISDKManager.getInstance().productManager
            val mediaManager = productManager?.mediaManager
            if (mediaManager == null) {
                return Result.failure(Exception("MediaManager not available"))
            }

            val file = mediaManager.sdCardFileListSnapshot.find { it.fileId == mediaFile.fileId }
            if (file == null) {
                return Result.failure(Exception("File not found"))
            }

            val outputPath = "/storage/emulated/0/Download/${mediaFile.fileName}"
            
            file.downloadMediaFile(
                object : DJIMedia.DownloadListener {
                    override fun onProgress(total: Int, current: Int) {
                        _downloadProgress.value = Pair(mediaFile.fileName, current * 100 / total)
                    }

                    override fun onStart() {
                        Timber.d("Download started: ${mediaFile.fileName}")
                    }

                    override fun onSuccess() {
                        Timber.d("Download success: ${mediaFile.fileName}")
                    }

                    override fun onFailure(errorCode: DJICameraError) {
                        Timber.e("Download failed: ${errorCode.description()}")
                    }
                },
                outputPath,
                false
            )

            Result.success(outputPath)
        } catch (e: Exception) {
            Timber.e("downloadFile failed: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun deleteFile(mediaFile: MediaFile): Result<Unit> {
        return try {
            val productManager = DJISDKManager.getInstance().productManager
            val mediaManager = productManager?.mediaManager
            if (mediaManager == null) {
                return Result.failure(Exception("MediaManager not available"))
            }

            val file = mediaManager.sdCardFileListSnapshot.find { it.fileId == mediaFile.fileId }
            if (file == null) {
                return Result.failure(Exception("File not found"))
            }

            file.deleteMediaFile(object : DJIMedia.DeleteListener {
                override fun onSuccess() {
                    Timber.d("Delete success: ${mediaFile.fileName}")
                }

                override fun onFailure(errorCode: DJICameraError) {
                    Timber.e("Delete failed: ${errorCode.description()}")
                }
            })

            Result.success(Unit)
        } catch (e: Exception) {
            Timber.e("deleteFile failed: ${e.message}")
            Result.failure(e)
        }
    }

    override fun getDownloadProgress(): Flow<Pair<String, Int>> = _downloadProgress
}