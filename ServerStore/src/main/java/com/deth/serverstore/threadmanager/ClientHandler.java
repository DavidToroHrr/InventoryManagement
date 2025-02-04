/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.threadmanager;

import com.deth.serverstore.logmanager.LogFile;
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
    MessageManager messageManager;
    LogFile log ;


    public ClientHandler(Socket socket, ProductManager productManager) {
        this.clientSocket = socket;
        this.productManager = productManager;
        messageManager = new MessageManager();
        log = new LogFile();
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

                    String[] response = messageManager.buildMessage(clientMessage);
                    String fmessage = selectOperation(response);
                    outputStream.writeUTF(fmessage);
                }

                System.out.println("Closing connection with client: " + clientSocket.getInetAddress());
                clientSocket.close();
            } catch (IOException ex) {
                System.out.println("Error in ClientHandler: " + ex.getMessage());
            }
        
    }
     private String selectOperation(String[] response) throws IOException {
            String operation = response[0];
            String message = "Error en la operación";

            switch (operation) {
                case "add":
                    message=productManager.createProduct(response);
                    log.writeLog(operation, response[1], response[2]);
                    break;
                case "delete":
                    message=productManager.deleteProduct(response[2]);
                    log.writeLog(operation, response[1], response[2]);
                    break;
                    
                case "edit"://TODO
                    message=productManager.editProduct(response);
                    log.writeLog(operation, response[1], response[2]);
                    break;
                    
                case "exportlog":
                    log.exportAllLogsToCsv("LogsRegister");
                    message="Éxito en la exportación del CSV";
                    break;
                    
                case "exportinventory":
                     message=messageManager.buildInventory(productManager.getProducts());
                     break;
                     
                default:
                    message = "Operación no reconocida";
            }
            return message;
        }
    }
    
    

