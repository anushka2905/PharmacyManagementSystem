<%@ page import="java.util.List" %>
<%@ page import="com.pharmacy.bean.MedicineBean" %>
<%@ page import="com.pharmacy.dao.MedicineDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>All Medicines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <style>
        body {
            background: linear-gradient(to right, #e3f2fd, #ffffff);
            font-family: 'Segoe UI', sans-serif;
        }

        .container {
            margin-top: 50px;
        }

        .table {
            border-radius: 10px;
            overflow: hidden;
        }

        .table th, .table td {
            vertical-align: middle !important;
        }

        .btn {
            border-radius: 10px;
        }

        .btn:hover {
            opacity: 0.9;
        }

        .header-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        .search-bar {
            width: 250px;
        }

        .top-buttons {
            margin-top: 20px;
        }
    </style>

    <script>
        function filterTable() {
            const input = document.getElementById("searchInput").value.toLowerCase();
            const rows = document.querySelectorAll("#medicineTable tbody tr");

            rows.forEach(row => {
                const text = row.innerText.toLowerCase();
                row.style.display = text.includes(input) ? "" : "none";
            });
        }
    </script>
</head>
<body>

<div class="container">

    <div class="header-section">
        <h2>📦 All Medicines</h2>
        <input type="text" id="searchInput" onkeyup="filterTable()" class="form-control search-bar" placeholder="🔍 Search Medicine...">
    </div>

    <%
        MedicineDAO dao = new MedicineDAO();
        List<MedicineBean> medicines = dao.getAllMedicines();
    %>

    <div class="table-responsive shadow">
        <table id="medicineTable" class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price (₹)</th>
                    <th>Stock</th>
                    <th>Expiry Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (MedicineBean med : medicines) {
            %>
                <tr>
                    <td><%= med.getMedicine_id() %></td>
                    <td><%= med.getName() %></td>
                    <td><%= med.getCategory() %></td>
                    <td><%= med.getPrice() %></td>
                    <td><%= med.getStock() %></td>
                    <td><%= med.getExpiryDate() %></td>
                    <td>
                        <a href="edit_medicine.jsp?id=<%= med.getMedicine_id() %>" class="btn btn-sm btn-warning">✏️ Edit</a>
                        <a href="DeleteMedicine?id=<%= med.getMedicine_id() %>" 
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Are you sure you want to delete this medicine?');">
                           🗑️ Delete
                        </a>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <div class="top-buttons d-flex justify-content-between">
        <a href="dashboard.jsp" class="btn btn-outline-primary">⬅ Back to Dashboard</a>
        <a href="add_medicine.jsp" class="btn btn-success">➕ Add New Medicine</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
