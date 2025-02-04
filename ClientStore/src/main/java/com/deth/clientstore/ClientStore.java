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
 * Clase ClientStore
 * 
 * Esta es la clase principal de la aplicación. Se encarga de inicializar la interfaz
 * gráfica del usuario y de cargar la configuración SSL desde un archivo de propiedades.
 * 
 * @author david
 */
public class ClientStore {
    
    /**
     * Constructor de ClientStore
     * 
     * No realiza ninguna acción específica, pero permite la creación de instancias de esta clase.
     */
    public ClientStore() {
    }
    
    /**
     * Método main
     * 
     * Punto de entrada de la aplicación. Se encarga de:
     * - Inicializar la interfaz gráfica del usuario.
     * - Cargar el archivo de configuración "configuration.properties".
     * - Configurar los parámetros SSL necesarios para la comunicación segura.
     * 
     * @param args Argumentos pasados por la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Inicialización de la interfaz gráfica
        UserInterface userInterface = new UserInterface();
        userInterface.setVisible(true); // Hace visible la ventana
        userInterface.setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Carga de propiedades desde un archivo de configuración
        Properties p = new Properties();
        try {
            // Carga el archivo "configuration.properties"
            p.load(new FileInputStream(new File("configuration.properties")));
        } catch (FileNotFoundException ex) {
            // Manejo de error si el archivo no se encuentra
            Logger.getLogger(ClientStore.class.getName()).log(Level.SEVERE, "Archivo de configuración no encontrado", ex);
        } catch (IOException ex) {
            // Manejo de error si hay problemas en la lectura del archivo
            Logger.getLogger(ClientStore.class.getName()).log(Level.SEVERE, "Error al leer el archivo de configuración", ex);
        }

        // Obtención de la ruta del certificado SSL desde el archivo de configuración
        String sslRoute = p.getProperty("SSL_CERTIFICATE_ROUTE");
        File certFile = new File(sslRoute);
        
        // Verificación de la existencia del archivo de certificado SSL
        if (!certFile.exists()) {
            System.out.println("Error: Certificado no encontrado en " + sslRoute);
        } else {
            System.out.println("Certificado encontrado correctamente.");
        }

        // Configuración de las propiedades del sistema para SSL
        String sslPassword = p.getProperty("SSL_PASSWORD");
        System.setProperty("javax.net.ssl.keyStore", sslRoute); // Ubicación del almacén de claves
        System.setProperty("javax.net.ssl.keyStorePassword", sslPassword); // Contraseña del almacén de claves
        System.setProperty("javax.net.ssl.keyStoreType", "PKCS12"); // Tipo de almacén de claves
        System.setProperty("javax.net.ssl.trustStore", sslRoute); // Ubicación del almacén de confianza
        System.setProperty("javax.net.ssl.trustStorePassword", sslPassword); // Contraseña del almacén de confianza
        System.setProperty("javax.net.ssl.trustStoreType", "PKCS12"); // Tipo de almacén de confianza
    }
}
