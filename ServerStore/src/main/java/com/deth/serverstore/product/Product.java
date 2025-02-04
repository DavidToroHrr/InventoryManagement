/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.product;

/**
 * Clase que representa un producto dentro del sistema.
 * Contiene información sobre el nombre, descripción, precio, cantidad y un identificador único.
 * 
 * @author david
 */
public class Product {
    
<<<<<<< HEAD
    
    private int quantity;
    private String productName;
    private String productDescription;
    private float productPrice;
=======
    // Atributos de la clase Product
    private int quantity; // Cantidad disponible del producto
    private int id; // Identificador único del producto
    private String productName; // Nombre del producto
    private String productDescription; // Descripción del producto
    private float productPrice; // Precio del producto
>>>>>>> ac2e508bd3971c9f33da24b860a43099be34ebea

    /**
     * Constructor de la clase Product.
     * 
     * @param productName Nombre del producto.
     * @param productDescription Descripción del producto.
     * @param productPrice Precio del producto.
     * @param quantity Cantidad disponible en stock.
     */
    public Product(String productName, String productDescription, float productPrice, int quantity) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Establece el nombre del producto.
     * @param productName Nuevo nombre del producto.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Obtiene la descripción del producto.
     * @return Descripción del producto.
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Establece la descripción del producto.
     * @param productDescription Nueva descripción del producto.
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Obtiene el precio del producto.
     * @return Precio del producto.
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * Establece el precio del producto.
     * @param productPrice Nuevo precio del producto.
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Obtiene la cantidad disponible del producto.
     * @return Cantidad en stock del producto.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad disponible del producto.
     * @param quantity Nueva cantidad en stock.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

<<<<<<< HEAD
 
=======
    /**
     * Obtiene el identificador único del producto.
     * @return ID del producto.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     * @param id Nuevo ID del producto.
     */
    public void setId(int id) {
        this.id = id;
    }
>>>>>>> ac2e508bd3971c9f33da24b860a43099be34ebea
}
