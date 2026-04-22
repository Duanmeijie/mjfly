package com.dmj.fly.domain.model

data class MediaFile(
    val fileId: Long = 0L,
    val fileName: String = "",
    val fileSize: Long = 0L,
    val createTime: Long = 0L,
    val duration: Long = 0L,
    val thumbnailPath: String? = null,
    val isVideo: Boolean = false,
    val filePath: String? = null
)