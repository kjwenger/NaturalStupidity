@echo off
echo Setting JAVA_HOME to OpenJDK 22... > build.log 2>&1
set JAVA_HOME=C:\Program Files\OpenJDK\jdk-22.0.2
set PATH=%JAVA_HOME%\bin;%PATH%

echo Java version check: >> build.log 2>&1
java -version >> build.log 2>&1
echo. >> build.log 2>&1

echo Building APK... >> build.log 2>&1
gradlew.bat assembleDebug --no-daemon --stacktrace >> build.log 2>&1

echo. >> build.log 2>&1
if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo SUCCESS: APK created >> build.log 2>&1
    dir "app\build\outputs\apk\debug\app-debug.apk" >> build.log 2>&1
) else (
    echo FAILED: APK was not created >> build.log 2>&1
)

echo Build completed. Check build.log for details.