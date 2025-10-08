package com.mycompany.eadproject.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Databaseconnector {
    private String url;
    private String username;
    private String password;

    private Connection conn;

    public Databaseconnector(String url, String username,String password){
        this.url=url;
        this.username=username;
        this.password=password;
        connectDatabase();
    }


    public void connectDatabase(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Connected to MySQL!");
        }
        catch(Exception e){
            System.out.println(e);
        }
      }

      
      public String selectUserFromUserEmail(String email){
        String result = null;
        try{
             String query = "SELECT * FROM User WHERE user_email = ?";

             PreparedStatement selectquery = conn.prepareStatement(query);
             selectquery.setString(1,email);

             ResultSet results = selectquery.executeQuery();

            if(results.next()){
               result = results.getString("user_password");
            }
        }
        catch(Exception e){
             return e.toString();
        }
        return result;
      }


      //add product
      public String addProduct(String product_id,String product_name, String supplier_number, double product_price, int product_quantity){
        try {
            String query = "INSERT INTO product (product_id, product_name, supplier_number, product_price,product_quantity) values (?,?,?,?,?)";

            PreparedStatement insertproduct = conn.prepareStatement(query);
            insertproduct.setString(1,product_id);
            insertproduct.setString(2,product_name);
            insertproduct.setString(3,supplier_number);
            insertproduct.setDouble(4, product_price);
            insertproduct.setInt(5, product_quantity);

            int results = insertproduct.executeUpdate();



            System.out.println("Entered Successfully!");

        } catch(Exception e) {
            System.out.println(e);
        }
        return product_id;
      }


    //update product
    // Inside your Databaseconnector class


    public String updateProduct(String product_id, String product_name, String supplier_number, double product_price, int product_quantity) throws SQLException {
    
    // The query sets new values for all four columns.
        String query = "UPDATE product SET product_name = ?, supplier_number = ?, product_price = ?, product_quantity = ? WHERE product_id = ?";

        try (PreparedStatement updateProduct = conn.prepareStatement(query)) {
        
        // Set new values for the columns to be updated
        updateProduct.setString(1, product_name);
        updateProduct.setString(2, supplier_number);
        updateProduct.setDouble(3, product_price);
        updateProduct.setInt(4, product_quantity);
        
        // Identify the row to update using the product_id (WHERE clause)
        updateProduct.setString(5, product_id); 

        int results = updateProduct.executeUpdate();

        if (results > 0) {
            System.out.println("Product Updated Successfully! ID: " + product_id);
        } else {
            System.out.println("Update failed: No product found with ID " + product_id + " or no changes were made.");
        }

         } catch(SQLException e) {
        // IMPORTANT: Re-throw the exception to notify the calling code (Inventorymodel)
        throw e; 
         }
        return product_id;
    }

      //when invoice created product updated
      // --- Fetch Product Price ---
public double getProductPrice(String productId) {
    double price = -1; // default if not found
    String sql = "SELECT product_price FROM product WHERE product_id = ?";
    try {
        if (conn == null || conn.isClosed()) {
            connectDatabase();
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            price = rs.getDouble("product_price");
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        System.out.println("Error in getProductPrice: " + e.getMessage());
    }
    return price;
}

// --- Fetch Product Name ---
public String getProductName(String productId) {
    String name = null;
    String sql = "SELECT product_name FROM product WHERE product_id = ?";
    try {
        if (conn == null || conn.isClosed()) {
            connectDatabase();
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            name = rs.getString("product_name");
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        System.out.println("Error in getProductName: " + e.getMessage());
    }
    return name;
}






    // Method 2: Check if product has enough stock
    public boolean checkProductStock(String product_id, int requestedQuantity) {
    try {
        String query = "SELECT product_quantity FROM Product WHERE product_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, product_id);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            int availableStock = rs.getInt("product_quantity");
             if (availableStock >= requestedQuantity) {
                System.out.println("✅ Stock available: " + availableStock);
                return true;
            } else {
                System.out.println("❌ Not enough stock. Available: " + availableStock);
                return false;
            }
        } else {
            System.out.println("❌ Product not found: " + product_id);
            return false;
        }
        
    }catch (Exception e) {
        System.out.println("Error checking stock: " + e);
    }
    return false;
    }
    


    public String generateInvoice(String[][] products, String invoiceDate) {
    try {
        // Start transaction for data consistency
        conn.setAutoCommit(false);
        
        // Generate unique invoice number
        String invoiceNumber = "INV" + System.currentTimeMillis();
        
        // Calculate total price
        double totalPrice = 0.0;
        
        // Validate all products first
        for (String[] product : products) {
            String productId = product[0];
            int quantity = Integer.parseInt(product[1]);
            
            if (!checkProductStock(productId, quantity)) {
                System.out.println("❌ Insufficient stock for product: " + productId);
                conn.rollback();
                conn.setAutoCommit(true);
                return null;
            }
            
            double price = getProductPrice(productId);
            totalPrice += (price * quantity);
        }
        
        // Step 1: Insert into Invoice table
        String invoiceQuery = "INSERT INTO Invoice (invoice_number, total, invoice_date) VALUES (?, ?, ?)";
        PreparedStatement invoiceStmt = conn.prepareStatement(invoiceQuery);
        invoiceStmt.setString(1, invoiceNumber);
        invoiceStmt.setDouble(2, totalPrice);
        invoiceStmt.setString(3, invoiceDate);
        invoiceStmt.executeUpdate();
        System.out.println("✅ Invoice created: " + invoiceNumber);
        
        // Step 2: Insert into Invoice_Details and update Product quantity
        for (String[] product : products) {
            String productId = product[0];
            int quantity = Integer.parseInt(product[1]);
            
            // Insert into Invoice_Details
            String detailsQuery = "INSERT INTO Invoice_Details (product_id, invoice_number, quantity) VALUES (?, ?, ?)";
            PreparedStatement detailsStmt = conn.prepareStatement(detailsQuery);
            detailsStmt.setString(1, productId);
            detailsStmt.setString(2, invoiceNumber);
            detailsStmt.setInt(3, quantity);
            detailsStmt.executeUpdate();
            System.out.println("✅ Added to Invoice_Details: " + productId);
            
            // Update Product quantity (decrement)
            String updateQuery = "UPDATE Product SET product_quantity = product_quantity - ? WHERE product_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, productId);
            updateStmt.executeUpdate();
            System.out.println("✅ Product quantity decremented: " + productId);
        }
        
        // Commit transaction
        conn.commit();
        conn.setAutoCommit(true);
        
        System.out.println("\n🎉 Invoice generated successfully!");
        return invoiceNumber;
        
       } catch (Exception e) {
        try {
            conn.rollback();
            conn.setAutoCommit(true);
            System.out.println("❌ Transaction rolled back: " + e);
        } catch (Exception rollbackEx) {
            System.out.println("❌ Rollback error: " + rollbackEx);
        }
        }
    return null;
}

    // Add this to Databaseconnector.java

public void displayInvoiceFromDB(String invoiceNumber) {
    try {
        String query = "SELECT " +
                       "i.invoice_number, " +
                       "i.total, " +
                       "i.invoice_date, " +
                       "id.product_id, " +
                       "p.product_name, " +
                       "id.quantity, " +
                       "p.product_price, " +
                       "(id.quantity * p.product_price) AS subtotal " +
                       "FROM Invoice i " +
                       "JOIN Invoice_Details id ON i.invoice_number = id.invoice_number " +
                       "JOIN Product p ON id.product_id = p.product_id " +
                       "WHERE i.invoice_number = ?";
        
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, invoiceNumber);
        ResultSet rs = stmt.executeQuery();
        
        System.out.println("\n");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("                    SUPERMARKET INVOICE                ");
        System.out.println("═══════════════════════════════════════════════════════");
        
        boolean hasData = false;
        String date = "";
        double grandTotal = 0.0;
        
        while (rs.next()) {
            if (!hasData) {
                System.out.println("Invoice Number: " + rs.getString("invoice_number"));
                date = rs.getString("invoice_date");
                System.out.println("Date: " + date);
                grandTotal = rs.getDouble("total");
                System.out.println("───────────────────────────────────────────────────────");
                System.out.printf("%-15s %-20s %-10s %-10s %-10s%n", 
                    "Product ID", "Product Name", "Quantity", "Price", "Subtotal");
                System.out.println("───────────────────────────────────────────────────────");
                hasData = true;
            }
            
            System.out.printf("%-15s %-20s %-10d Rs.%-8.2f Rs.%-8.2f%n",
                rs.getString("product_id"),
                rs.getString("product_name"),
                rs.getInt("quantity"),
                rs.getDouble("product_price"),
                rs.getDouble("subtotal")
            );
        }
        
        if (hasData) {
            System.out.println("───────────────────────────────────────────────────────");
            System.out.printf("%-46s Rs.%-8.2f%n", "GRAND TOTAL:", grandTotal);
            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println("           Thank you for shopping with us!             ");
            System.out.println("═══════════════════════════════════════════════════════\n");
        } else {
            System.out.println("❌ Invoice not found: " + invoiceNumber);
        }
        
        } catch (Exception e) {
            System.out.println("Error displaying invoice: " + e);
        }
    }

    //view all suppliers
    public void selectsuppliers(){
        try{
             String query = "SELECT * FROM supplier";

             PreparedStatement selectquery = conn.prepareStatement(query);

             ResultSet results = selectquery.executeQuery();
             while (results.next()) {
                System.out.println(results.getString("supplier_number") + " | " + results.getString("supplier_name") + " | " + results.getString("supplier_tellno"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
      }




    //View all products
    public String[][] selectsproducts() {
        String[][] products = new String[0][];
        try {
            int num_of_rows = getRows();
            products = new String[num_of_rows][5];

            String query = "SELECT * FROM product";
            PreparedStatement selectquery = conn.prepareStatement(query);
            ResultSet results = selectquery.executeQuery();

            int i = 0;
            while (results.next() && i < num_of_rows) {
                products[i][0] = results.getString("product_id");
                products[i][1] = results.getString("product_name");
                products[i][2] = results.getString("supplier_number");
                products[i][3] = String.valueOf(results.getDouble("product_price"));
                products[i][4] = String.valueOf(results.getInt("product_quantity"));
                i++;
            }
            results.close();
            selectquery.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return products;
    }

    public int getquantity(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private int getRows(){
        int number_of_rows = 0;
        try{
            String query = "select count(*) as Total_rows from product";

            PreparedStatement selectq = conn.prepareStatement(query);
        
            ResultSet result = selectq.executeQuery();
            while (result.next()){
                number_of_rows = result.getInt("total_rows");
                return number_of_rows;
            }
        }catch(SQLException e){
            System.out.println("SQL Exception");
        }
        return number_of_rows;
    }



}
