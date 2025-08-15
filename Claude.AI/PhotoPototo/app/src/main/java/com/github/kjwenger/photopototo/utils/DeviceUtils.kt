package com.github.kjwenger.photopototo.utils

import android.content.Context
import android.content.res.Configuration

object DeviceUtils {
    
    /**
     * Determines if the current device is a tablet based on screen size
     * Returns true if the smallest width is 600dp or larger
     */
    fun isTablet(context: Context): Boolean {
        val configuration = context.resources.configuration
        return configuration.smallestScreenWidthDp >= 600
    }
    
    /**
     * Determines if the device is in landscape orientation
     */
    fun isLandscape(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
    
    /**
     * Determines if the device is in portrait orientation
     */
    fun isPortrait(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }
}