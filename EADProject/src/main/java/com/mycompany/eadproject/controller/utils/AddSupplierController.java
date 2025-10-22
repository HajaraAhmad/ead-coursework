package com.mycompany.eadproject.controller.utils;

import com.mycompany.eadproject.models.DatabaseConnector;
import com.mycompany.eadproject.view.utillity.Addsupplier;

public class AddSupplierController {
    Addsupplier pane;

    public AddSupplierController(Addsupplier pane){
        this.pane = pane;
    }

    public void AddBtnClicked(){
        push_to_db();
    }

    public String[] getDetails(){
        String[] items = new String[3];
        items[0] = pane.jTextField2.getText();
        items[1] = pane.jTextField1.getText();
        items[2] = pane.jTextField3.getText(); 

        return items;
    }

    public void push_to_db(){
        try{
            DatabaseConnector conn = new DatabaseConnector("jdbc:mysql://127.0.0.1:3306/supermarket", "root", "ashroff64");
            String[] data = getDetails();
            conn.addSuplier(data[0], data[1], data[2]);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
