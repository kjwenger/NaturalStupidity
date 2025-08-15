# PhotoPototo02

A native Android photo browsing application built with Kotlin that provides directory listing and file management capabilities.

## Features

- **Browse Tab**: Native Android directory browsing with full file system access
- **Permission Handling**: Modern Android permission system for storage access
  - Android 11+ (API 30+): MANAGE_EXTERNAL_STORAGE permission
  - Android 6-10 (API 23-29): READ_EXTERNAL_STORAGE permission
  - Android 13+ (API 33+): Granular media permissions
- **File Type Recognition**: Icons for different file types (images, videos, audio, documents)
- **Directory Navigation**: Tap to navigate folders, parent directory support
- **File Information**: Detailed file info dialogs with size and modification date
- **Custom Photo Icon**: Professional camera-themed app icon

## Technical Details

- **Language**: Kotlin
- **Platform**: Android (API 26+)
- **Architecture**: Fragment-based with RecyclerView
- **UI Framework**: Material Design 3
- **Package**: `com.github.kjwenger.photopototo02`

## Project Structure

```
app/
├── src/main/
│   ├── java/com/github/kjwenger/photopototo02/
│   │   ├── MainActivity.kt           # Main activity with fragment management
│   │   ├── BrowseFragment.kt         # Directory browsing fragment
│   │   └── DirectoryAdapter.kt       # RecyclerView adapter for files/folders
│   ├── res/
│   │   ├── drawable/                 # App icons and file type icons
│   │   ├── layout/                   # XML layouts
│   │   ├── values/                   # Strings, colors, themes
│   │   └── mipmap-*/                 # Launcher icons
│   └── AndroidManifest.xml          # App permissions and configuration
├── build.gradle                     # App-level build configuration
└── proguard-rules.pro               # ProGuard configuration
```

## Building

### Prerequisites
- Android Studio or compatible IDE
- Java 11 or 17
- Android SDK API 26+

### Build APK
```bash
gradle assembleDebug
```

The APK will be generated at: `app/build/outputs/apk/debug/app-debug.apk`

### Install to Device
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Permissions

The app requests the following permissions:
- `READ_EXTERNAL_STORAGE` (Android 6-10)
- `MANAGE_EXTERNAL_STORAGE` (Android 11+)
- `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_MEDIA_AUDIO` (Android 13+)

## Development

This project uses:
- **Gradle**: 8.14.2
- **Android Gradle Plugin**: 8.5.0
- **Kotlin**: 1.9.24
- **Material Design**: 1.11.0
- **AndroidX**: Latest stable versions

## License

This project is part of the NaturalStupidity collection.