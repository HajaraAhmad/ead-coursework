package com.mycompany.eadproject.controller;

import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mycompany.eadproject.models.InvoiceModel;
import com.mycompany.eadproject.otherobjects.Product;

/**
 * InvoiceController - Mediates between InvoiceView and InvoiceModel
 * Handles all user interactions and business logic
 */
public class InvoiceController {
    private InvoiceModel invoiceModel;
    private DefaultTableModel tableModel;

    public InvoiceController(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        this.invoiceModel = new InvoiceModel();
        this.invoiceModel.createNewInvoice();
    }

    /**
     * Handle adding a product to the invoice
     * Returns true if successful, false otherwise
     */
    public boolean handleAddProduct(String productId, String quantityStr) {
        // Validate product ID
        if (productId == null || productId.trim().isEmpty()) {
            showError("Please enter Product ID");
            return false;
        }

        // Validate and parse quantity
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr.trim());
            if (quantity <= 0) {
                showError("Quantity must be greater than 0");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Enter a valid quantity");
            return false;
        }

        // Add product to invoice
        boolean success = invoiceModel.addProductToInvoice(productId.trim(), quantity);

        if (success) {
            refreshTableDisplay();
            return true;
        }
        return false;
    }

    /**
     * Handle generating and saving the invoice
     */
    public boolean handleGenerateInvoice() {
        // Check if invoice is empty
        if (invoiceModel.getCurrentInvoice().isEmpty()) {
            showWarning("No items to generate invoice!");
            return false;
        }

        try {
            // Save invoice
            String invoiceNumber = invoiceModel.saveInvoice();

            if (invoiceNumber != null) {
                showSuccess("Invoice generated successfully!\nInvoice #: " + invoiceNumber);
                
                // Display the invoice in console
                invoiceModel.displayInvoice(invoiceNumber);
                
                // Reset for next invoice
                clearTableDisplay();
                invoiceModel.createNewInvoice();
                return true;
            } else {
                showError("Failed to generate invoice!");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("Error generating invoice: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get current invoice total
     */
    public double getInvoiceTotal() {
        return invoiceModel.getInvoiceTotal();
    }

    /**
     * Refresh the table display from current invoice
     */
    private void refreshTableDisplay() {
        tableModel.setRowCount(0); // Clear existing rows
        
        for (Map.Entry<Product, Integer> entry : 
             invoiceModel.getCurrentInvoice().getItems().entrySet()) {
            
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getProductPrice() * quantity;
            
            tableModel.addRow(new Object[]{
                product.getProductId(),
                product.getProductName(),
                quantity,
                String.format("%.2f", product.getProductPrice()),
                String.format("%.2f", subtotal)
            });
        }
    }

    /**
     * Clear the table display
     */
    private void clearTableDisplay() {
        tableModel.setRowCount(0);
    }

    /**
     * Display error message
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Display warning message
     */
    private void showWarning(String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            "Warning",
            JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Display success message
     */
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Get the invoice model (if needed for direct access)
     */
    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }
}