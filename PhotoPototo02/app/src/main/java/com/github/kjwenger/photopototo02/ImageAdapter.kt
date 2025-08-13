package com.github.kjwenger.photopototo02

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class ImageAdapter(
    private val context: Context,
    private val onImageClick: (File) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var images = listOf<File>()

    fun updateImages(newImages: List<File>) {
        images = newImages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount() = images.size

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_thumbnail)
        private val nameTextView: TextView = itemView.findViewById(R.id.text_image_name)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onImageClick(images[position])
                }
            }
        }

        fun bind(image: File) {
            nameTextView.text = image.name
            
            // Reset imageView state
            imageView.setImageResource(R.drawable.ic_image)
            imageView.scaleType = android.widget.ImageView.ScaleType.CENTER
            
            // Load image thumbnail safely
            try {
                loadImageThumbnail(image)
            } catch (e: Exception) {
                // Fallback to placeholder on any error during binding
                imageView.setImageResource(R.drawable.ic_image)
                imageView.scaleType = android.widget.ImageView.ScaleType.CENTER
            }
        }
        
        private fun loadImageThumbnail(image: File) {
            try {
                // First, check if file exists and is readable
                if (!image.exists() || !image.canRead()) {
                    imageView.setImageResource(R.drawable.ic_image)
                    return
                }
                
                // Get image dimensions first
                val options = BitmapFactory.Options().apply {
                    inJustDecodeBounds = true
                }
                BitmapFactory.decodeFile(image.absolutePath, options)
                
                // Calculate sample size for efficient memory usage
                val targetSize = 300 // Target size in pixels
                var sampleSize = 1
                
                if (options.outHeight > targetSize || options.outWidth > targetSize) {
                    val halfHeight = options.outHeight / 2
                    val halfWidth = options.outWidth / 2
                    
                    while ((halfHeight / sampleSize) >= targetSize && (halfWidth / sampleSize) >= targetSize) {
                        sampleSize *= 2
                    }
                }
                
                // Load the actual bitmap with calculated sample size
                val finalOptions = BitmapFactory.Options().apply {
                    inSampleSize = sampleSize
                    inJustDecodeBounds = false
                    inPreferredConfig = android.graphics.Bitmap.Config.RGB_565 // Use less memory
                }
                
                val bitmap = BitmapFactory.decodeFile(image.absolutePath, finalOptions)
                if (bitmap != null && !bitmap.isRecycled) {
                    imageView.setImageBitmap(bitmap)
                    imageView.scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
                } else {
                    // Try alternative approach or set placeholder
                    loadImageWithContentResolver(image)
                }
                
            } catch (e: OutOfMemoryError) {
                // Handle memory issues
                System.gc()
                imageView.setImageResource(R.drawable.ic_image)
            } catch (e: Exception) {
                // Try alternative loading method or set placeholder
                loadImageWithContentResolver(image)
            }
        }
        
        private fun loadImageWithContentResolver(image: File) {
            try {
                // Alternative method using file URI
                val uri = android.net.Uri.fromFile(image)
                imageView.setImageURI(uri)
                imageView.scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
            } catch (e: Exception) {
                // Final fallback - show placeholder
                imageView.setImageResource(R.drawable.ic_image)
                imageView.scaleType = android.widget.ImageView.ScaleType.CENTER
            }
        }
    }
}