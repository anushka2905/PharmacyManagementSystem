
package com.pharmacy.controller.admin;

import com.pharmacy.dao.MedicineDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteMedicine extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            MedicineDAO dao = new MedicineDAO();

            boolean deleted = dao.deleteMedicine(id);

            if (deleted) {
                response.sendRedirect("view_stock.jsp"); // Redirect to stock view
            } else {
                response.getWriter().println("❌ Failed to delete medicine.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error: " + e.getMessage());
        }
    }
}
