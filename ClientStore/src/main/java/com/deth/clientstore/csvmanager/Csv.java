/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.clientstore.csvmanager;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Csv
 * 
 * Esta clase proporciona métodos para gestionar archivos CSV. En particular,
 * permite construir un archivo CSV a partir de una cadena de texto con
 * información de inventario.
 * 
 * @author david
 */
public class Csv {
    
    /**
     * Método buildInventoryCsv
     * 
     * Este método toma una cadena de texto que representa un inventario, la procesa y
     * genera un archivo CSV llamado "inventory.csv".
     * 
     * @param inventory Cadena de texto que contiene el inventario, con filas separadas por saltos de línea
     *                  y columnas separadas por comas.
     * @return true si el archivo se genera correctamente, false en caso de error.
     */
    public boolean buildInventoryCsv(String inventory) {
        boolean result = true; // Variable para almacenar el estado del proceso
        
        // Dividimos la cadena de inventario en filas usando el salto de línea como separador
        String[] rows = inventory.split("\n");

        // Intentamos escribir el archivo CSV
        try (FileWriter writer = new FileWriter("inventory.csv")) {
            for (String row : rows) {
                // Dividimos cada fila en columnas usando la coma como separador
                String[] columns = row.split(",");
                
                // Escribimos la fila en el archivo CSV
                writer.append(String.join(",", columns)); 
                writer.append("\n"); // Agregamos un salto de línea después de cada fila
            }
            System.out.println("Archivo CSV generado correctamente");
        } catch (IOException e) {
            // Capturamos cualquier excepción de entrada/salida y la mostramos
            e.printStackTrace();
            result = false; // Indicamos que hubo un error en la generación del archivo
        }
        
        return result; // Retornamos el estado del proceso
    }
}
