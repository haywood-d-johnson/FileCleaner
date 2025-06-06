# FileCleaner

A Java application that helps organize files in a directory based on their naming pattern. Files following the pattern "const.thing.maybe_something.var" will be moved into folders named after their "const" prefix.

## Features

- Organizes files based on their constant prefix (e.g., "CONST.thing.var" goes into "CONST" folder)
- Works on Windows, Linux, macOS, and Windows Subsystem for Linux (WSL)
- Command-line interface with interactive menu
- Automatic path conversion for different operating systems
- Configuration saving/loading
- Detailed logging and error handling
- Available as both JAR and native executable

## Requirements

For JAR version:
- Java 11 or higher

For native executable:
- No requirements (standalone binary)

For building from source:
- Java 11 or higher
- Maven
- GraalVM CE 22.3.0 or later (for native executable)

## Installation

### Option 1: Download Pre-built Binary (Recommended)

1. Download the appropriate version for your OS:
   - Windows: `filecleaner.exe`
   - Linux: `filecleaner`
   - macOS: `filecleaner-macos`

2. Make the file executable (Linux/macOS):
   ```bash
   chmod +x filecleaner
   ```

### Option 2: Download JAR

1. Download `filecleaner.jar`
2. Run with Java:
   ```bash
   java -jar filecleaner.jar
   ```

### Option 3: Build from Source

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/FileCleaner.git
   cd FileCleaner
   ```

2. Build JAR:
   ```bash
   mvn clean package
   ```
   Find the JAR in `target/filecleaner-jar-with-dependencies.jar`

3. Build native executable:
   ```bash
   # First install GraalVM and set JAVA_HOME
   export JAVA_HOME=/path/to/graalvm
   export PATH=$JAVA_HOME/bin:$PATH

   # Install native-image tool
   gu install native-image

   # Build native executable
   mvn -Pnative clean package
   ```
   Find the executable in `target/filecleaner`

## Usage

### Native Executable
```bash
# Linux/macOS
./filecleaner

# Windows
filecleaner.exe
```

### JAR Version
```bash
java -jar filecleaner.jar
```

Use the interactive menu to:
- Set the directory to organize
- View current settings
- Start the organization process
- Exit the application

### Path Format Examples

The application accepts paths in various formats:

- Windows: `C:\Users\YourName\Documents`
- Linux/macOS: `/home/username/documents`
- WSL: Both Windows paths (automatically converted) and Linux paths are accepted

## Notes

- The application organizes files in-place (within the same directory)
- Files are moved into subdirectories based on their constant prefix
- Original files are moved, not copied
- Configuration is saved between sessions
- Native executable has faster startup time and requires no Java installation

## Troubleshooting

If you encounter any issues:

1. When using JAR version:
   - Check Java installation: `java -version`
   - Ensure Java 11 or higher is installed

2. When using native executable:
   - Ensure the file has execute permissions (Linux/macOS)
   - Run from terminal to see any error messages

3. For all versions:
   - Ensure you have read/write permissions in the target directory
   - For WSL users: Make sure Windows paths are accessible

## License

This project is licensed under the MIT License - see the LICENSE file for details.
