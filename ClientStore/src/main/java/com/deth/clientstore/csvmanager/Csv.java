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
    String[] rows = inventory.split("\n"); // Split by lines

    try (FileWriter writer = new FileWriter("inventory.csv")) {
        for (String row : rows) {
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
