/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.clientstore.networklayer;

import com.deth.clientstore.filemanager.Csv;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * The TCPClient class establishes a secure connection to a remote server using SSL.
 * It provides methods for sending messages, handling inventory CSV generation, and closing the connection.
 */
public class TCPClient {
    private String serverAddress;
    private int port;
    private SSLSocket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean connected;

    Csv csvManager;

    /**
     * Constructor to initialize the TCP client with the server address and port.
     * Establishes a connection upon object creation.
     *
     * @param serverAddress The IP or hostname of the server.
     * @param port          The port number to connect to.
     */
    public TCPClient(String serverAddress, int port) {
        try {
            this.serverAddress = serverAddress;
            this.port = port;
            this.connected = false;
            csvManager = new Csv(); // Initializes CSV manager
            connect(); // Establishes the connection immediately
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, "Error establishing connection", ex);
        }
    }

    /**
     * Establishes an SSL connection to the server.
     *
     * @throws IOException If an error occurs while creating the socket.
     */
    public void connect() throws IOException {
        System.out.println("Attempting to create SSL socket connection...");
        System.out.println("Connecting to: " + serverAddress + " on port " + port);

        // Setting up SSL socket
        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        clientSocket = (SSLSocket) socketFactory.createSocket(serverAddress, port);
        connected = true;
        System.out.println("Connection established successfully.");

        // Initializing input and output streams
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
    }

    /**
     * Sends a message to the server and retrieves a response.
     *
     * @param sb The message to be sent, formatted as a StringBuilder.
     * @return The response received from the server.
     */
    public String sendMessage(StringBuilder sb) {
        String response = "Error";
        try {
            if (clientSocket == null || clientSocket.isClosed()) {
                throw new IOException("Socket is not connected.");
            }
            if (outputStream == null) {
                throw new IOException("Output stream is not initialized.");
            }

            String message = sb.toString();
            System.out.println("Sending: " + message);
            outputStream.writeUTF(message); // Send message
            System.out.println("Message sent successfully.");

            response = inputStream.readUTF(); // Receive response
            System.out.println("Response received: " + response);

        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
        }
        return response;
    }

    /**
     * Constructs a message for editing a product and sends it to the server.
     *
     * @param action            The action to perform (e.g., "edit").
     * @param newProductName    The new name of the product.
     * @param oldProductName    The previous name of the product.
     * @param productDescription The new product description.
     * @param productPrice      The new price of the product.
     * @param quantity          The updated quantity.
     * @return The server's response to the message.
     */
    public String buildMessage(String action, String newProductName, String oldProductName, String productDescription, float productPrice, int quantity) {
        StringBuilder sb = new StringBuilder();
        sb.append(action).append(":")
                .append(this.clientSocket.getInetAddress().getHostAddress()).append(":")
                .append(newProductName).append(":")
                .append(oldProductName).append(":")
                .append(productDescription).append(":")
                .append(productPrice).append(":")
                .append(quantity);

        return sendMessage(sb);
    }

    /**
     * Constructs a message for adding a product and sends it to the server.
     *
     * @param action            The action to perform (e.g., "add").
     * @param productName       The name of the product.
     * @param productDescription The product description.
     * @param productPrice      The price of the product.
     * @param quantity          The quantity available.
     * @return The server's response to the message.
     */
    public String buildMessage(String action, String productName, String productDescription, float productPrice, int quantity) {
        StringBuilder sb = new StringBuilder();
        sb.append(action).append(":")
                .append(this.clientSocket.getInetAddress().getHostAddress()).append(":")
                .append(productName).append(":")
                .append(productDescription).append(":")
                .append(productPrice).append(":")
                .append(quantity);

        return sendMessage(sb);
    }

    /**
     * Constructs a message for operations such as querying or deleting a product.
     *
     * @param action      The action to perform (e.g., "delete").
     * @param productName The name of the product.
     * @return The server's response to the message.
     */
    public String buildMessage(String action, String productName) {
        StringBuilder sb = new StringBuilder();
        sb.append(action).append(":")
                .append(this.clientSocket.getInetAddress()).append(":")
                .append(productName);

        return sendMessage(sb);
    }

    /**
     * Constructs a message for operations that require only the action type.
     *
     * @param action The action to perform (e.g., "exportinventory").
     * @return The server's response to the message.
     */
    public String buildMessage(String action) {
        StringBuilder sb = new StringBuilder();
        sb.append(action);

        return sendMessage(sb);
    }

    /**
     * Generates a CSV file using inventory data.
     *
     * @param inventory The inventory data as a formatted string.
     * @return True if the CSV file was successfully created, false otherwise.
     */
    public boolean buildCsv(String inventory) {
        return csvManager.buildInventoryCsv(inventory);
    }

    /**
     * Closes the connection to the server safely.
     * Sends an "exit" message before closing the socket and streams.
     */
    public void closeConnection() {
        try {
            if (connected) {
                buildMessage("exit");
                inputStream.close();
                outputStream.close();
                clientSocket.close();
                connected = false;
                System.out.println("Connection closed successfully.");
            }
        } catch (IOException ex) {
            System.out.println("Error closing connection: " + ex.getMessage());
        }
    }
}

