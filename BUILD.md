# Building FileCleaner

This document describes how to build the FileCleaner application, including both the JAR file and native executable versions.

## Quick Start

The easiest way to build FileCleaner is using the provided build script:

```bash
# Make the script executable
chmod +x build.sh

# Run the build script
./build.sh
```

The script will:
1. Check for and install GraalVM if needed
2. Install the native-image tool if needed
3. Build both the JAR and native executable
4. Display the locations of the built artifacts

## Manual Build Process

If you prefer to build manually or the script doesn't work for your environment, follow these steps:

### 1. Set Up GraalVM

```bash
# Download and extract GraalVM
curl -L https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java11-linux-amd64-22.3.0.tar.gz -o graalvm.tar.gz
tar xzf graalvm.tar.gz
rm graalvm.tar.gz

# Set environment variables
export JAVA_HOME=$PWD/graalvm-ce-java11-22.3.0
export PATH=$JAVA_HOME/bin:$PATH

# Install native-image tool
$JAVA_HOME/bin/gu install native-image
```

### 2. Build the Application

```bash
# Clean and build with native profile
mvn clean package -Pnative
```

## Build Artifacts

After a successful build, you'll find:
- Native executable: `target/filecleaner`
- JAR file: `target/filecleaner-jar-with-dependencies.jar`

## Running the Application

### Native Executable
```bash
./target/filecleaner
```

### JAR Version
```bash
java -jar target/filecleaner-jar-with-dependencies.jar
```

## Platform-Specific Notes

### Windows
- When building on Windows (not WSL), download the Windows version of GraalVM
- Use PowerShell or Command Prompt instead of WSL
- Native executable will have `.exe` extension

### WSL
- The build script automatically detects WSL
- Windows paths in the application will be automatically converted to WSL format
- Can build both Linux and Windows executables

### macOS
- Download the macOS version of GraalVM
- Use the same commands as Linux
- Native executable will be macOS compatible

## Troubleshooting

### Common Issues

1. **GraalVM Installation**
   - Ensure JAVA_HOME points to GraalVM directory
   - Verify with: `java -version` (should show GraalVM)

2. **Native Image Build**
   - Ensure native-image tool is installed
   - Check with: `native-image --version`

3. **Maven Build**
   - Clear Maven cache if needed: `mvn clean`
   - Verify Maven version: `mvn -version`

4. **Permission Issues**
   - Make build script executable: `chmod +x build.sh`
   - Ensure write permissions in target directory

### Environment Variables

For persistent GraalVM setup, add to your `~/.bashrc` or `~/.zshrc`:

```bash
export JAVA_HOME=/path/to/graalvm
export PATH=$JAVA_HOME/bin:$PATH
```

## Clean Build

To ensure a completely clean build:

```bash
# Remove all build artifacts
rm -rf target/

# Remove GraalVM if needed
rm -rf graalvm-ce-java11-22.3.0/

# Clean Maven cache
mvn clean

# Run full build
./build.sh
```
