package com.lordorb;

import com.lordorb.config.FileConfig;
import com.lordorb.helpers.CommonService;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Main class for the FileCleaner application.
 * Provides a command-line interface for users to organize files in their directories.
 */
public class FileCleaner {
    private static final Logger LOGGER = Logger.getLogger(FileCleaner.class.getName());
    private final FileConfig config;
    private final Scanner scanner;

    public FileCleaner() {
        this.config = new FileConfig();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu and handles user input.
     */
    public void showMenu() {
        while (true) {
            System.out.println("\nFileCleaner Menu:");
            System.out.println("1. Set Directory to Organize");
            System.out.println("2. Show Current Settings");
            System.out.println("3. Organize Files");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice (1-4): ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        setSourceDirectory();
                        break;
                    case "2":
                        showSettings();
                        break;
                    case "3":
                        organizeFiles();
                        break;
                    case "4":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An error occurred", e);
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void setSourceDirectory() {
        System.out.print("Enter the directory path to organize: ");
        String path = scanner.nextLine().trim();
        try {
            config.setSourcePath(path);
            System.out.println("Directory set successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showSettings() {
        System.out.println("\nCurrent Settings:");
        System.out.println("Directory to Organize: " +
            (config.getSourcePath() != null ? config.getSourcePath() : "Not set"));
    }

    private void organizeFiles() throws IOException {
        if (config.getSourcePath() == null) {
            System.out.println("Error: Please set the directory to organize first.");
            return;
        }

        System.out.println("\nOrganizing files...");
        CommonService.organizeFiles(config.getSourcePath().toFile());
        System.out.println("File organization completed!");
    }

    /**
     * Main entry point for the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Welcome to FileCleaner!");
        FileCleaner cleaner = new FileCleaner();
        cleaner.showMenu();
    }
}
