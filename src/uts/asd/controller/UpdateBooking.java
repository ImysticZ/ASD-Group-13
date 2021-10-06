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
        HttpSession session = request.getSession(); //retrieve session
        PaymentDB paymentDB = (PaymentDB) session.getAttribute("paymentDB"); // retrieve paymentDB from session
        int bookingID= Integer.parseInt(request.getParameter("bookingID")); //retrieve booking ID parameter
        String status= request.getParameter("status");
        for(Booking b: (ArrayList<Booking>) session.getAttribute("bookings")) { //Loop through list of bookings in session
            if (b.getBookingID()== bookingID) {
                b.setStatus(status);
                try {                    
                    paymentDB.updateBooking(b, status);  //update the status of booking 
                    request.getRequestDispatcher("cusBooking.jsp").include(request, response); //direct back to customer booking page
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    } 
}
