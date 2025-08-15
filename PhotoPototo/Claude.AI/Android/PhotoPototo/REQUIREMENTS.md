# PhotoPototo - Requirements Specification

## Overview
[PROJECT OVERVIEW TO BE FILLED]

## Functional Requirements

### Core Features
1. Access to all storage on Android device (not limited to photos)
2. Browse tab - File system navigation with folder/subfolder browsing
3. Rotate tab - Displays images from selected folder in grid layout with action toolbar
4. Image rotation actions - "To Portrait", "To Landscape", and "Preview" with appropriate icons
5. Preview pane - Overlays on tabs to show selected images rotated to landscape
6. Cross-tab communication - Selected folder in Browse affects Rotate tab content
7. Bottom navigation for seamless tab switching

### File System Access Requirements
- Full read access to Android device storage (internal and external)
- Navigate through all folders and subfolders
- Display folder structure in hierarchical format
- Support back navigation and breadcrumb navigation
- Handle different storage locations (internal storage, SD card, USB OTG)
- Support various file types with appropriate icons
- Efficiently load and display file/folder listings
- Handle large directory structures with performance optimization

### Image Grid Requirements
- Filter and display only image files from selected folder
- Grid layout for optimal image viewing
- Thumbnail generation for performance
- Support for various image formats (JPEG, PNG, WebP, GIF, etc.)
- Lazy loading for large image collections
- Maintain aspect ratios in grid display

### Manual Selection Requirements
- Tap-to-toggle selection on individual images
- Visual selection indicators (checkmarks, overlays, or highlighting)
- Support both selected and unselected states per image
- Selection state persists during grid scrolling
- Mixed selection modes: manual + automatic selection can coexist

### Action Toolbar Requirements
- Action icons positioned above image grid
- "To Portrait" action with appropriate rotation icon and tooltip
- "To Landscape" action with appropriate rotation icon and tooltip
- "Preview" action with appropriate preview icon and tooltip
- "Preview" action is only active/enabled when images are selected
- Icons should be intuitive and follow Material Design guidelines
- Tooltips appear on long press for accessibility

### "To Landscape" Action Functionality
- Automatically selects all portrait-oriented photos in the grid
- Portrait photos are defined as images where height > width
- Visual feedback shows selected images (checkmarks, highlighting, or selection overlay)
- Smart selection based on image dimensions analysis
- Works in combination with existing manual selections
- Does not deselect manually selected images, only adds portrait images to selection

### Preview Pane Requirements
- Overlay pane appears on top of tab navigation when "Preview" is activated
- Back button with left-arrow icon in top-left corner
- Save button with save icon and "Save" tooltip
- Save button is only enabled when images are selected in preview pane
- Horizontal row layout for displaying selected images
- All selected images are rotated 90 degrees counter-clockwise for preview
- Rotation is temporary/preview only - original images remain unmodified
- All rotated images in preview remain selected
- Preview images maintain aspect ratios in row layout
- Smooth transitions when entering/exiting preview mode

### Save Action Requirements
- Copies original image files to Android Trash folder as backup
- Applies permanent 90-degree counter-clockwise rotation to original files
- Overwrites original files with rotated versions
- Maintains EXIF data and image quality during rotation
- Provides feedback on save success/failure
- Works on all selected images in preview pane

### User Stories
- As a user, I want to browse all files and folders on my device so that I can navigate my entire storage system
- As a user, I want to navigate through folders and subfolders so that I can find files in specific locations
- As a user, I want to see a clear folder hierarchy so that I understand where I am in the file system
- As a user, I want to go back to parent folders easily so that navigation feels natural
- As a user, I want to select a folder in Browse tab and see its images in Rotate tab so that I can work with images from specific folders
- As a user, I want to see images displayed in a grid layout so that I can quickly preview multiple images at once
- As a user, I want the Rotate tab to automatically update when I select different folders so that the interface feels responsive and connected
- As a user, I want to rotate images to portrait orientation using a clear action button so that I can fix incorrectly oriented photos
- As a user, I want to manually select individual images by tapping them so that I have precise control over which photos to process
- As a user, I want to tap selected images to deselect them so that I can fine-tune my selection
- As a user, I want to combine manual selection with automatic selection so that I can efficiently select both specific images and categories of images
- As a user, I want to quickly select all portrait photos with one button click so that I can efficiently process multiple images
- As a user, I want to see which images are selected with clear visual indicators so that I know which photos will be affected
- As a user, I want the "To Landscape" action to automatically find and select portrait photos so that I don't have to manually identify them
- As a user, I want to preview my selected images rotated to landscape orientation so that I can see how they will look before making permanent changes
- As a user, I want the preview to show images in a horizontal row so that I can easily compare multiple rotated images
- As a user, I want to return from preview mode using a clear back button so that navigation feels intuitive
- As a user, I want the preview rotation to be temporary so that I can review changes before committing them
- As a user, I want to save my rotated images permanently using a clear save button so that changes are applied to my files
- As a user, I want the original images backed up to trash before rotation so that I can recover them if needed
- As a user, I want the save button to be disabled when no images are selected so that I don't accidentally perform empty operations
- As a user, I want feedback when saving is complete so that I know the operation was successful
- As a user, I want to see tooltips on action buttons so that I understand what each button does
- As a user, I want to switch between browsing and rotating modes using bottom tabs so that navigation is intuitive and follows Android conventions
- [ADD MORE USER STORIES]

### API Endpoints
[LIST KEY API ENDPOINTS AND THEIR PURPOSES]

### Data Models
[DESCRIBE KEY DATA STRUCTURES AND ENTITIES]

## Non-Functional Requirements

### Performance
- [PERFORMANCE REQUIREMENTS TO BE FILLED]

### Security
- Proper handling of sensitive photo permissions
- Respect user privacy and data protection
- Secure storage of any cached photo data
- Compliance with Android security best practices for media access

### Usability
- Intuitive bottom navigation following Material Design guidelines
- Seamless switching between Browse and Edit tabs
- Touch-friendly interface optimized for mobile use
- [ADDITIONAL USABILITY REQUIREMENTS TO BE FILLED]

### Scalability
- [SCALABILITY REQUIREMENTS TO BE FILLED]

## Technical Constraints
- Android API Level requirements for full storage access (minimum API 21+)
- Handle Android 13+ (API 33) scoped storage changes and MANAGE_EXTERNAL_STORAGE permission
- Runtime permission handling for file system access
- File system API integration for directory traversal
- Memory management for large directory structures
- Handle permission restrictions for accessing system directories
- Image rotation processing requires WRITE_EXTERNAL_STORAGE permission
- Bitmap manipulation for image rotation operations
- Maintain EXIF data during image transformations
- Image dimension analysis for orientation detection
- Efficient processing of image metadata without loading full bitmaps for selection
- Preview rotation processing without persisting changes to original files
- Overlay UI management for preview pane on top of existing tab navigation
- Android Trash folder integration for backup functionality
- File copying and overwriting operations with proper error handling
- Permanent image rotation with quality preservation

## Integration Requirements
- [EXTERNAL SYSTEM INTEGRATIONS TO BE FILLED]

## Testing Requirements
- [TESTING APPROACH AND REQUIREMENTS TO BE FILLED]

## Acceptance Criteria
- [DEFINE WHAT CONSTITUTES A SUCCESSFUL IMPLEMENTATION]