package com.mycompany.eadproject.models;

public class Posmodel {
    private String id;
    private double price;
    private int quantity;
    

    public Posmodel(String id, double price,int quantity){
        this.id=id;
        this.price=price;
        this.quantity=quantity;
    }

    public String getId() {
    return id;
    }


     public double getPrice() {
        return price;
    }
    public int getQuantity() {
    return quantity;
   }

    //check price based on id
    public void invoiceflow(String id){
       Databaseconnector db = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
        double price = db.getProductPrice(id);

        System.out.println("Price for product " + id + " is: " + price);

    }

    //check quantity
    public void checkStock(String id, int quantity){
        Databaseconnector db = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
        db.connectDatabase();

       boolean result = db.checkProductStock(id, quantity);

        if(result == true){
            System.out.println("Quantity is available "+id);
        }
        else{
            System.out.println("Quantity is unavailable for "+ id);
        }

    }

    //generate invoice
    public void generateInv(String[][] products, String invoiceDate){
         Databaseconnector db = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
         db.connectDatabase();

         // Step 1: Generate the invoice
         String invoiceNumber = db.generateInvoice(products, invoiceDate);

        // Step 2: Display it if successful
        if (invoiceNumber != null) {
        db.displayInvoiceFromDB(invoiceNumber);
        } else {
        System.out.println("❌ Invoice generation failed.");
        }
    }

  

}

