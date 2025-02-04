/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.productmanager;

import com.deth.serverstore.product.Product;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que gestiona los productos dentro del sistema.
 * Permite crear, eliminar, buscar y verificar productos en la lista de productos disponibles.
 * 
 * @author david
 */
public class ProductManager {
    
    // Lista que almacena los productos disponibles en el sistema
    private ArrayList<Product> products;

    /**
     * Constructor de la clase ProductManager.
     * Inicializa la lista de productos.
     */
    public ProductManager() {
        products = new ArrayList<>();
    }

    /**
     * Crea un nuevo producto y lo agrega a la lista si no existe previamente.
     * 
     * @param response Array de Strings que contiene los datos del producto (nombre, descripción, precio, cantidad).
     * @return Mensaje indicando si la creación del producto fue exitosa o si el producto ya existe.
     */
    public String createProduct(String[] response) {
        String message = "";
        
        // Verifica si el producto ya existe
        if (verifyProduct(response[2])) {
            message = "Error... el producto: " + response[2] + " ya existe";
            return message;
        }
        
        // Extrae los datos del producto desde el array de respuesta
        String productName = response[2];
        String productDescription = response[3];
        float productPrice = Float.parseFloat(response[4]);
        int quantity = Integer.parseInt(response[5]);
        
        // Crea el nuevo producto y lo agrega a la lista
        Product p1 = new Product(productName, productDescription, productPrice, quantity);
        getProducts().add(p1);
        
        message = "Producto creado con éxito";
        return message;
    }

    /**
     * Elimina un producto de la lista si existe.
     * 
     * @param name Nombre del producto a eliminar.
     * @return Mensaje indicando si el producto fue eliminado o si no existe en la lista.
     */
    public String deleteProduct(String name) {
        String message = "";
        
        // Verifica si el producto no existe
        if (!verifyProduct(name)) {
            message = "Error... el producto: " + name + " no existe";
            return message;
        }
        
        // Itera sobre la lista de productos para encontrar y eliminar el producto
        Iterator<Product> iterator = getProducts().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductName().equals(name)) {
                iterator.remove(); // Elimina el producto de la lista
                return "Producto " + name + " eliminado satisfactoriamente";
            }
        }
        return message;
    }
<<<<<<< HEAD
    public String editProduct(String[] response) {
    String newName=response[2];
    String newDescription=response[3];
    float newPrice=Float.parseFloat(response[4]);
    int newQuantity=Integer.parseInt(response[5]);
    for (Product product : products) {
        
        // Verifica si el nuevo nombre ya existe en otro producto
        if (verifyProduct(newName)) {
            return "Error... el producto: " + newName + " ya existe";
        }

        // Actualiza los valores del producto
        product.setProductName(newName);
        product.setProductDescription(newDescription);
        product.setProductPrice(newPrice);
        product.setQuantity(newQuantity);

        return "Producto actualizado correctamente";
        
    }
    return "Error... no se pudo actualizar el producto";
}

    
    public boolean verifyProduct(String name){
=======

    /**
     * Verifica si un producto existe en la lista de productos.
     * 
     * @param name Nombre del producto a verificar.
     * @return true si el producto existe, false en caso contrario.
     */
    public boolean verifyProduct(String name) {
>>>>>>> ac2e508bd3971c9f33da24b860a43099be34ebea
        for (Product product : getProducts()) {
            if (product.getProductName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un producto en la lista y lo devuelve si existe.
     * 
     * @param name Nombre del producto a buscar.
     * @return El producto encontrado o null si no existe.
     */
    public Product searchProduct(String name) {
        for (Product product : getProducts()) {
            if (product.getProductName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    // public boolean editProduct() {} // Método comentado, posiblemente para edición futura.

    /**
     * Obtiene la lista de productos disponibles.
     * 
     * @return Lista de productos.
     */
    public ArrayList<Product> getProducts() {
        return products;
    }
}
