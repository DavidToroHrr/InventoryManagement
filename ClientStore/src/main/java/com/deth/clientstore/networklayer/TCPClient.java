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
 * @author breaze
 */
public class TCPClient {
    private String serverAddress;
    private int port;
    private SSLSocket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public TCPClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }
    
    public void connect() throws IOException{
        //clientSocket = new (serverAddress, port);
        SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        clientSocket = (SSLSocket)socketFactory.createSocket(serverAddress, port);
        System.out.println("Connection established");
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
    }
    
    public String sendMessage(String name, String lastName){
        String response = "Error";
        try {
            connect();
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(":").append(lastName);
            String message = sb.toString();
            System.out.println("Sending: "+message);
            outputStream.writeUTF(message);
            response = inputStream.readUTF();
            System.out.println("Response: "+response);
        } catch (IOException ex) {
            System.out.println("Client error: "+ex.getMessage());
        } finally {
            closeConnection();
        }
        return response;
    }
    
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
