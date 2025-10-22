package com.mycompany.eadproject.otherobjects;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Invoice {
    private HashMap<Product, Integer> items = new HashMap<>();
    private String invoice_number;
    private double total;
    private String invoice_date;

    public Invoice(String invoice_number) {
        this.invoice_number = invoice_number;
        this.total = 0.0;
        this.invoice_date = LocalDate.now().toString();
    }

    /**
     * Add a product to the invoice with quantity
     */
    public void addItem(Product product, int quantity) {
        if (items.containsKey(product)) {
            // Update quantity if product already exists
            int existingQty = items.get(product);
            items.put(product, existingQty + quantity);
        } else {
            items.put(product, quantity);
        }
        updateTotal(product.getProductPrice() * quantity);
    }

    /**
     * Remove a product from the invoice
     */
    public void removeItem(Product product) {
        if (items.containsKey(product)) {
            int quantity = items.get(product);
            updateTotal(-(product.getProductPrice() * quantity));
            items.remove(product);
        }
    }

    /**
     * Update the total price
     */
    private void updateTotal(double incrementPrice) {
        this.total += incrementPrice;
    }

    /**
     * Convert invoice items to 2D array for database storage
     * Format: [productId, quantity]
     */
    public String[][] toProductArray() {
        String[][] productArray = new String[items.size()][2];
        int i = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            productArray[i][0] = entry.getKey().getProductId();
            productArray[i][1] = String.valueOf(entry.getValue());
            i++;
        }
        return productArray;
    }

    // Getters
    public String getInvoiceNumber() {
        return invoice_number;
    }

    public double getTotal() {
        return total;
    }

    public String getInvoiceDate() {
        return invoice_date;
    }

    public HashMap<Product, Integer> getItems() {
        return items;
    }

    public void setInvoiceDate(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}