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
        HttpSession session = request.getSession(); // retrieve session
        PaymentDB paymentDB = (PaymentDB) session.getAttribute("paymentDB"); // retrieve paymentDB from session
        try {
            ArrayList<Booking> allBooking = paymentDB.fetchBooking(); // fetch all Customer bookings and store in arraylist 
            session.setAttribute("bookings", allBooking); //store list of booking in session variable
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("cusBooking.jsp").include(request, response); //direct to Customer Bookings page
    }
}
