package com.mycompany.eadproject.controller;
import com.mycompany.eadproject.models.Loginmodel;
import com.mycompany.eadproject.view.Loginpage;
import com.mycompany.eadproject.view.AdminMainFrame;
import com.mycompany.eadproject.view.Invoice;

public class Logincontroller {
    private Loginmodel login;
    private Loginpage loginpage;

    public Logincontroller(Loginpage loginpage){
        this.loginpage = loginpage;
        this.loginpage.setVisible(true);
    }

    public void enterButtonClicked(){
        String email = this.loginpage.txtemail.getText();
        String password = this.loginpage.txtpassword.getText();
        System.out.println("pass "+password);
        login = new Loginmodel(email, password);
        String role = login.loginFlow();
        System.out.print(role);
        
        if (role.equals("Admin")){
            this.loginpage.setVisible(false);
            AdminMainFrame adminPannel = new AdminMainFrame();
            adminPannel.setVisible(true);
        } else if (role.equals("Cashier")){
            this.loginpage.setVisible(false);
            Invoice invoice = new Invoice();
            invoice.setVisible(true);
        }
    }
}
