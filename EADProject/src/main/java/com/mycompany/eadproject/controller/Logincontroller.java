package com.mycompany.eadproject.controller;
import com.mycompany.eadproject.models.Loginmodel;
import com.mycompany.eadproject.view.Loginpage;

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
        login.loginFlow();
    }
}
