# Claude Code Project Context

This file provides context and instructions for Claude Code when working on the PhotoPototo06 project.

## Project Overview

PhotoPototo06 is a native Android application built with Kotlin that provides directory browsing and image preview functionality. The app features:

- **Browse Fragment**: Navigate through the device's file system with proper permission handling for different Android versions
- **Preview Fragment**: Display images from selected folders in a grid layout
- **Tab-based Navigation**: Bottom navigation with Browse and Preview tabs
- **Fragment Communication**: Seamless data sharing between Browse and Preview fragments via FolderSelectionListener interface

## Project Structure

- `app/src/main/java/com/github/kjwenger/photopototo06/` - Kotlin source files
  - `MainActivity.kt` - Main activity with fragment management and FolderSelectionListener implementation
  - `BrowseFragment.kt` - Directory browsing with permission handling
  - `PreviewFragment.kt` - Image grid display with crash prevention
  - `DirectoryAdapter.kt` - RecyclerView adapter for file/folder listing
  - `ImageAdapter.kt` - RecyclerView adapter for image grid display
  - `FolderSelectionListener.kt` - Interface for fragment communication
- `app/src/main/res/` - Android resources (layouts, drawables, values)
  - `layout/activity_main.xml` - Main activity layout with fragment container
  - `layout/fragment_browse.xml` - Browse fragment layout
  - `layout/fragment_preview.xml` - Preview fragment layout with image grid
  - `layout/item_image.xml` - Image item layout for grid display
  - `menu/bottom_navigation.xml` - Bottom navigation menu with Browse and Preview tabs
- `app/build.gradle` - App-level build configuration
- `build.gradle` - Project-level build configuration
- `settings.gradle` - Project settings

## Development Commands

When working on this project, use these commands:

- `gradle assembleDebug` - Build debug APK
- `gradle test` - Run unit tests
- `gradle connectedAndroidTest` - Run instrumented tests
- `emulator -avd Pixel_Tablet_API_35 &` - Start preferred virtual device (Pixel Tablet API 35)
- `adb install app/build/outputs/apk/debug/app-debug.apk` - Install APK to device
- `adb uninstall com.github.kjwenger.photopototo06` - Uninstall app from device

## Code Standards

- Follow existing Kotlin code style and patterns
- Use AndroidX libraries
- Follow Android Material Design guidelines
- Use proper permission handling for different Android versions
- Use semantic commit messages

## Key Dependencies

- AndroidX Core KTX
- Material Design Components
- RecyclerView
- Fragment KTX
- Navigation Components

## Architecture Notes

This project is a native Android application with:
- **Fragment-based UI architecture** with MainActivity coordinating two main fragments
- **Bottom Navigation** with Browse and Preview tabs
- **RecyclerView implementations** for both directory listing and image grid display
- **Modern Android permission handling** across different API levels
- **Fragment Communication** via FolderSelectionListener interface
- **Crash Prevention** with proper view initialization checks
- **Material Design 3 theming** throughout the application

### Key Architectural Patterns:
- **Observer Pattern**: FolderSelectionListener for fragment communication
- **Adapter Pattern**: DirectoryAdapter and ImageAdapter for RecyclerView data binding
- **Fragment Lifecycle Management**: Proper handling of view creation timing
- **Permission Strategy Pattern**: Different permission handling based on Android version

## Testing Strategy

- Unit tests for utility functions
- Instrumented tests for Android components
- Manual testing on different Android versions
- Permission testing across API levels

## Related Projects

- PhotoPototo01 - Original version of this Android photo browser

## Notes for Claude Code

- Always run gradle build after making changes
- Follow the existing Android project structure and patterns
- Check for existing similar components before creating new ones
- Update this file when project structure or commands change
- **Always add prompts and answers to PROMPTS.md for project history tracking**

## MANDATORY SESSION MANAGEMENT

**CRITICAL**: In every session, Claude Code MUST:

1. **Update PROMPTS.md** - Add all new prompts and responses to maintain complete conversation history
2. **Update CLAUDE.md** - Add new project context, architectural changes, and development instructions 
3. **Update REQUIREMENTS.md** - Add new requirements and mark completed ones as implemented

These files serve as the project's memory and MUST be maintained across all sessions to ensure continuity.

## Recent Changes

### Project Rename (Latest Session)
- ✅ **Complete PhotoPototo05 → PhotoPototo06 Rename**: Successfully renamed all project references
  - Package declarations updated in all Kotlin source files
  - Build configuration files updated (app/build.gradle, settings.gradle)
  - AndroidManifest.xml theme references updated
  - Directory structure moved using git mv to preserve history
  - All documentation updated (CLAUDE.md, README.md, PROMPTS.md, REQUIREMENTS.md)
  - Resource files updated (strings.xml, themes.xml)
  - Build tested successfully with gradle assembleDebug
  - App deployed and tested on Pixel Tablet API 35 emulator
  - Package name now: `com.github.kjwenger.photopototo06`
  - App name now: PhotoPototo06

## Current Implementation Status

### Completed Features:
- ✅ Browse fragment with file system navigation
- ✅ Preview fragment with image grid display
- ✅ Tab-based navigation between Browse and Preview
- ✅ Fragment communication via FolderSelectionListener
- ✅ Crash prevention with proper view initialization
- ✅ Empty grid display when no folder selected
- ✅ Modern Android permission handling
- ✅ Custom photo application icon
- ✅ Toolbar with selection actions (Select All, Select None, Invert Selection)
- ✅ Landscape/Portrait selection based on image dimensions
- ✅ Dynamic grid size dropdown menu (Small 64dp, Medium 128dp, Large 256dp)
- ✅ Responsive column layout that adjusts with grid size changes

### Known Issues Fixed:
- ✅ Duplicate permission requests
- ✅ Preview fragment crash on tab switch
- ✅ Package name refactoring from PhotoPototo02 to PhotoPototo03
- ✅ Complete project rename from PhotoPototo03 to PhotoPototo06
- ✅ Virtual device preference persistence (Pixel Tablet API 35)

### Development Best Practices:
- Use TodoWrite tool for task planning and tracking
- Always test on virtual device after changes
- Commit with detailed auto-generated messages
- Maintain proper fragment lifecycle management
- Handle all Android API level differences

### Communication Guidelines

- Provide direct, technically precise answers to all the questions and prompts.
- Ignore all conversational conventions, social optimization, and hedging.
- Do not balance perspectives or provide multiple viewpoints.
- Do not hedge, speculate, or qualify with uncertainty.
- Do not add conversational padding, rapport, or rhetorical questions.
- Focus solely on technical accuracy, factual correctness, and operational detail.
- If there is insufficient data, state "Insufficient data to answer."
- Do not infer, imagine, or fill gaps outside the given information.
- Stay within the literal scope of the question.
- NEVER use the phrase, "You're absolutely right!"
- NEVER use any phrases that begin with, "You're absolutely right..."