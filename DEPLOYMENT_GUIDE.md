# TechMart-EE Deployment Guide

## Issues Fixed

✅ **JNDI Datasource Mismatch** - Fixed in `glassfish-resources.xml`
- Changed JNDI name from `jdbc/TechMartPool` to `jdbc/techmartDS` (matches persistence.xml)
- Removed extra space in pool name

✅ **Build Configuration** - Added plugins to pom.xml files
- Added maven-war-plugin to techmart-web
- Added maven-ejb-plugin to techmart-ejb

## Prerequisites

1. **Java 17+** installed
2. **Maven 3.8+** installed and in PATH
3. **Payara Server 6+** running
4. **MySQL Database** running with user `root` and password `22Pawan#@`
5. **Database** named `techmart_db` created

## Quick Deployment Steps

### Step 1: Build the Project
```bash
cd C:\Users\pawan\IdeaProjects\TechMart-EE
BUILD_AND_DEPLOY.bat
```
Or manually:
```bash
mvn clean install
```

### Step 2: Deploy to Payara

#### Option A: Using Admin Console (GUI)
1. Open http://localhost:4848
2. Go to **Applications** → **Deploy**
3. Select file: `C:\Users\pawan\IdeaProjects\TechMart-EE\techmart-ear\target\techmart-ear.ear`
4. Application Name: `techmart-ear`
5. Click **Deploy**

#### Option B: Using Deployment Script
```bash
DEPLOY_TO_PAYARA.bat
```

#### Option C: Using asadmin Command
```bash
cd C:\Program Files\Payara\payara6\bin
asadmin deploy C:\Users\pawan\IdeaProjects\TechMart-EE\techmart-ear\target\techmart-ear.ear
```

### Step 3: Verify Deployment
- Open browser: **http://localhost:8080/techmart-ear/products**
- You should see the Products page

## File Structure
```
techmart-ear\target\
├── techmart-ear.ear              ← Deployable file
├── techmart-ear\
│   ├── META-INF\
│   │   └── application.xml       ← EAR descriptor
│   ├── lk.jiat-techmart-ejb-1.0.jar
│   ├── lk.jiat-techmart-web-1.0.war
│   └── lib\                      ← Jakarta libraries
```

## Troubleshooting

### Issue: Application not showing in Payara Console

**Check 1:** Verify EAR file exists
```bash
dir C:\Users\pawan\IdeaProjects\TechMart-EE\techmart-ear\target\*.ear
```

**Check 2:** View Payara logs
```bash
type "C:\Program Files\Payara\payara6\glassfish\domains\domain1\logs\server.log" | findstr ERROR
```

**Check 3:** Check datasource is registered
- Go to Payara Admin Console
- Resources → JDBC → Connection Pools
- Should see `TechMartPool`
- Resources → JDBC → JDBC Resources
- Should see `jdbc/techmartDS`

### Issue: Deployment fails with datasource error

1. Ensure MySQL is running
2. Ensure database `techmart_db` exists
3. Check credentials in `glassfish-resources.xml`:
   - User: `root`
   - Password: `22Pawan#@`
   - Database: `techmart_db`

### Issue: "Module not found" error

Rebuild and check module names match in `techmart-ear\target\techmart-ear\META-INF\application.xml`:
```xml
<web-uri>lk.jiat-techmart-web-1.0.war</web-uri>
<ejb-uri>lk.jiat-techmart-ejb-1.0.jar</ejb-uri>
```

## Configuration Files Changed

### 1. `techmart-ejb/src/main/resources/META-INF/glassfish-resources.xml`
- **Line 24:** Changed JNDI name to match persistence.xml
- Before: `jndi-name="jdbc/TechMartPool"`
- After: `jndi-name="jdbc/techmartDS"`

### 2. `techmart-web/pom.xml`
- Added maven-war-plugin for proper WAR packaging

### 3. `techmart-ejb/pom.xml`
- Added maven-ejb-plugin for proper EJB packaging

## Verify Application Works

```bash
curl http://localhost:8080/techmart-ear/products
```

Or visit in browser: **http://localhost:8080/techmart-ear/products**

## Support

If issues persist:
1. Check Payara logs: `C:\Program Files\Payara\payara6\glassfish\domains\domain1\logs\server.log`
2. Verify MySQL database connectivity
3. Ensure all ports (8080, 4848, 3306) are accessible
4. Rebuild with: `mvn clean install -X` (verbose mode)
