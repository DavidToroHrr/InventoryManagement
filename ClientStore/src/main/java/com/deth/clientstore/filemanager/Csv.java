/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.clientstore.filemanager;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The Csv class provides functionality to generate a CSV file 
 * from an inventory string.
 */
public class Csv {
    
    /**
     * Builds and writes an inventory CSV file from a formatted string.
     * 
     * @param inventory The inventory data represented as a string 
     *                  where each row is separated by a newline character 
     *                  and columns are separated by '|'.
     * @return true if the CSV file is successfully generated, false otherwise.
     */
    public boolean buildInventoryCsv(String inventory) {
        boolean result = true;

        // Split the input inventory string into rows
        String[] rows = inventory.split("\n"); 

        // Try-with-resources to automatically close the FileWriter
        try (FileWriter writer = new FileWriter("inventory.csv")) {
            for (String row : rows) {
                // Replace '|' with ',' to match CSV format
                String formattedRow = row.replace("|", ",");
                writer.append(formattedRow);
                writer.append("\n");
            }
            System.out.println("Archivo CSV generado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo CSV.");
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
