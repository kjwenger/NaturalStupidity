package com.github.kjwenger.photopototo.managers

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StorageManagerInstrumentedTest {

    private lateinit var storageManager: StorageManager

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        storageManager = StorageManager(context)
    }

    @Test
    fun testDefaultStorageDirectory() {
        val directory = storageManager.getDefaultStorageDirectory()
        assertNotNull("Default storage directory should not be null", directory)
        assertTrue("Default storage directory should not be empty", directory.isNotEmpty())
    }

    @Test
    fun testTrashDirectoryCreation() {
        val trashDir = storageManager.getTrashDirectory()
        assertNotNull("Trash directory should not be null", trashDir)
        assertTrue("Trash directory should exist", trashDir.exists())
        assertTrue("Trash directory should be a directory", trashDir.isDirectory)
    }

    @Test
    fun testCanWriteToExternalStorage() {
        val canWrite = storageManager.canWriteToExternalStorage()
        // This test just ensures the method doesn't crash
        // The actual result depends on device state
        assertNotNull("canWriteToExternalStorage should return a boolean", canWrite)
    }

    @Test
    fun testGetAvailableSpaceBytes() {
        val availableSpace = storageManager.getAvailableSpaceBytes()
        assertTrue("Available space should be >= 0", availableSpace >= 0)
    }
}