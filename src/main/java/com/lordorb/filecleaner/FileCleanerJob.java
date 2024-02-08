package com.lordorb.filecleaner;

import com.lordorb.helpers.*;
import static com.lordorb.helpers.CommonService.flattenFolderStructure;
import static com.lordorb.helpers.CommonService.clearEmptyDirectories;
import java.io.File;

import java.io.IOException;


/**
 *
 * @author hwood
 */
public class FileCleanerJob {

    public static void main(String[] args) {
        
        var envMap = UnitOfWork.setEnvHashMap();   

        var directory = envMap.get("DESTINATION_PATH");
        
        // Send both. Destination folder will be appended with the name of the folder
        File sourceFolder = new File(directory);
        File destinationFolder = new File(directory);

        try {
            flattenFolderStructure(sourceFolder, destinationFolder);
            clearEmptyDirectories(destinationFolder);
            System.out.println("Folder structure flattened successfully!");
        } catch (IOException e) {
            System.err.println("Error flattening folder structure: " + e.getMessage());
        }
    }
}
