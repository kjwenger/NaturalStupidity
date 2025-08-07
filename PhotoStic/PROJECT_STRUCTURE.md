# PhotoStic - Android Project Structure

## Directory Structure
```
PhotoStic/
├── app/                           # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/              # Java/Kotlin source files
│   │   │   │   └── com/github/kjwenger/photostic/
│   │   │   │       ├── activities/    # Activity classes
│   │   │   │       │   └── MainActivity.java
│   │   │   │       ├── fragments/     # Fragment classes
│   │   │   │       │   ├── BrowseFragment.java
│   │   │   │       │   ├── RotateFragment.java
│   │   │   │       │   └── PreviewFragment.java
│   │   │   │       ├── adapters/      # RecyclerView adapters
│   │   │   │       │   ├── FileSystemAdapter.java
│   │   │   │       │   ├── ImageGridAdapter.java
│   │   │   │       │   └── PreviewRowAdapter.java
│   │   │   │       ├── models/        # Data models
│   │   │   │       │   ├── FileItem.java
│   │   │   │       │   └── ImageItem.java (includes selection state)
│   │   │   │       ├── listeners/     # Event listeners
│   │   │   │       │   └── ImageSelectionListener.java
│   │   │   │       ├── services/      # Background services
│   │   │   │       ├── managers/      # State managers
│   │   │   │       │   └── SelectedFolderManager.java
│   │   │   │       ├── processors/   # Image processing
│   │   │   │       │   ├── ImageRotationProcessor.java
│   │   │   │       │   ├── PreviewRotationProcessor.java
│   │   │   │       │   └── PermanentRotationProcessor.java
│   │   │   │       └── utils/         # Utility classes
│   │   │   │       │   ├── FileSystemUtils.java
│   │   │   │       │   ├── ImageFilterUtils.java
│   │   │   │       │   ├── ImageOrientationUtils.java
│   │   │   │       │   └── TrashManager.java
│   │   │   ├── res/               # Resources
│   │   │   │   ├── layout/        # XML layout files
│   │   │   │   │   ├── toolbar_rotate_actions.xml
│   │   │   │   │   └── fragment_preview.xml
│   │   │   │   ├── values/        # Strings, colors, dimensions
│   │   │   │   ├── drawable/      # Images and drawable resources
│   │   │   │   │   ├── ic_portrait.xml
│   │   │   │   │   ├── ic_landscape.xml
│   │   │   │   │   ├── ic_preview.xml
│   │   │   │   │   ├── ic_save.xml
│   │   │   │   │   └── ic_back_arrow.xml
│   │   │   │   └── mipmap/        # App icons
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                  # Unit tests
│   │   └── androidTest/           # Instrumented tests
│   ├── build.gradle               # App-level build configuration
│   └── proguard-rules.pro        # ProGuard configuration
├── gradle/                        # Gradle wrapper files
├── build.gradle                   # Project-level build configuration
├── settings.gradle                # Project settings
└── local.properties              # Local SDK paths
```

## Key Files
- `CLAUDE.md` - Project overview for Claude Code
- `PROJECT_STRUCTURE.md` - This file, describing project architecture
- `REQUIREMENTS.md` - Functional requirements and specifications
- `.claudeignore` - Files for Claude to ignore
- `AndroidManifest.xml` - App permissions and components (requires photo access permissions)
- `build.gradle` - Build configuration and dependencies

## Architecture Patterns
- [TO BE FILLED - e.g., MVVM, MVP, Clean Architecture, etc.]

## Technology Stack
- Android SDK
- Java/Kotlin
- Bottom Navigation (Material Design components)
- Fragments for tab content
- [UI FRAMEWORK TO BE FILLED - e.g., Jetpack Compose, XML layouts]
- [ADDITIONAL LIBRARIES TO BE FILLED]

## Data Flow
1. User selects folder in Browse tab
2. SelectedFolderManager stores selected folder state
3. When switching to Rotate tab, RotateFragment retrieves selected folder from manager
4. RotateFragment filters images from selected folder using ImageFilterUtils
5. ImageGridAdapter displays filtered images in grid layout
6. Manual selection interaction:
   - User taps on image in grid
   - ImageSelectionListener handles tap events
   - ImageItem selection state is toggled (selected ↔ unselected)
   - ImageGridAdapter updates visual selection indicators
7. When "To Landscape" action is triggered:
   - ImageOrientationUtils analyzes dimensions of all images
   - Images with height > width are automatically selected
   - ImageGridAdapter updates selection state visually
8. When "Preview" action is triggered:
   - PreviewFragment overlays on top of tab navigation
   - Selected images are passed to PreviewRotationProcessor
   - Images are rotated 90 degrees counter-clockwise (preview only, not persisted)
   - PreviewRowAdapter displays rotated images in horizontal row layout
   - All preview images remain selected
9. When "Save" action is triggered in preview pane:
   - TrashManager copies original images to Android Trash folder
   - PermanentRotationProcessor applies 90-degree counter-clockwise rotation to originals
   - Original files are overwritten with rotated versions
   - Save action is only enabled when images are selected in preview pane

## Module Dependencies
[TO BE FILLED - Android libraries and third-party dependencies]