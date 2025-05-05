
package com.pharmacy.dao;

import com.pharmacy.bean.AdminBean;
import com.pharmacy.utility.ConnectionPool;

import java.sql.*;

public class AdminDAO {

    public int addAdmin(AdminBean admin) {
        String sql = "INSERT INTO admin (username, firstName, lastName, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getFirstName());
            ps.setString(3, admin.getLastName());
            ps.setString(4, admin.getEmail());
            ps.setString(5, admin.getPhone());
            ps.setString(6, admin.getPassword());

            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public AdminBean getAdminByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection conn = ConnectionPool.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AdminBean admin = new AdminBean();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setFirstName(rs.getString("firstName"));
                admin.setLastName(rs.getString("lastName"));
                admin.setEmail(rs.getString("email"));
                admin.setPhone(rs.getString("phone"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int updateAdmin(AdminBean admin) {
        String sql = "UPDATE admin SET username=?, firstName=?, lastName=?, email=?, phone=?, password=? WHERE admin_id=?";
        try (Connection conn = ConnectionPool.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getFirstName());
            ps.setString(3, admin.getLastName());
            ps.setString(4, admin.getEmail());
            ps.setString(5, admin.getPhone());
            ps.setString(6, admin.getPassword());
            ps.setInt(7, admin.getAdminId());

            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int deleteAdmin(int adminId) {
        String sql = "DELETE FROM admin WHERE admin_id=?";
        try (Connection conn = ConnectionPool.connectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, adminId);
            return ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();

        // Add admin
        AdminBean admin = new AdminBean();
        admin.setUsername("Joe123");
        admin.setFirstName("Joe");
        admin.setLastName("Adams");
        admin.setEmail("testadmin@example.com");
        admin.setPhone("1234567890");
        admin.setPassword("password123");

        int added = dao.addAdmin(admin);
        System.out.println(added > 0 ? "Admin added." : "Add failed.");

        // Get admin
        AdminBean fetched = dao.getAdminByUsernameAndPassword("Joe123", "password123");
        if (fetched != null) {
            System.out.println("Fetched: " + fetched.getUsername() + " - " + fetched.getEmail());
        } else {
            System.out.println("Admin not found.");
        }

        // Update admin
        if (fetched != null) {
            fetched.setUsername("UpdatedAdmin");
            fetched.setEmail("updated@example.com");
            int updated = dao.updateAdmin(fetched);
            System.out.println(updated > 0 ? "Admin updated." : "Update failed.");
        }

        // Delete admin
        if (fetched != null) {
            int deleted = dao.deleteAdmin(fetched.getAdminId());
            System.out.println(deleted > 0 ? "Admin deleted." : "Delete failed.");
        }
         AdminBean verified = dao.getAdminByUsernameAndPassword("admin01", "securePass123");
    if (verified != null) {
        System.out.println("✅ Admin verified: " + verified.getUsername() + " - " + verified.getEmail());
    } else {
        System.out.println("❌ Admin login failed.");
    }
    }
}
