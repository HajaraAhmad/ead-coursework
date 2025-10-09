package com.mycompany.eadproject.view;

import com.mycompany.eadproject.models.Posmodel;
import com.mycompany.eadproject.models.Services.PosServices;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Invoice extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(Invoice.class.getName());

    DefaultTableModel model;
    ArrayList<Posmodel> cart = new ArrayList<>();
    PosServices posService = new PosServices();

    public Invoice() {
        initComponents();
        model = (DefaultTableModel) tblInvoice.getModel();
        model.setColumnIdentifiers(
            new String[] {"Product ID", "Name", "Quantity", "Price", "Subtotal"}
        );
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProductId = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new javax.swing.JTable();
        btnGenerateInvoice = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Product ID");
        jLabel2.setText("Quantity");

        txtProductId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductIdActionPerformed(evt);
            }
        });

        jLabel3.setText("Invoice");

        btnAdd.setText("Add Item");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Qty", "Price", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tblInvoice);

        btnGenerateInvoice.setText("Generate Invoice");
        btnGenerateInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateInvoiceActionPerformed(evt);
            }
        });

        lblTotal.setText("Total Rs: 0.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(btnAdd))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnGenerateInvoice)
                        .addGap(132, 132, 132)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAdd)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerateInvoice)
                    .addComponent(lblTotal))
                .addGap(15, 15, 15))
        );

        pack();
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String productId = txtProductId.getText().trim();
            if (productId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Product ID");
                return;
            }

            int qty;
            try {
                qty = Integer.parseInt(txtQuantity.getText().trim());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Enter a valid quantity");
                return;
            }

            // Use service layer to check stock
            if (!posService.hasEnoughStock(productId, qty)) {
                JOptionPane.showMessageDialog(this, 
                    "Not enough stock for product: " + productId);
                return;
            }

            // Get price and name from service
            double price = posService.getProductPrice(productId);
            if (price < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Product not found: " + productId);
                return;
            }

            String name = posService.getProductName(productId);
            if (name == null) name = "Unknown";

            // Create model object (pure data, no DB logic)
            Posmodel item = new Posmodel(productId, price, qty);
            item.setProductName(name);
            
            double subtotal = item.getSubtotal();
            cart.add(item);
            model.addRow(new Object[]{
                productId, name, qty, price, subtotal
            });

            // Update total
            double total = posService.calculateTotal(cart);
            lblTotal.setText("Total Rs: " + total);
            
            // Clear inputs
            txtProductId.setText("");
            txtQuantity.setText("");
            txtProductId.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error adding item: " + e.getMessage());
        }
    }

    private void btnGenerateInvoiceActionPerformed(java.awt.event.ActionEvent evt) {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No items to generate invoice!");
            return;
        }

        try {
            // Use service to generate invoice
            String invoiceNumber = posService.generateAndSaveInvoice(cart);

            if (invoiceNumber != null) {
                JOptionPane.showMessageDialog(this, 
                    "Invoice generated successfully!\n" +
                    "Invoice #: " + invoiceNumber);
                
                // Display the invoice
                posService.displayInvoice(invoiceNumber);

                // Clear UI
                model.setRowCount(0);
                cart.clear();
                lblTotal.setText("Total Rs: 0.0");
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Failed to generate invoice!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error generating invoice: " + e.getMessage());
        }
    }

    private void txtProductIdActionPerformed(java.awt.event.ActionEvent evt) {
        // Optional: Allow pressing Enter to add item
        btnAddActionPerformed(null);
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnGenerateInvoice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblInvoice;
    private javax.swing.JTextField txtProductId;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration
}