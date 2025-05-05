package com.pharmacy.dao;

import com.pharmacy.bean.OrderItemBean;
import com.pharmacy.utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    // 1. Add order item
    public boolean addOrderItem(OrderItemBean item) {
        String sql = "INSERT INTO orderitems (order_id, medicine_id, quantity, total_price, name) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getOrder_id());
            ps.setInt(2, item.getMedicine_id());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getTotal_price());
            ps.setString(5, item.getName());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Get order items by order ID
    public List<OrderItemBean> getItemsByOrderId(int orderId) {
        List<OrderItemBean> itemList = new ArrayList<>();
        String sql = "SELECT * FROM orderitems WHERE order_id = ?";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItemBean item = new OrderItemBean(
                        rs.getInt("order_item_id"),
                        rs.getInt("order_id"),
                        rs.getInt("medicine_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("name")
                );
                itemList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    // 3. Delete order items by order ID
    public boolean deleteItemsByOrderId(int orderId) {
        String sql = "DELETE FROM orderitems WHERE order_id = ?";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Delete single item by order_item_id
    public boolean deleteOrderItem(int orderItemId) {
        String sql = "DELETE FROM orderitems WHERE order_item_id = ?";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. Update order item
    public boolean updateOrderItem(OrderItemBean item) {
        String sql = "UPDATE orderitems SET medicine_id=?, quantity=?, total_price=?, name=? WHERE order_item_id=?";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getMedicine_id());
            ps.setInt(2, item.getQuantity());
            ps.setDouble(3, item.getTotal_price());
            ps.setString(4, item.getName());
            ps.setInt(5, item.getOrder_item_id());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
