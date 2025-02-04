/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.networklayer;

import com.deth.serverstore.productmanager.ProductManager;
import com.deth.serverstore.threadmanager.ClientHandler;
import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * The TCPServer class initializes a secure SSL server socket 
 * and listens for client connections.
 * 
 * It manages incoming connections by spawning a separate thread for each client.
 * The server uses SSL for encrypted communication.
 * 
 * @author David Toro
 */
public class TCPServer {
    private int port;
    private ProductManager productManager;

    /**
     * Constructs a TCPServer with the specified port.
     * Initializes the ProductManager to handle product-related operations.
     * 
     * @param port The port number on which the server will listen.
     */
    public TCPServer(int port) {
        this.port = port;
        productManager = new ProductManager();
    }

    /**
     * Starts the SSL server and listens for incoming client connections.
     * Each client is handled in a separate thread.
     */
    public void start() {
        try {
            // Create an SSL server socket factory
            SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

            // Create an SSL server socket bound to the specified port
            SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(port);
            System.out.println("Server listening on port: " + port);

            // Continuously accept client connections
            while (true) {
                // Wait for a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected from: " + clientSocket.getInetAddress() + " port: " + clientSocket.getPort());

                // Create a new thread to handle the client connection
                new ClientHandler(clientSocket, productManager).start();
            }
        } catch (IOException ex) {
            System.out.println("Server error: " + ex.getMessage());
        }
    }
}

