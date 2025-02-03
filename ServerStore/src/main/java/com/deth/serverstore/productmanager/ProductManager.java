/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.productmanager;

import com.deth.serverstore.product.Product;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author david
 */



public class ProductManager {
    private ArrayList<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public String createProduct(String[] response) {
        String message="";
        if (verifyProduct(response[2])) {
            message="Error...el producto: "+response[2]+" ya existe";
            return message;
        }
        
        String productName=response[2];
        String productDescription=response[3];
        float productPrice=Float.parseFloat(response[4]);
        int quantity=Integer.parseInt(response[5]);
        
        Product p1 = new Product(productName, productDescription, productPrice, quantity);
        getProducts().add(p1);
        message="Producto creado con éxito";
        return message;
    }

    public String deleteProduct(String name) {
        // Método correcto 
        String message="";
        if (!verifyProduct(name)){
            message=("Error...el producto: "+name+" no existe");
            return message;
        }
        
        Iterator<Product> iterator = getProducts().iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductName().equals(name)) {
                iterator.remove(); // 
                return "producto"+name+"eliminado satisfactoriamente";
            }
        }
        return message;
    }
    
    public boolean verifyProduct(String name){
        for (Product product : getProducts()) {
            if (product.getProductName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Product searchProduct(String name) {
        for (Product product : getProducts()) {
            if (product.getProductName().equals(name)) {
                return product;
            }
        }
        return null; // 
    }
    
    //public boolean editProduct(){}

    /**
     * @return the products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }
}
