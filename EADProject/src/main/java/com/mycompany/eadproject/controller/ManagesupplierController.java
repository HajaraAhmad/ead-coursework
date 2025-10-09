package com.mycompany.eadproject.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.eadproject.models.Databaseconnector;
import com.mycompany.eadproject.view.Managesupplierpanel;

public class ManagesupplierController {
    public Managesupplierpanel pannel;
    public JTable table;

    public ManagesupplierController(Managesupplierpanel pannel, JTable table){
        this.pannel = pannel;
        this.table = table;
    }

    public void populateTable(){
        Databaseconnector dbc = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
        String[][] test = dbc.selectSupplier();  

        for (int i = 0; i < test.length; i++) { // Loop through rows
            for (int j = 0; j < test[i].length; j++) { // Loop through columns
                System.out.print(test[i][j] + " "); // Print each item in the row
            }
            System.out.println(); // Move to next line after each row
        }

        // Define column names
        String[] columnNames = {"Supplier Number", "Tell No", "Supplier Name"};

        // Create a table model and set it to the JTable
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // 0 rows initially

        // Add rows from the database
        for (String[] row : test) {
            model.addRow(row); // Each row is a String[]
        }

        table.setModel(model); // Update JTable
        table.setColumnSelectionAllowed(true);
    }
}

