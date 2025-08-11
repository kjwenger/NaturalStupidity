package com.github.kjwenger.photopototo01

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
            nameTextView.text = file.name
            
            if (file.isDirectory) {
                iconImageView.setImageResource(R.drawable.ic_folder)
                val itemCount = file.listFiles()?.size ?: 0
                sizeTextView.text = "$itemCount items"
            } else {
                iconImageView.setImageResource(R.drawable.ic_file)
                sizeTextView.text = formatFileSize(file.length())
            }
            
            itemView.setOnClickListener {
                onItemClick(file)
            }
        }
        
        private fun formatFileSize(sizeInBytes: Long): String {
            return when {
                sizeInBytes < 1024 -> "$sizeInBytes B"
                sizeInBytes < 1024 * 1024 -> "${sizeInBytes / 1024} KB"
                sizeInBytes < 1024 * 1024 * 1024 -> "${sizeInBytes / (1024 * 1024)} MB"
                else -> "${sizeInBytes / (1024 * 1024 * 1024)} GB"
            }
        }
    }
}