<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.pharmacy.bean.OrderBean" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - View Orders</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #eaf6ff;
        }
        h2 {
            color: #333;
        }
        .flash {
            padding: 10px;
            background-color: #e0ffe0;
            border: 1px solid #b2d8b2;
            color: #2d662d;
            margin-bottom: 20px;
            border-radius: 5px;
            animation: fadeOut 2s 3s forwards;
        }
        @keyframes fadeOut {
            to { opacity: 0; visibility: hidden; }
        }
        .filter-form {
            margin-bottom: 20px;
        }
        select, input[type="submit"] {
            padding: 6px;
            font-size: 14px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            max-height: 400px;
            overflow-y: auto;
            display: block;
        }
        thead {
            background-color: #d0e6f8;
            position: sticky;
            top: 0;
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        tbody {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
            word-wrap: break-word;
        }
        tr:hover {
            background-color: #f1f9ff;
            transition: background-color 0.3s ease;
        }
        .inline-form {
            display: inline-block;
        }
        .btn-success {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 3px;
            transition: background-color 0.3s ease;
        }
        .btn-success:hover {
            background-color: #218838;
        }
        .success-icon {
            color: green;
            font-size: 18px;
        }
        @media (max-width: 768px) {
            table, thead, tbody, th, td, tr {
                display: block;
            }
            thead {
                display: none;
            }
            tr {
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 10px;
                background: #fff;
            }
            td {
                text-align: left;
                padding-left: 50%;
                position: relative;
            }
            td::before {
                content: attr(data-label);
                position: absolute;
                left: 10px;
                width: 45%;
                font-weight: bold;
                white-space: nowrap;
            }
            .btn-back {
    background-color: #007bff;
    color: white;
    padding: 8px 14px;
    text-decoration: none;
    border-radius: 5px;
    font-size: 14px;
    transition: background-color 0.3s ease;
    }
    .btn-back:hover {
        background-color: #0056b3;
    }

        }
    </style>
    <script>
        function confirmStatusChange() {
            return confirm("Are you sure you want to mark this order as Success?");
        }

        function autoSubmitForm(select) {
            select.form.submit();
        }
    </script>
</head>
<body>



<div style="display: flex; justify-content: space-between; align-items: center;">
    <h2>Admin - Orders</h2>
    <a href="dashboard.jsp" class="btn btn-outline-primary">⬅ Back to Dashboard</a>
</div>


<% String flash = (String) request.getAttribute("flash");
   if (flash != null) { %>
   <div class="flash"><%= flash %></div>
<% } %>

<!-- Filter by Status -->
<form action="<%= request.getContextPath() %>/admin/AdminOrderServlet" method="get" class="filter-form">
    <label for="status">Filter by Status:</label>
    <select name="status" id="status" onchange="autoSubmitForm(this)">
        <%
            String statusFilter = (String) request.getAttribute("statusFilter");
        %>
        <option value="All" <%= "All".equals(statusFilter) ? "selected" : "" %>>All</option>
        <option value="Pending" <%= "Pending".equals(statusFilter) ? "selected" : "" %>>Pending</option>
        <option value="Success" <%= "Success".equals(statusFilter) ? "selected" : "" %>>Success</option>
    </select>
</form>

<%
    List<OrderBean> orders = (List<OrderBean>) request.getAttribute("orders");
    if (orders != null && !orders.isEmpty()) {
%>

<table>
    <thead>
        <tr>
            <th>Order ID</th>
            <th>User ID</th>
            <th>Medicine ID</th>
            <th>Medicine Name</th>
            <th>Quantity</th>
            <th>Total Price</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
    <%
        for (OrderBean o : orders) {
    %>
        <tr>
            <td><%= o.getOrder_id() %></td>
            <td><%= o.getUser_id() %></td>
            <td><%= o.getMedicine_id() %></td>
            <td><%= o.getName() %></td>
            <td><%= o.getQuantity() %></td>
            <td>₹<%= String.format("%.2f", o.getTotal_price()) %></td>
            <td><%= o.getOrder_status() %></td>
            <td>
                <% if (!"Success".equalsIgnoreCase(o.getOrder_status())) { %>
                    <form action="<%= request.getContextPath() %>/admin/AdminOrderServlet" method="post" onsubmit="return confirmStatusChange()" style="display:inline;">
                        <input type="hidden" name="orderId" value="<%= o.getOrder_id() %>">
                        <input type="hidden" name="newStatus" value="Success">
                        <input type="submit" value="Mark as Success" class="btn-success">
                    </form>
                <% } else { %>
                    <span class="success-icon">✔</span>
                <% } %>
            </td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>

<% } else { %>
    <p>No orders found.</p>
<% } %>

</body>
</html>
