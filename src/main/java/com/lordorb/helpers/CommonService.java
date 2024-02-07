package com.lordorb.helpers;

import java.io.*;

/**
 *
 * @author hwood
 */
public class CommonService {
    
    public static void GetAllFilesInDirectory(String path) throws IOException {
        StringBuilder sourceFolder;
        
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                sourceFolder = new StringBuilder(path);
                sourceFolder.append(file.getName());
            }
            System.out.println(file.getName());
        }
    }
    
    public static void FlattenFolderStructure(String path, String source) throws IOException {
        
        var destinationFolder = new File(path);
        
        File[] files = source.listFiles();
        
        for (File file : files) {
            if (file.isDirectory()) {
                FlattenFolderStructure();
            }
        }
    }
}
