package com.github.kjwenger.photopototo04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class DirectoryAdapter(
    private val onItemClick: (File) -> Unit
) : RecyclerView.Adapter<DirectoryAdapter.ViewHolder>() {
    
    private var files: List<File> = emptyList()
    
    fun updateFiles(newFiles: List<File>) {
        files = newFiles
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_directory, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val file = files[position]
        holder.bind(file, onItemClick)
    }
    
    override fun getItemCount(): Int = files.size
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.icon_file_type)
        private val nameTextView: TextView = itemView.findViewById(R.id.text_file_name)
        private val sizeTextView: TextView = itemView.findViewById(R.id.text_file_size)
        
        fun bind(file: File, onItemClick: (File) -> Unit) {
            when {
                file.name == ".." -> {
                    // Parent directory
                    nameTextView.text = ".. (Parent Directory)"
                    iconImageView.setImageResource(R.drawable.ic_folder)
                    sizeTextView.text = "Go up"
                }
                file.isDirectory -> {
                    // Regular directory
                    nameTextView.text = file.name
                    iconImageView.setImageResource(R.drawable.ic_folder)
                    try {
                        val itemCount = file.listFiles()?.size ?: 0
                        sizeTextView.text = if (itemCount == 1) "1 item" else "$itemCount items"
                    } catch (e: SecurityException) {
                        sizeTextView.text = "Access denied"
                    }
                }
                else -> {
                    // Regular file
                    nameTextView.text = file.name
                    setFileIcon(file)
                    sizeTextView.text = formatFileSize(file.length())
                }
            }
            
            itemView.setOnClickListener {
                onItemClick(file)
            }
        }
        
        private fun setFileIcon(file: File) {
            val extension = file.extension.lowercase()
            val iconRes = when (extension) {
                "jpg", "jpeg", "png", "gif", "bmp", "webp" -> R.drawable.ic_image
                "mp4", "avi", "mkv", "mov", "wmv", "flv", "webm" -> R.drawable.ic_video
                "mp3", "wav", "flac", "aac", "ogg", "m4a" -> R.drawable.ic_audio
                "pdf" -> R.drawable.ic_document
                else -> R.drawable.ic_file
            }
            iconImageView.setImageResource(iconRes)
        }
        
        private fun formatFileSize(sizeInBytes: Long): String {
            return when {
                sizeInBytes == 0L -> "0 B"
                sizeInBytes < 1024 -> "$sizeInBytes B"
                sizeInBytes < 1024 * 1024 -> "${(sizeInBytes / 1024.0).let { if (it < 10) "%.1f".format(it) else "${it.toInt()}" }} KB"
                sizeInBytes < 1024 * 1024 * 1024 -> "${(sizeInBytes / (1024.0 * 1024)).let { if (it < 10) "%.1f".format(it) else "${it.toInt()}" }} MB"
                else -> "${(sizeInBytes / (1024.0 * 1024 * 1024)).let { if (it < 10) "%.1f".format(it) else "${it.toInt()}" }} GB"
            }
        }
    }
}