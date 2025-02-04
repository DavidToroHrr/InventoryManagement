/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.filemanager;
 

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The LogFile class provides functionality to log operations performed 
 * in the system and export logs to a CSV file.
 * 
 * It allows writing logs with details such as operation type, client IP, 
 * and resource, and exporting all logs into a structured CSV file.
 * 
 * @author David toro
 */
public class LogFile {

    /**
     * Writes an operation log entry to a file.
     * 
     * @param operation The type of operation performed (e.g., "add", "delete").
     * @param ipCliente The IP address of the client that performed the operation.
     * @param resource  The resource affected by the operation.
     */
    public void writeLog(String operation, String ipCliente, String resource) {
        // Generate log file name based on operation and timestamp
        String nameLogFile = generateFileName(operation);
        File archivoLog = new File("logs", nameLogFile);

        // Ensure logs directory exists
        archivoLog.getParentFile().mkdirs();

        // Try-with-resources to automatically close FileWriter and PrintWriter
        try (FileWriter fileWriter = new FileWriter(archivoLog);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Format the current timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha = sdf.format(new Date());

            // Write log entry in the format: date | operation | client IP | resource
            printWriter.println(fecha + " | " + operation.toLowerCase() + " | " + ipCliente + " | " + resource);

            System.out.println("Log file created: " + archivoLog.getName());

        } catch (IOException e) {
            System.out.println("Error writing to log file");
            e.printStackTrace();
        }
    }

    /**
     * Generates a unique log file name using the operation name and timestamp.
     * 
     * @param operation The type of operation being logged.
     * @return A unique log file name in the format "log_operation_timestamp.txt".
     */
    private String generateFileName(String operation) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS");
        return "log_" + operation + "_" + sdf.format(new Date()) + ".txt";
    }

    /**
     * Exports all log files in the "logs" directory to a CSV file.
     * 
     * @param nameCSVFile The name of the CSV file where logs will be exported.
     */
    public void exportAllLogsToCsv(String nameCSVFile) {
        File carpetaLogs = new File("logs");
        File archivoCSV = new File("logs", nameCSVFile);

        // Ensure logs directory exists
        if (!carpetaLogs.exists()) {
            System.out.println("Logs directory does not exist. No logs to export.");
            return;
        }

        // Try-with-resources to ensure FileWriter and PrintWriter are closed properly
        try (FileWriter fileWriter = new FileWriter(archivoCSV);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Write CSV header
            printWriter.println("Fecha,Operacion,IP,Recurso");

            // Retrieve all log files from the directory
            File[] archivosLog = carpetaLogs.listFiles((dir, name) -> name.endsWith(".txt"));

            if (archivosLog != null) {
                for (File archivoLog : archivosLog) {
                    // Read each log file and write its content to the CSV
                    try (BufferedReader reader = new BufferedReader(new FileReader(archivoLog))) {
                        String linea;
                        while ((linea = reader.readLine()) != null) {
                            String[] partes = linea.split(" \\| ");
                            if (partes.length == 4) {
                                // Format the line for CSV and write it
                                printWriter.println(partes[0].trim() + "," + partes[1].trim() + "," + partes[2].trim() + "," + partes[3].trim());
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading file: " + archivoLog.getName());
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Logs successfully exported to CSV: " + archivoCSV.getName());

        } catch (IOException e) {
            System.out.println("Error writing to CSV file");
            e.printStackTrace();
        }
    }
}
