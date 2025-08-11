package com.github.kjwenger.photopototo.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kjwenger.photopototo.adapters.PreviewAdapter
import com.github.kjwenger.photopototo.databinding.FragmentPreviewBinding
import com.github.kjwenger.photopototo.processors.ImageProcessor
import kotlinx.coroutines.launch
import java.io.File

class PreviewFragment : Fragment() {
    
    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var previewAdapter: PreviewAdapter
    private lateinit var imageProcessor: ImageProcessor
    private var selectedImages: List<File> = emptyList()
    private var rotationDegrees: Float = 90f
    private var onBackPressed: (() -> Unit)? = null
    private var onSavePressed: ((List<File>, Float) -> Unit)? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreviewBinding.inflate(inflater, container, false)
        
        imageProcessor = ImageProcessor(requireContext())
        
        setupRecyclerView()
        setupButtons()
        
        return binding.root
    }
    
    private fun setupRecyclerView() {
        previewAdapter = PreviewAdapter()
        binding.previewRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = previewAdapter
        }
    }
    
    private fun setupButtons() {
        binding.btnBack.setOnClickListener {
            onBackPressed?.invoke()
        }
        
        binding.btnSave.setOnClickListener {
            onSavePressed?.invoke(selectedImages, rotationDegrees)
        }
    }
    
    fun loadPreview(images: List<File>, degrees: Float) {
        selectedImages = images
        rotationDegrees = degrees
        
        binding.progressBar.visibility = View.VISIBLE
        binding.previewRecyclerView.visibility = View.GONE
        
        lifecycleScope.launch {
            val previews = imageProcessor.createPreviews(images, degrees) { current, total ->
                binding.progressText.text = "Loading preview $current of $total"
            }
            
            binding.progressBar.visibility = View.GONE
            binding.previewRecyclerView.visibility = View.VISIBLE
            binding.progressText.text = "${images.size} images selected"
            
            val previewItems = images.map { file ->
                PreviewItem(file, previews[file])
            }
            previewAdapter.submitList(previewItems)
        }
    }
    
    fun setOnBackPressed(callback: () -> Unit) {
        onBackPressed = callback
    }
    
    fun setOnSavePressed(callback: (List<File>, Float) -> Unit) {
        onSavePressed = callback
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    data class PreviewItem(
        val file: File,
        val previewBitmap: Bitmap?
    )
}