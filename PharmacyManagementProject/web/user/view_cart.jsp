<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<html>
<head>
    <title>View Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e6f2ff;
        }
    </style>
</head>

<%
    int index = 1;
    double grandTotal = 0;
    DecimalFormat df = new DecimalFormat("#.##");
    ArrayList<HashMap<String, String>> cartList = (ArrayList<HashMap<String, String>>) session.getAttribute("cartList");
%>

<body class="container mt-4">
    <h2 class="text-center mb-4">Your Shopping Cart</h2>

    <!-- Back to Dashboard Button -->
    <div class="mb-3">
        <a href="index.jsp" class="btn btn-secondary">← Go to Dashboard</a>
    </div>

    <% if (cartList != null && !cartList.isEmpty()) { %>

        <!-- Update Cart Form -->
        <form method="post" action="${pageContext.request.contextPath}/user/UpdateCart">
            <div class="table-responsive">
                <table class="table table-bordered text-center align-middle">
                    <thead class="table-primary">
                        <tr>
                            <th>#</th>
                            <th>Medicine Name</th>
                            <th>Price (₹)</th>
                            <th>Quantity</th>
                            <th>Total (₹)</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% for (HashMap<String, String> item : cartList) {
                        String id = item.get("id");
                        String name = item.get("name");
                        String priceStr = item.get("price");
                        String quantityStr = item.get("quantity");

                        double price = 0;
                        int quantity = 0;
                        try {
                            price = Double.parseDouble(priceStr);
                            quantity = Integer.parseInt(quantityStr);
                        } catch (Exception e) {}

                        double total = price * quantity;
                        grandTotal += total;
                    %>
                        <tr>
                            <td><%= index++ %></td>
                            <td><%= name %></td>
                            <td><%= df.format(price) %></td>
                            <td>
                                <input type="number" name="quantity_<%= id %>" value="<%= quantity %>" min="1" class="form-control" style="width: 80px; margin: auto;">
                            </td>
                            <td><%= df.format(total) %></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/user/RemoveFromCart?id=<%= id %>" class="btn btn-danger btn-sm">Remove</a>
                            </td>
                        </tr>
                    <% } %>
                    <tr class="table-success">
                        <td colspan="4"><strong>Grand Total</strong></td>
                        <td colspan="2"><strong>₹<%= df.format(grandTotal) %></strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="text-end mb-3">
                <button type="submit" class="btn btn-primary">Update Cart</button>
            </div>
        </form>

        <!-- Proceed to Checkout Form -->
       <form action="${pageContext.request.contextPath}/user/Checkout" method="post" style="display:inline;">
    <button type="submit" class="btn btn-success">Proceed to Checkout</button>
</form>




    <% } else { %>
        <div class="alert alert-info text-center">
            Your cart is empty.
        </div>
    <% } %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
