package com.lordorb.filecleaner;

import com.lordorb.helpers.*;
import static com.lordorb.helpers.CommonService.flattenFolderStructure;
import static com.lordorb.helpers.CommonService.clearEmptyDirectories;
import static com.lordorb.helpers.UnitOfWork.copyIniFileToDesktop;
import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hwood
 */
public class FileCleanerJob {

    public static void main(String[] args) {
        
         // Copy INI file from resources to user's desktop if not already present
         File iniFile = copyIniFileToDesktop();
        
        Map<String, String> envMap = new HashMap<>();
        
        try {
            envMap = UnitOfWork.readConfigFromIniFile(iniFile);
        } catch (IOException ex) {
            Logger.getLogger(FileCleanerJob.class.getName()).log(Level.SEVERE, null, ex);
        }

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
