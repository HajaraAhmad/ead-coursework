package com.mycompany.eadproject.models;

import java.sql.SQLException;

public class Inventorymodel{
    private String id;
    private String name;
    private String supplier_no;
    private double price;
    private int quantity;


    public Inventorymodel(String id, String name, String supplier_no, double price,int quantity){
        this.id=id;
        this.name=name;
        this.supplier_no=supplier_no;
        this.price=price;
        this.quantity=quantity;
    }

    public double getprice(){
        return this.price;
    }

    public String getid(){
        return  this.id;
    }

    public String getname(){
        return  this.name;
    }

    public String getsupplierno(){
        return supplier_no;
    }

    public int getquantity(){
        return quantity;
    }


    public void enterProduct(){
        try{
        Databaseconnector db = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
        String result=db.addProduct(this.id, this.name, this.supplier_no, this.price, this.quantity);

       

    }catch(Exception e){
        System.out.println(e);
    }
    }

    // Inside your Inventorymodel class

/**
 * Executes the database update operation using the current state of the model's fields.
 */
    public void updateProduct(){
    try{
        // 1. Establish database connection
        Databaseconnector db = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
        
        // 2. Call the updateProduct method from the connector
        String result = db.updateProduct(
            this.id, 
            this.name, 
            this.supplier_no, 
            this.price, 
            this.quantity
        );

    } catch(SQLException e){
        System.out.println("Database Error during product update: " + e.getMessage());
    } catch(Exception e){
        System.out.println("General Error during product update: " + e.getMessage());
    }
    }


}




