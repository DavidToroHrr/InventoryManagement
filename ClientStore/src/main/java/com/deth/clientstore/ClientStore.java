/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deth.clientstore;

import com.deth.clientstore.gui.UserInterface;
import com.deth.clientstore.networklayer.TCPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class ClientStore {
    
    public ClientStore() {
    }
    
    public static void main(String[] args) {
        UserInterface userInterface=new UserInterface();
        userInterface.setVisible(true);
        userInterface.setLocationRelativeTo(null);
        
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File("configuration.properties")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String sslRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        File certFile = new File(sslRoute);
        if (!certFile.exists()) {
            System.out.println("Error: Certificado no encontrado en " + "SSL_CERTIFICATE_ROUTE");
        } else {
            System.out.println("Certificado encontrado correctamente.");
        }
        
        String sslPassword = p.getProperty("SSL_PASSWORD");
        System.setProperty("javax.net.ssl.keyStore",sslRoute);
        System.setProperty("javax.net.ssl.keyStorePassword",sslPassword);
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
        System.setProperty("javax.net.ssl.trustStore", sslRoute);
        System.setProperty("javax.net.ssl.trustStorePassword", sslPassword);
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12");
        
    }

    
    
}
