package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.Booking;
import uts.asd.model.dao.DBManager;


public class DeleteAccServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
            manager.deleteUser(user.getId());
            session.invalidate();
            request.getRequestDispatcher("accDeleted.jsp").include(request, response);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() == null ? "Unable to delete account" : ex.getMessage());
        }
    }
}
