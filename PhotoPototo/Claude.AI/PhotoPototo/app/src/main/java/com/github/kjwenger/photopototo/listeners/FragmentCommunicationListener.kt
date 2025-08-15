package com.github.kjwenger.photopototo.listeners

import java.io.File

/**
 * Interface for communication between Browse and Rotate fragments
 * Used primarily in tablet mode where both fragments are visible simultaneously
 */
interface FragmentCommunicationListener {
    
    /**
     * Called when a folder is selected in the Browse fragment
     * The Rotate fragment should update to show images from this folder
     */
    fun onFolderSelected(folder: File)
    
    /**
     * Called when images are selected in the Rotate fragment
     * Can be used for updating UI or enabling/disabling actions
     */
    fun onImagesSelected(selectedImages: Set<File>)
    
    /**
     * Called when the preview action is triggered
     * Should show the preview overlay
     */
    fun onPreviewRequested(selectedImages: Set<File>)
}