package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.dao.DBManager;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        
       DBManager manager = (DBManager) session.getAttribute("manager");

        if (!validator.validateFirstName(first_name)) {
            session.setAttribute("nameErr", "Invalid Name Format");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Invalid Email Format");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validatePhone(phone)) {
            session.setAttribute("phoneErr", "Invalid Phone Format");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passwordErr", "Invalid Password Format");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else {
            try {
                User check = manager.findUserByEmail(email);
                if (check == null) {
                    if (type.isEmpty()){
                        // manager.addUser(first_name, last_name, email, phone, password, address, "c");
                        // User user = manager.findUserByEmail(email);
                        // session.setAttribute("user", user);
                        request.getRequestDispatcher("main.jsp").include(request, response);
                    } 
                    else if (type.equals("s")){
                        // manager.addUser(first_name, last_name, email, phone, password, address, "s");
                        // User user = manager.findUserByEmail(email);
                        // session.setAttribute("user", user);
                        request.getRequestDispatcher("main.jsp").include(request, response);
                    } 
                    else if (type.equals("a")){
                        // manager.addUser(first_name, last_name, email, phone, password, address, "a");
                        // User user = manager.findUserByEmail(email);
                        // session.setAttribute("user", user);
                        request.getRequestDispatcher("main.jsp").include(request, response);
                    }
                }   else {
                    session.setAttribute("codeErr", "Incorrect Code");
                    request.getRequestDispatcher("register.jsp").include(request, response);
                }
            } catch (SQLException | NullPointerException ex) {
                System.out.println(ex.getMessage() == null ? "User doesn't exist" : "Welcome!");
            }
        }
    validator.clear(session);
    }
}