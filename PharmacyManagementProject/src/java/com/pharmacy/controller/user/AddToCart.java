
package com.pharmacy.controller.user;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;


public class AddToCart extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<HashMap<String, String>> cartList = (List<HashMap<String, String>>) session.getAttribute("cartList");
        if (cartList == null) {
            cartList = new ArrayList<>();
        }

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");

        if (name != null && price != null && quantity != null) {
            HashMap<String, String> item = new HashMap<>();
            item.put("name", name);
            item.put("price", price);
            item.put("quantity", quantity);

            cartList.add(item);
            session.setAttribute("cartList", cartList);
        }

        response.sendRedirect("view_cart.jsp");  // adjust if path differs
    }
}
