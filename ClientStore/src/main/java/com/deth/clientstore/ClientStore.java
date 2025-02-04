/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deth.clientstore;

import com.deth.clientstore.gui.UserInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ClientStore class is responsible for initializing the user interface 
 * and setting up SSL certificate configurations.
 */
public class ClientStore {

    public static void main(String[] args) {
        // Initialize and display the user interface
        UserInterface userInterface = new UserInterface();
        userInterface.setVisible(true);
        userInterface.setLocationRelativeTo(null); // Center the window on the screen

        // Load properties from the configuration file
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File("configuration.properties")));
        } catch (FileNotFoundException ex) {
            // Log an error if the configuration file is not found
            Logger.getLogger(ClientStore.class.getName()).log(Level.SEVERE, "Configuration file not found", ex);
        } catch (IOException ex) {
            // Log an error if there is an issue reading the configuration file
            Logger.getLogger(ClientStore.class.getName()).log(Level.SEVERE, "Error reading configuration file", ex);
        }

        // Retrieve SSL certificate path from the properties file
        String sslRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        File certFile = new File(sslRoute);

        // Verify if the SSL certificate file exists
        if (!certFile.exists()) {
            System.out.println("Error: Certificado no encontrado en " + sslRoute);
        } else {
            System.out.println("Certificado encontrado correctamente.");
        }

        // Retrieve SSL password from the properties file
        String sslPassword = p.getProperty("SSL_PASSWORD");

        // Set SSL system properties for secure communication
        System.setProperty("javax.net.ssl.keyStore", sslRoute);
        System.setProperty("javax.net.ssl.keyStorePassword", sslPassword);
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.trustStore", sslRoute);
        System.setProperty("javax.net.ssl.trustStorePassword", sslPassword);
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");
    }
}
