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
public class FileMethods {
    
    public static void GetAllFilesInDirectory(String path) throws IOException {
        
        UnitOfWork unitOfWork = new UnitOfWork();
        
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
}
