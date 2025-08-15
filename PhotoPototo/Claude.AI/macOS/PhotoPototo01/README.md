# PhotoPototo01

A native macOS application built with Swift that provides a directory browser interface.

## Overview

PhotoPototo01 is a macOS app that displays all local directories in a custom Browse view. The application uses native macOS UI components and Swift to provide a clean, efficient way to explore the file system.

## Features

- **Browse View**: Custom NSView with NSTableView that lists directories
- **Directory Discovery**: Automatically finds and displays:
  - System volumes and drives
  - Common system directories (/Users, /Applications, /System, /Library)
  - User home directory and common folders (Desktop, Documents, Downloads, Pictures, Music, Movies)
- **File System Access**: Proper sandbox entitlements for reading directories
- **Native UI**: Uses standard macOS table view with alternating row colors

## Requirements

- macOS 14.5 or later
- Xcode 15.4 or later
- Swift 5.0

## Building and Running

1. Open `PhotoPototo01.xcodeproj` in Xcode
2. Select the PhotoPototo01 scheme
3. Choose Product → Build (⌘B) to build
4. Choose Product → Run (⌘R) to build and launch the app

## Project Structure

- `AppDelegate.swift`: Application delegate with state restoration disabled
- `ViewController.swift`: Main view controller that sets up the Browse view
- `BrowseView.swift`: Custom view that handles directory discovery and display
- `Main.storyboard`: Interface builder file with window and view controller setup
- `PhotoPototo01.entitlements`: Sandbox entitlements for file system access

## Technical Details

- Bundle Identifier: `com.photopototo01.app`
- Deployment Target: macOS 14.5
- Uses App Sandbox with file system read permissions
- State restoration is disabled to avoid window restoration errors

## Development Notes

- Uses `FileManager.default.homeDirectoryForCurrentUser` for accessing user home directory
- Implements proper git workflow with `git mv` for file operations
- All file system operations respect macOS sandbox restrictions

## Project Status

✅ **Complete and Functional**
- Project successfully built and tested
- All build errors resolved (FileManager API issues fixed)
- Runtime errors addressed (state restoration disabled)
- Complete project rename from PhotoPototo to PhotoPototo01
- Comprehensive documentation added

## Session History

This project was developed through an interactive session with Claude Code, demonstrating:
- Native macOS app development with Swift
- Xcode project management and configuration
- File system access with proper sandbox entitlements
- Error debugging and resolution
- Git workflow best practices
- Documentation-driven development