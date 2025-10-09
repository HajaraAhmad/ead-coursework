package com.mycompany.eadproject.models;

/**
 * Posmodel - Represents a single line item in a POS transaction
 * This is a DATA MODEL only (no database logic)
 */
public class Posmodel {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    
    public Posmodel(String productId, double price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Business logic (stays with the model)
    public double getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Posmodel{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subtotal=" + getSubtotal() +
                '}';
    }
}

