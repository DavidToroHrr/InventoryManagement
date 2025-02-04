/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.deth.gui;
/**
 * Clase que representa la interfaz de usuario de la aplicación.
 * Extiende de javax.swing.JFrame, lo que permite crear una ventana gráfica.
 * 
 * @author david
 */
public class UserInterface extends javax.swing.JFrame {

    
    /**
     * Constructor de la clase UserInterface.
     * Llama al método initComponents() para inicializar los componentes gráficos de la interfaz.
     */
    public UserInterface() {
        initComponents();// Método que inicializa todos los componentes gráficos definidos en el diseño
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addProducts = new javax.swing.JButton();
        deleteProducts = new javax.swing.JButton();
        editProducts = new javax.swing.JButton();
        exportInventory = new javax.swing.JButton();
        searchProduct = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("User Interface");

        addProducts.setText("add products");
        addProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductsActionPerformed(evt);
            }
        });

        deleteProducts.setText("delete products");
        deleteProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductsActionPerformed(evt);
            }
        });

        editProducts.setText("edit products");
        editProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProductsActionPerformed(evt);
            }
        });

        exportInventory.setText("export inventory");
        exportInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportInventoryActionPerformed(evt);
            }
        });

        searchProduct.setText("search products");
        searchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addProducts)
                    .addComponent(editProducts))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteProducts)
                    .addComponent(searchProduct))
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(exportInventory)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addProducts)
                    .addComponent(deleteProducts))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editProducts)
                    .addComponent(searchProduct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(exportInventory)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addProductsActionPerformed

    private void deleteProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteProductsActionPerformed

    private void editProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProductsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editProductsActionPerformed

    private void exportInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportInventoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportInventoryActionPerformed

    private void searchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchProductActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProducts;
    private javax.swing.JButton deleteProducts;
    private javax.swing.JButton editProducts;
    private javax.swing.JButton exportInventory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton searchProduct;
    // End of variables declaration//GEN-END:variables
}
