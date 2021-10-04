package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.dao.AdminDBManager;

public class AdminUserManagementServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String firstName = request.getParameter("firstname");
        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        ArrayList<User> userList = null;
        System.out.println(session.toString());

        try {
            userList = manager.fetchAllUsers();
            
        } catch (SQLException | NullPointerException ex) {

        }

        if(firstName == null || firstName.isEmpty() || !validator.validateFirstName(firstName)) {
            session.setAttribute("userList", userList);
            request.getRequestDispatcher("admin_user_management.jsp").include(request, response);
            System.out.println("displaying userlist" + firstName + firstName.isEmpty() + validator.validateFirstName(firstName));
        }
        else {
            try {
                userList = manager.fetchUsersFirstName(firstName);
            } 
            catch (SQLException | NullPointerException ex) {
                Logger.getLogger(AdminUserManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            session.setAttribute("userList", userList);
            request.getRequestDispatcher("admin_user_management.jsp").include(request, response);
            System.out.println("displaying userlist with firstname" + firstName);
        }


    }
}