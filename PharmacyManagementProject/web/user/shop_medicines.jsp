<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Shop Medicines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #eaf4ff;">

<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Pharmacy</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="user_login.jsp">Login</a></li>
                <li class="nav-item"><a class="nav-link" href="user_register.jsp">Register</a></li>
                <li class="nav-item"><a class="nav-link active" href="shop_medicines.jsp">Shop Medicines</a></li>
                <li class="nav-item"><a class="nav-link" href="view_cart.jsp">View Cart</a></li>
            </ul>
        </div>
        <form class="d-flex" role="search" action="SearchMedicine" method="get">
            <input class="form-control me-2" type="search" name="query" placeholder="Search Medicines" required>
            <button class="btn btn-light" type="submit">Search</button>
        </form>
    </div>
</nav>

<!-- Page Content -->
<div class="container mt-5 text-center">
    <h2 class="mb-3">Welcome to the Medicine Store</h2>
    <p class="mb-4">Click the button below to view available medicines.</p>
    
    <form action="view_medicine.jsp" method="get">
        <button type="submit" class="btn btn-success btn-lg">Shop Now</button>
    </form>
</div>

</body>
</html>
