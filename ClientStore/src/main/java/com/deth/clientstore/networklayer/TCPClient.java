/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.clientstore.networklayer;

import com.deth.clientstore.csvmanager.Csv;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Clase TCPClient
 * 
 * Maneja la conexión con el servidor utilizando un socket seguro (SSL).
 * Permite enviar y recibir mensajes con el servidor y gestionar la creación de archivos CSV.
 * 
 * @author david
 */
public class TCPClient {
    
    // Dirección del servidor al que se conectará el cliente
    private String serverAddress;
    
    // Puerto en el que el servidor está escuchando conexiones
    private int port;
    
    // Socket SSL para la comunicación segura con el servidor
    private SSLSocket clientSocket;
    
    // Flujo de entrada para recibir datos del servidor
    private DataInputStream inputStream;
    
    // Flujo de salida para enviar datos al servidor
    private DataOutputStream outputStream;
    
    // Indica si la conexión con el servidor está activa
    private boolean connected;
    
    // Instancia de la clase Csv para la gestión de archivos CSV
    Csv csvManager;
    
    /**
     * Constructor de TCPClient.
     * 
     * Inicializa la conexión con el servidor y crea una instancia del manejador de archivos CSV.
     * 
     * @param serverAddress Dirección del servidor.
     * @param port Puerto de conexión.
     */
    public TCPClient(String serverAddress, int port) {
        try {
            this.serverAddress = serverAddress;
            this.port = port;
            this.connected = false;
            connect(); // Llama al método connect para establecer la conexión

            csvManager = new Csv(); // Inicializa la gestión de CSV

        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, "Error al conectar con el servidor", ex);
        }
    }
    
    /**
     * Método connect
     * 
     * Establece la conexión SSL con el servidor y configura los flujos de entrada y salida.
     * 
     * @throws IOException Si ocurre un error al conectar el socket.
     */
    public void connect() throws IOException {
        System.out.println("ENTRAMOS A CONNECT PARA CREAR EL SOCKET");
        System.out.println("En connect: serverAddress, port -> " + serverAddress + " " + port);
        
        connected = true;
        
        // Crea un socket SSL utilizando la fábrica de sockets
        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        clientSocket = (SSLSocket) socketFactory.createSocket(serverAddress, port);
        
        System.out.println("Connection established");
        
        // Inicializa los flujos de entrada y salida
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
    }
    
    /**
     * Método sendMessage
     * 
     * Envía un mensaje al servidor y espera una respuesta.
     * 
     * @param sb Mensaje a enviar, en formato StringBuilder.
     * @return Respuesta del servidor.
     */
    public String sendMessage(StringBuilder sb) {
        String response = "Error";
        try {
            // Verifica que el socket y el flujo de salida estén disponibles
            if (clientSocket == null || clientSocket.isClosed()) {
                throw new IOException("El socket no está conectado.");
            }
            if (outputStream == null) {
                throw new IOException("El outputStream no está inicializado.");
            }
            
            String message = sb.toString(); // Convierte StringBuilder a String
            System.out.println("Sending: " + message);
            
            outputStream.writeUTF(message); // Envía el mensaje al servidor
            System.out.println("Mensaje enviado de manera exitosa");
            
            response = inputStream.readUTF(); // Recibe la respuesta del servidor
            System.out.println("Response: " + response);
            
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
        } 
        return response;
    }
    
    /**
     * Método buildMessage
     * 
     * Construye un mensaje con información de un producto y lo envía al servidor.
     * 
     * @param action Acción a realizar (crear, editar, eliminar, consultar).
     * @param productName Nombre del producto.
     * @param productDescription Descripción del producto.
     * @param productPrice Precio del producto.
     * @param quantity Cantidad del producto.
     * @return Respuesta del servidor.
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
     * Método buildMessage (sobrecarga)
     * 
     * Construye y envía un mensaje con solo el nombre del producto.
     * Se usa para acciones como consultar o eliminar productos.
     * 
     * @param action Acción a realizar.
     * @param productName Nombre del producto.
     * @return Respuesta del servidor.
     */
    public String buildMessage(String action, String productName) {
        StringBuilder sb = new StringBuilder();
        sb.append(action).append(":")
          .append(this.clientSocket.getInetAddress()).append(":")
          .append(productName);
        
        return sendMessage(sb);
    }
    
    /**
     * Método buildMessage (sobrecarga)
     * 
     * Construye y envía un mensaje sin parámetros adicionales.
     * Se usa para acciones generales como obtener toda la información del servidor.
     * 
     * @param action Acción a realizar.
     * @return Respuesta del servidor.
     */
    public String buildMessage(String action) {
        StringBuilder sb = new StringBuilder();
        sb.append(action);
        
        return sendMessage(sb);
    }
    
    /**
     * Método buildCsv
     * 
     * Genera un archivo CSV a partir de un inventario recibido.
     * 
     * @param inventory Datos del inventario en formato String.
     * @return `true` si la generación fue exitosa, `false` en caso contrario.
     */
    public boolean buildCsv(String inventory) {
        return csvManager.buildInventoryCsv(inventory);
    }
    
    /**
     * Método closeConnection
     * 
     * Cierra la conexión con el servidor y libera los recursos utilizados.
     */
    public void closeConnection() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.out.println("Error closing connection: " + ex.getMessage());
        }
    }
}
