package com.mycompany.eadproject.models;

public class Loginmodel {
    private String email;
    private String password;
    


public Loginmodel(String email,String password){
    this.email=email;
    this.password=password;
}

public void loginFlow(){

    try{
        Databaseconnector db = new Databaseconnector("jdbc:mysql://127.0.0.1:3306/users", "root", "ashroff64");
        String result=db.selectUserFromUserEmail(this.email);

        if(result == null){
            System.out.println("User Doesn't Exist");
        }
        else{
            if(password.equals((result))){
                System.out.println("User logged sucessfully");
            }
            else{
                System.out.println("Incorrect password");
            }

        }

    }catch(Exception e){
        System.out.println(e);
    }
}
}