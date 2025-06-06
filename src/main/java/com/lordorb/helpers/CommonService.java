package com.lordorb.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * Common service utility class for file operations.
 * Organizes files based on their constant prefix in dot-separated filenames.
 * Example: for a file named "CONST.thing.maybe_something.var", it will be moved
 * to a folder named "CONST" in the same directory.
 *
 * @author hwood
 */
public final class CommonService {
    private static final Logger LOGGER = Logger.getLogger(CommonService.class.getName());
    private static final Pattern DOT_PATTERN = Pattern.compile("\\.");

    // Private constructor to prevent instantiation
    private CommonService() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Organizes files in a directory by creating subdirectories based on file prefixes.
     * Files will be organized in their current location.
     *
     * @param sourceDir The directory containing files to organize
     * @throws IOException If any I/O error occurs during the operation
     * @throws IllegalArgumentException If source is null or invalid
     */
    public static void organizeFiles(final File sourceDir) throws IOException {
        if (sourceDir == null) {
            throw new IllegalArgumentException("Source directory cannot be null");
        }

        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            throw new IllegalArgumentException("Source must be a valid directory: " + sourceDir);
        }

        LOGGER.log(Level.INFO, "Starting file organization in directory: {0}",
                  sourceDir.getPath());

        final Set<String> createdFolders = new HashSet<>();
        final File[] files = sourceDir.listFiles();

        if (files != null) {
            // First pass: create folders and collect file information
            for (final File file : files) {
                if (!file.isFile()) {
                    continue; // Skip directories and non-regular files
                }

                final String fileName = file.getName();
                final String[] parts = DOT_PATTERN.split(fileName);

                if (parts.length < 2) {
                    LOGGER.warning("File " + fileName + " does not follow the const.thing.maybe_something.var pattern, skipping");
                    continue;
                }

                final String constPrefix = parts[0];
                if (constPrefix.isEmpty()) {
                    LOGGER.warning("File " + fileName + " has an empty constant prefix, skipping");
                    continue;
                }

                // Create folder if it doesn't exist
                final Path folderPath = sourceDir.toPath().resolve(constPrefix);
                if (!createdFolders.contains(constPrefix)) {
                    Files.createDirectories(folderPath);
                    createdFolders.add(constPrefix);
                    LOGGER.log(Level.FINE, "Created new directory: {0}", folderPath);
                }
            }

            // Second pass: move files to their respective folders
            for (final File file : files) {
                if (!file.isFile()) {
                    continue;
                }

                final String fileName = file.getName();
                final String[] parts = DOT_PATTERN.split(fileName);

                if (parts.length < 2) {
                    continue; // Already logged in first pass
                }

                final String constPrefix = parts[0];
                if (constPrefix.isEmpty()) {
                    continue; // Already logged in first pass
                }

                final Path destinationFolder = sourceDir.toPath().resolve(constPrefix);
                moveFileToDestination(file.toPath(), destinationFolder);
            }
        }

        LOGGER.log(Level.INFO, "Completed file organization in directory: {0}", sourceDir.getPath());
        validateFolderContents(sourceDir);
    }

    /**
     * Validates that all files in each folder belong there based on their constant prefix.
     *
     * @param rootDir The root directory containing organized files
     * @throws IOException If any I/O error occurs during validation
     */
    private static void validateFolderContents(final File rootDir) throws IOException {
        if (!rootDir.isDirectory()) {
            return;
        }

        final File[] folders = rootDir.listFiles(File::isDirectory);
        if (folders == null) {
            return;
        }

        for (final File folder : folders) {
            final String folderName = folder.getName();
            final File[] files = folder.listFiles(File::isFile);

            if (files == null) {
                continue;
            }

            for (final File file : files) {
                final String fileName = file.getName();
                final String[] parts = DOT_PATTERN.split(fileName);

                if (parts.length < 2 || !parts[0].equals(folderName)) {
                    LOGGER.warning("File " + fileName + " in folder " + folderName +
                                 " does not match the folder's constant prefix");
                }
            }
        }
    }

    /**
     * Moves a file to the specified destination folder.
     *
     * @param sourcePath The source file path
     * @param destinationFolder The destination folder path
     * @throws IOException If any I/O error occurs during the move operation
     */
    private static void moveFileToDestination(final Path sourcePath, final Path destinationFolder) throws IOException {
        final Path destinationPath = destinationFolder.resolve(sourcePath.getFileName());
        LOGGER.log(Level.FINE, "Moving file from {0} to {1}",
                  new Object[]{sourcePath, destinationPath});

        Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
