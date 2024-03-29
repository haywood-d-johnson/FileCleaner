package com.lordorb.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hwood
 */

public class CommonService {
    
    public static void flattenFolderStructure(File source, File destination) throws IOException {
        Set<String> createdFolders = new HashSet<>();
        // should always be a directory
        if (source.isDirectory()) {
            File[] files = source.listFiles();
            if (files != null) {
                for (File file : files) {
                    flattenFolderStructure(file, destination, createdFolders);
                }
            }
        } else {
            moveFileToDestination(source, destination);
        }
    }

    
    public static void flattenFolderStructure(File sourceFile, File destinationFolder, Set<String> createdFolders) throws IOException {
        // if it is a subfolder
        if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    flattenFolderStructure(file, destinationFolder, createdFolders);
                }
            }
        } else {
            String fileName = sourceFile.getName();
            String folderName = fileName.split("\\.")[0].trim();
            File destinationSubFolder = new File(destinationFolder, folderName);

            if (!createdFolders.contains(folderName)) {
                createdFolders.add(folderName);
                if (!destinationSubFolder.exists()) {
                    destinationSubFolder.mkdirs();
                }
            }

            moveFileToDestination(sourceFile, destinationSubFolder);
        }
    }

    
    public static void moveFileToDestination(File sourceFile, File destinationFolder) throws IOException {
        File destinationFile = new File(destinationFolder, sourceFile.getName());
        Files.move(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    
    
    public static void clearEmptyDirectories(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    clearEmptyDirectories(file);
                }
            }
        }
        
        // Delete the directory if it's empty
        if (directory.isDirectory() && directory.listFiles().length == 0) {
            directory.delete();
        }
    }
}

