/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.clientstore.csvmanager;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author david
 */
public class Csv {
    public boolean buildInventoryCsv(String inventory) {
        boolean result = true;
        String[] rows = inventory.split("\n"); //lo dividimos por lineas

        try (FileWriter writer = new FileWriter("inventory.csv")) {
            for (String row : rows) {
                String[] columns = row.split(","); // dividimos las columnas usando ","
                writer.append(String.join(",", columns)); 
                writer.append("\n"); 
            }
            System.out.println("Archivo CSV generado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        
        return result;
    }
}
