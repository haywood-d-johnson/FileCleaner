/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lordorb.filecleaner;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author hwood
 */
public class FolderMethods {
    
    public static void FlattenFolderStructure(String path) throws IOException {
        StringBuilder sourceFolder;
        File destinationFolder = new File(path);
        
        File[] files = destinationFolder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                sourceFolder = new StringBuilder(path);
                System.out.println("This is SourceFolder -> " + path);
                sourceFolder.append(file.getName());
                System.out.println("This is a folder: -> " + sourceFolder.toString());
            }
        }
    }
}
