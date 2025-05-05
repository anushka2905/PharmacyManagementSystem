
package com.pharmacy.controller.admin;

import com.pharmacy.bean.MedicineBean;
import com.pharmacy.dao.MedicineDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateMedicine extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MedicineBean med = new MedicineBean();

            med.setMedicine_id(Integer.parseInt(request.getParameter("id")));
            med.setName(request.getParameter("name"));
            med.setDescription(request.getParameter("description"));
            med.setPrice(Double.parseDouble(request.getParameter("price")));
            med.setStock(Integer.parseInt(request.getParameter("stock")));
            med.setCategory(request.getParameter("category"));
            med.setManufacturer(request.getParameter("manufacturer"));
            med.setExpiryDate(request.getParameter("expiry_date")); // Assuming format is yyyy-MM-dd

            MedicineDAO dao = new MedicineDAO();
            boolean updated = dao.updateMedicine(med);

            if (updated) {
                response.sendRedirect("view_stock.jsp");
            } else {
                response.getWriter().println("❌ Failed to update medicine. Try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("❌ Error: " + e.getMessage());
        }
    }
}
