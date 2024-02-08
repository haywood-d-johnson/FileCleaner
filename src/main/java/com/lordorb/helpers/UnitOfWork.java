package com.lordorb.helpers;

import java.util.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.ini4j.Ini;


public class UnitOfWork {

 public static Map<String, String> readConfigFromIniFile(File iniFile) throws IOException {
        Map<String, String> configMap = new HashMap<>();

        try {
            Ini ini = new Ini(new FileReader(iniFile));

            // Iterate over sections
            for (String sectionName : ini.keySet()) {
                Ini.Section section = ini.get(sectionName);

                // Iterate over keys in each section
                for (String key : section.keySet()) {
                    String value = section.get(key);
                    configMap.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading INI file: " + e.getMessage());
        }

        return configMap;
    }
    
    public static File copyIniFileToDesktop() throws IOException {
        // Define the destination directory (e.g., user's desktop)
        File desktopDir = new File(System.getProperty("user.home"), "Desktop");

        // Create a File object for the INI file on the desktop
        File iniFileOnDesktop = new File(desktopDir, "config.ini");

        // Check if the INI file already exists on the desktop
        if (!iniFileOnDesktop.exists()) {
            // INI file doesn't exist on the desktop, so copy it from resources
            InputStream inputStream = UnitOfWork.class.getResourceAsStream("/config.ini");
            Files.copy(inputStream, iniFileOnDesktop.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        // Return the File object representing the INI file on the desktop
        return iniFileOnDesktop;
    }
}
