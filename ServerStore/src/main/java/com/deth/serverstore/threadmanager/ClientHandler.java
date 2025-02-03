/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.threadmanager;

import com.deth.serverstore.messagemanager.MessageManager;
import com.deth.serverstore.productmanager.ProductManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author david
 */
public class ClientHandler extends Thread{
    private Socket clientSocket;
    private ProductManager productManager;

    public ClientHandler(Socket socket, ProductManager productManager) {
        this.clientSocket = socket;
        this.productManager = productManager;
    }

    @Override
    public void run() {
        try (DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                 DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream())) {

                while (true) {
                    String clientMessage = inputStream.readUTF();
                    System.out.println("Client message: " + clientMessage);

                    if (clientMessage.equalsIgnoreCase("exit")) {
                        System.out.println("Client requested to close the connection.");
                        break;
                    }

                    MessageManager messageManager = new MessageManager();
                    String[] response = messageManager.buildMessage(clientMessage);
                    String flag = selectOperation(response);
                    outputStream.writeUTF(flag);
                }

                System.out.println("Closing connection with client: " + clientSocket.getInetAddress());
                clientSocket.close();
            } catch (IOException ex) {
                System.out.println("Error in ClientHandler: " + ex.getMessage());
            }
        
    }
     private String selectOperation(String[] response) {
            String operation = response[0];
            String message = "Error en la operación";

            switch (operation) {
                case "add":
                    message=productManager.createProduct(response);
                    break;
                case "delete":
                    message=productManager.deleteProduct(response[2]);
                    break;
                default:
                    message = "Operación no reconocida";
            }
            return message;
        }
    }
    
    

