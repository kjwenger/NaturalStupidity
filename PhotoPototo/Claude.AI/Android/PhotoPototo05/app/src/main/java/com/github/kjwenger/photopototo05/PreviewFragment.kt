package com.github.kjwenger.photopototo05

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import java.io.File

class PreviewFragment : Fragment() {
    
    private lateinit var toolbar: MaterialToolbar
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
        
        toolbar = view.findViewById(R.id.toolbar_preview)
        recyclerView = view.findViewById(R.id.recycler_view_images)
        emptyTextView = view.findViewById(R.id.text_empty)
        
        setupToolbar()
        setupRecyclerView()
        
        // Load images if folder was set before view was created
        if (currentFolder != null) {
            loadImages()
        } else {
            showEmptyGrid()
        }
    }
    
    private fun setupToolbar() {
        // Inflate menu
        toolbar.inflateMenu(R.menu.menu_preview_toolbar)
        
        // Set menu item click listener
        toolbar.setOnMenuItemClickListener { menuItem ->
            handleMenuItemClick(menuItem)
        }
        
        // Update toolbar title based on current folder
        updateToolbarTitle()
    }
    
    private fun setupRecyclerView() {
        imageAdapter = ImageAdapter(
            requireContext(),
            onImageClick = { imageFile ->
                // Handle image click - could open full screen view
                // For now, just show a toast or do nothing
            },
            onSelectionChange = { imageFile, isSelected ->
                // Handle selection change
                handleSelectionChange(imageFile, isSelected)
            }
        )
        
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = imageAdapter
    }
    
    fun showImagesForFolder(folder: File) {
        currentFolder = folder
        // Only load images if view is created
        if (::recyclerView.isInitialized) {
            loadImages()
            updateToolbarTitle()
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
    
    private fun updateToolbarTitle() {
        if (::toolbar.isInitialized) {
            val baseName = currentFolder?.name ?: "Preview"
            if (::imageAdapter.isInitialized) {
                val selectedCount = imageAdapter.getSelectedImages().size
                if (selectedCount > 0) {
                    toolbar.title = "$baseName ($selectedCount selected)"
                } else {
                    toolbar.title = baseName
                }
            } else {
                toolbar.title = baseName
            }
        }
    }
    
    private fun handleMenuItemClick(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_select_all -> {
                selectAllImages()
                true
            }
            R.id.action_select_none -> {
                selectNoneImages()
                true
            }
            R.id.action_invert_selection -> {
                invertSelection()
                true
            }
            R.id.action_select_landscape -> {
                selectLandscapeImages()
                true
            }
            R.id.action_select_portrait -> {
                selectPortraitImages()
                true
            }
            else -> false
        }
    }
    
    private fun selectAllImages() {
        if (::imageAdapter.isInitialized) {
            imageAdapter.selectAll()
            updateToolbarTitle()
        }
    }
    
    private fun selectNoneImages() {
        if (::imageAdapter.isInitialized) {
            imageAdapter.selectNone()
            updateToolbarTitle()
        }
    }
    
    private fun invertSelection() {
        if (::imageAdapter.isInitialized) {
            imageAdapter.invertSelection()
            updateToolbarTitle()
        }
    }
    
    private fun selectLandscapeImages() {
        if (::imageAdapter.isInitialized) {
            imageAdapter.selectLandscape()
            updateToolbarTitle()
        }
    }
    
    private fun selectPortraitImages() {
        if (::imageAdapter.isInitialized) {
            imageAdapter.selectPortrait()
            updateToolbarTitle()
        }
    }
    
    private fun handleSelectionChange(imageFile: File, isSelected: Boolean) {
        // Update toolbar title to show selection count
        updateToolbarTitle()
    }
    
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}