package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.dao.AdminDBManager;


public class AdminUpdateUserServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();

        String id = request.getParameter("id");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String type = request.getParameter("type");

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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            session.setAttribute("editusermsg", "User has updated");
            request.getRequestDispatcher("admin_update_user.jsp").include(request, response);
        }


    }
}