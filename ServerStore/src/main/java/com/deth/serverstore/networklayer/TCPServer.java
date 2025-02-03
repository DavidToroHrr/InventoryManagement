/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.networklayer;

//import com.breaze.tcpdemoserver.business.NamesManager;
import com.deth.serverstore.business.NamesManager;
import com.deth.serverstore.messagemanager.MessageManager;
import com.deth.serverstore.productmanager.ProductManager;
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
            // Accept connection 
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected from: " + clientSocket.getInetAddress());

            // Defining input and output
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            String clientMessage = inputStream.readUTF();
            System.out.println("Client message: " + clientMessage);
            
            MessageManager messageManager=new MessageManager();
            String[] response=messageManager.buildMessage(clientMessage);
                
            String flag=selectOperation(response);
                      
            
            System.out.println("Response: " + flag);
            outputStream.writeUTF(flag+"...");  // Envía la respuesta como String
            
            // Cerrar conexión con el cliente
            clientSocket.close();
            System.out.println("Connection closed");

            }
        } catch (IOException ex) {
            System.out.println("Error en server: "+ex.getMessage());
        }
    }
    
    public String selectOperation(String[] response){
        String operation=response[0];
        String message="error en la operación";
        switch (operation) {
            case "add":
                if (productManager.createProduct(response)) {
                    message="Producto creado exitosamente";
                    return message;
                }
                
            
            case "delete":
                if (productManager.deleteProduct(response[2])) {
                    message="producto"+response[2]+"eliminado exitosamente";
                }
                
            default:
                throw new AssertionError();
        }
    }
}
