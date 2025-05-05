
package com.pharmacy.controller.user;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;


 
public class UpdateCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    if (session == null) {
        response.sendRedirect("view_cart.jsp");
        return;
    }

    ArrayList<HashMap<String, String>> cartList =
        (ArrayList<HashMap<String, String>>) session.getAttribute("cartList");

    if (cartList == null) {
        response.sendRedirect("view_cart.jsp");
        return;
    }

    for (int i = 0; i < cartList.size(); i++) {
        HashMap<String, String> item = cartList.get(i);
        String id = item.get("id");
        String paramName = "quantity_" + id;
        String quantityStr = request.getParameter(paramName);

        if (quantityStr != null) {
            try {
                int quantity = Integer.parseInt(quantityStr);
                if (quantity > 0) {
                    item.put("quantity", String.valueOf(quantity));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    session.setAttribute("cartList", cartList);
    response.sendRedirect("view_medicine.jsp");
}

}
