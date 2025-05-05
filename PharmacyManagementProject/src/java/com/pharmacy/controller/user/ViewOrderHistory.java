//package com.pharmacy.controller.user;
//
//import com.pharmacy.dao.OrderDAO;
//import com.pharmacy.bean.OrderBean;
//import com.pharmacy.utility.ConnectionPool;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//
//
//public class ViewOrderHistory extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//        Integer userId = (Integer) session.getAttribute("user_id");
//
//    if (userId != null) {
//        OrderDAO orderDAO = new OrderDAO(ConnectionPool.connectDB());
//        List<OrderBean> orderList = orderDAO.getOrdersByUserId(userId);
//        request.setAttribute("orders", orderList);
//        request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
//    } else {
//        response.sendRedirect("user_login.jsp");
//    }
//        int user_id = (int) session.getAttribute("user_id");
//        OrderDAO orderDAO = new OrderDAO(ConnectionPool.connectDB());
//
//        List<OrderBean> orderList = orderDAO.getOrdersByUserId(user_id);
//        request.setAttribute("orders", orderList);
//        request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
//    }
//}
//
//
