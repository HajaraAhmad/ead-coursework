/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.eadproject;
import java.util.Scanner;

import com.mycompany.eadproject.models.Databaseconnector;
import com.mycompany.eadproject.models.Posmodel;

/**
 *
 * @author ASUS VIVOBOOK
 */
public class EADProject {

    public static void main(String[] args) {
    /*    
        Scanner sc = new Scanner(System.in);

        String id;
        String name;
        String supplier_no;
        double price;
        int quantity;


        System.out.println("Enter pid");
        id = sc.nextLine();

        System.out.println("Enter name");
        name = sc.nextLine();

        System.out.println("Enter supplier_no");
        supplier_no = sc.nextLine();

        System.out.println("Enter price");
        price = sc.nextDouble();

        System.out.println("Enter qo");
        quantity = sc.nextInt();

        sc.close();

        Inventorymodel inventory = new Inventorymodel(id, name, supplier_no, price, quantity);
        inventory.updateProduct();


        /*String email;
        String password;

        System.out.println("Enter email");
        email = sc.nextLine();

        System.out.println("Enter password");
        password = sc.nextLine();

        sc.close();

        Loginmodel login = new Loginmodel(email, password);
        login.loginFlow();


       /* Databaseconnector database = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
        database.connectDatabase();

       
        database.addProduct("981234","Oranges", "1234", 450,750);
    }*/

   /* 
    Databaseconnector  database = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
    database.connectDatabase();

    // Test invoice generation
    String[][] products = {{"1234567", "5"}};
    String invoiceNum = database.generateInvoice(products, "2025-10-05");
    
    if (invoiceNum != null) {
        database.displayInvoiceFromDB(invoiceNum);
    }
  */


    /* 
    Databaseconnector database = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
     database.connectDatabase();

     database.selectsuppliers();

     */   

     /* 
    //This is posmodal
    Databaseconnector database = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
    database.connectDatabase();

    Scanner sc = new Scanner(System.in);

    
    System.out.println("Enter product ID:");
    String id = sc.nextLine();

    System.out.println("Enter quantity:");
    int quantity = sc.nextInt();



    sc.close();


      Posmodel p = new Posmodel(null, 0.0,0);
      
    p.checkStock(id, quantity);

    */

   Scanner sc = new Scanner(System.in);

    
    System.out.print("How many products? ");
    int count = sc.nextInt();
    sc.nextLine();

    String[][] products = new String[count][2];

    for (int i = 0; i < count; i++) {
        System.out.println("\nEnter details for product " + (i + 1) + ":");
        System.out.print("Product ID: ");
        String productId = sc.nextLine();
        System.out.print("Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        products[i][0] = productId;
        products[i][1] = String.valueOf(quantity);
    }

    String invoiceDate = "2025-10-07"; // you can dynamically generate this too

    Posmodel pos = new Posmodel(null, 0.0, 0);
    pos.generateInv(products, invoiceDate);
    
    
   // Loginpage loginpage = new Loginpage();



    




    }

}

