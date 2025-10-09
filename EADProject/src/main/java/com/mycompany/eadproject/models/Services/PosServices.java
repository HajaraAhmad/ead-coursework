package com.mycompany.eadproject.models.Services;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mycompany.eadproject.models.Databaseconnector;
import com.mycompany.eadproject.models.Posmodel;

/**
 * PosService - Handles all POS business logic
 * Separates database operations from the UI
 */
public class PosServices {
    private Databaseconnector db;

    public PosServices() {
        this.db = new Databaseconnector(
            "jdbc:mysql://127.0.0.1:3306/supermarket", 
            "root", 
            "ashroff64"
        );
        try {
            db.connectDatabase();
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    /**
     * Get product price by ID
     */
    public double getProductPrice(String productId) {
        return db.getProductPrice(productId);
    }

    /**
     * Get product name by ID
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
     * Generate invoice in the database
     * Takes cart items and creates invoice + invoice details
     */
    public String generateAndSaveInvoice(ArrayList<Posmodel> cartItems) {
        if (cartItems.isEmpty()) {
            return null; // No items to invoice
        }

        // Convert cart to 2D array format for database method
        String[][] products = new String[cartItems.size()][3];
        for (int i = 0; i < cartItems.size(); i++) {
            Posmodel item = cartItems.get(i);
            products[i][0] = item.getProductId();
            products[i][1] = String.valueOf(item.getPrice());
            products[i][2] = String.valueOf(item.getQuantity());
        }

        String invoiceDate = LocalDate.now().toString();
        String invoiceNumber = db.generateInvoice(products, invoiceDate);

        return invoiceNumber;
    }

    /**
     * Display invoice details (for confirmation/receipt)
     */
    public void displayInvoice(String invoiceNumber) {
        db.displayInvoiceFromDB(invoiceNumber);
    }

    /**
     * Calculate total from cart
     */
    public double calculateTotal(ArrayList<Posmodel> cartItems) {
        double total = 0;
        for (Posmodel item : cartItems) {
            total += item.getSubtotal();
        }
        return total;
    }
}