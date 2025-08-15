package com.github.kjwenger.photopototo.managers

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class ImageManagerTest {

    @Test
    fun testIsImageFile() {
        val jpgFile = File("test.jpg")
        val pngFile = File("test.png")
        val txtFile = File("test.txt")
        
        assertTrue("JPG files should be recognized as images", ImageManager.isImageFile(jpgFile))
        assertTrue("PNG files should be recognized as images", ImageManager.isImageFile(pngFile))
        assertFalse("TXT files should not be recognized as images", ImageManager.isImageFile(txtFile))
    }

    @Test
    fun testImageFileExtensions() {
        val supportedExtensions = listOf("jpg", "jpeg", "png", "gif", "bmp", "webp")
        val unsupportedExtensions = listOf("txt", "pdf", "mp4", "doc")
        
        for (ext in supportedExtensions) {
            val file = File("test.$ext")
            assertTrue("$ext files should be supported", ImageManager.isImageFile(file))
        }
        
        for (ext in unsupportedExtensions) {
            val file = File("test.$ext")
            assertFalse("$ext files should not be supported", ImageManager.isImageFile(file))
        }
    }
}