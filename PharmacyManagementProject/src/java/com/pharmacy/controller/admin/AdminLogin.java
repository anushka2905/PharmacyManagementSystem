
package com.pharmacy.controller.admin;

import com.pharmacy.dao.AdminDAO;
import com.pharmacy.bean.AdminBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AdminDAO dao = new AdminDAO();
        AdminBean admin = dao.getAdminByUsernameAndPassword(username, password);

        if (admin != null && admin.getAdminId() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("adminId", admin.getAdminId());
            session.setAttribute("adminUsername", admin.getUsername());
            session.setAttribute("adminName", admin.getFirstName() + " " + admin.getLastName());
            response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/admin_login.jsp?error=true");
        }
    }
}
