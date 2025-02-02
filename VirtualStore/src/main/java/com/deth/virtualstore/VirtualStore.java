/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deth.virtualstore;

import com.deth.gui.UserInterface;
import com.deth.productmamager.Product;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class VirtualStore {
    private ArrayList<Product> products;

    public VirtualStore() {
        products=new ArrayList<>();
    }
    
    public static void main(String[] args) {
        
        
        
        System.out.println("Hello World!");
        UserInterface userInterface=new UserInterface();
        userInterface.setVisible(true);
        userInterface.setLocationRelativeTo(null);
        
        
        
    }
}
