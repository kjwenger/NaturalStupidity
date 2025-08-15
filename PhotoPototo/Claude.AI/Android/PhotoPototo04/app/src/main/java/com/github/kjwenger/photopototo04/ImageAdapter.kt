package com.github.kjwenger.photopototo04

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class ImageAdapter(
    private val context: Context,
    private val onImageClick: (File) -> Unit,
    private val onSelectionChange: (File, Boolean) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    
    private var images = listOf<File>()
    private val selectedImages = mutableSetOf<File>()
    
    fun updateImages(newImages: List<File>) {
        images = newImages
        selectedImages.clear()
        notifyDataSetChanged()
    }
    
    fun selectAll() {
        selectedImages.clear()
        selectedImages.addAll(images)
        notifyDataSetChanged()
    }
    
    fun selectNone() {
        selectedImages.clear()
        notifyDataSetChanged()
    }
    
    fun invertSelection() {
        val newSelection = images.toSet() - selectedImages
        selectedImages.clear()
        selectedImages.addAll(newSelection)
        notifyDataSetChanged()
    }
    
    fun getSelectedImages(): Set<File> = selectedImages.toSet()
    
    fun isSelected(file: File): Boolean = selectedImages.contains(file)
    
    fun setSelection(file: File, selected: Boolean) {
        val wasSelected = selectedImages.contains(file)
        if (wasSelected == selected) return // No change needed
        
        if (selected) {
            selectedImages.add(file)
        } else {
            selectedImages.remove(file)
        }
        
        // Find the position and update only that item
        val position = images.indexOf(file)
        if (position >= 0) {
            notifyItemChanged(position)
        }
        
        onSelectionChange(file, selected)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageFile = images[position]
        holder.bind(imageFile)
    }
    
    override fun getItemCount(): Int = images.size
    
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view)
        private val nameTextView: TextView = itemView.findViewById(R.id.text_name)
        private val checkbox: CheckBox = itemView.findViewById(R.id.checkbox_select)
        
        fun bind(imageFile: File) {
            nameTextView.text = imageFile.name
            
            // Clear any existing listeners to prevent issues
            checkbox.setOnCheckedChangeListener(null)
            
            // Set checkbox state without triggering listener
            checkbox.isChecked = isSelected(imageFile)
            
            // Set checkbox click listener
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                // Double-check to avoid unnecessary calls
                if (isSelected(imageFile) != isChecked) {
                    setSelection(imageFile, isChecked)
                }
            }
            
            // Set item click listener
            itemView.setOnClickListener {
                onImageClick(imageFile)
            }
            
            // Load image thumbnail
            loadImageThumbnail(imageFile)
        }
        
        private fun loadImageThumbnail(imageFile: File) {
            try {
                // Create a scaled down bitmap for thumbnail
                val options = BitmapFactory.Options().apply {
                    inJustDecodeBounds = true
                }
                BitmapFactory.decodeFile(imageFile.absolutePath, options)
                
                // Calculate sample size for thumbnail
                options.inSampleSize = calculateInSampleSize(options, 200, 200)
                options.inJustDecodeBounds = false
                
                val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath, options)
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap)
                    imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                } else {
                    imageView.setImageResource(R.drawable.ic_image)
                    imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                }
            } catch (e: Exception) {
                // Fallback to generic image icon
                imageView.setImageResource(R.drawable.ic_image)
                imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
        }
        
        private fun calculateInSampleSize(
            options: BitmapFactory.Options,
            reqWidth: Int,
            reqHeight: Int
        ): Int {
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1
            
            if (height > reqHeight || width > reqWidth) {
                val halfHeight = height / 2
                val halfWidth = width / 2
                
                while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                    inSampleSize *= 2
                }
            }
            
            return inSampleSize
        }
    }
}