package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.Card;
import uts.asd.model.dao.DBManager;
import uts.asd.model.dao.PaymentDB;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DBManager manager = (DBManager) session.getAttribute("manager");
        PaymentDB paymentDB = (PaymentDB) session.getAttribute("paymentDB");
        User user = null;
        Card card = null;

        if (!validator.validateEmail(email)) {
            session.setAttribute("emailErr", "Invalid Email Format");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.validatePassword(password)) {
            session.setAttribute("passErr", "Invalid Password Format");
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else {
            try {
                user = manager.authenticateUser(email, password);
                if (user != null) {
                    session.setAttribute("user", user);
                    card = paymentDB.returnCard(user);
                    session.setAttribute("card", card);
                    request.getRequestDispatcher("main.jsp").include(request, response);
                } else {
                    session.setAttribute("existErr", "Incorrect email or password. Please try again");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
            } catch (SQLException | NullPointerException ex) {
                System.out.println(ex.getMessage() == null ? ex.getMessage() : ex.getMessage());
            }
        }
        validator.clear(session);
    }
}
