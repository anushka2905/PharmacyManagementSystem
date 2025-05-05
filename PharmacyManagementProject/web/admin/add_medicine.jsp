<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<%@ page errorPage="error.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Medicine</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        body {
            background: linear-gradient(135deg, #f0f8ff, #e3f2fd);
            font-family: 'Segoe UI', sans-serif;
        }

        .form-section {
            background-color: #ffffff;
            border-radius: 20px;
            padding: 30px;
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
            margin-top: 40px;
        }

        .form-control {
            border-radius: 10px;
        }

        .btn-success {
            border-radius: 10px;
            font-weight: 600;
        }

        .form-title {
            font-weight: 700;
            font-size: 1.8rem;
            margin-bottom: 20px;
            color: #0d6efd;
        }

        .alert {
            border-radius: 12px;
            font-weight: 500;
        }

        .back-link {
            margin-top: 15px;
            display: inline-block;
        }
    </style>

    <script>
        window.onload = function () {
            const alert = document.getElementById("alertMsg");
            if (alert) {
                setTimeout(() => {
                    alert.style.opacity = '0';
                    setTimeout(() => alert.style.display = 'none', 600);
                }, 4000);
            }

            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.get('success') === 'true') {
                setTimeout(() => {
                    window.location.href = 'dashboard.jsp';
                }, 5000);
            }
        };
    </script>
</head>
<body>

<div class="container col-md-8">
    <div class="form-section">

        <div class="form-title text-center">
            <i class="fas fa-pills"></i> Add New Medicine
        </div>

        <!-- Alert Messages -->
        <%
            String success = request.getParameter("success");
            String error = request.getParameter("error");
            if ("true".equals(success)) {
        %>
            <div id="alertMsg" class="alert alert-success text-center">
                ✅ Medicine added successfully! Redirecting to dashboard...
            </div>
            <a href="dashboard.jsp" class="btn btn-outline-primary back-link">⬅ Back to Dashboard Now</a>
        <%
            } else if ("true".equals(error)) {
        %>
            <div id="alertMsg" class="alert alert-danger text-center">
                ❌ Failed to add medicine. Please check your input and try again.
            </div>
        <%
            }
        %>

        <!-- Add Medicine Form -->
        <form action="AddMedicine" method="post">
            <div class="row">
                <div class="col-md-6">
                    <input class="form-control mb-3" type="text" name="name" placeholder="Medicine Name" required>
                </div>
                <div class="col-md-6">
                    <input class="form-control mb-3" type="text" name="manufacturer" placeholder="Manufacturer" required>
                </div>
                <div class="col-md-6">
                    <input class="form-control mb-3" type="number" name="price" step="0.01" placeholder="Price (₹)" required>
                </div>
                <div class="col-md-6">
                    <input class="form-control mb-3" type="number" name="stock" placeholder="Stock Quantity" required>
                </div>
                <div class="col-md-6">
                    <input class="form-control mb-3" type="text" name="category" placeholder="Category" required>
                </div>
                <div class="col-md-6">
                    <input class="form-control mb-3" type="date" name="expiry_date" required>
                </div>
                <div class="col-12">
                    <textarea class="form-control mb-3" name="description" placeholder="Description" rows="3" required></textarea>
                </div>
            </div>

            <div class="text-center">
                <button class="btn btn-success px-5" type="submit">➕ Add Medicine</button>
            </div>
        </form>

    </div>
</div>
        
        <a href="dashboard.jsp" class="btn btn-outline-primary">⬅ Back to Dashboard</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
