<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="header.jsp" />

<%
    DecimalFormat df = new DecimalFormat("#.##");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <c:choose>
        <c:when test="${empty orderItems}">
            <div class="alert alert-warning text-center">
                No items found in the order.
            </div>
        </c:when>
        <c:otherwise>
            <div class="text-center mb-4">
                <h2 class="text-success">🎉 Order Placed Successfully!</h2>
                <h5>Order ID: <strong>${orderId}</strong></h5>
            </div>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th>Medicine Name</th>
                    <th>Quantity</th>
                    <th>Price (₹)</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="total" value="0" />
                <c:forEach var="item" items="${orderItems}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.quantity}</td>
                        <td>₹${item.total_price}</td>
                    </tr>
                    <c:set var="total" value="${total + item.total_price}" />
                </c:forEach>
                <tr class="table-info">
                    <td colspan="2" class="text-end"><strong>Total:</strong></td>
                    <td><strong>₹${total}</strong></td>
                </tr>
                </tbody>
            </table>

            <div class="text-center mt-4">
                <a href="index.jsp" class="btn btn-primary">← Continue Shopping</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<jsp:include page="footer.jsp" />
