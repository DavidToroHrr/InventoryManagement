/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.deth.virtualstore;

import com.deth.gui.UserInterface; // Importa la clase UserInterface desde el paquete com.deth.gui
import com.deth.productmamager.Product; // Importa la clase Product desde el paquete com.deth.productmamager
import java.util.ArrayList; // Importa la clase ArrayList de la biblioteca estándar de Java

/**
 * Clase principal que representa la tienda virtual.
 * Contiene una lista de productos y el punto de entrada para ejecutar la aplicación.
 * 
 * @author david
 */
public class VirtualStore {
    // Atributo: lista de productos que se manejan en la tienda virtual
    private ArrayList<Product> products;

    /**
     * Constructor de la clase VirtualStore. 
     * Inicializa el atributo 'products' como un nuevo ArrayList vacío.
     */
    public VirtualStore() {
        // Se crea una nueva ArrayList para almacenar productos
        products = new ArrayList<>();
    }

    /**
     * Método principal (main) que se ejecuta al iniciar la aplicación.
     * Muestra un mensaje en la consola y lanza la interfaz de usuario.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Muestra el mensaje "Hello World!" en la consola
        System.out.println("Hello World!");

        // Crea una instancia de la interfaz de usuario
        UserInterface userInterface = new UserInterface();
        
        // Hace visible la interfaz de usuario
        userInterface.setVisible(true);
        
        // Centra la interfaz de usuario en la pantalla
        userInterface.setLocationRelativeTo(null);
    }
}

