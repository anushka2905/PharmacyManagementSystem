<%@ page import="java.util.List" %>
<%@ page import="com.pharmacy.bean.OrderBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Your Order History</h2>
    <%
        List<OrderBean> orders = (List<OrderBean>) request.getAttribute("orders");
        if (orders == null || orders.isEmpty()) {
    %>
        <div class="alert alert-warning">No orders found.</div>
    <%
        } else {
    %>
    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Order ID</th>
            <th>Medicine Name</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (OrderBean order : orders) {
        %>
        <tr>
            <td><%= order.getOrder_id() %></td>
            <td><%= order.getName() %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= order.getTotal_price() %></td>
            <td><%= order.getOrder_status() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>
</div>
</body>
</html>
