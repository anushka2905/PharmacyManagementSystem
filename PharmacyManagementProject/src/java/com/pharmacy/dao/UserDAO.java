
package com.pharmacy.dao;

import com.pharmacy.bean.UserBean;
import com.pharmacy.utility.ConnectionPool;
import java.sql.*;

public class UserDAO {
    private Connection connection;

    // Default Constructor: Connects to DB
    public UserDAO() {
        this.connection = ConnectionPool.connectDB();
    }

    // Constructor with existing connection (optional)
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // 1. Get User by Username and Password
    public UserBean getUserByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserBean(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2. Validate User Credentials
    public boolean validateUser(UserBean user) {
        boolean status = false;
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // 3. Add (Register) New User
    public boolean addUser(UserBean user) {
        String sql = "INSERT INTO Users (username, password, firstName, lastName, email, phone ) VALUES (?, ?, ?, ?, ?, ? )";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // In real apps, HASH the password
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());
           
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0; // Success if at least 1 row inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. MAIN method to test Add and Validate
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // 1. Create a new UserBean
        UserBean newUser = new UserBean();
        newUser.setUsername("john123");
        newUser.setPassword("password123");
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setEmail("john.doe@example.com");
        newUser.setPhone("1234567890");
       

        // 2. Add the new user
//        boolean isUserAdded = userDAO.addUser(newUser);
//
//        if (isUserAdded) {
//            System.out.println("✅ User added successfully!");
//        } else {
//            System.out.println("❌ Failed to add user.");
//        }

//        // 3. Validate the same user login
        boolean isValid = userDAO.validateUser(newUser);

        if (isValid) {
            System.out.println("✅ User login successful after registration!");
        } else {
            System.out.println("❌ User login failed.");
        }
    }
}
