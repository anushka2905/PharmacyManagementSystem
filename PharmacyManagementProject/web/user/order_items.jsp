<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.pharmacy.bean.OrderItemBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Items</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #444;
            text-align: center;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>

<h2>Order Items</h2>

<%
    List<OrderItemBean> orderItemList = (List<OrderItemBean>) request.getAttribute("orderItemList");

    if (orderItemList != null && !orderItemList.isEmpty()) {
%>
    <table>
        <tr>
            <th>Order Item ID</th>
            <th>Order ID</th>
            <th>Medicine ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Total Price</th>
        </tr>
<%
        for (OrderItemBean item : orderItemList) {
%>
        <tr>
            <td><%= item.getOrder_item_id() %></td>
            <td><%= item.getOrder_id() %></td>
            <td><%= item.getMedicine_id() %></td>
            <td><%= item.getName() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getTotal_price() %></td>
        </tr>
<%
        }
%>
    </table>
<%
    } else {
%>
    <p style="text-align:center;">No order items found for this order.</p>
<%
    }
%>

</body>
</html>
