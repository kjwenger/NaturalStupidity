package com.github.kjwenger.photopototo.models

import java.io.File

data class ImageItem(
    val file: File,
    val name: String = file.name,
    val size: Long = file.length(),
    val lastModified: Long = file.lastModified(),
    val isSelected: Boolean = false,
    val isPortrait: Boolean = false
) {
    val path: String get() = file.absolutePath
    val extension: String get() = file.extension.lowercase()
    val sizeString: String get() = formatFileSize(size)
    
    private fun formatFileSize(bytes: Long): String {
        if (bytes <= 0) return "0 B"
        
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        val digitGroups = (kotlin.math.log10(bytes.toDouble()) / kotlin.math.log10(1024.0)).toInt()
        
        return "%.1f %s".format(
            bytes / kotlin.math.pow(1024.0, digitGroups.toDouble()),
            units[digitGroups]
        )
    }
}