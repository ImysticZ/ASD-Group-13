package uts.asd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.*;
import uts.asd.model.dao.*;
import java.util.*;

public class CancelBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RoomDBManager manager = (RoomDBManager) session.getAttribute("room");
        try {
            int bookingID = Integer.parseInt(request.getParameter("bookingID"));
            manager.cancelBooking(bookingID);
            int userID = ((User)session.getAttribute("user")).getId();;
            ArrayList<Booking> bookings = manager.listBookingByUserID(userID);
            session.setAttribute("bookings", bookings); // sets the booking into session
            request.getRequestDispatcher("listBookings.jsp").include(request, response);
            
        }
        catch (Exception e) {
            
        }
    }
}