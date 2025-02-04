/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.threadmanager;

import com.deth.serverstore.filemanager.Csv;
import com.deth.serverstore.filemanager.LogFile;
import com.deth.serverstore.messagemanager.MessageManager;
import com.deth.serverstore.productmanager.ProductManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * The ClientHandler class is responsible for handling communication with a single client.
 * 
 * It listens for client messages, processes them, performs the appropriate operations,
 * and sends responses back to the client. Each client connection is handled in a separate thread.
 * 
 * @author david
 */
public class ClientHandler extends Thread {
    private Socket clientSocket;         // Socket for client communication
    private ProductManager productManager; // Manages product-related operations
    private MessageManager messageManager; // Handles message formatting and parsing
    private LogFile log;                 // Logs operations performed by the client
    private Csv csv;                      // Manages CSV exports

    /**
     * Constructs a ClientHandler for a specific client connection.
     * 
     * @param socket The client socket.
     * @param productManager The product manager to handle inventory operations.
     */
    public ClientHandler(Socket socket, ProductManager productManager) {
        this.clientSocket = socket;
        this.productManager = productManager;
        this.messageManager = new MessageManager();
        this.log = new LogFile();
        this.csv = new Csv();
    }

    /**
     * Runs the client communication process.
     * Continuously listens for messages from the client, processes them, and responds accordingly.
     */
    @Override
    public void run() {
        try (DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                // Read message from client
                String clientMessage = inputStream.readUTF();
                System.out.println("Client message: " + clientMessage);

                // Check for client disconnect request
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client requested to close the connection.");
                    break;
                }

                // Parse and process the message
                String[] response = messageManager.buildMessage(clientMessage);
                String fmessage = selectOperation(response);

                // Send response back to client
                outputStream.writeUTF(fmessage);
            }

            System.out.println("Closing connection with client: " + clientSocket.getInetAddress());
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Error in ClientHandler: " + ex.getMessage());
        }
    }

    /**
     * Processes the client request based on the operation type.
     * 
     * @param response The parsed message components.
     * @return The result message after executing the requested operation.
     * @throws IOException If an error occurs during file operations.
     */
    private String selectOperation(String[] response) throws IOException {
        String operation = response[0];
        String message = "Error en la operación";

        switch (operation) {
            case "add":
                message = productManager.createProduct(response);
                log.writeLog(operation, response[1], response[2]);
                break;

            case "delete":
                message = productManager.deleteProduct(response[2]);
                log.writeLog(operation, response[1], response[2]);
                break;

            case "edit":
                message = productManager.editProduct(response);
                log.writeLog(operation, response[1], response[2]);
                break;

            case "exportlog":
                log.exportAllLogsToCsv("LogsRegister");
                message = "Éxito en la exportación del CSV";
                break;

            case "exportinventory":
                message = messageManager.buildInventory(productManager.getProducts());
                if (csv.buildInventoryCsv(message)) {
                    System.out.println("Inventario guardado en el servidor");
                }
                break;

            default:
                message = "Operación no reconocida";
        }

        return message;
    }
}
