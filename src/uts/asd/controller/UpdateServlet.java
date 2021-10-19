package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.dao.DBManager;

public class UpdateServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        
        int id = Integer.parseInt(request.getParameter("ID")); // get id
        String email = request.getParameter("email"); // get email
        String password = request.getParameter("password"); // get pass
        String fname = request.getParameter("fname"); // get name
        String lname = request.getParameter("lname");// get name
        String phone = request.getParameter("phone");// get phone
        String address = request.getParameter("address"); // get address
        String type = request.getParameter("type"); // get type
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        session.setAttribute("updated", ""); //clear any error messages
        // System.out.println("");
        // System.out.println(fname);
        // System.out.println(fname.trim());
        if (!validator.validateEmail(email)) {
            session.setAttribute("updated", "Invalid Email Format");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else if (!validator.validateType(type)) {
            session.setAttribute("updated", "Invalid Account Type");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else {
            try {
                User check = manager.findUserByEmail(email);
                if (check == null || check.getId()==id) {
                    manager.updateFirstName(id, fname);
                    manager.updateLastName(id, lname);
                    manager.updateEmail(id, email);
                    manager.updatePhone(id, phone);
                    manager.updatePassword(id, password);
                    manager.updateAddress(id, address);
                    manager.updateType(id, type);
                    User user = manager.findUserByID(id);
                    session.setAttribute("user", user);
                    session.setAttribute("updated", "Details successfully updated.");
                    request.getRequestDispatcher("manage_account.jsp").include(request, response);
                } else {
                    session.setAttribute("updated", "Email is already in use");
                    request.getRequestDispatcher("manage_account.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage() == null ? "Unable to update user" : "User updated");
            }
            response.sendRedirect("manage_account.jsp");
        }
    }
}
