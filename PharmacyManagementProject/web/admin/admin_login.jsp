<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
        .container {
            width: 400px; margin: 100px auto; padding: 30px;
            background: white; box-shadow: 0 0 10px #ccc; border-radius: 10px;
        }
        h2 { text-align: center; }
        input[type="username"], input[type="password"] {
            width: 100%; padding: 10px; margin: 10px 0;
        }
        button {
            width: 100%; padding: 10px; background: #28a745;
            color: white; border: none; cursor: pointer;
        }
        .error { color: red; text-align: center; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Admin Login</h2>
        <form action="${pageContext.request.contextPath}/admin/AdminLogin" method="post">
            <input type="username" name="username" placeholder="UserName" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>

        <% if (request.getParameter("error") != null) { %>
            <div class="error">Invalid credentials. Try again.</div>
        <% } %>
    </div>
</body>
</html>
