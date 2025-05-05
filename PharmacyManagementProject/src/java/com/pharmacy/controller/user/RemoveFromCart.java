
package com.pharmacy.controller.user;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;


public class RemoveFromCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        ArrayList<HashMap<String, String>> cartList =
                (ArrayList<HashMap<String, String>>) session.getAttribute("cartList");

        if (cartList != null && id != null) {
            cartList.removeIf(item -> item.get("id").equals(id));
            session.setAttribute("cartList", cartList);
        }

        response.sendRedirect("view_cart.jsp");
    }
}
