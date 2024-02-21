package com.lordorb.helpers;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UnitOfWork {

    public static Map<String, Map<String, String>> readConfigFromIniFile(String iniFilePath) throws IOException {
        Map<String, Map<String, String>> iniMap = new HashMap<>();

        try (FileReader fileReader = new FileReader(new File(iniFilePath));
             BufferedReader reader = new BufferedReader(fileReader)) {

            Ini readIni = new Ini(reader);

            for (String sectionName : readIni.keySet()) {
                Map<String, String> sectionMap = new HashMap<>();
                Section section = readIni.get(sectionName);

                for (String key : section.keySet()) {
                    sectionMap.put(key, section.get(key));
                }

                iniMap.put(sectionName, sectionMap);
            }
        }

        return iniMap;
    }

    public static boolean copyIniFileToDesktop(String iniFilePath) throws IOException {
        File sourceFile = new File(iniFilePath);

        if (!sourceFile.exists()) {
            throw new FileNotFoundException("INI file not found: " + iniFilePath);
        }

        File desktopDirectory = new File(System.getProperty("user.home"), "Desktop");
        File destinationFile = new File(desktopDirectory, sourceFile.getName());

        if (destinationFile.exists()) {
            return false; // File already exists on the desktop
        }

        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }

            System.out.println("File successfully copied to the desktop: " + destinationFile.getAbsolutePath());
        }

        return true;
    }
}
