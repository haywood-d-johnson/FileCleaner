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

## Quick Start

### Building the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/FileCleaner.git
   cd FileCleaner
   ```

2. Run the build script:
   ```bash
   chmod +x build.sh
   ./build.sh
   ```

The script will automatically:
- Set up GraalVM if needed
- Build both native executable and JAR versions
- Show the location of the built artifacts

For detailed build instructions and troubleshooting, see [BUILD.md](BUILD.md).

### Running the Application

After building, you can run either:

**Native Executable** (recommended, no Java required):
```bash
./target/filecleaner
```

**JAR Version** (requires Java 11+):
```bash
java -jar target/filecleaner-jar-with-dependencies.jar
```

## Usage

1. Start the application using either method above
2. Use the interactive menu to:
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

- Files are organized in-place (within the same directory)
- Files are moved into subdirectories based on their constant prefix
- Original files are moved, not copied
- Configuration is saved between sessions
- Native executable has faster startup time and requires no Java installation

## Requirements

For running the application:
- Native executable: No requirements
- JAR version: Java 11 or higher

For building from source:
- See [BUILD.md](BUILD.md) for detailed requirements and instructions

## Buy Me a Coffee

If you find this project helpful, consider supporting me!

[![Buy Me a Coffee](https://www.buymeacoffee.com/assets/img/custom_images/yellow_img.png)](https://www.buymeacoffee.com/)

Scan the QR code below to buy me a coffee:

<img src="public/bmc_qr.png" alt="Buy Me a Coffee QR" width="200"/>

## License

This project is licensed under the MIT License - see the LICENSE file for details.
