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

    public boolean createProduct(String[] response) {
        String productName=response[2];
        String productDescription=response[3];
        float productPrice=Float.parseFloat(response[4]);
        int quantity=Integer.parseInt(response[5]);
        
        Product p1 = new Product(productName, productDescription, productPrice, quantity);
        products.add(p1);
        return true;
    }

    public boolean deleteProduct(String name) {
        // Método correcto 
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductName().equals(name)) {
                iterator.remove(); // 
                return true;
            }
        }
        return false;
    }

    public Product searchProduct(String name) {
        for (Product product : products) {
            if (product.getProductName().equals(name)) {
                return product;
            }
        }
        return null; // 
    }
    
    //public boolean editProduct(){}
}
