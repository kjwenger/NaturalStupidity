package com.github.kjwenger.photopototo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.github.kjwenger.photopototo.adapters.ImageGridAdapter
import com.github.kjwenger.photopototo.databinding.FragmentRotateBinding
import com.github.kjwenger.photopototo.listeners.FragmentCommunicationListener
import java.io.File

class RotateFragment : Fragment() {

    private var _binding: FragmentRotateBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var imageAdapter: ImageGridAdapter
    private val selectedImages = mutableSetOf<File>()
    private var communicationListener: FragmentCommunicationListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRotateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        setupActionButtons()
        
        // For now, load images from a default directory
        // In a complete implementation, this would be synchronized with the Browse fragment
        loadImagesFromDirectory("/storage/emulated/0/DCIM/Camera")

        return root
    }

    private fun setupRecyclerView() {
        imageAdapter = ImageGridAdapter(
            onImageClick = { file -> onImageClick(file) },
            onSelectionChanged = { updateActionButtons() }
        )
        
        binding.imagesRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3) // 3 columns
            adapter = imageAdapter
        }
    }

    private fun setupActionButtons() {
        binding.btnToPortrait.setOnClickListener {
            // TODO: Implement portrait rotation
            // This would rotate selected landscape images to portrait
        }
        
        binding.btnToLandscape.setOnClickListener {
            selectPortraitImages()
        }
        
        binding.btnPreview.setOnClickListener {
            if (selectedImages.isNotEmpty()) {
                // TODO: Show preview fragment
                // This would open the preview overlay
            }
        }
        
        updateActionButtons()
    }

    private fun loadImagesFromDirectory(path: String) {
        try {
            val directory = File(path)
            if (directory.exists() && directory.isDirectory) {
                val imageFiles = directory.listFiles { file ->
                    file.isFile && isImageFile(file.extension.lowercase())
                }?.toList() ?: emptyList()
                
                imageAdapter.submitList(imageFiles)
                
                // Show/hide empty view
                binding.emptyView.visibility = if (imageFiles.isEmpty()) View.VISIBLE else View.GONE
                binding.imagesRecyclerView.visibility = if (imageFiles.isEmpty()) View.GONE else View.VISIBLE
            }
        } catch (e: Exception) {
            // Handle errors
            binding.emptyView.visibility = View.VISIBLE
            binding.imagesRecyclerView.visibility = View.GONE
        }
    }

    private fun onImageClick(file: File) {
        if (selectedImages.contains(file)) {
            selectedImages.remove(file)
        } else {
            selectedImages.add(file)
        }
        imageAdapter.updateSelection(selectedImages)
        updateActionButtons()
    }

    private fun selectPortraitImages() {
        // Get all portrait images (height > width) and add them to selection
        val currentList = imageAdapter.currentList
        for (file in currentList) {
            if (isPortraitImage(file)) {
                selectedImages.add(file)
            }
        }
        imageAdapter.updateSelection(selectedImages)
        updateActionButtons()
    }

    private fun updateActionButtons() {
        val hasSelection = selectedImages.isNotEmpty()
        binding.btnPreview.isEnabled = hasSelection
        
        // Notify listener about selection changes
        communicationListener?.onImagesSelected(selectedImages)
    }

    private fun isImageFile(extension: String): Boolean {
        return extension in setOf("jpg", "jpeg", "png", "gif", "bmp", "webp")
    }

    private fun isPortraitImage(file: File): Boolean {
        // This is a simplified check. In a real implementation,
        // you would read the actual image dimensions
        return true // Placeholder - assume some images are portrait
    }

    fun loadImagesFromFolder(folder: File) {
        selectedImages.clear()
        imageAdapter.updateSelection(selectedImages)
        loadImagesFromDirectory(folder.absolutePath)
        updateActionButtons()
    }
    
    fun setCommunicationListener(listener: FragmentCommunicationListener?) {
        communicationListener = listener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}