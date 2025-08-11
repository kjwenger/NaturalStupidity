package com.github.kjwenger.photopototo.models

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

data class FileItem(
    val file: File,
    val name: String = file.name,
    val size: Long = file.length(),
    val lastModified: Long = file.lastModified(),
    val isDirectory: Boolean = file.isDirectory,
    val isHidden: Boolean = file.isHidden
) {
    val path: String get() = file.absolutePath
    val extension: String get() = if (isDirectory) "" else file.extension.lowercase()
    val sizeString: String get() = if (isDirectory) "" else formatFileSize(size)
    val lastModifiedString: String get() = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).format(Date(lastModified))
    val fileType: FileType get() = determineFileType()
    
    enum class FileType {
        DIRECTORY,
        IMAGE,
        VIDEO,
        AUDIO,
        DOCUMENT,
        OTHER
    }
    
    private fun determineFileType(): FileType {
        return when {
            isDirectory -> FileType.DIRECTORY
            isImageFile(extension) -> FileType.IMAGE
            isVideoFile(extension) -> FileType.VIDEO
            isAudioFile(extension) -> FileType.AUDIO
            isDocumentFile(extension) -> FileType.DOCUMENT
            else -> FileType.OTHER
        }
    }
    
    private fun isImageFile(ext: String): Boolean {
        return ext in setOf("jpg", "jpeg", "png", "gif", "bmp", "webp", "svg", "tiff", "ico")
    }
    
    private fun isVideoFile(ext: String): Boolean {
        return ext in setOf("mp4", "avi", "mkv", "mov", "wmv", "flv", "webm", "m4v", "3gp")
    }
    
    private fun isAudioFile(ext: String): Boolean {
        return ext in setOf("mp3", "wav", "flac", "aac", "ogg", "m4a", "wma")
    }
    
    private fun isDocumentFile(ext: String): Boolean {
        return ext in setOf("pdf", "doc", "docx", "txt", "rtf", "xls", "xlsx", "ppt", "pptx")
    }
    
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