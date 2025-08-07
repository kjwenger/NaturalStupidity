package com.github.kjwenger.photostic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.kjwenger.photostic.databinding.ItemImageBinding
import java.io.File

class ImageGridAdapter(
    private val onImageClick: (File) -> Unit,
    private val onSelectionChanged: () -> Unit
) : ListAdapter<File, ImageGridAdapter.ImageViewHolder>(ImageDiffCallback()) {

    private val selectedImages = mutableSetOf<File>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding, onImageClick)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position), selectedImages.contains(getItem(position)))
    }

    fun updateSelection(newSelection: Set<File>) {
        val oldSelection = selectedImages.toSet()
        selectedImages.clear()
        selectedImages.addAll(newSelection)
        
        // Notify changed items for efficient updates
        currentList.forEachIndexed { index, file ->
            val wasSelected = oldSelection.contains(file)
            val isSelected = selectedImages.contains(file)
            if (wasSelected != isSelected) {
                notifyItemChanged(index)
            }
        }
        
        onSelectionChanged()
    }

    class ImageViewHolder(
        private val binding: ItemImageBinding,
        private val onImageClick: (File) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(file: File, isSelected: Boolean) {
            // Load image thumbnail using Glide
            Glide.with(binding.imageThumbnail.context)
                .load(file)
                .centerCrop()
                .into(binding.imageThumbnail)
            
            // Update selection UI
            binding.selectionOverlay.visibility = if (isSelected) View.VISIBLE else View.GONE
            binding.selectionIndicator.visibility = if (isSelected) View.VISIBLE else View.GONE
            
            binding.root.setOnClickListener {
                onImageClick(file)
            }
        }
    }

    private class ImageDiffCallback : DiffUtil.ItemCallback<File>() {
        override fun areItemsTheSame(oldItem: File, newItem: File): Boolean {
            return oldItem.absolutePath == newItem.absolutePath
        }

        override fun areContentsTheSame(oldItem: File, newItem: File): Boolean {
            return oldItem.name == newItem.name && oldItem.lastModified() == newItem.lastModified()
        }
    }
}