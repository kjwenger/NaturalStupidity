package com.github.kjwenger.photopototo.processors

import android.content.Context
import com.github.kjwenger.photopototo.managers.ImageManager
import com.github.kjwenger.photopototo.managers.StorageManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class ImageProcessor(private val context: Context) {
    
    private val storageManager = StorageManager(context)
    
    /**
     * Processes a batch of images with rotation
     */
    suspend fun processImages(
        images: List<File>,
        rotationDegrees: Float,
        onProgress: (Int, Int) -> Unit,
        onComplete: (List<ProcessResult>) -> Unit
    ) = withContext(Dispatchers.IO) {
        val results = mutableListOf<ProcessResult>()
        
        images.forEachIndexed { index, image ->
            try {
                val result = processImage(image, rotationDegrees)
                results.add(result)
                
                withContext(Dispatchers.Main) {
                    onProgress(index + 1, images.size)
                }
            } catch (e: Exception) {
                results.add(ProcessResult(image, false, "Error: ${e.message}"))
            }
        }
        
        withContext(Dispatchers.Main) {
            onComplete(results)
        }
    }
    
    /**
     * Processes a single image with rotation and backup
     */
    private fun processImage(originalFile: File, rotationDegrees: Float): ProcessResult {
        return try {
            // Create backup in trash
            val backupFile = storageManager.moveToTrash(originalFile)
            if (backupFile == null) {
                return ProcessResult(originalFile, false, "Failed to create backup")
            }
            
            // Apply rotation to original file
            val success = ImageManager.rotateImage(backupFile, originalFile, rotationDegrees)
            
            ProcessResult(
                originalFile,
                success,
                if (success) "Rotated successfully" else "Rotation failed"
            )
        } catch (e: Exception) {
            ProcessResult(originalFile, false, "Error: ${e.message}")
        }
    }
    
    /**
     * Creates preview images with rotation applied
     */
    suspend fun createPreviews(
        images: List<File>,
        rotationDegrees: Float,
        onProgress: (Int, Int) -> Unit
    ): Map<File, android.graphics.Bitmap?> = withContext(Dispatchers.IO) {
        val previews = mutableMapOf<File, android.graphics.Bitmap?>()
        
        images.forEachIndexed { index, image ->
            val preview = ImageManager.createPreviewBitmap(image, rotationDegrees)
            previews[image] = preview
            
            withContext(Dispatchers.Main) {
                onProgress(index + 1, images.size)
            }
        }
        
        previews
    }
    
    /**
     * Gets portrait images from a list
     */
    fun getPortraitImages(images: List<File>): List<File> {
        return images.filter { ImageManager.isPortraitImage(it) }
    }
    
    /**
     * Gets landscape images from a list
     */
    fun getLandscapeImages(images: List<File>): List<File> {
        return images.filter { !ImageManager.isPortraitImage(it) }
    }
    
    data class ProcessResult(
        val file: File,
        val success: Boolean,
        val message: String
    )
}