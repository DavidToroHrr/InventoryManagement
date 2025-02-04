/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.logmanager;
 

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Diego Molina
 */
public class LogFile {
    
   
     public void writeLog(String operation, String ipCliente, String resource) {
        String nameLogFile = generateFileName(operation);
        File archivoLog = new File("logs", nameLogFile);

        try (FileWriter fileWriter = new FileWriter(archivoLog);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha = sdf.format(new Date());

            // Escribir en el archivo con el formato correcto
            printWriter.println(fecha + " | " + operation.toLowerCase() + " | " + ipCliente + " | " + resource);

            System.out.println("✅ Archivo de log generado: " + archivoLog.getName());

        } catch (IOException e) {
            System.out.println("❌ Error al escribir en el archivo de log");
            e.printStackTrace();
        }
    }
     
     private String generateFileName(String operation) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS");
        return "log_" + operation + "_" + sdf.format(new Date()) + ".txt";
    }

 
        
   public void exportAllLogsToCsv(String nameCSVFile) {
        File carpetaLogs = new File("logs");
        File archivoCSV = new File("logs", nameCSVFile);

        try (FileWriter fileWriter = new FileWriter(archivoCSV);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Escribir encabezado del CSV
            printWriter.println("Fecha,Operacion,IP,Recurso");

            // Obtener todos los archivos de log en la carpeta
            File[] archivosLog = carpetaLogs.listFiles((dir, name) -> name.endsWith(".txt"));

            if (archivosLog != null) {
                for (File archivoLog : archivosLog) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(archivoLog))) {
                        String linea;
                        while ((linea = reader.readLine()) != null) {
                            String[] partes = linea.split(" \\| ");
                            if (partes.length == 4) {
                                // Formatear la línea para el CSV
                                printWriter.println(partes[0].trim() + "," + partes[1].trim() + "," + partes[2].trim() + "," + partes[3].trim());
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo: " + archivoLog.getName());
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Logs exportados a CSV correctamente: " + archivoCSV.getName());

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV");
            e.printStackTrace();
        }
    }
    
     
}
