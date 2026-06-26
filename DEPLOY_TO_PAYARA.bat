@echo off
REM Payara Deployment Script for TechMart-EE

setlocal enabledelayedexpansion

REM Set Payara home (modify if needed)
set PAYARA_HOME=C:\Program Files\Payara\payara6
set ASADMIN=%PAYARA_HOME%\bin\asadmin.bat

if not exist "%ASADMIN%" (
    echo ERROR: Payara not found at %PAYARA_HOME%
    echo Please modify PAYARA_HOME in this script
    pause
    exit /b 1
)

echo.
echo ============================================
echo Payara Deployment Script
echo ============================================
echo.

set EAR_FILE=%~dp0techmart-ear\target\techmart-ear.ear

if not exist "%EAR_FILE%" (
    echo ERROR: EAR file not found at %EAR_FILE%
    echo Please run BUILD_AND_DEPLOY.bat first
    pause
    exit /b 1
)

echo [1/3] Checking if application already deployed...
call "%ASADMIN%" list-applications | findstr techmart-ear >nul

if %errorlevel% equ 0 (
    echo [1/3] Undeploying previous version...
    call "%ASADMIN%" undeploy techmart-ear
    timeout /t 2
)

echo [2/3] Deploying application...
call "%ASADMIN%" deploy --contextroot techmart "%EAR_FILE%"

if %errorlevel% equ 0 (
    echo [3/3] Deployment successful!
    echo.
    echo ============================================
    echo APPLICATION DEPLOYED
    echo ============================================
    echo.
    echo Access Application at:
    echo   http://localhost:8080/techmart/products
    echo.
    echo Payara Admin Console:
    echo   http://localhost:4848
    echo.
) else (
    echo ERROR: Deployment failed
    echo Check Payara logs at %PAYARA_HOME%\glassfish\domains\domain1\logs\server.log
)

pause
