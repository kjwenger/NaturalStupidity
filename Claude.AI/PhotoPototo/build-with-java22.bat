@echo off
echo Setting JAVA_HOME to OpenJDK 22...
set JAVA_HOME=C:\Program Files\OpenJDK\jdk-22.0.2
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java version check:
java -version
echo.

echo Building APK...
gradlew.bat assembleDebug --no-daemon --stacktrace

echo.
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo SUCCESS: APK created at app\build\outputs\apk\debug\app-debug.apk
    dir "app\build\outputs\apk\debug\app-debug.apk"
) else (
    echo FAILED: APK was not created
)