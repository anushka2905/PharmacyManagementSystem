<%@ page import="java.sql.*, javax.sql.*, javax.naming.*, javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
   
    Integer userId = (Integer) session.getAttribute("user_id");
    if (userId == null) {
        response.sendRedirect("user_login.jsp?notLoggedIn=true");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f2f2f2;
            padding-top: 60px;
        }
        .table {
            background-color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="mb-4">Your Orders</h2>

    <table class="table table-bordered table-hover">
        <thead class="table-primary">
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Total Amount</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <%
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "E3/343arera");

                String sql = "SELECT order_id, order_date, total_amount, status FROM orders WHERE user_id = ? ORDER BY order_date DESC";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();

                boolean hasOrders = false;
                while (rs.next()) {
                    hasOrders = true;
        %>
                    <tr>
                        <td><%= rs.getInt("order_id") %></td>
                        <td><%= rs.getDate("order_date") %></td>
                        <td>₹<%= rs.getDouble("total_amount") %></td>
                        <td><%= rs.getString("status") %></td>
                    </tr>
        <%
                }
                if (!hasOrders) {
        %>
                    <tr>
                        <td colspan="4" class="text-center">No orders found.</td>
                    </tr>
        <%
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
            } finally {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            }
        %>
        </tbody>
    </table>

    <a href="index.jsp" class="btn btn-secondary">Back to Home</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
