/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deth.serverstore;

import com.deth.serverstore.networklayer.TCPServer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.deth.serverstore.logmanager.LogFile;
/**
 *
 * @author david
 */
public class ServerStore {

    public static void main(String[] args) {
        /*
        LogFile log = new LogFile("log.txt", "logs.csv");

        log.escribirLog("generar reporte", "192.168.1.1", "Producto 123");
        log.escribirLog("Eliminar", "192.168.1.2", "Producto ABC");
        log.escribirLog("editar", "192.168.1.3", "Producto 456");
        log.escribirLog("Buscar", "192.168.1.4", "Producto XYZ");
        
        
        try {
            log.exportarALogCSV();
        } catch (IOException e) {
            System.out.println("Error al exportar los logs a CSV");
            e.printStackTrace(); 
        }
        */
        
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File("configuration.properties")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("SSL_CERTIFICATE_ROUTE: " + p.getProperty("SSL_CERTIFICATE_ROUTE"));
        System.out.println("SSL_PASSWORD-------------: " + p.getProperty("SSL_PASSWORD"));

        String sslRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        String sslPassword = p.getProperty("SSL_PASSWORD");

        System.setProperty("javax.net.ssl.keyStore", sslRoute);
        System.setProperty("javax.net.ssl.keyStorePassword", sslPassword);
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.trustStore", sslRoute);
        System.setProperty("javax.net.ssl.trustStorePassword", sslPassword);
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");

        TCPServer server = new TCPServer(9090);
        server.start();
    }
}
