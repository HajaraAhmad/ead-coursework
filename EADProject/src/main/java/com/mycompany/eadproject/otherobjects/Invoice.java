package com.mycompany.eadproject.otherobjects;
import java.util.HashMap;

public class Invoice {
    private HashMap<Product,Integer> hash = new HashMap<Product,Integer>();
    private String invoice_number;
    private double total;

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
}
