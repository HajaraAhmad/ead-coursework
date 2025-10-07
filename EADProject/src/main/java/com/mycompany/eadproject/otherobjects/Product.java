package com.mycompany.eadproject.otherobjects;

public class Product {
    private String product_id;
    private String product_name;
    private String supplier_number;
    private double product_price;

    public Product(String product_id, String product_name, String supplier_number, double product_price){
        this.product_id=product_id;
        this.product_name=product_name;
        this.supplier_number=supplier_number;
        this.product_price=product_price;
    }

    public double getproductprice(){
        return this.product_price;
    }

    public String getproductid(){
        return  this.product_id;
    }

    public String getproductname(){
        return  this.product_name;
    }


}





