/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.logmanager;
 

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Diego Molina
 */
public class LogFile {
    
    private String nombreArchivoLog;
    private String nombreArchivoCSV;
    private File archivoLog;

    public LogFile(String nombreArchivoLog, String nombreArchivoCSV) {
        this.nombreArchivoLog = nombreArchivoLog;
        this.nombreArchivoCSV = nombreArchivoCSV;
        this.archivoLog = new File(nombreArchivoLog);
        crearArchivoLog();
    }

    /**
     * Crear el archivo de log si no existe
     */
    private void crearArchivoLog() {
        try {
            if (!archivoLog.exists()) {
                archivoLog.createNewFile();
                System.out.println("Archivo de log creado");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de log");
            e.printStackTrace();
        }
    }

    /**
     * Escribir una operación en el archivo de log (log.txt)
     *
     * @param operacion La operación realizada (agregar, eliminar, actualizar, buscar)
     * @param recurso   El nombre o ID del producto sobre el que se realizó la operación
     */
    public void escribirLog(String operacion, String ipCliente, String recurso) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha = sdf.format(new Date());
            FileWriter fileWriter = new FileWriter(archivoLog, true); 
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(fecha + " | " + operacion + " | " + ipCliente + " | " + recurso);
            printWriter.close();

            System.out.println("Operacion registrada en el archivo de log");
            } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de log");
            e.printStackTrace();
        }
    }

    /**
     * Exporta todas las operaciones registradas en el archivo de log a un archivo CSV.
     * 
     * @throws IOException Si ocurre algún error al leer o escribir los archivos.
     */
    public void exportarALogCSV() throws IOException {
        File archivoCSV = new File(nombreArchivoCSV);
        
        FileWriter fileWriter = new FileWriter(archivoCSV);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Fecha, Operacion, IP, Recurso");

        BufferedReader reader = new BufferedReader(new FileReader(archivoLog));
        String linea;

        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(" \\| ");
            String fecha = partes[0];
            String operacion = partes[1];
            String ip = partes[2];
            String recurso = partes[3];
            printWriter.println(fecha + "," + operacion + "," + ip + "," + recurso);
        }

        reader.close();
        printWriter.close();
        
        System.out.println("Logs exportados a CSV correctamente");
    }
}
