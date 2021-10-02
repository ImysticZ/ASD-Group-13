package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.asd.model.*;
import uts.asd.model.dao.*;

public class CustomerBooking extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // retrieve paymentDB from session
        PaymentDB paymentDB = (PaymentDB) session.getAttribute("paymentDB");
        try {
            ArrayList<Booking> allBooking = paymentDB.fetchBooking();
            session.setAttribute("bookings", allBooking);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("cusBooking.jsp").include(request, response);
    }
}
