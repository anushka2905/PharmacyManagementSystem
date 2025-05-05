package com.pharmacy.dao;

import com.pharmacy.bean.MedicineBean;
import com.pharmacy.utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

    private Connection connection;

    public MedicineDAO() {
        this.connection = ConnectionPool.connectDB();
    }

    public MedicineDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addMedicine(MedicineBean medicine) {
        String query = "INSERT INTO medicines (name, description, price, stock, category, manufacturer, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getDescription());
            stmt.setDouble(3, medicine.getPrice());
            stmt.setInt(4, medicine.getStock());
            stmt.setString(5, medicine.getCategory());
            stmt.setString(6, medicine.getManufacturer());
            stmt.setString(7, medicine.getExpiryDate());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public MedicineBean getMedicineById(int medicineId) {
        String query = "SELECT * FROM Medicines WHERE medicine_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, medicineId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MedicineBean(
                            rs.getInt("medicine_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getString("category"),
                            rs.getString("manufacturer"),
                            rs.getString("expiry_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MedicineBean> getAllMedicines() {
        List<MedicineBean> list = new ArrayList<>();
        String sql = "SELECT * FROM medicines";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MedicineBean med = new MedicineBean();
                med.setMedicine_id(rs.getInt("medicine_id"));
                med.setName(rs.getString("name"));
                med.setPrice(rs.getDouble("price"));
                med.setDescription(rs.getString("description"));
                med.setStock(rs.getInt("stock"));
                med.setCategory(rs.getString("category"));
                med.setManufacturer(rs.getString("manufacturer"));
                med.setExpiryDate(rs.getString("expiry_date"));
                list.add(med);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean updateMedicine(MedicineBean medicine) {
        String query = "UPDATE Medicines SET name = ?, description = ?, price = ?, stock = ?, category = ?, manufacturer = ?, expiry_date = ? WHERE medicine_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getDescription());
            stmt.setDouble(3, medicine.getPrice());
            stmt.setInt(4, medicine.getStock());
            stmt.setString(5, medicine.getCategory());
            stmt.setString(6, medicine.getManufacturer());
            stmt.setString(7, medicine.getExpiryDate());
            stmt.setInt(8, medicine.getMedicine_id());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getPriceById(int medicineId) {
        double price = 0;
        String query = "SELECT price FROM Medicines WHERE medicine_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, medicineId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    price = rs.getDouble("price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public String getNameById(int medicineId) {
        String name = null;
        String query = "SELECT name FROM Medicines WHERE medicine_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, medicineId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public boolean deleteMedicine(int medicineId) {
        String query = "DELETE FROM Medicines WHERE medicine_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, medicineId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // =================== MAIN METHOD FOR TESTING ===================
    public static void main(String[] args) {
        MedicineDAO dao = new MedicineDAO();

        List<MedicineBean> allMeds = dao.getAllMedicines();

        if (allMeds.isEmpty()) {
            // Insert 2 sample medicines
            MedicineBean med1 = new MedicineBean();
            med1.setName("Paracetamol");
            med1.setDescription("Fever reducer");
            med1.setPrice(25.0);
            med1.setStock(100);
            med1.setCategory("Antipyretic");
            med1.setManufacturer("Medico Labs");
            med1.setExpiryDate("2026-01-01");
            dao.addMedicine(med1);

            MedicineBean med2 = new MedicineBean();
            med2.setName("Amoxicillin");
            med2.setDescription("Antibiotic");
            med2.setPrice(45.0);
            med2.setStock(80);
            med2.setCategory("Antibiotic");
            med2.setManufacturer("Heal Pharma");
            med2.setExpiryDate("2025-10-10");
            dao.addMedicine(med2);

            System.out.println("Sample medicines inserted.");
        }

        // ✅ Show all medicines
        System.out.println("\nAll Medicines in DB:");
        for (MedicineBean med : dao.getAllMedicines()) {
            System.out.println("ID: " + med.getMedicine_id() + " | Name: " + med.getName());
        }

        // ✅ Update test
        MedicineBean existingMed = dao.getMedicineById(2); // Change ID as needed
        if (existingMed != null) {
            existingMed.setPrice(49.99);
            existingMed.setStock(90);
            boolean updated = dao.updateMedicine(existingMed);
            System.out.println(updated ? "Medicine updated!" : "Update failed.");
        }

        // ❌ DELETION COMMENTED OUT TO KEEP MEDICINES
//        int deleteId = 1;
//        boolean deleted = dao.deleteMedicine(deleteId);
//        System.out.println(deleted ? "Deleted medicine ID " + deleteId : "Deletion failed.");

        // ✅ Test get price & name by ID
        int testId = 2;
        String name = dao.getNameById(testId);
        double price = dao.getPriceById(testId);
        System.out.println("Medicine " + name + " costs ₹" + price);
    }
}
