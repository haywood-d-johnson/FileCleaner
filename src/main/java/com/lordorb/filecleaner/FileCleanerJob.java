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
        
        System.out.println(directory);
    
//        System.out.println("Getting all files from path: " + directory);
//        try {
//            FileMethods.GetAllFilesInDirectory(directory.toString());
//            //FolderMethods.FlattenFolderStructure();
//        } catch (IOException ex) {
//            Logger.getLogger(FileCleanerJob.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
