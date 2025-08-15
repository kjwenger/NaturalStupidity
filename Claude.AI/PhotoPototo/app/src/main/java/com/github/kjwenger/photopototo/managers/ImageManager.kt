package com.github.kjwenger.photopototo.managers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import java.io.File
import java.io.FileOutputStream

class ImageManager {
    
    companion object {
        
        /**
         * Checks if a file is an image based on its extension
         */
        fun isImageFile(file: File): Boolean {
            val extension = file.extension.lowercase()
            return extension in setOf("jpg", "jpeg", "png", "gif", "bmp", "webp")
        }
        
        /**
         * Gets the orientation of an image file
         * @return 0 for landscape/square, 1 for portrait
         */
        fun getImageOrientation(file: File): Int {
            return try {
                val options = BitmapFactory.Options().apply {
                    inJustDecodeBounds = true
                }
                BitmapFactory.decodeFile(file.absolutePath, options)
                
                if (options.outHeight > options.outWidth) 1 else 0
            } catch (e: Exception) {
                0 // Default to landscape
            }
        }
        
        /**
         * Checks if an image is in portrait orientation (height > width)
         */
        fun isPortraitImage(file: File): Boolean {
            return getImageOrientation(file) == 1
        }
        
        /**
         * Rotates an image by 90 degrees clockwise
         */
        fun rotateImage(sourceFile: File, targetFile: File, degrees: Float): Boolean {
            return try {
                val bitmap = BitmapFactory.decodeFile(sourceFile.absolutePath)
                val matrix = Matrix().apply {
                    postRotate(degrees)
                }
                
                val rotatedBitmap = Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
                )
                
                val outputStream = FileOutputStream(targetFile)
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 95, outputStream)
                outputStream.close()
                
                bitmap.recycle()
                rotatedBitmap.recycle()
                
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
        
        /**
         * Gets image dimensions without loading the full bitmap
         */
        fun getImageDimensions(file: File): Pair<Int, Int> {
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeFile(file.absolutePath, options)
            return Pair(options.outWidth, options.outHeight)
        }
        
        /**
         * Creates a preview bitmap with rotation applied
         */
        fun createPreviewBitmap(file: File, degrees: Float, maxSize: Int = 300): Bitmap? {
            return try {
                // First decode with inSampleSize for memory efficiency
                val options = BitmapFactory.Options().apply {
                    inJustDecodeBounds = true
                }
                BitmapFactory.decodeFile(file.absolutePath, options)
                
                val sampleSize = calculateInSampleSize(options, maxSize, maxSize)
                options.inSampleSize = sampleSize
                options.inJustDecodeBounds = false
                
                val bitmap = BitmapFactory.decodeFile(file.absolutePath, options)
                
                if (degrees != 0f) {
                    val matrix = Matrix().apply {
                        postRotate(degrees)
                    }
                    val rotatedBitmap = Bitmap.createBitmap(
                        bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
                    )
                    bitmap.recycle()
                    rotatedBitmap
                } else {
                    bitmap
                }
            } catch (e: Exception) {
                null
            }
        }
        
        private fun calculateInSampleSize(
            options: BitmapFactory.Options,
            reqWidth: Int,
            reqHeight: Int
        ): Int {
            val (height: Int, width: Int) = options.run { outHeight to outWidth }
            var inSampleSize = 1
            
            if (height > reqHeight || width > reqWidth) {
                val halfHeight: Int = height / 2
                val halfWidth: Int = width / 2
                
                while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                    inSampleSize *= 2
                }
            }
            
            return inSampleSize
        }
    }
}