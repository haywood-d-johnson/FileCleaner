package com.lordorb.filecleaner;

import com.lordorb.helpers.*;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author hwood
 */
public class FileCleanerJob {

    public static void main(String[] args) throws IOException {
        
        String resourcePath = "config.ini", absolutePath = "";
        URL resourceUrl = FileCleanerJob.class.getClassLoader().getResource(resourcePath);

        if (resourceUrl != null) {
            absolutePath = new File(resourceUrl.getFile()).getAbsolutePath();
        }
        
        Map<String, Map<String, String>> map = new HashMap<>();
        boolean checkINIFile = UnitOfWork.copyIniFileToDesktop(absolutePath);

        if (checkINIFile) {
            try {
                map = UnitOfWork.readConfigFromIniFile(absolutePath);
                // Now you can use the 'map' variable which contains the contents of the INI file
            } catch (IOException e) {
                System.err.println("Error reading INI file: " + e.getMessage());
            }
        } else {
            System.out.println("INI file is already present on the desktop.");
        }


        //var directory = ini.get("DESTINATION_PATH");
        
        
        // Send both. Destination folder will be appended with the name of the folder
        //File sourceFolder = new File(directory);
        //File destinationFolder = new File(directory);

//        try {
//            flattenFolderStructure(sourceFolder, destinationFolder);
//            clearEmptyDirectories(destinationFolder);
//            System.out.println("Folder structure flattened successfully!");
//        } catch (IOException e) {
//            System.err.println("Error flattening folder structure: " + e.getMessage());
//        }
    }
}
