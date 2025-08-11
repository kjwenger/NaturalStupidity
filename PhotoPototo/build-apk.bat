@echo off
echo Building PhotoPototo APK...
echo.

REM Clean any existing build cache
echo Cleaning Gradle cache...
if exist "%USERPROFILE%\.gradle\caches" (
    rmdir /s /q "%USERPROFILE%\.gradle\caches"
)

REM Clean project build directory
if exist "app\build" (
    rmdir /s /q "app\build"
)

if exist "build" (
    rmdir /s /q "build"
)

echo.
echo Starting fresh build...
echo.

REM Run the build with clean cache
gradlew.bat clean assembleDebug --no-daemon --stacktrace

echo.
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo SUCCESS: APK created at app\build\outputs\apk\debug\app-debug.apk
    echo File size:
    dir "app\build\outputs\apk\debug\app-debug.apk"
) else (
    echo FAILED: APK was not created
)

pause