package com.pharmacy.controller.admin;

import com.pharmacy.bean.MedicineBean;
import com.pharmacy.dao.MedicineDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

 
public class AddMedicine extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
         System.out.println(">>> AddMedicine servlet triggered");

   

        // Get parameters from the form
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String category = request.getParameter("category");
        String supplier = request.getParameter("supplier");
        String expiry = request.getParameter("expiry");

        // Parse and validate
        double price = 0;
        int quantity = 0;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price input: " + priceStr);
            price = 0;
        }

        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity input: " + quantityStr);
            quantity = 0;
        }

        // Create and populate the bean
        MedicineBean medicine = new MedicineBean();
        medicine.setName(name);
        medicine.setDescription(description);
        medicine.setPrice(price);
        medicine.setStock(quantity);
        medicine.setCategory(category);
        medicine.setManufacturer(supplier); // assuming manufacturer = supplier
        medicine.setExpiryDate(expiry);

        // Use DAO (create instance and call method)
        MedicineDAO dao = new MedicineDAO();
        boolean added = dao.addMedicine(medicine);

        if (added) {
            System.out.println("Medicine Added Successfully");
        } else {
            response.sendRedirect("add_medicine.jsp?error=true");
        }
        response.sendRedirect("add_medicine.jsp?success=true");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("add_medicine.jsp");
    }
}
