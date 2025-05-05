<%@ page import="java.util.*, com.pharmacy.dao.MedicineDAO, com.pharmacy.bean.MedicineBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String keyword = request.getParameter("keyword");
    MedicineDAO dao = new MedicineDAO();
    List<MedicineBean> medicine = dao.getAllMedicines();

    // Filter based on keyword (if given)
    if (keyword != null && !keyword.trim().isEmpty()) {
        keyword = keyword.toLowerCase();
        
    }
%>
<html>
<head>
    <title>Search Medicine</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h2 class="mb-4">Search Medicines</h2>
    <form method="get" action="search_medicine.jsp" class="mb-4 d-flex gap-2">
        <input type="text" class="form-control" name="keyword" placeholder="Enter medicine name" value="<%= keyword != null ? keyword : "" %>"/>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <% if (!medicine.isEmpty()) { %>
        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Name</th>
                    <th>Price (₹)</th>
                    <th>Quantity</th>
                    <th>Add to Cart</th>
                </tr>
            </thead>
            <tbody>
            <% for (MedicineBean med : medicine) { %>
                <tr>
                    <form method="post" action="<%= request.getContextPath() %>/user/AddToCart">
                        <td><%= med.getName() %></td>
                        <td><%= med.getPrice() %></td>
                        <td>
                            <input type="number" name="quantity" value="1" min="1" class="form-control"/>
                            <input type="hidden" name="name" value="<%= med.getName() %>"/>
                            <input type="hidden" name="price" value="<%= med.getPrice() %>"/>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-success">Add to Cart</button>
                        </td>
                    </form>
                </tr>
            <% } %>
            </tbody>
        </table>
    <% } else if (keyword != null) { %>
        <p class="text-danger">No medicines found for "<%= keyword %>".</p>
    <% } %>
</body>
</html>
