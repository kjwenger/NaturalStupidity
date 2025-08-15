package com.github.kjwenger.photopototo05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File

class MainActivity : AppCompatActivity(), FolderSelectionListener {
    
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var browseFragment: BrowseFragment
    private lateinit var previewFragment: PreviewFragment
    private var currentSelectedFolder: File? = null
    
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
                    // Update preview with current selected folder when tab is clicked
                    currentSelectedFolder?.let { folder ->
                        previewFragment.showImagesForFolder(folder)
                    }
                    true
                }
                else -> false
            }
        }
        
        // Load initial fragment
        loadFragment(browseFragment)
        bottomNavigation.selectedItemId = R.id.nav_browse
    }
    
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
    
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment is BrowseFragment && currentFragment.navigateBack()) {
            // Fragment handled the back press
        } else {
            super.onBackPressed()
        }
    }
    
    override fun onFolderSelected(folder: File) {
        currentSelectedFolder = folder
        // If preview fragment is currently visible, update it immediately
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment is PreviewFragment) {
            previewFragment.showImagesForFolder(folder)
        }
    }
}