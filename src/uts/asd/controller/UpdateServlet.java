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
        
        int id = Integer.parseInt(request.getParameter("ID"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String type = request.getParameter("type");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        session.setAttribute("updated", ""); //clear any error messages

        if (!validator.validateFirstName(fname)) { //validate format for all fields
            session.setAttribute("updated", "Invalid Name Format");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else if (!validator.validateLastName(lname)) {
            session.setAttribute("updated", "Invalid Name Format");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("updated", "Invalid Email Format");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else if (!validator.validatePhone(phone)) {
            session.setAttribute("updated", "Invalid Phone Format");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("updated", "Invalid Password Format");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else if (!validator.validateAddress(address)) {
            session.setAttribute("updated", "Invalid Address Format");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else if (!validator.validateType(password)) {
            session.setAttribute("updated", "Invalid Account Type");
            request.getRequestDispatcher("manage_account.jsp").include(request, response);
        } else {
            try {
                User check = manager.findUserByEmail(email);
                if (check == null || check.getEmail().equals(email)) {
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
