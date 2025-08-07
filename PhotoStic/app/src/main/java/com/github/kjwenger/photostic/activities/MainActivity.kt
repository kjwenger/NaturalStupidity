package com.github.kjwenger.photostic.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.kjwenger.photostic.R
// Note: databinding import will be auto-generated after first build
// import com.github.kjwenger.photostic.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // private lateinit var binding: ActivityMainBinding
    private val STORAGE_PERMISSION_CODE = 1001
    private val MANAGE_STORAGE_PERMISSION_CODE = 1002

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        val navController = findNavController(R.id.nav_host_fragment)
        
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_browse, R.id.navigation_rotate)
        )
        
        navView.setupWithNavController(navController)
        
        // Request necessary permissions
        checkAndRequestPermissions()
    }

    private fun checkAndRequestPermissions() {
        val permissionsNeeded = mutableListOf<String>()
        
        // Check for storage permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ permissions
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) 
                != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.READ_MEDIA_IMAGES)
            }
        } else {
            // Pre-Android 13 permissions
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) 
                != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
        
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) 
            != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (permissionsNeeded.isNotEmpty()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, 
                    permissionsNeeded.first())) {
                showPermissionDialog(permissionsNeeded.toTypedArray())
            } else {
                ActivityCompat.requestPermissions(this, 
                    permissionsNeeded.toTypedArray(), STORAGE_PERMISSION_CODE)
            }
        } else {
            // Check for manage external storage permission on Android 11+
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                checkManageExternalStoragePermission()
            }
        }
    }

    private fun checkManageExternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!android.os.Environment.isExternalStorageManager()) {
                showManageStoragePermissionDialog()
            }
        }
    }

    private fun showPermissionDialog(permissions: Array<String>) {
        AlertDialog.Builder(this)
            .setTitle(R.string.permission_storage_title)
            .setMessage(R.string.permission_storage_message)
            .setPositiveButton(R.string.permission_grant) { _, _ ->
                ActivityCompat.requestPermissions(this, permissions, STORAGE_PERMISSION_CODE)
            }
            .setNegativeButton(R.string.permission_cancel) { _, _ ->
                Toast.makeText(this, R.string.message_permission_required, Toast.LENGTH_LONG).show()
            }
            .show()
    }

    private fun showManageStoragePermissionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Full Storage Access")
            .setMessage("PhotoStic needs full storage access to browse all files and folders. This permission allows the app to manage all files on your device.")
            .setPositiveButton(R.string.permission_grant) { _, _ ->
                // Note: In a real app, you would open the system settings for manage external storage
                // For now, we'll just show a message
                Toast.makeText(this, "Please grant 'All files access' in system settings", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton(R.string.permission_cancel) { _, _ ->
                Toast.makeText(this, R.string.message_permission_required, Toast.LENGTH_LONG).show()
            }
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && 
                    grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // Permissions granted, check for manage external storage on Android 11+
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        checkManageExternalStoragePermission()
                    }
                } else {
                    Toast.makeText(this, R.string.message_permission_required, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}