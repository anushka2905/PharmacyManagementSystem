<%-- File: admin_logout.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    session.invalidate(); // Ends the session
    response.sendRedirect("../home.jsp"); // Redirect to home page
%>
