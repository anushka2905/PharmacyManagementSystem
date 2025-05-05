
package com.pharmacy.controller.user;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;

public class ViewMedicine extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<HashMap<String, String>> medicineList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Adjust if using another DB
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pharmacy", "root", "E3/343arera");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM medicines");

            while (rs.next()) {
                HashMap<String, String> med = new HashMap<>();
                med.put("id", rs.getString("id"));
                med.put("name", rs.getString("name"));
                med.put("description", rs.getString("description"));
                med.put("price", rs.getString("price"));
                med.put("expiryDate",rs.getString("expiryDate"));
                
                medicineList.add(med);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("medicines", medicineList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/view_medicines.jsp");
        dispatcher.forward(request, response);
    }
}
