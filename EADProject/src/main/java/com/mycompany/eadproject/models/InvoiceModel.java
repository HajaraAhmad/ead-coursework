package com.mycompany.eadproject.models;

import java.util.Map;

import javax.swing.JOptionPane;

import com.mycompany.eadproject.otherobjects.Invoice;
import com.mycompany.eadproject.otherobjects.Product;

/**
 * InvoiceModel - Handles invoice business logic
 * Creates and manages invoices using Product and Invoice objects
 */
public class InvoiceModel {
    private DatabaseConnector db;
    private Invoice currentInvoice;

    public InvoiceModel() {
        this.db = new DatabaseConnector(
            "jdbc:mysql://127.0.0.1:3306/supermarket",
            "root",
            "ashroff64"
        );
    }

    /**
     * Create a new invoice
     */
    public void createNewInvoice() {
        String invoiceNumber = "INV" + System.currentTimeMillis();
        this.currentInvoice = new Invoice(invoiceNumber);
    }

    /**
     * Get current invoice
     */
    public Invoice getCurrentInvoice() {
        return this.currentInvoice;
    }

    /**
     * Add product to current invoice
     * Fetches product details from database and creates Product object
     */
    public boolean addProductToInvoice(String productId, int quantity) {
        // Check stock availability
        if (!db.checkProductStock(productId, quantity)) {
            JOptionPane.showMessageDialog(
                null,
                "Insufficient stock for product: " + productId,
                "Stock Error",
                JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        // Fetch product details from database
        String productName = db.getProductName(productId);
        double productPrice = db.getProductPrice(productId);

        if (productName == null || productPrice < 0) {
            JOptionPane.showMessageDialog(
                null,
                "Product not found: " + productId,
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        // Create Product object and add to invoice
        Product product = new Product(productId, productName, null, productPrice);
        currentInvoice.addItem(product, quantity);
        return true;
    }

    /**
     * Remove product from current invoice by product ID
     */
    public void removeProductFromInvoice(String productId) {
        for (Product product : currentInvoice.getItems().keySet()) {
            if (product.getProductId().equals(productId)) {
                currentInvoice.removeItem(product);
                break;
            }
        }
    }

    /**
     * Save invoice to database
     */
    public String saveInvoice() {
        if (currentInvoice == null || currentInvoice.isEmpty()) {
            return null;
        }

        // Convert invoice to database format
        String[][] products = currentInvoice.toProductArray();
        String invoiceDate = currentInvoice.getInvoiceDate();
        
        // Save to database
        String invoiceNumber = db.generateInvoice(products, invoiceDate);
        
        return invoiceNumber;
    }

    /**
     * Display invoice (from database)
     */
    public void displayInvoice(String invoiceNumber) {
        db.displayInvoiceFromDB(invoiceNumber);
    }

    /**
     * Get invoice total
     */
    public double getInvoiceTotal() {
        return currentInvoice != null ? currentInvoice.getTotal() : 0.0;
    }

    /**
     * Get product price from database
     */
    public double getProductPrice(String productId) {
        return db.getProductPrice(productId);
    }

    /**
     * Get product name from database
     */
    public String getProductName(String productId) {
        return db.getProductName(productId);
    }

    /**
     * Check if enough stock is available
     */
    public boolean hasEnoughStock(String productId, int quantity) {
        return db.checkProductStock(productId, quantity);
    }

    /**
     * Print current invoice summary (before saving)
     */
    public void printInvoiceSummary() {
        if (currentInvoice == null || currentInvoice.isEmpty()) {
            System.out.println("No items in invoice");
            return;
        }

        System.out.println("\n═══════════════════════════════════════");
        System.out.println("         INVOICE SUMMARY");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Invoice Number: " + currentInvoice.getInvoiceNumber());
        System.out.println("Date: " + currentInvoice.getInvoiceDate());
        System.out.println("───────────────────────────────────────");
        System.out.printf("%-15s %-20s %-8s %-10s%n", "Product ID", "Name", "Qty", "Subtotal");
        System.out.println("───────────────────────────────────────");

        for (Map.Entry<Product, Integer> entry : currentInvoice.getItems().entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double subtotal = p.getProductPrice() * qty;
            System.out.printf("%-15s %-20s %-8d Rs.%.2f%n",
                p.getProductId(),
                p.getProductName(),
                qty,
                subtotal
            );
        }

        System.out.println("───────────────────────────────────────");
        System.out.printf("%-44s Rs.%.2f%n", "TOTAL:", currentInvoice.getTotal());
        System.out.println("═══════════════════════════════════════\n");
    }
}
