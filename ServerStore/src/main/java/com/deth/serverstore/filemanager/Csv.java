/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.filemanager;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The Csv class provides functionality to generate a CSV file 
 * from an inventory string.
 * This class formats the inventory data and writes it to a file.
 * 
 * @author david
 */
public class Csv {

    /**
     * Generates a CSV file from the provided inventory data.
     * 
     * @param inventory The inventory data represented as a string, 
     *                  where each row is separated by a newline character ('\n')
     *                  and columns are separated by a pipe ('|').
     * @return true if the CSV file is successfully generated, false otherwise.
     */
    public boolean buildInventoryCsv(String inventory) {
        boolean result = true;

        // Split the input inventory string into rows
        String[] rows = inventory.split("\n"); 

        // Try-with-resources to automatically close the FileWriter
        try (FileWriter writer = new FileWriter("inventory.csv")) {
            for (String row : rows) {
                // Replace '|' with ',' to format as CSV
                String formattedRow = row.replace("|", ",");
                writer.append(formattedRow);
                writer.append("\n"); // Append a new line after each row
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

