package com.github.kjwenger.photopototo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.kjwenger.photopototo.databinding.ItemPreviewBinding
import com.github.kjwenger.photopototo.fragments.PreviewFragment

class PreviewAdapter : ListAdapter<PreviewFragment.PreviewItem, PreviewAdapter.PreviewViewHolder>(PreviewDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        val binding = ItemPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PreviewViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class PreviewViewHolder(
        private val binding: ItemPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: PreviewFragment.PreviewItem) {
            binding.fileName.text = item.file.name
            
            if (item.previewBitmap != null) {
                binding.previewImage.setImageBitmap(item.previewBitmap)
            } else {
                // Show placeholder or error image
                binding.previewImage.setImageResource(android.R.drawable.ic_menu_report_image)
            }
        }
    }
    
    private class PreviewDiffCallback : DiffUtil.ItemCallback<PreviewFragment.PreviewItem>() {
        override fun areItemsTheSame(oldItem: PreviewFragment.PreviewItem, newItem: PreviewFragment.PreviewItem): Boolean {
            return oldItem.file.absolutePath == newItem.file.absolutePath
        }
        
        override fun areContentsTheSame(oldItem: PreviewFragment.PreviewItem, newItem: PreviewFragment.PreviewItem): Boolean {
            return oldItem.file.name == newItem.file.name && 
                   oldItem.previewBitmap == newItem.previewBitmap
        }
    }
}