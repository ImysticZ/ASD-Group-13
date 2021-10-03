package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.asd.model.Booking;
import uts.asd.model.dao.*;

public class UpdateBooking extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // retrieve paymentDB from session
        PaymentDB paymentDB = (PaymentDB) session.getAttribute("paymentDB");
        int bookingID= Integer.parseInt(request.getParameter("bookingID"));
        String status= request.getParameter("status");
        for(Booking b: (ArrayList<Booking>) session.getAttribute("bookings")) {
            if (b.getBookingID()== bookingID) {
                b.setStatus(status);
                try {                    
                    paymentDB.updateBooking(b, status);
                    System.out.println("Update Booking works lol");
                    request.getRequestDispatcher("cusBooking.jsp").include(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    } 
}
