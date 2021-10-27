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
import org.apache.commons.text.StringEscapeUtils;


public class AdminUpdateUserServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // ADMIN VIBE CHECK
        User currentUser = (User) session.getAttribute("user");
        String userType = (currentUser == null) ? "" : currentUser.getType();
        if(userType == null || !userType.equals("a")) return;

        Validator validator = new Validator();

        String id = StringEscapeUtils.unescapeHtml4(request.getParameter("id"));
        String firstName = StringEscapeUtils.unescapeHtml4(request.getParameter("firstname"));
        String lastName = StringEscapeUtils.unescapeHtml4(request.getParameter("lastname"));
        String email = StringEscapeUtils.unescapeHtml4(request.getParameter("email"));
        String phone = StringEscapeUtils.unescapeHtml4(request.getParameter("phone"));
        String password = StringEscapeUtils.unescapeHtml4(request.getParameter("password"));
        String address = StringEscapeUtils.unescapeHtml4(request.getParameter("address"));
        String type = StringEscapeUtils.unescapeHtml4(request.getParameter("type"));
        
        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        System.out.println(session.toString());

        
        if(firstName == null || lastName == null || email == null || phone == null || password == null|| address == null|| type == null) {
            // null error
            session.setAttribute("editusermsg", "ERROR: Field is null");
            request.getRequestDispatcher("admin_update_user.jsp").include(request, response);
            System.out.println("NULLFIELD");
        }
        else if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()|| address.isEmpty()|| type.isEmpty()) {
            // empty field error
            session.setAttribute("editusermsg", "ERROR: Empty field/s");
            request.getRequestDispatcher("admin_update_user.jsp").include(request, response);
            System.out.println("EMPTYFIELD");
        }
        /*
        else if (!(validator.validateFirstName(firstName) && validator.validateLastName(lastName) && validator.validateEmail(email) && validator.validatePhone(phone) && validator.validatePassword(password) && validator.validateAddress(address) && validator.validateType(type) )) {
            // validation error
            session.setAttribute("createusermsg", "ERROR: Invalid input!");
            request.getRequestDispatcher("admin_create_user.jsp").include(request, response);
        }
        */
        else {
            // no error
            try {
                manager.updateUser(Integer.parseInt(id), firstName, lastName, email, phone, password, address, type);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ArrayList<User> userList = null;
            try {
                userList = manager.fetchAllUsers();
            } catch (SQLException | NullPointerException ex) {

            }
            session.setAttribute("userList", userList);
            session.setAttribute("editusermsg", "User has updated");
            request.getRequestDispatcher("admin_update_user.jsp").include(request, response);
        }


    }
}