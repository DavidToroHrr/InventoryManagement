/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deth.serverstore.business;

/**
 *
 * @author breaze
 */
public class NamesManager {
    
    public String generateMessage(String name, String lastName){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ")
                .append(name)
                .append(" Last Name: ")
                .append(lastName);
        return sb.toString();
    }
    
}
