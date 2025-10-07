package com.mycompany.eadproject.otherobjects;
import java.util.HashMap;

import javax.print.attribute.standard.DateTimeAtCreation;

public class Invoice {
    private HashMap<Product,Integer> hash = new HashMap<Product,Integer>();
    private String invoice_number;
    private double total;
    private String invoice_date;
    private String product_id;
    private String product_name;

    public Invoice( String invoice_number){
        this.invoice_number=invoice_number;
        this.total= 0.0;
    }

    public void addItemList(Product item,int quantity){
        hash.put(item,quantity);

        updatetotal(item.getproductprice()*quantity);

    }

    public void updatetotal(double increment_price){
        this.total+=increment_price;
    }


    public String getInvoiceNumber() {
        return invoice_number;
    }

    public double getTotal() {
        return total;
    }

    public String getInvoiceDate() {
        return invoice_date;
    }

    public void setInvoiceDate(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    

    public HashMap<Product, Integer> getItems() {
        return hash;
    }

    // Display invoice (console version)
    public void displayInvoice() {
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("               SUPERMARKET INVOICE                 ");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Invoice Number: " + invoice_number);
        System.out.println("Date: " + invoice_date);
        System.out.println("───────────────────────────────────────────────────────");
        System.out.printf("%-15s %-20s %-10s %-10s %-10s%n", 
            "Product ID", "Product Name", "Quantity", "Price", "Subtotal");
        System.out.println("───────────────────────────────────────────────────────");

        // Loop through all products
        for (HashMap.Entry<Product, Integer> entry : hash.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getproductprice() * quantity;

            System.out.printf("%-15s %-20s %-10d Rs.%-8.2f Rs.%-8.2f%n",
                product.getproductid(), 
                product.getproductname(),
                quantity,
                product.getproductprice(),
                subtotal
            );
        }

        System.out.println("───────────────────────────────────────────────────────");
        System.out.printf("%-46s Rs.%-8.2f%n", "GRAND TOTAL:", total);
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("           Thank you for shopping with us!             ");
        System.out.println("═══════════════════════════════════════════════════════\n");
    }
}

