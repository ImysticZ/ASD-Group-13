package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.dao.AdminDBManager;

public class AdminDeleteUserServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // ADMIN VIBE CHECK
        User currentUser = (User) session.getAttribute("user");
        String userType = (currentUser == null) ? "" : currentUser.getType();
        if(userType == null || !userType.equals("a")) return;

        String id = request.getParameter("id");
        ArrayList<User> userList = null;

        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        System.out.println(session.toString());

        if(id == null) {
            request.getRequestDispatcher("admin_user_management.jsp").include(request, response);
        }
        else {
            try {
                manager.deleteUser(Integer.parseInt(id));
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
            try {
                userList = manager.fetchAllUsers();
                
            } catch (SQLException | NullPointerException ex) {
    
            }
            session.setAttribute("userList", userList);
            session.setAttribute("msg", "User deleted");
            request.getRequestDispatcher("admin_user_management.jsp").include(request, response);
        }


    }
}