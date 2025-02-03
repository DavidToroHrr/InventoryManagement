/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.messagemanager;

import com.deth.serverstore.product.Product;
import java.util.ArrayList;

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
    
    public String buildInventory(ArrayList<Product> products){
        StringBuilder sb= new StringBuilder();
        int cont=0;
        
        if (products.size()!=0) {
            
          for (Product product : products) {
               cont+=1;
               sb.append(product.getProductName())
                       .append(",")
                       .append(Float.toString(product.getProductPrice()));
                       
               
               if (cont<products.size()) {
                  sb.append("|");
              }
            }  
          
          String message=sb.toString();
          return message; 
        }
        
        
        return "ERROR...no hay productos en el inventario";
    }
    
    
}
//construimos el mensaje para mandarlo listo a product manager
//todo o hacemos em serverStore
//una vez ya tengamos el mensaje separado debemos:
//crear el log con la operación respectiva
//ejecutar la acción respectiva en product manager
