# PhotoStic

A native Android application for browsing your file system and rotating images with an intuitive interface.

## Overview

PhotoStic is a powerful image rotation tool that allows you to navigate through your entire Android device storage, select images (both manually and automatically), and rotate them to landscape orientation with preview functionality and backup to trash.

## Features

### 🗂️ File System Navigation
- Browse all folders and subfolders on your Android device
- Access internal storage, SD card, and USB OTG devices  
- Navigate through complete file system with hierarchical display
- Support for various file types with appropriate icons

### 📱 Dual-Tab Interface
- **Browse Tab**: File system navigation with folder/subfolder browsing
- **Rotate Tab**: Image grid display with action toolbar and selection capabilities

### 🖼️ Smart Image Selection
- **Manual Selection**: Tap individual images to select/deselect
- **Automatic Portrait Detection**: "To Landscape" button automatically selects all portrait photos (height > width)
- **Combined Selection**: Mix manual and automatic selection methods
- **Visual Indicators**: Clear selection feedback with checkmarks/highlighting

### 👁️ Preview Mode
- **Non-destructive Preview**: See how images will look after rotation without modifying originals
- **Overlay Interface**: Preview pane appears on top of tab navigation
- **Horizontal Row Layout**: Compare multiple rotated images side by side
- **90° Counter-clockwise Rotation**: Preview shows final result

### 💾 Safe Save Operation
- **Trash Backup**: Original images automatically copied to Android Trash folder before modification
- **Permanent Rotation**: Apply 90° counter-clockwise rotation to original files
- **Quality Preservation**: Maintain EXIF data and image quality during rotation
- **Operation Feedback**: Clear success/failure notifications

## Technical Specifications

### Architecture
- **Platform**: Native Android Application
- **Languages**: Java/Kotlin
- **UI Framework**: Android SDK with Material Design components
- **Navigation**: Bottom Navigation with Fragment-based architecture

### Permissions Required
- `READ_EXTERNAL_STORAGE` - Access device storage for browsing
- `WRITE_EXTERNAL_STORAGE` - Modify images and create backups
- `MANAGE_EXTERNAL_STORAGE` - Full file system access (Android 11+)

### Minimum Requirements
- **Android API Level**: 21+ (Android 5.0)
- **Special Handling**: Android 13+ (API 33) scoped storage compatibility
- **Storage**: Access to internal storage, external storage, and trash folder

## Project Structure

```
PhotoStic/
├── app/                           # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/              # Java/Kotlin source files
│   │   │   │   └── com/github/kjwenger/photostic/
│   │   │   │       ├── activities/    # MainActivity
│   │   │   │       ├── fragments/     # Browse, Rotate, Preview fragments
│   │   │   │       ├── adapters/      # File system and image grid adapters
│   │   │   │       ├── models/        # Data models with selection state
│   │   │   │       ├── processors/    # Image rotation processors
│   │   │   │       ├── managers/      # State management
│   │   │   │       └── utils/         # Utility classes
│   │   │   ├── res/               # Resources
│   │   │   │   ├── layout/        # XML layouts
│   │   │   │   ├── drawable/      # Icons and drawables
│   │   │   │   └── values/        # Strings, colors, dimensions
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                  # Unit tests
│   │   └── androidTest/           # Instrumented tests
│   └── build.gradle               # App-level configuration
├── build.gradle                   # Project-level configuration
├── settings.gradle                # Project settings
└── local.properties              # Local SDK paths
```

## Key Components

### Core Classes
- `MainActivity` - Main activity with bottom navigation
- `BrowseFragment` - File system navigation
- `RotateFragment` - Image grid with selection and actions
- `PreviewFragment` - Overlay preview pane
- `ImageRotationProcessor` - Handle permanent rotations
- `PreviewRotationProcessor` - Handle temporary preview rotations
- `TrashManager` - Android trash folder operations

### User Interface
- Bottom navigation tabs (Browse/Rotate)
- Action toolbar with rotation and preview actions
- Grid layout for image display
- Row layout for preview mode
- Material Design components and icons

## Installation

> **Note**: This project is currently in specification/planning phase. Implementation is pending.

### Development Setup
1. Install Android Studio
2. Clone this repository
3. Copy `.env.example` to `.env.local` and configure paths:
   ```bash
   cp .env.example .env.local
   # Edit .env.local with your local Android Studio and SDK paths
   ```
4. Open project in Android Studio (path from ${ANDROID_STUDIO_PATH})
   - SDK will be configured from ${ANDROID_SDK_PATH}
5. Configure Android SDK (API 21+)
6. Build and run on device/emulator

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK with API level 21+
- Device with Android 5.0+ for testing

## Usage

### Basic Workflow
1. **Browse**: Navigate to folder containing images
2. **Select**: Switch to Rotate tab to see images in grid
3. **Choose Images**: 
   - Tap individual images for manual selection
   - Use "To Landscape" for automatic portrait detection
4. **Preview**: Tap "Preview" to see rotated images without changes
5. **Save**: In preview mode, tap "Save" to make permanent changes

### Selection Methods
- **Manual**: Tap images to toggle selection state
- **Automatic**: "To Landscape" selects all portrait photos (height > width)
- **Combined**: Mix both methods for precise control

### Safety Features
- All originals backed up to Android Trash before modification
- Preview mode shows changes without altering files
- Clear visual feedback for all operations

## Contributing

This project follows standard Android development practices:

- **Code Style**: Follow Android Kotlin/Java style guidelines
- **Architecture**: MVVM or MVP patterns preferred
- **Testing**: Unit tests for business logic, instrumented tests for UI
- **Documentation**: Update specifications when adding features

## Development Status

📋 **Current Phase**: Initial Implementation Complete
- ✅ Requirements documentation complete
- ✅ Architecture design finalized  
- ✅ Android project structure created
- ✅ Basic UI components implemented (MainActivity, Browse/Rotate fragments)
- ✅ File system navigation functionality
- ✅ Image grid display with selection
- ⏳ Image rotation processing pending
- ⏳ Preview overlay functionality pending
- ⏳ Save functionality with trash backup pending
- ⏳ Testing phase pending
- ⏳ Release preparation pending

## Building the Project

The PhotoStic Android application has been created and can be built using Android Studio:

1. Open the project in Android Studio (path configured in your .env.local)
2. Let Android Studio sync the project and download dependencies
3. Build the project using: **Build > Make Project** or **Ctrl+F9**
4. Run on device/emulator: **Run > Run 'app'** or **Shift+F10**

### Command Line Building
```bash
# Generate Gradle wrapper first (if using Android Studio):
# File > New > Project > ... (creates wrapper automatically)

# Then build from command line:
gradlew assembleDebug          # Build debug APK
gradlew installDebug           # Install on connected device
```

## Documentation

- [`CLAUDE.md`](CLAUDE.md) - Claude Code project context
- [`REQUIREMENTS.md`](REQUIREMENTS.md) - Detailed functional requirements
- [`PROJECT_STRUCTURE.md`](PROJECT_STRUCTURE.md) - Architecture and data flow
- [`PROMPTS.md`](PROMPTS.md) - Complete specification conversation log

## License

> **Note**: License to be determined during implementation phase.

## Contact

**Klaus Wenger**  
- GitHub: [@kjwenger](https://github.com/kjwenger)
- Email: kjwenger@yahoo.com

---

*This project was designed with assistance from Claude Code for comprehensive specification and documentation.*