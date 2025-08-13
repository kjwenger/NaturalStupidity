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
            
            // Load image thumbnail
            try {
                val options = BitmapFactory.Options()
                options.inSampleSize = 4 // Scale down the image to reduce memory usage
                options.inJustDecodeBounds = false
                
                val bitmap = BitmapFactory.decodeFile(image.absolutePath, options)
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap)
                } else {
                    // Set placeholder for unsupported image formats
                    imageView.setImageResource(R.drawable.ic_image)
                }
            } catch (e: Exception) {
                // Set placeholder on error
                imageView.setImageResource(R.drawable.ic_image)
            }
        }
    }
}