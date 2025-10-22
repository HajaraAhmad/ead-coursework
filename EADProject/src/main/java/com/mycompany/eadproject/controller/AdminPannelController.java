package com.mycompany.eadproject.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.eadproject.models.DatabaseConnector;
import com.mycompany.eadproject.view.Adminpanel;

public class AdminPannelController {
    public Adminpanel pannel;
    public JTable table;

    public AdminPannelController(Adminpanel pannel, JTable table){
        this.pannel = pannel;
        this.table = table;
    }

    public void populateTable(){
        DatabaseConnector dbc = new DatabaseConnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
        String[][] test = dbc.selectsproducts();  

        for (int i = 0; i < test.length; i++) { // Loop through rows
            for (int j = 0; j < test[i].length; j++) { // Loop through columns
                System.out.print(test[i][j] + " "); // Print each item in the row
            }
            System.out.println(); // Move to next line after each row
        }

        // Define column names
        String[] columnNames = {"Product ID", "Product Name", "Supplier Number", "Product Price", "Product Quantity"};

        // Create a table model and set it to the JTable
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // 0 rows initially

        // Add rows from the database
        for (String[] row : test) {
            model.addRow(row); // Each row is a String[]
        }

        table.setModel(model); // Update JTable
        table.setColumnSelectionAllowed(true);
    }
    public void AddBtnClicked(){
    }

    
}
