/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.messagemanager;

import com.deth.serverstore.product.Product;
import java.util.ArrayList;

/**
 * The MessageManager class is responsible for processing messages 
 * received from clients and formatting inventory data.
 * 
 * It provides methods to parse client messages and generate inventory 
 * messages in a structured format.
 * 
 * @author david
 */
public class MessageManager {

    /**
     * Parses a client message into its components using ":" as a separator.
     * 
     * @param clientMessage The message received from the client.
     * @return An array of strings containing the separated message components.
     */
    public String[] buildMessage(String clientMessage) {
        // Trim leading and trailing spaces from the message
        clientMessage = clientMessage.trim();

        // Split the message into parts based on the ":" delimiter
        return clientMessage.split(":");
    }

    /**
     * Constructs an inventory message from a list of products.
     * The inventory is formatted as "product1,price1|product2,price2|...".
     * 
     * @param products The list of products to be included in the inventory.
     * @return A formatted inventory string or an error message if the list is empty.
     */
    public String buildInventory(ArrayList<Product> products) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        // Check if there are products in the inventory
        if (!products.isEmpty()) {
            for (Product product : products) {
                count++;
                
                // Append product name and price
                sb.append(product.getProductName())
                  .append(",")
                  .append(Float.toString(product.getProductPrice()));

                // Append "|" only if it's not the last product
                if (count < products.size()) {
                    sb.append("|");
                }
            }

            // Convert StringBuilder to String and return the formatted inventory
            return sb.toString();
        }

        // Return error message if there are no products
        return "ERROR... there are no products in the inventory.";
    }
}
