package com.github.kjwenger.photostic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kjwenger.photostic.R
// import com.github.kjwenger.photostic.databinding.FragmentBrowseBinding
import com.github.kjwenger.photostic.adapters.FileSystemAdapter
import java.io.File

class BrowseFragment : Fragment() {

    // private var _binding: FragmentBrowseBinding? = null
    // private val binding get() = _binding!!
    
    private lateinit var fileAdapter: FileSystemAdapter
    private var currentPath: String = "/storage/emulated/0"
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_browse, container, false)

        setupRecyclerView()
        loadDirectory(currentPath)

        return rootView
    }

    private fun setupRecyclerView() {
        fileAdapter = FileSystemAdapter { file ->
            onFileItemClick(file)
        }
        
        rootView.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.files_recycler_view).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = fileAdapter
        }
    }

    private fun loadDirectory(path: String) {
        try {
            val directory = File(path)
            if (directory.exists() && directory.isDirectory) {
                val files = directory.listFiles()?.toList() ?: emptyList()
                
                // Sort files: directories first, then files, both alphabetically
                val sortedFiles = files.sortedWith(compareBy<File> { !it.isDirectory }
                    .thenBy { it.name.lowercase() })
                
                fileAdapter.submitList(sortedFiles)
                rootView.findViewById<android.widget.TextView>(R.id.current_path_text).text = path
                currentPath = path
                
                // Show/hide empty view
                val emptyView = rootView.findViewById<android.widget.TextView>(R.id.empty_view)
                val recyclerView = rootView.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.files_recycler_view)
                emptyView.visibility = if (files.isEmpty()) View.VISIBLE else View.GONE
                recyclerView.visibility = if (files.isEmpty()) View.GONE else View.VISIBLE
            }
        } catch (e: Exception) {
            // Handle permission errors or other issues
            val emptyView = rootView.findViewById<android.widget.TextView>(R.id.empty_view)
            val recyclerView = rootView.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.files_recycler_view)
            emptyView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            emptyView.text = "Unable to access this directory"
        }
    }

    private fun onFileItemClick(file: File) {
        if (file.isDirectory) {
            loadDirectory(file.absolutePath)
        }
        // If it's a file, we don't need to do anything in browse mode
        // File selection happens in the rotate tab
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // _binding = null
    }
}