/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.productmanager;

import com.deth.serverstore.product.Product;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The ProductManager class handles the creation, deletion, editing, 
 * and searching of products in the inventory.
 * 
 * It maintains a list of products and provides methods to manage them efficiently.
 * 
 * @author david
 */
public class ProductManager {
    private ArrayList<Product> products; // List to store products

    /**
     * Constructs a new ProductManager instance with an empty product list.
     */
    public ProductManager() {
        products = new ArrayList<>();
    }

    /**
     * Creates a new product and adds it to the inventory.
     * 
     * @param response An array containing product details:
     *                 [0] action, [1] client IP, [2] productName, 
     *                 [3] productDescription, [4] productPrice, [5] quantity
     * @return A message indicating success or failure.
     */
    public String createProduct(String[] response) {
        String message = "";

        // Check if product already exists
        if (verifyProduct(response[2])) {
            message = "Error... el producto: " + response[2] + " ya existe";
            return message;
        }

        // Extract product details
        String productName = response[2];
        String productDescription = response[3];
        float productPrice = Float.parseFloat(response[4]);
        int quantity = Integer.parseInt(response[5]);

        // Create new product and add to the list
        Product p1 = new Product(productName, productDescription, productPrice, quantity);
        products.add(p1);
        
        return "Producto creado con éxito";
    }

    /**
     * Deletes a product from the inventory.
     * 
     * @param name The name of the product to delete.
     * @return A message indicating success or failure.
     */
    public String deleteProduct(String name) {
        String message = "";

        // Check if product exists
        if (!verifyProduct(name)) {
            message = "Error... el producto: " + name + " no existe";
            return message;
        }

        // Use iterator to safely remove product while iterating
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductName().equals(name)) {
                iterator.remove();
                return "Producto " + name + " eliminado satisfactoriamente";
            }
        }

        return message;
    }

    /**
     * Edits an existing product in the inventory.
     * 
     * @param response An array containing product update details:
     *                 [0] action, [1] client IP, [2] newProductName, 
     *                 [3] oldProductName, [4] productDescription, 
     *                 [5] productPrice, [6] quantity
     * @return A message indicating success or failure.
     */
    public String editProduct(String[] response) {
        String newName = response[2];
        String oldName = response[3];
        String newDescription = response[4];
        float newPrice = Float.parseFloat(response[5]);
        int newQuantity = Integer.parseInt(response[6]);

        for (Product product : products) {
            if (product.getProductName().equals(oldName)) {
                // Check if the new name already exists in another product
                if (!oldName.equals(newName) && verifyProduct(newName)) {
                    return "Error... el producto: " + newName + " ya existe";
                }

                // Update product details
                product.setProductName(newName);
                product.setProductDescription(newDescription);
                product.setProductPrice(newPrice);
                product.setQuantity(newQuantity);

                return "Producto actualizado correctamente";
            }
        }

        return "Error... el producto: " + oldName + " no existe";
    }

    /**
     * Checks if a product exists in the inventory.
     * 
     * @param name The name of the product to check.
     * @return True if the product exists, false otherwise.
     */
    public boolean verifyProduct(String name) {
        for (Product product : products) {
            if (product.getProductName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for a product in the inventory.
     * 
     * @param name The name of the product to search for.
     * @return The Product object if found, otherwise null.
     */
    public Product searchProduct(String name) {
        for (Product product : products) {
            if (product.getProductName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Gets the list of products in the inventory.
     * 
     * @return The ArrayList of products.
     */
    public ArrayList<Product> getProducts() {
        return products;
    }
}
