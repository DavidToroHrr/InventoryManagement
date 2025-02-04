/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.networklayer;

// Importación de clases necesarias para la gestión del servidor
import com.deth.serverstore.messagemanager.MessageManager;
import com.deth.serverstore.productmanager.ProductManager;
import com.deth.serverstore.threadmanager.ClientHandler;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
/**
 *
 * @author david
 */

/**
 * Clase que representa un servidor TCP seguro utilizando SSL.
 * Escucha conexiones entrantes y maneja múltiples clientes.
 */
public class TCPServer {
    
    // Puerto en el que el servidor escuchará las conexiones
    private int port;
    
    // Manejador de productos para gestionar las operaciones relacionadas con los productos
    private ProductManager productManager;
    
    /**
     * Constructor de la clase TCPServer.
     * @param port Puerto en el que se ejecutará el servidor.
     */
    public TCPServer(int port) {
        this.port = port;
        productManager = new ProductManager(); // Inicializa el gestor de productos
    }
    
    /**
     * Método que inicia el servidor y espera conexiones de clientes.
     */
    public void start() {
        try {
            // Crea un socket de servidor seguro utilizando SSL
            SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(port);
            System.out.println("Server listening on port: " + port);
            
            // Bucle infinito para aceptar múltiples conexiones de clientes
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Acepta una nueva conexión
                System.out.println("Connected from: " + clientSocket.getInetAddress() + " port: " + clientSocket.getPort());
                
                // Crea un nuevo hilo para manejar la conexión con el cliente
                new ClientHandler(clientSocket, productManager).start();
            }
        } catch (IOException ex) {
            System.out.println("Error en server: " + ex.getMessage());
        }
    }
}
