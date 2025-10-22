package com.mycompany.eadproject.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mycompany.eadproject.controller.HomePanelController;

/**
 * This panel represents the initial content (home page)
 * that was originally inside jPanel2 of your Admin Dashboard.
 */
public class HomePanel extends JPanel {

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JButton jButton6;
    HomePanelController controller;

    public HomePanel() {
        initComponents();
        this.controller = new HomePanelController(this);
    }

    private void initComponents() {
        // Set background and layout
        setBackground(Color.WHITE);
        setLayout(null);  // using null layout to preserve exact positioning like in NetBeans GUI builder

        // -------- Label 1 (Title) --------
        jLabel1 = new JLabel("Admin DashBoard Home");
        jLabel1.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        jLabel1.setBounds(370, 50, 300, 25);
        add(jLabel1);

        // -------- Label 2 (Welcome text) --------
        jLabel2 = new JLabel("Welcome Admin!");
        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        jLabel2.setBounds(420, 100, 200, 25);
        add(jLabel2);
        
        // -------- Button 6 (Generate Stock Report) --------
        jButton6 = new JButton("Generate Stock Report");
        jButton6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jButton6.setBounds(250, 400, 220, 50);
        jButton6.addActionListener(evt -> this.controller.generateStockReport());
        add(jButton6);
    }
}