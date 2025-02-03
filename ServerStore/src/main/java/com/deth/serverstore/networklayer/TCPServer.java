/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.networklayer;

//import com.breaze.tcpdemoserver.business.NamesManager;
import com.deth.serverstore.messagemanager.MessageManager;
import com.deth.serverstore.productmanager.ProductManager;
import com.deth.serverstore.threadmanager.ClientHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 *
 * @author breaze
 */
public class TCPServer {
    private int port;
    ProductManager productManager;
    
    public TCPServer(int port) {
        this.port = port;
        productManager=new ProductManager();
    }
    
    public void start(){
        try {
        SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(port);
        System.out.println("Server listening on port: " + port);
        
        while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected from: " + clientSocket.getInetAddress()+"port: "+clientSocket.getPort());
                // Crear un nuevo hilo para manejar la conexión con el cliente
                new ClientHandler(clientSocket, productManager).start();
            }
        } catch (IOException ex) {
            System.out.println("Error en server: "+ex.getMessage());
        }
    }
    
}
