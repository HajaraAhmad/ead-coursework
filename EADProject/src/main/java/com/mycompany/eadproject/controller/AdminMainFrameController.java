// ============================================================================
// 1. NEW AdminController.java - Handles navigation and admin logic
// ============================================================================
package com.mycompany.eadproject.controller;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * AdminController - Manages admin dashboard navigation and actions
 * Coordinates between AdminMainFrame (view) and business logic
 */
public class AdminMainFrameController {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private String currentView;

    public AdminMainFrameController(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.currentView = "home";
    }

    /**
     * Navigate to inventory management view
     */
    public void navigateToInventory() {
        try {
            cardLayout.show(contentPanel, "inventory");
            currentView = "inventory";
            logNavigation("Inventory Management");
        } catch (Exception e) {
            handleError("Failed to load Inventory view", e);
        }
    }

    /**
     * Navigate to supplier management view
     */
    public void navigateToSuppliers() {
        try {
            cardLayout.show(contentPanel, "suppliers");
            currentView = "suppliers";
            logNavigation("Supplier Management");
        } catch (Exception e) {
            handleError("Failed to load Suppliers view", e);
        }
    }

    /**
     * Navigate to home/dashboard view
     */
    public void navigateToHome() {
        try {
            cardLayout.show(contentPanel, "home");
            currentView = "home";
            logNavigation("Home Dashboard");
        } catch (Exception e) {
            handleError("Failed to load Home view", e);
        }
    }

    /**
     * Handle logout action
     */
    public void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            logAction("User logged out");
            // You can add session cleanup here if needed
            System.exit(0);
        }
    }

    /**
     * Get current view name
     */
    public String getCurrentView() {
        return currentView;
    }

    /**
     * Log navigation action
     */
    private void logNavigation(String viewName) {
        System.out.println("📍 Navigated to: " + viewName);
    }

    /**
     * Log general action
     */
    private void logAction(String action) {
        System.out.println("🔹 Action: " + action);
    }

    /**
     * Handle errors with user-friendly messages
     */
    private void handleError(String message, Exception e) {
        System.err.println("❌ Error: " + message);
        e.printStackTrace();
        JOptionPane.showMessageDialog(
            null,
            message + "\nPlease contact support if the issue persists.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Validate admin access (example - extend based on your needs)
     */
    public boolean validateAdminAccess() {
        // Add your authentication/authorization logic here
        // For now, returns true
        return true;
    }
}