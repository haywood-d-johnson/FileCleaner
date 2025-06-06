package com.lordorb.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Configuration class for managing file paths and settings.
 */
public class FileConfig {
    private static final Logger LOGGER = Logger.getLogger(FileConfig.class.getName());
    private static final String CONFIG_FILE = "filecleaner.properties";

    private Path sourcePath;
    private Properties properties;

    public FileConfig() {
        this.properties = new Properties();
        loadConfig();
    }

    /**
     * Sets the directory path to organize.
     *
     * @param path The path to the directory
     * @throws IllegalArgumentException if the path is invalid or doesn't exist
     */
    public void setSourcePath(String path) {
        Path proposedPath = convertWindowsPath(path);
        validateSourcePath(proposedPath);
        this.sourcePath = proposedPath;
        saveConfig();
    }

    /**
     * Gets the directory path to organize.
     *
     * @return The source path
     */
    public Path getSourcePath() {
        return sourcePath;
    }

    /**
     * Converts a Windows path to a WSL path if necessary.
     * Example: "F:\Games\Something" becomes "/mnt/f/Games/Something" in WSL
     * or remains as a normalized path in Windows/other OS
     */
    private Path convertWindowsPath(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        // Replace backslashes with forward slashes for consistency
        path = path.replace('\\', '/');

        // Check if running in WSL
        boolean isWSL = System.getProperty("os.name").toLowerCase().contains("linux")
            && System.getProperty("os.version").toLowerCase().contains("microsoft");

        // Only convert to WSL format if we're actually in WSL
        if (isWSL && path.matches("^[A-Za-z]:.*")) {
            try {
                String driveLetter = path.substring(0, 1).toLowerCase();
                String remainingPath = path.substring(2);
                path = "/mnt/" + driveLetter + remainingPath;
                LOGGER.info("Converted Windows path to WSL format: " + path);
            } catch (Exception e) {
                LOGGER.warning("Error converting Windows path: " + e.getMessage());
                throw new IllegalArgumentException("Invalid Windows path format: " + path);
            }
        }

        return Paths.get(path).toAbsolutePath().normalize();
    }

    private void validateSourcePath(Path path) {
        File directory = path.toFile();
        if (!directory.exists()) {
            throw new IllegalArgumentException("Directory does not exist: " + path +
                "\nMake sure the path is correct and accessible from WSL.");
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Path is not a directory: " + path);
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException("Cannot read and write in directory: " + path +
                "\nMake sure you have the necessary permissions.");
        }
    }

    private void loadConfig() {
        Path configPath = Paths.get(System.getProperty("user.home"), CONFIG_FILE);
        if (Files.exists(configPath)) {
            try {
                properties.load(Files.newBufferedReader(configPath));
                String savedSourcePath = properties.getProperty("source.path");

                if (savedSourcePath != null) {
                    try {
                        setSourcePath(savedSourcePath);
                    } catch (IllegalArgumentException e) {
                        LOGGER.warning("Saved source path is no longer valid: " + savedSourcePath);
                    }
                }
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Could not load configuration file", e);
            }
        }
    }

    private void saveConfig() {
        if (sourcePath != null) {
            properties.setProperty("source.path", sourcePath.toString());
        }

        Path configPath = Paths.get(System.getProperty("user.home"), CONFIG_FILE);
        try {
            properties.store(Files.newBufferedWriter(configPath), "FileCleaner Configuration");
            LOGGER.info("Configuration saved to: " + configPath);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not save configuration file", e);
        }
    }
}
