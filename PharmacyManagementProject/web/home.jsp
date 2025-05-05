<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pharmacy Management System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Loader */
        #loader {
            position: fixed;
            top: 0; left: 0;
            width: 100%; height: 100%;
            background: #fff;
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 9999;
        }

        #main-content {
            display: none;
        }

        body {
            background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)),
                        url('images/pharmacy.png') no-repeat center center;
            background-size: cover;
            color: #fff;
            animation: fadeIn 1.5s ease-in;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .container {
            text-align: center;
        }

        .role-card {
            background: rgba(255, 255, 255, 0.9);
            color: #333;
            border-radius: 15px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .role-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
        }

        .icon-circle {
            width: 80px;
            height: 80px;
            line-height: 80px;
            border-radius: 50%;
            background-color: #e9ecef;
            font-size: 35px;
            color: #0d6efd;
            margin: 0 auto 15px auto;
        }

        .logo {
            width: 100px;
            margin-bottom: 20px;
        }

        footer {
            position: absolute;
            bottom: 10px;
            width: 100%;
            text-align: center;
            color: #ddd;
            font-size: 0.9rem;
        }
    </style>

    <script>
        window.onload = function () {
            document.getElementById("loader").style.display = "none";
            document.getElementById("main-content").style.display = "block";
        }
    </script>
</head>
<body>

    <!-- Loader -->
    <div id="loader">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
    </div>

    <!-- Main Content -->
    <div id="main-content" class="container">
        
        <h1>💊 Pharmacy Management System</h1>
        <p class="lead mb-5">Please select your role to continue</p>

        <div class="row justify-content-center g-4">
            <!-- User Card -->
            <div class="col-md-4">
                <div class="card role-card p-4">
                    <div class="icon-circle">👤</div>
                    <h4>User</h4>
                    <p>Browse and order medicines online.</p>
                    <a href="user/index.jsp" class="btn btn-primary w-100">I'm a User</a>
                </div>
            </div>

            <!-- Admin Card -->
            <div class="col-md-4">
                <div class="card role-card p-4">
                    <div class="icon-circle">🛠️</div>
                    <h4>Admin</h4>
                    <p>Manage inventory, orders, and medicines.</p>
                    <a href="admin/admin_login.jsp" class="btn btn-dark w-100">I'm an Admin</a>
                </div>
            </div>
        </div>
    </div>

    <footer>
        &copy; 2025 Pharmacy Management System | All rights reserved.
    </footer>

</body>
</html>
