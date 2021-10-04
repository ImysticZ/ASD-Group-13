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

public class ViewBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RoomDBManager manager = (RoomDBManager) session.getAttribute("room");
        try {
            int userID = ((User)session.getAttribute("user")).getId();;
            ArrayList<Booking> bookings = manager.listBookingByUserID(userID);
            session.setAttribute("bookings", bookings);
            request.getRequestDispatcher("listBookings.jsp").include(request, response);
        }
        catch (Exception e) {
            
        }
    }
}