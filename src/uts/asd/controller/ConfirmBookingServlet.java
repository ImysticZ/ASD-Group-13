package uts.asd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.*;
import uts.asd.model.dao.*;

public class ConfirmBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RoomDBManager manager = (RoomDBManager) session.getAttribute("room");
        try {
            int userID = ((User)session.getAttribute("user")).getId();
            int roomID = Integer.parseInt(request.getParameter("roomID"));
            String startingDate = (String)request.getParameter("startingDate");
            String endingDate = (String)request.getParameter("endingDate");
            double totalCost = Double.parseDouble(request.getParameter("totalCost"));
            int bookingID = manager.addNewBooking(userID, roomID, startingDate, endingDate, "Booked", false, totalCost);
            Booking newBooking = manager.findBookingByID(bookingID);
            session.setAttribute("booking", newBooking);
            request.getRequestDispatcher("payments.jsp").include(request, response);
        }
        catch (Exception e) {
            
        }
    }
}