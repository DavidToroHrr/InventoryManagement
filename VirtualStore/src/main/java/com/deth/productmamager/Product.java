/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.productmamager;

/**
 * Clase que representa un producto en el sistema de gestión de inventarios.
 * Contiene información como el nombre, descripción, precio, cantidad e identificador del producto.
 */
public class Product {
    // Cantidad disponible del producto en stock
    private int quantity;
    
    // Identificador único del producto
    private int id;
    
    // Nombre del producto
    private String productName;
    
    // Descripción del producto
    private String productDescription;
    
    // Precio del producto
    private float productPrice;

    /**
     * Constructor que inicializa un producto con su nombre, descripción y precio.
     * @param productName Nombre del producto
     * @param productDescription Descripción del producto
     * @param productPrice Precio del producto
     */
    public Product(String productName, String productDescription, float productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Establece el nombre del producto.
     * @param productName Nombre del producto
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Obtiene la descripción del producto.
     * @return Descripción del producto
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Establece la descripción del producto.
     * @param productDescription Descripción del producto
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Obtiene el precio del producto.
     * @return Precio del producto
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * Establece el precio del producto.
     * @param productPrice Precio del producto
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Obtiene la cantidad disponible del producto.
     * @return Cantidad en stock del producto
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad disponible del producto.
     * @param quantity Cantidad en stock del producto
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el identificador único del producto.
     * @return ID del producto
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     * @param id ID del producto
     */
    public void setId(int id) {
        this.id = id;
    }
}

