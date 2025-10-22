package com.mycompany.eadproject.controller;
import com.mycompany.eadproject.models.Loginmodel;
import com.mycompany.eadproject.view.Loginpage;
import com.mycompany.eadproject.view.AdminMainFrame;
import com.mycompany.eadproject.view.InvoiceView;

public class LoginController {
    private Loginmodel login;  //Database model for connecting with the User database and actions
    private Loginpage loginpage;

    public LoginController(Loginpage loginpage){
        this.loginpage = loginpage;
        this.loginpage.setVisible(true); //Set the view vissible 
    }

    public void enterButtonClicked(){
        String email = this.loginpage.txtemail.getText();
        String password = this.loginpage.txtpassword.getText();
        login = new Loginmodel(email, password);
        String role = login.loginFlow();
        
        //Setting the view acording to the user Admin:>> Admin Main Frame || Cashier:>>Invoice page
        if (role.equals("Admin")){
            this.loginpage.setVisible(false);
            AdminMainFrame adminPannel = new AdminMainFrame();
            adminPannel.setVisible(true);

        } else if (role.equals("Cashier")){
            this.loginpage.setVisible(false);
            InvoiceView invoice = new InvoiceView();
            invoice.setVisible(true);
        }
    }
}
