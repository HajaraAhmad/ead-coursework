package com.mycompany.eadproject.view;
import java.awt.CardLayout;

import com.mycompany.eadproject.controller.AdminMainFrameController;

/**
 * AdminMainFrame - Admin dashboard view
 * Delegates all business logic to AdminController
 */
public class AdminMainFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(AdminMainFrame.class.getName());
    
    private CardLayout cardLayout;
    private AdminMainFrameController controller;

    public AdminMainFrame() {
        initComponents();
        setupDynamicContent();
        initializeController();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        btnInventory = new javax.swing.JButton();
        btnSuppliers = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 600));

        btnHome.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnHome.setText("🏠 Home");
        btnHome.addActionListener(this::btnHomeActionPerformed);

        btnInventory.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnInventory.setText("📦 Inventory");
        btnInventory.addActionListener(this::btnInventoryActionPerformed);

        btnSuppliers.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnSuppliers.setText("🚚 Suppliers");
        btnSuppliers.addActionListener(this::btnSuppliersActionPerformed);

        btnLogout.setFont(new java.awt.Font("Segoe UI", 2, 12));
        btnLogout.setText("🚪 Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(20, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSuppliers, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLogout))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(80)
                    .addComponent(btnHome)
                    .addGap(30)
                    .addComponent(btnInventory)
                    .addGap(30)
                    .addComponent(btnSuppliers)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                    .addComponent(btnLogout)
                    .addGap(40))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    /**
     * Setup dynamic content panels with CardLayout
     */
    private void setupDynamicContent() {
        cardLayout = new CardLayout();
        jPanel2.setLayout(cardLayout);

        // Add your page panels
        jPanel2.add(new HomePanel(), "home");
        jPanel2.add(new Managesupplierpanel(), "suppliers");
        jPanel2.add(new Adminpanel(), "inventory");

        cardLayout.show(jPanel2, "home"); // show home on start
    }

    /**
     * Initialize controller after UI is set up
     */
    private void initializeController() {
        controller = new AdminMainFrameController(cardLayout, jPanel2);
    }

    // ========== Button Action Handlers ==========

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {
        controller.navigateToHome();
    }

    private void btnInventoryActionPerformed(java.awt.event.ActionEvent evt) {
        controller.navigateToInventory();
    }

    private void btnSuppliersActionPerformed(java.awt.event.ActionEvent evt) {
        controller.navigateToSuppliers();
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        controller.handleLogout();
    }

    // Variables declaration
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnInventory;
    private javax.swing.JButton btnSuppliers;
    private javax.swing.JButton btnLogout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration
}

