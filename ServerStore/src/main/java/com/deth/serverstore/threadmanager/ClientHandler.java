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
 * Clase encargada de manejar la comunicación con un cliente.
 * Extiende de Thread para manejar múltiples clientes de forma concurrente.
 * Se encarga de recibir mensajes, procesar operaciones y devolver respuestas al cliente.
 * 
 * @author david
 */
public class ClientHandler extends Thread {
    
    // Socket del cliente que se conectó al servidor
    private Socket clientSocket;
    
    // Gestor de productos para realizar operaciones sobre el inventario
    private ProductManager productManager;
    
    // Administrador de mensajes para procesar solicitudes del cliente
    private MessageManager messageManager;
    
    // Manejador de logs para registrar operaciones
    private LogFile log;

    /**
     * Constructor de la clase ClientHandler.
     * 
     * @param socket Socket del cliente conectado.
     * @param productManager Instancia de ProductManager para gestionar productos.
     */
    public ClientHandler(Socket socket, ProductManager productManager) {
        this.clientSocket = socket;
        this.productManager = productManager;
        this.messageManager = new MessageManager();
        this.log = new LogFile("log.txt", "logs.csv"); // Archivos donde se almacenan los logs
    }

    /**
     * Método principal del hilo que maneja la comunicación con el cliente.
     * Lee mensajes enviados por el cliente y ejecuta la operación correspondiente.
     */
    @Override
    public void run() {
        try (DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                // Recibe el mensaje del cliente
                String clientMessage = inputStream.readUTF();
                System.out.println("Client message: " + clientMessage);

                // Si el cliente envía "exit", se cierra la conexión
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client requested to close the connection.");
                    break;
                }

                // Procesa el mensaje recibido
                String[] response = messageManager.buildMessage(clientMessage);
                String flag = selectOperation(response);
                
                // Envía la respuesta al cliente
                outputStream.writeUTF(flag);
            }

            // Cierra la conexión con el cliente
            System.out.println("Closing connection with client: " + clientSocket.getInetAddress());
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Error in ClientHandler: " + ex.getMessage());
        }
    }
    
    /**
     * Método que selecciona la operación a ejecutar según el mensaje recibido.
     * 
     * @param response Array de Strings con la información de la operación solicitada.
     * @return Mensaje con el resultado de la operación.
     * @throws IOException Si ocurre un error en el manejo de archivos o logs.
     */
    private String selectOperation(String[] response) throws IOException {
        // La operación solicitada por el cliente (ej. "add", "delete", "edit", etc.)
        String operation = response[0];
        String message = "Error en la operación"; // Mensaje de error por defecto

        switch (operation) {
            case "add":
                // Agrega un nuevo producto al inventario
                message = productManager.createProduct(response);
                log.escribirLog(operation, response[1], response[2]); // Registra la operación en los logs
                break;

            case "delete":
                // Elimina un producto del inventario
                message = productManager.deleteProduct(response[2]);
                log.escribirLog(operation, response[1], response[2]);
                break;
                
            case "edit":
                // TODO: Implementar funcionalidad para editar un producto
                log.escribirLog(operation, response[1], response[2]);
                break;
                
            case "exportlog":
                // Exporta los logs a un archivo CSV
                log.exportarALogCSV();
                break;
                
            case "exportinventory":
                // Construye un mensaje con la información del inventario
                message = messageManager.buildInventory(productManager.getProducts());
                break;
                
            default:
                message = "Operación no reconocida";
        }

        return message;
    }
}
