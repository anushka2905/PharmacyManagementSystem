package com.pharmacy.controller.user;

import com.pharmacy.bean.MedicineBean;
import com.pharmacy.dao.MedicineDAO;
import com.pharmacy.utility.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProceedToCheckout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MedicineDAO medicineDAO = new MedicineDAO(ConnectionPool.connectDB());
        List<MedicineBean> medicines = medicineDAO.getAllMedicines(); // Ensure this DAO method exists

        // Log for debugging
        System.out.println("Medicines loaded: " + (medicines != null ? medicines.size() : "null"));

        request.setAttribute("medicines", medicines);

        // ✅ Updated forward path
        request.getRequestDispatcher("user/place_order.jsp").forward(request, response);
    }
}
