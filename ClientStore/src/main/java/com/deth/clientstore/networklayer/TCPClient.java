/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.clientstore.networklayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Class that handle connection with the server
 * @author david
 */
public class TCPClient {
    private String serverAddress;
    private int port;
    private SSLSocket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private boolean connected;
    
    public TCPClient(String serverAddress, int port) {
        try {
            this.serverAddress = serverAddress;
            this.port = port;
            this.connected=false;
            connect();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void connect() throws IOException{
        //clientSocket = new (serverAddress, port);
        System.out.println("ENTRAMOS A CONNCET PARA CREAR EL SOCKET");
        System.out.println("en connect: sadress, port"+serverAddress+" "+port);
        
        connected=true;
        SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        clientSocket = (SSLSocket)socketFactory.createSocket(serverAddress, port);
        System.out.println("Connection established");
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
    }
    
    public String sendMessage(StringBuilder sb){
        //TODO: String action,SSLSocket clientSocket,string prodcuctName
        String response = "Error";
        try {
            if (clientSocket == null || clientSocket.isClosed()) {
                throw new IOException("El socket no está conectado.");
            }
            if (outputStream == null) {
                throw new IOException("El outputStream no está inicializado.");
            }
            
            //System.out.println("ss"+clientSocket.getInetAddress());
            //aqui pasamos el sb a string
            String message = sb.toString();
            System.out.println("Sending: "+message);
            outputStream.writeUTF(message);//aquí se envía el mensaje
            System.out.println("mensaje enviado de manera exitosa");
            response = inputStream.readUTF();//aquí obtenemos la respuesta del servidor
            System.out.println("Response: "+response);
        } catch (IOException ex) {
            System.out.println("Client error: "+ex.getMessage());
        } 
        return response;
    }
    
    public String buildMessage(String action,String productName,String productDescription,float productPrice,int quantity){
   
        StringBuilder sb= new StringBuilder();
        sb.append(action).append(":")
                .append(this.clientSocket.getInetAddress().getHostAddress()).append(":")
                .append(productName).append(":")
                .append(productDescription).append(":")
                .append(productPrice).append(":")
                .append(quantity);
        
        String message=sendMessage(sb);     
        return message;
        
    }
    /*PASAR EL SOCKET Y PASAR LA ACTION*/
    public String buildMessage(String action,String productName){

        //método para enviar el mensaje de consultar, eliminar
        StringBuilder sb= new StringBuilder();
        sb.append(action).append(":")
                .append(this.clientSocket.getInetAddress()).append(":")
                .append(productName);
        
        String message=sendMessage(sb);
        return message;
    }
    
    
   
    //TODO: para enviar el mensaje se deben de tener otros tipos, para crear necesitamos
    //crear: 
    //private int quantity;
    //private int id;
    //private String productName;
    //private String productDescription;
    //private float productPrice;
    
    //eliminar: productName
    //consultar: productName
    //editar: productName, puede(cantidad,id,nombrem,descripción,)
    public void closeConnection(){
        try{
            if(inputStream != null){
            inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
            if(clientSocket != null){
                clientSocket.close();
            }
        }catch(IOException ex){
            System.out.println("Error closing connection: "+ex.getMessage());
        }
        
        
    }
    
    
    
     
    
    
}
