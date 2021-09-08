package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.dao.DBManager;

@WebServlet("/AdminManagementServlet")
public class AdminManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        DBManager manager = (DBManager) session.getAttribute("manager");
        ArrayList<User> userList = null;

        try {
            userList = manager.fetchAllUsers();
            
        } catch (SQLException | NullPointerException ex) {

        }

        if(email == null || email.isEmpty() || validator.validateEmail(email)) {
            session.setAttribute("userList", userList);
            request.getRequestDispatcher("admin_user_management.jsp").include(request, response);
        }
        else {
            try {
                userList = manager.fetchUsersEmail(email);
                } 
                catch (SQLException | NullPointerException ex) {

                }
                
                session.setAttribute("userList", userList);
                request.getRequestDispatcher("admin_user_management.jsp").include(request, response);
        }


    }
}