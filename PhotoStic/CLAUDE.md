# PhotoStic - Project Overview

## Project Description
PhotoStic is an Android native application.

## Architecture
Android native application using Java/Kotlin with Android SDK

## Key Components
- MainActivity with bottom navigation tabs
- Browse tab (Fragment) - file system navigation with folder/subfolder browsing
- Rotate tab (Fragment) - displays images in grid with action toolbar
- Action toolbar with "To Portrait", "To Landscape", and "Preview" actions
- "To Landscape" action automatically selects all portrait-oriented photos (taller than wide)
- Preview pane - overlays on top of tabs when images are selected
- Preview back button with left-arrow navigation
- Save action in preview pane for permanent rotation with backup to trash
- Row layout for displaying rotated preview images
- File system adapter for folder navigation
- Image grid adapter for Rotate tab with manual selection support
- Manual selection mode with tap-to-toggle functionality
- Shared state management for selected folder between tabs
- Image rotation processing functionality (preview and permanent)
- [ADDITIONAL COMPONENTS TO BE FILLED]

## Development Setup
- Android Studio IDE (Path configured in .env.local: ${ANDROID_STUDIO_PATH})
- Android SDK (Path configured in .env.local: ${ANDROID_SDK_PATH})
- Java/Kotlin development environment
- Copy .env.example to .env.local and configure local paths
- [ADDITIONAL SETUP REQUIREMENTS TO BE FILLED]

## Testing
- Android Unit Tests
- Android Instrumented Tests
- [TESTING FRAMEWORK TO BE FILLED]

## Deployment
- Google Play Store deployment
- APK generation and signing
- [BUILD PROCESS TO BE FILLED]

## Important Notes
- Native Android application
- Requires access to all storage on Android OS (not just photos)
- Will need READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, and MANAGE_EXTERNAL_STORAGE permissions
- Must handle Android 13+ scoped storage restrictions for full file system access
- File browser functionality requires careful permission handling
- Save functionality requires access to Android Trash folder and file manipulation capabilities
- [ANY ADDITIONAL NOTES FOR CLAUDE TO KNOW]