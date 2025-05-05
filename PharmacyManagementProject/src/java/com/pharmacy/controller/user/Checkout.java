package com.pharmacy.controller.user;

import com.pharmacy.bean.OrderBean;
import com.pharmacy.dao.OrderDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Checkout extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Checkout.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Retrieve cart and user ID from session
        ArrayList<HashMap<String, String>> cartList = (ArrayList<HashMap<String, String>>) session.getAttribute("cartList");
        Integer userId = (Integer) session.getAttribute("user_id");

        LOGGER.log(Level.INFO, "CheckoutServlet - cartList: {0}", cartList);
        LOGGER.log(Level.INFO, "CheckoutServlet - userId: {0}", userId);

        if (cartList != null && !cartList.isEmpty() && userId != null) {
            List<OrderBean> orderList = new ArrayList<>();

            try {
                for (HashMap<String, String> cartItem : cartList) {
                    int medicineId = Integer.parseInt(cartItem.get("id"));
                    int quantity = Integer.parseInt(cartItem.get("quantity"));
                    double price = Double.parseDouble(cartItem.get("price"));
                    double totalPrice = price * quantity;

                    OrderBean order = new OrderBean();
                    order.setMedicine_id(medicineId);
                    order.setQuantity(quantity);
                    order.setTotal_price(totalPrice);

                    orderList.add(order);
                }
            } catch (Exception parseException) {
                LOGGER.log(Level.SEVERE, "Error parsing cart item values: ", parseException);
                session.setAttribute("error_message", "Invalid cart item data.");
                response.sendRedirect("../user/view_cart.jsp");
                return;
            }

            OrderDAO orderDAO = new OrderDAO();
            int orderId = 0;

            try {
                orderId = orderDAO.addOrderAndReturnId(orderList, userId);
                LOGGER.log(Level.INFO, "CheckoutServlet - orderId from DAO: {0}", orderId);

                if (orderId > 0) {
                    session.removeAttribute("cartList");
                    session.setAttribute("recent_order_id", orderId);
                    LOGGER.info("Checkout successful. Redirecting to order success.");
                    response.sendRedirect("../user/order_success.jsp");
                } else {
                    LOGGER.warning("Checkout failed in OrderDAO. orderId was not > 0");
                    session.setAttribute("error_message", "Checkout failed. Please try again.");
                    response.sendRedirect("../user/view_cart.jsp");
                }

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Exception during checkout: ", e);
                session.setAttribute("error_message", "An error occurred during checkout.");
                response.sendRedirect("../user/view_cart.jsp");
            }

        } else {
            LOGGER.warning("Cart is empty or session expired.");
            session.setAttribute("error_message", "Cart is empty or session expired.");
            response.sendRedirect("../user/view_cart.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("../user/view_cart.jsp");
    }
}
