package com.github.kjwenger.photopototo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.kjwenger.photopototo.R
// import com.github.kjwenger.photopototo.databinding.ItemFileBinding
import java.io.File
import java.text.DecimalFormat
import kotlin.math.log10
import kotlin.math.pow

class FileSystemAdapter(
    private val onItemClick: (File) -> Unit
) : ListAdapter<File, FileSystemAdapter.FileViewHolder>(FileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_file, parent, false)
        return FileViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FileViewHolder(
        private val itemView: android.view.View,
        private val onItemClick: (File) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(file: File) {
            val fileName = itemView.findViewById<android.widget.TextView>(R.id.file_name)
            val fileIcon = itemView.findViewById<android.widget.ImageView>(R.id.file_icon)
            val fileSize = itemView.findViewById<android.widget.TextView>(R.id.file_size)
            
            fileName.text = file.name
            
            // Set appropriate icon
            if (file.isDirectory) {
                fileIcon.setImageResource(R.drawable.ic_folder)
                fileSize.visibility = android.view.View.GONE
            } else {
                // Set file icon based on type
                fileIcon.setImageResource(getFileIcon(file))
                
                // Show file size for files
                fileSize.visibility = android.view.View.VISIBLE
                fileSize.text = formatFileSize(file.length())
            }
            
            itemView.setOnClickListener {
                onItemClick(file)
            }
        }
        
        private fun getFileIcon(file: File): Int {
            val extension = file.extension.lowercase()
            return when {
                isImageFile(extension) -> R.drawable.ic_image
                isVideoFile(extension) -> R.drawable.ic_video
                isAudioFile(extension) -> R.drawable.ic_audio
                isDocumentFile(extension) -> R.drawable.ic_document
                else -> R.drawable.ic_file
            }
        }
        
        private fun isImageFile(extension: String): Boolean {
            return extension in setOf("jpg", "jpeg", "png", "gif", "bmp", "webp", "svg")
        }
        
        private fun isVideoFile(extension: String): Boolean {
            return extension in setOf("mp4", "avi", "mkv", "mov", "wmv", "flv", "webm")
        }
        
        private fun isAudioFile(extension: String): Boolean {
            return extension in setOf("mp3", "wav", "flac", "aac", "ogg", "m4a")
        }
        
        private fun isDocumentFile(extension: String): Boolean {
            return extension in setOf("pdf", "doc", "docx", "txt", "rtf", "xls", "xlsx", "ppt", "pptx")
        }
        
        private fun formatFileSize(bytes: Long): String {
            if (bytes <= 0) return "0 B"
            
            val units = arrayOf("B", "KB", "MB", "GB", "TB")
            val digitGroups = (log10(bytes.toDouble()) / log10(1024.0)).toInt()
            
            return DecimalFormat("#,##0.#").format(
                bytes / 1024.0.pow(digitGroups.toDouble())
            ) + " " + units[digitGroups]
        }
    }

    private class FileDiffCallback : DiffUtil.ItemCallback<File>() {
        override fun areItemsTheSame(oldItem: File, newItem: File): Boolean {
            return oldItem.absolutePath == newItem.absolutePath
        }

        override fun areContentsTheSame(oldItem: File, newItem: File): Boolean {
            return oldItem.name == newItem.name && 
                   oldItem.isDirectory == newItem.isDirectory &&
                   oldItem.length() == newItem.length()
        }
    }
}