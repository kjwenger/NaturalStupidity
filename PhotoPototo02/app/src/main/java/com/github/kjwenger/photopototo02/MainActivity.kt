package com.github.kjwenger.photopototo02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File

class MainActivity : AppCompatActivity() {
    
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var browseFragment: BrowseFragment
    private lateinit var previewFragment: PreviewFragment
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        browseFragment = BrowseFragment()
        previewFragment = PreviewFragment()
        
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_browse -> {
                    loadFragment(browseFragment)
                    true
                }
                R.id.nav_preview -> {
                    loadFragment(previewFragment)
                    // When Preview tab is selected, load images from current folder
                    try {
                        if (::browseFragment.isInitialized) {
                            val currentFolder = browseFragment.getCurrentFolder()
                            if (currentFolder != null && currentFolder.exists() && currentFolder.isDirectory) {
                                // Post to ensure fragment is fully loaded before updating
                                previewFragment.view?.post {
                                    previewFragment.loadImagesFromFolder(currentFolder)
                                } ?: run {
                                    // Fragment view not ready, try direct call
                                    previewFragment.loadImagesFromFolder(currentFolder)
                                }
                            } else {
                                // No valid folder, show default empty state
                                previewFragment.view?.post {
                                    previewFragment.showEmptyState(true, "No folder selected in Browse tab")
                                }
                            }
                        } else {
                            // Browse fragment not initialized, show waiting message
                            previewFragment.view?.post {
                                previewFragment.showEmptyState(true, "Browse tab not initialized yet")
                            }
                        }
                    } catch (e: Exception) {
                        // If any error occurs, show error message
                        previewFragment.view?.post {
                            previewFragment.showEmptyState(true, "Error loading folder: ${e.message}")
                        }
                    }
                    true
                }
                else -> false
            }
        }
        
        // Load initial fragment
        loadFragment(browseFragment)
        bottomNavigation.selectedItemId = R.id.nav_browse
        
        // Give BrowseFragment time to initialize before Preview can access it
        browseFragment.view?.post {
            // Fragment is now fully loaded and can be accessed by Preview tab
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        try {
            if (!isFinishing && !isDestroyed) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commitAllowingStateLoss()
            }
        } catch (e: Exception) {
            // Handle fragment transaction errors
        }
    }
    
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment is BrowseFragment && currentFragment.navigateBack()) {
            // Fragment handled the back press
        } else {
            super.onBackPressed()
        }
    }
}