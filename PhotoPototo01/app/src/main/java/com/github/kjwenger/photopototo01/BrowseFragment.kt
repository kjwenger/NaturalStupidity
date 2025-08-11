package com.github.kjwenger.photopototo01

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class BrowseFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var directoryAdapter: DirectoryAdapter
    private var currentPath: File = Environment.getExternalStorageDirectory()
    
    companion object {
        private const val STORAGE_PERMISSION_CODE = 1001
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)
        
        recyclerView = view.findViewById(R.id.recycler_view_directories)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        directoryAdapter = DirectoryAdapter { file ->
            if (file.isDirectory) {
                currentPath = file
                loadDirectories()
            }
        }
        
        recyclerView.adapter = directoryAdapter
        
        checkPermissionAndLoad()
        
        return view
    }
    
    private fun checkPermissionAndLoad() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            loadDirectories()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        }
    }
    
    private fun loadDirectories() {
        try {
            val files = currentPath.listFiles()?.filter { 
                it.isDirectory || it.isFile 
            }?.sortedWith(compareBy({ !it.isDirectory }, { it.name.lowercase() }))
            
            directoryAdapter.updateFiles(files ?: emptyList())
        } catch (e: SecurityException) {
            Toast.makeText(
                requireContext(),
                "Permission denied to access this directory",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadDirectories()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Storage permission is required to browse directories",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    
    fun navigateBack(): Boolean {
        val parent = currentPath.parentFile
        return if (parent != null && parent.canRead()) {
            currentPath = parent
            loadDirectories()
            true
        } else {
            false
        }
    }
}