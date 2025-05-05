
package com.pharmacy.dao;

import com.pharmacy.bean.StoreBean;
import com.pharmacy.utility.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    private Connection connection;

    // Default constructor - connects to DB
    public StoreDAO() {
        this.connection = ConnectionPool.connectDB();
    }

    // Constructor with existing connection (optional)
    public StoreDAO(Connection connection) {
        this.connection = connection;
    }

    // Fetch all stores
    public List<StoreBean> getAllStores() {
        List<StoreBean> stores = new ArrayList<>();
        String sql = "SELECT * FROM stores";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                StoreBean store = new StoreBean();
                store.setStoreId(rs.getInt("store_id"));
                store.setStoreName(rs.getString("store_name"));
                store.setLocation(rs.getString("location"));
                stores.add(store);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }

    // Main method to test fetching stores
    public static void main(String[] args) {
        StoreDAO storeDAO = new StoreDAO();
        List<StoreBean> stores = storeDAO.getAllStores();

        if (stores.isEmpty()) {
            System.out.println("❌ No stores found.");
        } else {
            System.out.println("✅ Stores List:");
            for (StoreBean store : stores) {
                System.out.println("Store ID: " + store.getStoreId());
                System.out.println("Store Name: " + store.getStoreName());
                System.out.println("Location: " + store.getLocation());
                System.out.println("----------------------");
            }
        }
    }
}
