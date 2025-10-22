package com.mycompany.eadproject.models;

import javax.swing.JOptionPane;

public class Loginmodel {
    private String email;
    private String password;

    public Loginmodel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String loginFlow() {
        String role = "";
        try {
            DatabaseConnector db = new DatabaseConnector("jdbc:mysql://127.0.0.1:3306/users", "root", "ashroff64");     //Model that handles all the database query operations
            String[] result = db.selectUserFromUserEmail(this.email);

            if (result[0] == null) {
                JOptionPane.showMessageDialog(null, 
                    "User does not exist!", 
                    "Login Failed", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                if (password.equals(result[0])) {
                    role = result[1];
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Incorrect password. Please try again.", 
                        "Login Failed", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "An error occurred while logging in:\n" + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        return role;
    }
}
