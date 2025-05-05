<%@page import="java.util.List"%>
<%@page import="com.pharmacy.bean.MedicineBean"%>
<%@page import="com.pharmacy.dao.MedicineDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Medicines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Pharmacy</a>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">Available Medicines</h2>
    <div class="row">
        <%
            MedicineDAO dao = new MedicineDAO();
            List<MedicineBean> medicines = dao.getAllMedicines();

            if (medicines != null && !medicines.isEmpty()) {
                for (MedicineBean med : medicines) {
        %>
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-body">
                    <h5 class="card-title"><%= med.getName() %></h5>
                    <p class="card-text"><%= med.getDescription() %></p>
                    <p class="card-text"><strong>₹<%= med.getPrice() %></strong></p>
                    <form action="AddToCart" method="post">
                        <input type="hidden" name="name" value="<%= med.getName() %>">
                        <input type="hidden" name="price" value="<%= med.getPrice() %>">
                        <div class="mb-2">
                            <input type="number" name="quantity" value="1" min="1" class="form-control form-control-sm">
                        </div>
                        <button type="submit" class="btn btn-success btn-sm">Add to Cart</button>
                    </form>
                </div>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p>No medicines found in the database.</p>
        <%
            }
        %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
