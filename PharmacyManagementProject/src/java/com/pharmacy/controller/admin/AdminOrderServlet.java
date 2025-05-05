package com.pharmacy.controller.admin;

import com.pharmacy.bean.OrderBean;
import com.pharmacy.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminOrderServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Handle flash message from session
        HttpSession session = request.getSession();
        String flash = (String) session.getAttribute("flash");
        if (flash != null) {
            request.setAttribute("flash", flash);
            session.removeAttribute("flash");
        }

        // Get status filter
        String statusFilter = request.getParameter("status");
        List<OrderBean> orders;

        if (statusFilter != null && !statusFilter.equalsIgnoreCase("All")) {
            orders = orderDAO.getOrdersByStatus(statusFilter);
        } else {
            orders = orderDAO.getAllOrders();
            statusFilter = "All";
        }

        // Set attributes for JSP
        request.setAttribute("orders", orders);
        request.setAttribute("statusFilter", statusFilter);

        // Forward to JSP
        request.getRequestDispatcher("/admin/admin_orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String newStatus = request.getParameter("newStatus");

        boolean updated = orderDAO.updateOrderStatus(orderId, newStatus);

        // Set flash message
        HttpSession session = request.getSession();
        if (updated) {
            session.setAttribute("flash", "Order status updated successfully.");
        } else {
            session.setAttribute("flash", "Failed to update order status.");
        }

        // Redirect back to the same servlet
        response.sendRedirect(request.getContextPath() + "/admin/AdminOrderServlet?status=All");
    }
}
