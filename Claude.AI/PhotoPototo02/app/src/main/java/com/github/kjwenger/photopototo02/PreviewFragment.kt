package com.github.kjwenger.photopototo02

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
    private lateinit var emptyTextView: TextView
    private lateinit var imageAdapter: ImageAdapter
    private var currentFolder: File? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        recyclerView = view.findViewById(R.id.recycler_view_images)
        emptyTextView = view.findViewById(R.id.text_empty)
        
        setupRecyclerView()
        
        // Load images if folder was set before view was created
        if (currentFolder != null) {
            loadImages()
        } else {
            showEmptyGrid()
        }
    }
    
    private fun setupRecyclerView() {
        imageAdapter = ImageAdapter(requireContext()) { imageFile ->
            // Handle image click - could open full screen view
            // For now, just show a toast or do nothing
        }
        
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = imageAdapter
    }
    
    fun showImagesForFolder(folder: File) {
        currentFolder = folder
        // Only load images if view is created
        if (::recyclerView.isInitialized) {
            loadImages()
        }
        // If view is not created yet, loadImages() will be called in onViewCreated()
    }
    
    private fun loadImages() {
        val folder = currentFolder ?: return
        
        try {
            val imageFiles = folder.listFiles { file ->
                file.isFile && isImageFile(file)
            }?.toList() ?: emptyList()
            
            if (imageFiles.isEmpty()) {
                showEmptyGrid()
            } else {
                showImages(imageFiles)
            }
        } catch (e: SecurityException) {
            showEmptyGrid()
        }
    }
    
    private fun isImageFile(file: File): Boolean {
        val imageExtensions = setOf("jpg", "jpeg", "png", "gif", "bmp", "webp")
        val extension = file.extension.lowercase()
        return extension in imageExtensions
    }
    
    private fun showImages(images: List<File>) {
        emptyTextView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        imageAdapter.updateImages(images)
    }
    
    private fun showEmptyGrid() {
        emptyTextView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        imageAdapter.updateImages(emptyList())
    }
    
    private fun showEmptyState() {
        recyclerView.visibility = View.GONE
        emptyTextView.visibility = View.VISIBLE
        emptyTextView.text = if (currentFolder == null) {
            "Select a folder in Browse tab to view images"
        } else {
            "No images found in this folder"
        }
    }
}