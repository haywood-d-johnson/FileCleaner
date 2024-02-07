package com.lordorb.filecleaner;

import com.lordorb.helpers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.*;


/**
 *
 * @author hwood
 */
public class FileCleanerJob {

    public static void main(String[] args) {
        
        var envMap = UnitOfWork.setEnvHashMap();   

        StringBuilder directory = new StringBuilder(envMap.get("DESTINATION_PATH"));
        
        File sourceFolder = new File("/path/to/source/folder");
        File destinationFolder = new File("/path/to/destination/folder");

        try {
            flattenFolderStructure(sourceFolder, destinationFolder);
            System.out.println("Folder structure flattened successfully!");
        } catch (IOException e) {
            System.err.println("Error flattening folder structure: " + e.getMessage());
        }
    }
}
