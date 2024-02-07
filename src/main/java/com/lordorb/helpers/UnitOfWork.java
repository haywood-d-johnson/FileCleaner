package com.lordorb.helpers;

import java.util.*;
import io.github.cdimascio.dotenv.Dotenv;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hwood
 */
public class UnitOfWork {

    static Dotenv dotenv = Dotenv.load();
    
    public static HashMap<String, String> setEnvHashMap() {
        HashMap<String, String> hash_map = new HashMap<>();
        hash_map.put("DESTINATION_PATH", dotenv.get("DESTINATION_PATH"));
        
        return hash_map;
    }
}
