#!/bin/bash

set -e

echo "FileCleaner Build Script"
echo "======================="

# Check if GRAALVM_HOME is set
if [ -z "${GRAALVM_HOME}" ]; then
    echo "GRAALVM_HOME is not set. Installing GraalVM..."

    # Download and install GraalVM
    GRAALVM_VERSION="22.3.1"
    GRAALVM_FILENAME="graalvm-ce-java11-linux-amd64-${GRAALVM_VERSION}.tar.gz"

    # Create temp directory for download
    mkdir -p /tmp/graalvm
    cd /tmp/graalvm

    # Download GraalVM
    wget "https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAALVM_VERSION}/${GRAALVM_FILENAME}"

    # Extract to home directory
    tar -xzf "${GRAALVM_FILENAME}"
    mkdir -p ~/graalvm
    mv graalvm-ce-java11-* ~/graalvm/

    # Set GRAALVM_HOME
    export GRAALVM_HOME=~/graalvm/graalvm-ce-java11-${GRAALVM_VERSION}
    echo "export GRAALVM_HOME=~/graalvm/graalvm-ce-java11-${GRAALVM_VERSION}" >> ~/.bashrc

    # Install native-image
    ${GRAALVM_HOME}/bin/gu install native-image

    # Cleanup
    cd -
    rm -rf /tmp/graalvm
fi

echo "Building project with Maven..."

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Maven not found. Installing Maven..."
    sudo apt-get update
    sudo apt-get install -y maven
fi

# Build with Maven
mvn clean package

echo "Creating native image..."
${GRAALVM_HOME}/bin/native-image \
    -jar target/filecleaner-jar-with-dependencies.jar \
    -H:Name=filecleaner \
    --no-fallback \
    --enable-url-protocols=https \
    -H:+ReportExceptionStackTraces

# Move the executable to target directory
mv filecleaner target/

echo "Build completed successfully!"
echo "Native executable: target/filecleaner"
echo "JAR file: target/filecleaner-jar-with-dependencies.jar"
