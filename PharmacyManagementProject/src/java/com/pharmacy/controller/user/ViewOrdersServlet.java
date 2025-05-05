
package com.pharmacy.controller.user;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ViewOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Integer userId = (Integer) session.getAttribute("user_id");

        if (userId == null) {
            response.sendRedirect("user_login.jsp?notLoggedIn=true");
            return;
        }

        List<Map<String, Object>> orders = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "E3/343arera");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> order = new HashMap<>();
                order.put("order_id", rs.getInt("order_id"));
                order.put("order_date", rs.getDate("order_date"));
                order.put("total_amount", rs.getDouble("total_amount"));
                order.put("status", rs.getString("status"));
                orders.add(order);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/view_orders.jsp");
        dispatcher.forward(request, response);
    }
}
