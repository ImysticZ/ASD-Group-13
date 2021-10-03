package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.dao.AdminDBManager;
import uts.asd.model.dao.DBConnector;
import uts.asd.model.dao.DBManager;

public class AdminDeleteUserServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

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
                // TODO Auto-generated catch block
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