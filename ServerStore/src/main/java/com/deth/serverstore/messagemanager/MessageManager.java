/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.messagemanager;

/**
 *
 * @author david
 */
public class MessageManager {
    public String[] buildMessage(String clientMessage)
    {
        clientMessage = clientMessage.trim();
        String[] parts = clientMessage.split(":");

        return parts;
    }
}
//construimos el mensaje para mandarlo listo a product manager
//todo o hacemos em serverStore
//una vez ya tengamos el mensaje separado debemos:
//crear el log con la operación respectiva
//ejecutar la acción respectiva en product manager
