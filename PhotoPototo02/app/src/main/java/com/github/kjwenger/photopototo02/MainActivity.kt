package com.github.kjwenger.photopototo02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File

class MainActivity : AppCompatActivity(), FolderSelectionListener {
    
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var browseFragment: BrowseFragment
    private lateinit var previewFragment: PreviewFragment
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        browseFragment = BrowseFragment()
        previewFragment = PreviewFragment()
        
        // Set folder selection listener for browse fragment
        browseFragment.folderSelectionListener = this
        
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_browse -> {
                    loadFragment(browseFragment)
                    true
                }
                R.id.nav_preview -> {
                    loadFragment(previewFragment)
                    true
                }
                else -> false
            }
        }
        
        // Load initial fragment
        loadFragment(browseFragment)
        bottomNavigation.selectedItemId = R.id.nav_browse
    }
    
    override fun onFolderSelected(folder: File) {
        // Load images in preview fragment when a folder is selected
        previewFragment.loadImagesFromFolder(folder)
        
        // Automatically switch to preview tab
        bottomNavigation.selectedItemId = R.id.nav_preview
        loadFragment(previewFragment)
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
}

interface FolderSelectionListener {
    fun onFolderSelected(folder: File)
}