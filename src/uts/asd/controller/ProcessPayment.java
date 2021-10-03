package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.asd.model.*;
import uts.asd.model.dao.PaymentDB;

public class ProcessPayment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); // retrieve session

        PaymentDB paymentDB = (PaymentDB) session.getAttribute("paymentDB"); // retrieve paymentDB from session

        // retrieve user from session if exists
        User user = session.getAttribute("user") != null ? (User) session.getAttribute("user") : null; 

        // retrieve card from session if exists
        Card card = session.getAttribute("card") != null ? (Card) session.getAttribute("card") : null; 
        
        // retrieve booking from session if exists
        Booking booking = session.getAttribute("booking") != null ? (Booking) session.getAttribute("booking") : null; 

        String cardNo = request.getParameter("card").trim();
        String cvc = request.getParameter("cvc").trim();
        String date = request.getParameter("date");

        Card userCard= null;

        // regex to check user input

        if (!cardNo.matches("^[0-9]{16}")) { // check cardNo is in correct format
            session.setAttribute("cardErr", "*Invalid card Number, must be 16 digits*");
            request.getRequestDispatcher("payments.jsp").include(request, response);
        } else if (!cvc.matches("^[0-9]{3}")) { // check cvc is in correct format
            session.setAttribute("cardErr", "*Invalid cvc Number, must be 3 digits*");
            request.getRequestDispatcher("payments.jsp").include(request, response);
        } else {
            System.out.println("Input were validated");
            try {
                userCard = paymentDB.saveCard(cardNo, cvc, date); // card details inserted by user
                System.out.println("card detail stored");
                // paymentDB.makePayment(booking.getBookingID(), userCard.getcardID());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            if (request.getParameter("save") != null) { // save credit card to user database
                try {
                //    System.out.println(" card ID is " + userCard.getcardID());
                //    System.out.println("user id is " + user.getId()); 
                    paymentDB.saveCardToUser(user.getId(), userCard);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("card", userCard);
                System.out.println("card is " + userCard.getnumber() + " cvc is " + userCard.getcvc() + " date is " + userCard.getdate());
                // System.out.println("Scream and shout and let it all out");  successful
            }
            request.getRequestDispatcher("success.jsp").include(request, response);

        }
    }
}
