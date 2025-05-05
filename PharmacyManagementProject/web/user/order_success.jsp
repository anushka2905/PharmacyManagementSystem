<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer orderId = (Integer) session.getAttribute("recent_order_id");
%>
<html>
<head>
    <title>Order Placed</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e3f2fd;
            text-align: center;
            padding: 100px;
        }
        .message-box {
            background-color: #fff;
            padding: 40px;
            margin: auto;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 50%;
        }
        a {
            color: white;
            background-color: #007bff;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
        }
        .order-id {
            font-size: 18px;
            color: #333;
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div class="message-box">
    <h1>✅ Order Placed Successfully!</h1>
    <p>Thank you for your purchase. Your order has been successfully recorded.</p>

    <% if (orderId != null) { %>
        <div class="order-id">Your Order ID: <strong><%= orderId %></strong></div>
    <% } %>

    <a href="index.jsp">Go to Dashboard</a>
</div>
</body>
</html>
