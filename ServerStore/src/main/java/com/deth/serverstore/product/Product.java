/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.product;

/**
 * The Product class represents a product in the inventory.
 * 
 * It includes attributes for the product name, description, price, 
 * and quantity, along with getter and setter methods for each attribute.
 * 
 * @author david
 */
public class Product {
    
    private int quantity;                // Quantity of the product available
    private String productName;          // Name of the product
    private String productDescription;   // Description of the product
    private float productPrice;          // Price of the product

    /**
     * Constructs a new Product instance with the given attributes.
     * 
     * @param productName        The name of the product.
     * @param productDescription A brief description of the product.
     * @param productPrice       The price of the product.
     * @param quantity           The available quantity of the product.
     */
    public Product(String productName, String productDescription, float productPrice, int quantity) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    /**
     * Gets the name of the product.
     * 
     * @return The product name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets a new name for the product.
     * 
     * @param productName The new product name.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets the description of the product.
     * 
     * @return The product description.
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets a new description for the product.
     * 
     * @param productDescription The new product description.
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Gets the price of the product.
     * 
     * @return The product price.
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * Sets a new price for the product.
     * 
     * @param productPrice The new product price.
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Gets the available quantity of the product.
     * 
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets a new quantity for the product.
     * 
     * @param quantity The new quantity of the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
