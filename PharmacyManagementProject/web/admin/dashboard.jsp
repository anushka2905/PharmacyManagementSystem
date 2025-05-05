<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        body {
            background: linear-gradient(135deg, #e3f2fd, #f1f8ff);
            min-height: 100vh;
        }

        .dashboard-card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border: none;
            border-radius: 20px;
            background-color: #ffffff;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
        }

        .card-icon {
            font-size: 2.8rem;
            color: #0d6efd;
            margin-bottom: 15px;
        }

        .card-title {
            font-weight: 600;
        }

        .navbar-brand {
            font-size: 1.6rem;
            font-weight: 600;
        }

        .card-body {
            padding: 2rem;
        }

        a.card-link {
            text-decoration: none;
            color: inherit;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
    <div class="container-fluid">
        <span class="navbar-brand">Admin Dashboard</span>
        <div class="d-flex">
            <a href="../home.jsp" class="btn btn-outline-light">Logout</a>

        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row g-4 justify-content-center">

        <!-- Add Medicine Card -->
        <div class="col-md-4">
            <a href="add_medicine.jsp" class="card-link">
                <div class="card dashboard-card text-center h-100">
                    <div class="card-body">
                        <i class="fas fa-capsules card-icon"></i>
                        <h5 class="card-title">Add Medicine</h5>
                        <p class="text-muted">Add new items to your medicine inventory with details and stock.</p>
                    </div>
                </div>
            </a>
        </div>

        <!-- View Stock Card -->
        <div class="col-md-4">
            <a href="view_stock.jsp" class="card-link">
                <div class="card dashboard-card text-center h-100">
                    <div class="card-body">
                        <i class="fas fa-warehouse card-icon"></i>
                        <h5 class="card-title">View Stock</h5>
                        <p class="text-muted">Check availability, quantity, and details of all medicines in store.</p>
                    </div>
                </div>
            </a>
        </div>
        
        
        <div class="col-md-4">
            <a href="admin_orders.jsp" class="card-link">
                <div class="card dashboard-card text-center h-100">
                    <div class="card-body">
                        <i class="fas fa-warehouse card-icon"></i>
                        <h5 class="card-title">View Orders</h5>
                        <p class="text-muted">View all orders and check which are pending and which are completed.</p>
                    </div>
                </div>
            </a>
        </div>
        
        
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
