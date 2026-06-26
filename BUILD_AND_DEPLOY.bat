@echo off
REM TechMart-EE Build and Deploy Script for Payara

setlocal enabledelayedexpansion

cd /d "%~dp0"

echo.
echo ============================================
echo TechMart-EE Build Script
echo ============================================
echo.

REM Check if Maven is installed
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Maven not found. Please install Maven.
    pause
    exit /b 1
)

echo [1/3] Cleaning previous build...
call mvn clean

if %errorlevel% neq 0 (
    echo ERROR: Clean failed
    pause
    exit /b 1
)

echo [2/3] Building project...
call mvn install -DskipTests

if %errorlevel% neq 0 (
    echo ERROR: Build failed
    pause
    exit /b 1
)

echo [3/3] Build successful!
echo.
echo ============================================
echo DEPLOYMENT INFO
echo ============================================
echo.
echo EAR Location:
echo   %cd%\techmart-ear\target\techmart-ear.ear
echo.
echo To Deploy to Payara:
echo 1. Open Payara Admin Console: http://localhost:4848
echo 2. Go to Applications ^> Deploy
echo 3. Select the EAR file above
echo 4. Click Deploy
echo.
echo To Access Application:
echo   http://localhost:8080/techmart-ear/products
echo.
echo ============================================
pause
