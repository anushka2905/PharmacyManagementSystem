package com.pharmacy.dao;

import com.pharmacy.bean.CartItem;
import com.pharmacy.bean.OrderBean;
import com.pharmacy.utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // DO NOT MODIFY THIS METHOD (used by AdminViewOrderServlet)
    public boolean addOrder(List<OrderBean> orderList, int userId) {
        String orderSql = "INSERT INTO orders (user_id, total_amount, order_status) VALUES (?, ?, ?)";
        String itemSql = "INSERT INTO order_items (order_id, medicine_id, quantity, price) VALUES (?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement orderPs = null;
        PreparedStatement itemPs = null;
        ResultSet rs = null;

        try {
            double totalAmount = 0;
            for (OrderBean ob : orderList) {
                totalAmount += ob.getTotal_price();
            }

            con = ConnectionPool.connectDB();
            con.setAutoCommit(false);

            orderPs = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            orderPs.setInt(1, userId);
            orderPs.setDouble(2, totalAmount);
            orderPs.setString(3, "Pending");

            int affectedRows = orderPs.executeUpdate();
            if (affectedRows == 0) {
                con.rollback();
                return false;
            }

            rs = orderPs.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            } else {
                con.rollback();
                return false;
            }

            itemPs = con.prepareStatement(itemSql);
            for (OrderBean order : orderList) {
                itemPs.setInt(1, orderId);
                itemPs.setInt(2, order.getMedicine_id());
                itemPs.setInt(3, order.getQuantity());
                itemPs.setDouble(4, order.getTotal_price());
                itemPs.addBatch();
            }

            itemPs.executeBatch();
            con.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (SQLException ignored) {}
            return false;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (orderPs != null) orderPs.close(); } catch (SQLException ignored) {}
            try { if (itemPs != null) itemPs.close(); } catch (SQLException ignored) {}
            try { if (con != null) con.setAutoCommit(true); con.close(); } catch (SQLException ignored) {}
        }
    }

    // Get all orders
    public List<OrderBean> getAllOrders() {
        List<OrderBean> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                orders.add(extractOrderFromOrderTable(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Get order by ID
    public OrderBean getOrderById(int orderId) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractOrderFromOrderTable(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update order status
    public boolean updateOrderStatus(int orderId, String newStatus) {
        String query = "UPDATE orders SET order_status = ? WHERE order_id = ?";
        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete order
    public boolean deleteOrder(int orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get orders by user ID
    public List<OrderBean> getOrdersByUserId(int userId) {
        List<OrderBean> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(extractOrderFromOrderTable(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Get orders by status
    public List<OrderBean> getOrdersByStatus(String status) {
        List<OrderBean> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE order_status = ?";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(extractOrderFromOrderTable(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Get items for a specific order
    public List<OrderBean> getOrderItemsByOrderId(int orderId) {
        List<OrderBean> items = new ArrayList<>();
        String query = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection con = ConnectionPool.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(extractOrderItem(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // === Utility methods ===
    private OrderBean extractOrderFromOrderTable(ResultSet rs) throws SQLException {
        OrderBean order = new OrderBean();
        order.setOrder_id(rs.getInt("order_id"));
        order.setUser_id(rs.getInt("user_id"));
        order.setOrder_status(rs.getString("order_status"));
        order.setTotal_price(rs.getDouble("total_amount")); // assuming this is the column name
        return order;
    }

    private OrderBean extractOrderItem(ResultSet rs) throws SQLException {
        OrderBean item = new OrderBean();
        item.setOrder_id(rs.getInt("order_id"));
        item.setMedicine_id(rs.getInt("medicine_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setTotal_price(rs.getDouble("price")); // make sure your DB column name is 'price'
        return item;
    }

    // Optional: Future use
    public boolean addOrder(int user_id, List<CartItem> cart) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public boolean placeOrder(int user_id, List<OrderBean> orderItems, double grandTotal) {
    String insertOrderSql = "INSERT INTO orders (user_id, total_amount, order_status) VALUES (?, ?, ?)";
    String insertItemSql = "INSERT INTO order_items (order_id, medicine_id, quantity, price) VALUES (?, ?, ?, ?)";

    Connection con = null;
    PreparedStatement orderPs = null;
    PreparedStatement itemPs = null;
    ResultSet rs = null;

    try {
        con = ConnectionPool.connectDB();
        con.setAutoCommit(false); // Start transaction

        // Insert into orders table
        orderPs = con.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
        orderPs.setInt(1, user_id);
        orderPs.setDouble(2, grandTotal);
        orderPs.setString(3, "Pending");

        int rows = orderPs.executeUpdate();
        if (rows == 0) {
            con.rollback();
            return false;
        }

        rs = orderPs.getGeneratedKeys();
        int orderId = 0;
        if (rs.next()) {
            orderId = rs.getInt(1);
        } else {
            con.rollback();
            return false;
        }

        // Insert order items
        itemPs = con.prepareStatement(insertItemSql);
        for (OrderBean item : orderItems) {
            itemPs.setInt(1, orderId);
            itemPs.setInt(2, item.getMedicine_id());
            itemPs.setInt(3, item.getQuantity());
            itemPs.setDouble(4, item.getTotal_price());
            itemPs.addBatch();
        }

        itemPs.executeBatch();
        con.commit();
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        try { if (con != null) con.rollback(); } catch (SQLException ignored) {}
        return false;

    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
        try { if (orderPs != null) orderPs.close(); } catch (SQLException ignored) {}
        try { if (itemPs != null) itemPs.close(); } catch (SQLException ignored) {}
        try { if (con != null) con.setAutoCommit(true); con.close(); } catch (SQLException ignored) {}
    }
    
}
    public int addOrderAndReturnId(List<OrderBean> orderList, int userId) {
    String orderSql = "INSERT INTO orders (user_id, total_amount, order_status) VALUES (?, ?, ?)";
    String itemSql = "INSERT INTO order_items (order_id, medicine_id, quantity, price) VALUES (?, ?, ?, ?)";

    Connection con = null;
    PreparedStatement orderPs = null;
    PreparedStatement itemPs = null;
    ResultSet rs = null;

    try {
        double totalAmount = 0;
        for (OrderBean ob : orderList) {
            totalAmount += ob.getTotal_price();
        }

        con = ConnectionPool.connectDB();
        con.setAutoCommit(false);

        orderPs = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
        orderPs.setInt(1, userId);
        orderPs.setDouble(2, totalAmount);
        orderPs.setString(3, "Pending");

        int affectedRows = orderPs.executeUpdate();
        if (affectedRows == 0) {
            con.rollback();
            return -1;
        }

        rs = orderPs.getGeneratedKeys();
        int orderId = 0;
        if (rs.next()) {
            orderId = rs.getInt(1);
        } else {
            con.rollback();
            return -1;
        }

        itemPs = con.prepareStatement(itemSql);
        for (OrderBean order : orderList) {
            itemPs.setInt(1, orderId);
            itemPs.setInt(2, order.getMedicine_id());
            itemPs.setInt(3, order.getQuantity());
            itemPs.setDouble(4, order.getTotal_price());
            itemPs.addBatch();
        }

        itemPs.executeBatch();
        con.commit();
        return orderId;

    } catch (Exception e) {
        e.printStackTrace();
        try { if (con != null) con.rollback(); } catch (SQLException ignored) {}
        return -1;
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
        try { if (orderPs != null) orderPs.close(); } catch (SQLException ignored) {}
        try { if (itemPs != null) itemPs.close(); } catch (SQLException ignored) {}
        try { if (con != null) con.setAutoCommit(true); con.close(); } catch (SQLException ignored) {}
    }
}


}
