package com.github.kjwenger.photopototo.managers

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class StorageManager(private val context: Context) {
    
    /**
     * Gets the default storage directory (usually /storage/emulated/0)
     */
    fun getDefaultStorageDirectory(): String {
        return Environment.getExternalStorageDirectory().absolutePath
    }
    
    /**
     * Gets the DCIM directory for camera photos
     */
    fun getDcimDirectory(): String {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absolutePath
    }
    
    /**
     * Creates a trash directory for backup files
     */
    fun getTrashDirectory(): File {
        val trashDir = File(context.getExternalFilesDir(null), "trash")
        if (!trashDir.exists()) {
            trashDir.mkdirs()
        }
        return trashDir
    }
    
    /**
     * Moves a file to trash with timestamp
     */
    fun moveToTrash(originalFile: File): File? {
        return try {
            val trashDir = getTrashDirectory()
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val backupFile = File(trashDir, "${timestamp}_${originalFile.name}")
            
            if (originalFile.copyTo(backupFile, overwrite = true)) {
                backupFile
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    
    /**
     * Gets all image files from a directory
     */
    fun getImagesFromDirectory(directory: File): List<File> {
        return if (directory.exists() && directory.isDirectory) {
            directory.listFiles { file ->
                file.isFile && ImageManager.isImageFile(file)
            }?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }
    
    /**
     * Checks if we have permission to write to external storage
     */
    fun canWriteToExternalStorage(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }
    
    /**
     * Gets free space available in bytes
     */
    fun getAvailableSpaceBytes(): Long {
        return try {
            val externalStorageDirectory = Environment.getExternalStorageDirectory()
            externalStorageDirectory.freeSpace
        } catch (e: Exception) {
            0L
        }
    }
    
    /**
     * Checks if there's enough space for file operations
     */
    fun hasEnoughSpace(requiredBytes: Long): Boolean {
        return getAvailableSpaceBytes() > requiredBytes
    }
}