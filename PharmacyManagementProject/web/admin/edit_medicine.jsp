<%@ page import="com.pharmacy.dao.MedicineDAO" %>
<%@ page import="com.pharmacy.bean.MedicineBean" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    MedicineDAO dao = new MedicineDAO();
    MedicineBean med = dao.getMedicineById(id);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Medicine</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="mb-4">Edit Medicine</h2>

        <!-- ? Fixed: Form tag now closed properly -->
        <form action="<%=request.getContextPath()%>/admin/UpdateMedicine" method="post" class="border p-4 bg-white shadow-sm rounded">

            <!-- Hidden field for ID -->
            <input type="hidden" name="id" value="<%= med.getMedicine_id() %>">

            <div class="mb-3">
                <label class="form-label">Medicine Name</label>
                <input type="text" class="form-control" name="name" value="<%= med.getName() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea class="form-control" name="description" required><%= med.getDescription() %></textarea>
            </div>

            <div class="mb-3">
                <label class="form-label">Price (?)</label>
                <input type="number" step="0.01" class="form-control" name="price" value="<%= med.getPrice() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Stock</label>
                <input type="number" class="form-control" name="stock" value="<%= med.getStock() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Category</label>
                <input type="text" class="form-control" name="category" value="<%= med.getCategory() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Manufacturer</label>
                <input type="text" class="form-control" name="manufacturer" value="<%= med.getManufacturer() %>" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Expiry Date</label>
                <input type="date" class="form-control" name="expiry_date" value="<%= med.getExpiryDate() %>" required>
            </div>

            <button type="submit" class="btn btn-primary">Update Medicine</button>
            <a href="view_stock.jsp" class="btn btn-secondary ms-2">Cancel</a>
        </form>
    </div>
</body>
</html>
