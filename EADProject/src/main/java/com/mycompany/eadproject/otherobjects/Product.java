package com.mycompany.eadproject.otherobjects;

public class Product {
    private String product_id;
    private String product_name;
    private String supplier_number;
    private double product_price;

    public Product(String product_id, String product_name, String supplier_number, double product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.supplier_number = supplier_number;
        this.product_price = product_price;
    }

    // Getters
    public double getProductPrice() {
        return this.product_price;
    }

    public String getProductId() {
        return this.product_id;
    }

    public String getProductName() {
        return this.product_name;
    }

    public String getSupplierNumber() {
        return this.supplier_number;
    }

    // Override equals and hashCode for HashMap usage
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return product_id.equals(product.product_id);
    }

    @Override
    public int hashCode() {
        return product_id.hashCode();
    }
}