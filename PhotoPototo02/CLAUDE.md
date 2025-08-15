# Claude Code Project Context

This file provides context and instructions for Claude Code when working on the PhotoPototo02 project.

## Project Overview

PhotoPototo02 is a native Android application built with Kotlin that provides directory browsing functionality. The app allows users to navigate through the device's file system with proper permission handling for different Android versions.

## Project Structure

- `app/src/main/java/com/github/kjwenger/photopototo02/` - Kotlin source files
- `app/src/main/res/` - Android resources (layouts, drawables, values)
- `app/build.gradle` - App-level build configuration
- `build.gradle` - Project-level build configuration
- `settings.gradle` - Project settings

## Development Commands

When working on this project, use these commands:

- `gradle assembleDebug` - Build debug APK
- `gradle test` - Run unit tests
- `gradle connectedAndroidTest` - Run instrumented tests
- `adb install app/build/outputs/apk/debug/app-debug.apk` - Install APK to device
- `adb uninstall com.github.kjwenger.photopototo02` - Uninstall app from device

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
- Fragment-based UI architecture
- RecyclerView for directory listing
- Modern Android permission handling
- Material Design 3 theming

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