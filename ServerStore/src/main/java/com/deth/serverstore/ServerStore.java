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

/**
 * The ServerStore class initializes and starts the TCP server for handling client connections.
 * It also loads SSL configuration settings from a properties file.
 * 
 * @author david
 */
public class ServerStore {

    public static void main(String[] args) {
        
        // Create a Properties object to load configuration settings
        Properties p = new Properties();
        try {
            // Load properties from configuration file
            p.load(new FileInputStream(new File("configuration.properties")));
        } catch (FileNotFoundException ex) {
            // Log an error if the configuration file is not found
            Logger.getLogger(ServerStore.class.getName()).log(Level.SEVERE, "Configuration file not found", ex);
        } catch (IOException ex) {
            // Log an error if there is an issue reading the configuration file
            Logger.getLogger(ServerStore.class.getName()).log(Level.SEVERE, "Error reading configuration file", ex);
        }

        // Print SSL configuration settings for debugging purposes
        System.out.println("SSL_CERTIFICATE_ROUTE: " + p.getProperty("SSL_CERTIFICATE_ROUTE"));
        System.out.println("SSL_PASSWORD: " + p.getProperty("SSL_PASSWORD"));

        // Retrieve SSL certificate path and password from properties
        String sslRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        String sslPassword = p.getProperty("SSL_PASSWORD");

        // Set system properties for SSL configuration
        System.setProperty("javax.net.ssl.keyStore", sslRoute);
        System.setProperty("javax.net.ssl.keyStorePassword", sslPassword);
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.trustStore", sslRoute);
        System.setProperty("javax.net.ssl.trustStorePassword", sslPassword);
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");

        // Initialize and start the TCP server on port 9090
        TCPServer server = new TCPServer(9090);
        server.start();
    }
}

