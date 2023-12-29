/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lordorb.filecleaner;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hwood
 */
public class FileCleaner {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        StringBuilder directory = new StringBuilder(dotenv.get("DESTINATION_PATH"));
    
        System.out.println("Getting all files from path: " + directory);
        try {
            FileMethods.GetAllFilesInDirectory(directory.toString());
            //FolderMethods.FlattenFolderStructure();
        } catch (IOException ex) {
            Logger.getLogger(FileCleaner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
