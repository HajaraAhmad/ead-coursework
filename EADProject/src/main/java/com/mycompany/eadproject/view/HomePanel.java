package com.mycompany.eadproject.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This panel represents the initial content (home page)
 * that was originally inside jPanel2 of your Admin Dashboard.
 */
public class HomePanel extends JPanel {

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JButton jButton5;
    private JButton jButton6;

    public HomePanel() {
        initComponents();
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

        // -------- Button 5 (Generate Inventory Report) --------
        jButton5 = new JButton("Generate Inventory Report");
        jButton5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jButton5.setBounds(550, 400, 220, 50);
        jButton5.addActionListener(evt -> generateInventoryReport());
        add(jButton5);

        // -------- Button 6 (Generate Stock Report) --------
        jButton6 = new JButton("Generate Stock Report");
        jButton6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jButton6.setBounds(250, 400, 220, 50);
        jButton6.addActionListener(evt -> generateStockReport());
        add(jButton6);
    }

    // ---- Placeholder methods for button actions ----
    private void generateInventoryReport() {
        JOptionPane.showMessageDialog(this, "Generating Inventory Report...");
    }

    private void generateStockReport() {
        JOptionPane.showMessageDialog(this, "Generating Stock Report...");
    }
}


