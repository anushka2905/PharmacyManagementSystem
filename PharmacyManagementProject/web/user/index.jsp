<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    String firstName = (String) session.getAttribute("firstName");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online Pharmacy - Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Segoe UI', sans-serif;
            background-color: #e6f0ff;
            color: #333;
        }
        .navbar {
            background-color: rgba(0, 123, 255, 0.8);
        }
        .navbar-brand, .nav-link, .dropdown-toggle {
            color: white !important;
            font-weight: 500;
        }
        .nav-link:hover, .dropdown-item:hover {
            color: #ffd700 !important;
        }
        .form-control {
            width: 250px;
        }
        .hero {
            margin-top: 120px;
            text-align: center;
            animation: fadeIn 1.5s ease;
        }
        .hero h1 {
            font-size: 3.5rem;
            font-weight: bold;
        }
        .hero .btn {
            font-size: 1.2rem;
            padding: 12px 25px;
            border-radius: 30px;
            margin: 10px;
            transition: all 0.3s ease;
        }
        .hero .btn:hover {
            transform: scale(1.05);
        }
        .btn-outline-primary:hover {
            background-color: #0056b3;
            color: white;
        }
        @keyframes fadeIn {
            0% { opacity: 0; transform: translateY(30px); }
            100% { opacity: 1; transform: translateY(0); }
        }
        .welcome-text {
            color: #fff;
            margin-left: 20px;
            font-weight: 500;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg shadow">
    <div class="container">
        <a class="navbar-brand" href="#">Pharmacy</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <% if (firstName == null) { %>
                    <li class="nav-item"><a class="nav-link" href="user_login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="user_register.jsp">Register</a></li>
                <% } else { %>
                    <li class="nav-item"><a class="nav-link" href="view_medicine.jsp">Shop Medicines</a></li>
                <% } %>
            </ul>

            <% if (firstName != null) { %>
                <div class="dropdown">
                    <button class="btn btn-link dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="white" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
                            <path d="M9.5 13.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
                        </svg>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton">
                        <li><a class="dropdown-item" href="view_cart.jsp">View Cart</a></li>
                        <li><a class="dropdown-item" href="viewOrderHistory">View Orders</a></li>
                        <li><a class="dropdown-item text-danger" href="<%= request.getContextPath() %>/user/logout">Logout</a></li>
                    </ul>
                </div>
                <span class="welcome-text">Welcome, <%= firstName %>!</span>
            <% } %>

            <form class="d-flex ms-3" action="search_medicine.jsp" method="get">
                <input class="form-control me-2" type="search" name="query" placeholder="Search Medicines" aria-label="Search">
                <button class="btn btn-light" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<div class="container hero">
    <h1 class="display-4">Welcome to Online Pharmacy</h1>
    <p class="lead">Buy medicines online at affordable prices. Fast delivery. Trusted brands.</p>
    <a href="<%= request.getContextPath() %>/user/view_medicine.jsp" class="btn btn-primary btn-lg">
        <i class="bi bi-bag-heart-fill"></i> Shop Now
    </a>

    <% if (firstName != null) { %>
        <li><a class="dropdown-item" href="<%= request.getContextPath() %>/user/viewOrderHistory">View Orders</a></li>

            <i class="bi bi-receipt"></i> My Orders
        </a>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
