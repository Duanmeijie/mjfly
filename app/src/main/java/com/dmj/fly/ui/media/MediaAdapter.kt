package com.dmj.fly.ui.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmj.fly.databinding.ItemMediaBinding
import com.dmj.fly.domain.model.MediaFile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MediaAdapter(
    private val onDownloadClick: (MediaFile) -> Unit,
    private val onDeleteClick: (MediaFile) -> Unit
) : ListAdapter<MediaFile, MediaAdapter.MediaViewHolder>(MediaDiffCallback()) {

    private var downloadProgress: Map<String, Int> = emptyMap()

    fun setDownloadProgress(progress: Map<String, Int>) {
        downloadProgress = progress
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MediaViewHolder(
        private val binding: ItemMediaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(file: MediaFile) {
            binding.textFileName.text = file.fileName
            binding.textFileSize.text = formatFileSize(file.fileSize)
            binding.textFileDate.text = formatDate(file.createTime)

            val progress = downloadProgress[file.fileName]
            binding.progressDownload.isVisible = progress != null && progress < 100
            binding.progressDownload.progress = progress ?: 0

            binding.btnDownload.setOnClickListener { onDownloadClick(file) }
            binding.btnDelete.setOnClickListener { onDeleteClick(file) }
        }

        private fun formatFileSize(size: Long): String {
            return when {
                size < 1024 -> "$size B"
                size < 1024 * 1024 -> "${size / 1024} KB"
                else -> "${size / (1024 * 1024)} MB"
            }
        }

        private fun formatDate(timestamp: Long): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
    }

    class MediaDiffCallback : DiffUtil.ItemCallback<MediaFile>() {
        override fun areItemsTheSame(oldItem: MediaFile, newItem: MediaFile): Boolean {
            return oldItem.fileId == newItem.fileId
        }

        override fun areContentsTheSame(oldItem: MediaFile, newItem: MediaFile): Boolean {
            return oldItem == newItem
        }
    }
}