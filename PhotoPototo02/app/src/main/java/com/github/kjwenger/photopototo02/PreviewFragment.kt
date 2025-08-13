package com.github.kjwenger.photopototo02

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class PreviewFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var folderTextView: TextView
    private lateinit var emptyStateTextView: TextView
    private var selectedFolder: File? = null
    
    companion object {
        // Supported image formats
        private val SUPPORTED_IMAGE_EXTENSIONS = setOf(
            "jpg", "jpeg", "png", "gif", "bmp", "webp", "heic", "heif"
        )
        
        fun isImageFile(file: File): Boolean {
            val extension = file.extension.lowercase()
            return SUPPORTED_IMAGE_EXTENSIONS.contains(extension)
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_preview, container, false)
        
        try {
            folderTextView = view.findViewById(R.id.text_selected_folder)
            emptyStateTextView = view.findViewById(R.id.text_empty_state)
            recyclerView = view.findViewById(R.id.recycler_view_images)
            
            // Use a 2-column grid layout
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            
            imageAdapter = ImageAdapter(requireContext()) { imageFile ->
                try {
                    showImageInfo(imageFile)
                } catch (e: Exception) {
                    // Handle any errors in image info display
                }
            }
            
            recyclerView.adapter = imageAdapter
            
            // Show empty state initially
            showEmptyState(true)
            
        } catch (e: Exception) {
            // Handle any initialization errors
            showEmptyState(true, "Error initializing preview")
        }
        
        return view
    }
    
    fun loadImagesFromFolder(folder: File) {
        try {
            selectedFolder = folder
            
            // Update folder path display
            if (::folderTextView.isInitialized) {
                folderTextView.text = folder.absolutePath
            }
            
            val imageFiles = folder.listFiles()
                ?.filter { it.isFile && isImageFile(it) && it.canRead() }
                ?.sortedBy { it.name.lowercase() }
                ?: emptyList()
            
            if (::imageAdapter.isInitialized) {
                imageAdapter.updateImages(imageFiles)
                if (imageFiles.isNotEmpty()) {
                    showEmptyState(false)
                } else {
                    showEmptyState(true, "No images found in folder: ${folder.name}")
                }
            } else {
                showEmptyState(true, "Adapter not initialized")
            }
            
        } catch (e: SecurityException) {
            showEmptyState(true, "Access denied to folder: ${folder.name}")
        } catch (e: Exception) {
            showEmptyState(true, "Error reading folder: ${e.message}")
        }
    }
    
    private fun showEmptyState(show: Boolean, message: String = "Select a folder in Browse tab to view images") {
        try {
            if (show) {
                if (::emptyStateTextView.isInitialized) {
                    emptyStateTextView.text = message
                    emptyStateTextView.visibility = View.VISIBLE
                }
                if (::recyclerView.isInitialized) {
                    recyclerView.visibility = View.GONE
                }
            } else {
                if (::emptyStateTextView.isInitialized) {
                    emptyStateTextView.visibility = View.GONE
                }
                if (::recyclerView.isInitialized) {
                    recyclerView.visibility = View.VISIBLE
                }
            }
        } catch (e: Exception) {
            // Ignore UI update errors
        }
    }
    
    private fun showImageInfo(imageFile: File) {
        val info = buildString {
            append("Image: ${imageFile.name}\n")
            append("Size: ${formatFileSize(imageFile.length())}\n")
            append("Path: ${imageFile.absolutePath}\n")
            append("Last modified: ${android.text.format.DateFormat.format("MMM dd, yyyy hh:mm a", imageFile.lastModified())}")
        }
        
        AlertDialog.Builder(requireContext())
            .setTitle("Image Information")
            .setMessage(info)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
    
    private fun formatFileSize(bytes: Long): String {
        return when {
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            bytes < 1024 * 1024 * 1024 -> "${bytes / (1024 * 1024)} MB"
            else -> "${bytes / (1024 * 1024 * 1024)} GB"
        }
    }
    
    fun clearImages() {
        selectedFolder = null
        folderTextView.text = "No folder selected"
        imageAdapter.updateImages(emptyList())
        showEmptyState(true)
    }
}