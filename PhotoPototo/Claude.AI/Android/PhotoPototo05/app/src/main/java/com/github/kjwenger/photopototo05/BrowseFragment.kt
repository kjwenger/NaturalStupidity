package com.github.kjwenger.photopototo05

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class BrowseFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var directoryAdapter: DirectoryAdapter
    private lateinit var pathTextView: TextView
    private var currentPath: File = Environment.getExternalStorageDirectory()
    private var permissionRequested = false
    private var folderSelectionListener: FolderSelectionListener? = null
    
    // Permission request launchers for different Android versions
    private lateinit var storagePermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var manageStorageLauncher: ActivityResultLauncher<Intent>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Set up the folder selection listener
        folderSelectionListener = activity as? FolderSelectionListener
        
        // Initialize permission launchers
        storagePermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            permissionRequested = false
            handlePermissionResults(permissions)
        }
        
        manageStorageLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            permissionRequested = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    loadDirectories()
                } else {
                    showPermissionDeniedDialog()
                }
            }
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)
        
        pathTextView = view.findViewById(R.id.text_current_path)
        recyclerView = view.findViewById(R.id.recycler_view_directories)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        directoryAdapter = DirectoryAdapter { file ->
            if (file.isDirectory) {
                navigateToDirectory(file)
            } else {
                showFileInfo(file)
            }
        }
        
        recyclerView.adapter = directoryAdapter
        
        // Initialize with common Android directories
        initializeDefaultDirectories()
        
        return view
    }
    
    private fun initializeDefaultDirectories() {
        // Check permissions and load directories
        checkPermissionsAndLoad()
    }
    
    private fun checkPermissionsAndLoad() {
        if (permissionRequested) {
            return
        }
        
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                // Android 11+ - Check for MANAGE_EXTERNAL_STORAGE
                if (Environment.isExternalStorageManager()) {
                    loadDirectories()
                } else {
                    requestManageExternalStoragePermission()
                }
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                // Android 6+ - Check for READ_EXTERNAL_STORAGE
                val permissions = mutableListOf<String>()
                
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
                
                if (permissions.isNotEmpty()) {
                    permissionRequested = true
                    storagePermissionLauncher.launch(permissions.toTypedArray())
                } else {
                    loadDirectories()
                }
            }
            else -> {
                // Pre-Android 6 - Permissions are granted at install time
                loadDirectories()
            }
        }
    }
    
    private fun requestManageExternalStoragePermission() {
        if (permissionRequested) {
            return
        }
        
        permissionRequested = true
        AlertDialog.Builder(requireContext())
            .setTitle("Storage Access Required")
            .setMessage("This app needs access to browse all files and folders on your device. Please enable 'All files access' in the next screen.")
            .setPositiveButton("Grant Access") { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                    intent.data = Uri.parse("package:${requireContext().packageName}")
                    manageStorageLauncher.launch(intent)
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                permissionRequested = false
                dialog.dismiss()
                loadLimitedDirectories()
            }
            .show()
    }
    
    private fun handlePermissionResults(permissions: Map<String, Boolean>) {
        val allGranted = permissions.values.all { it }
        
        if (allGranted) {
            loadDirectories()
        } else {
            // Show rationale and offer limited access
            showPermissionRationale()
        }
    }
    
    private fun showPermissionRationale() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Required")
            .setMessage("Storage permission is needed to browse folders. Without it, you can only access limited directories.")
            .setPositiveButton("Grant Permission") { _, _ ->
                permissionRequested = false
                checkPermissionsAndLoad()
            }
            .setNegativeButton("Limited Access") { _, _ ->
                loadLimitedDirectories()
            }
            .show()
    }
    
    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("Without storage permission, you can only browse limited directories.")
            .setPositiveButton("OK") { _, _ ->
                loadLimitedDirectories()
            }
            .show()
    }
    
    private fun loadDirectories() {
        try {
            updateCurrentPath()
            
            val files = mutableListOf<File>()
            
            // Add parent directory option if not at root
            val parent = currentPath.parentFile
            if (parent != null && parent.canRead()) {
                files.add(File(currentPath, "..").apply {
                    // Mark as parent directory for special handling
                })
            }
            
            // Add current directory contents
            currentPath.listFiles()?.let { dirFiles ->
                val sortedFiles = dirFiles
                    .filter { it.canRead() }
                    .sortedWith(compareBy({ !it.isDirectory }, { it.name.lowercase() }))
                files.addAll(sortedFiles)
            }
            
            directoryAdapter.updateFiles(files)
            
        } catch (e: SecurityException) {
            Toast.makeText(
                requireContext(),
                "Access denied to ${currentPath.name}",
                Toast.LENGTH_SHORT
            ).show()
            navigateToParent()
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "Error reading directory: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    private fun loadLimitedDirectories() {
        // Load accessible directories without full storage permission
        val limitedDirs = mutableListOf<File>()
        
        try {
            // Add app-specific directories that don't need permission
            requireContext().getExternalFilesDirs(null).forEach { dir ->
                dir?.let { limitedDirs.add(it) }
            }
            
            // Add media directories if accessible
            listOf(
                Environment.DIRECTORY_DCIM,
                Environment.DIRECTORY_PICTURES,
                Environment.DIRECTORY_MOVIES,
                Environment.DIRECTORY_MUSIC,
                Environment.DIRECTORY_DOWNLOADS
            ).forEach { type ->
                try {
                    val dir = Environment.getExternalStoragePublicDirectory(type)
                    if (dir.exists() && dir.canRead()) {
                        limitedDirs.add(dir)
                    }
                } catch (e: Exception) {
                    // Ignore directories that can't be accessed
                }
            }
            
            directoryAdapter.updateFiles(limitedDirs)
            updateCurrentPath("Limited Access Mode")
            
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "Unable to access directories: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    
    private fun navigateToDirectory(file: File) {
        if (file.name == "..") {
            navigateToParent()
        } else if (file.isDirectory && file.canRead()) {
            currentPath = file
            loadDirectories()
            // Notify listener about folder selection
            folderSelectionListener?.onFolderSelected(currentPath)
        } else {
            Toast.makeText(
                requireContext(),
                "Cannot access ${file.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    private fun navigateToParent() {
        val parent = currentPath.parentFile
        if (parent != null && parent.canRead()) {
            currentPath = parent
            loadDirectories()
            // Notify listener about folder selection
            folderSelectionListener?.onFolderSelected(currentPath)
        }
    }
    
    private fun showFileInfo(file: File) {
        val info = buildString {
            append("File: ${file.name}\n")
            append("Size: ${formatFileSize(file.length())}\n")
            append("Path: ${file.absolutePath}\n")
            append("Last modified: ${android.text.format.DateFormat.format("MMM dd, yyyy hh:mm a", file.lastModified())}")
        }
        
        AlertDialog.Builder(requireContext())
            .setTitle("File Information")
            .setMessage(info)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
    
    private fun updateCurrentPath(customPath: String? = null) {
        pathTextView.text = customPath ?: currentPath.absolutePath
    }
    
    private fun formatFileSize(bytes: Long): String {
        return when {
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            bytes < 1024 * 1024 * 1024 -> "${bytes / (1024 * 1024)} MB"
            else -> "${bytes / (1024 * 1024 * 1024)} GB"
        }
    }
    
    fun navigateBack(): Boolean {
        val parent = currentPath.parentFile
        return if (parent != null && parent.canRead()) {
            currentPath = parent
            loadDirectories()
            // Notify listener about folder selection
            folderSelectionListener?.onFolderSelected(currentPath)
            true
        } else {
            false
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Only refresh if we have permissions, don't trigger permission requests again
        if (::directoryAdapter.isInitialized && hasPermissions()) {
            loadDirectories()
        }
    }
    
    private fun hasPermissions(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                Environment.isExternalStorageManager()
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            }
            else -> true
        }
    }
}