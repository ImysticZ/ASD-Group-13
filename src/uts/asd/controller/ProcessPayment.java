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

        String cardNo = request.getParameter("card").trim(); //retrive cardNo from parameter
        String cvc = request.getParameter("cvc").trim(); //retrive cvc from parameter
        String date = request.getParameter("date"); //retrive date from parameter

        Card userCard= null; //initiate card class based on user card details

        // regex to check user input

        if (!cardNo.matches("^[0-9]{16}")) { // check cardNo is in correct format
            session.setAttribute("cardErr", "*Invalid card Number, must be 16 digits*");
            request.getRequestDispatcher("payments.jsp").include(request, response); // return to payments page
        } else if (!cvc.matches("^[0-9]{3}")) { // check cvc is in correct format
            session.setAttribute("cardErr", "*Invalid cvc Number, must be 3 digits*");
            request.getRequestDispatcher("payments.jsp").include(request, response); // return to payments page
        } else {
            System.out.println("Input were validated");
            try {
                userCard = paymentDB.saveCard(cardNo, cvc, date); // card details inserted by user saved to database 
                paymentDB.makePayment(booking.getBookingID(), userCard.getcardID()); // insert payment record
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            if (request.getParameter("save") != null) { // save credit card to user for auto - fill, allow system to remember card
                try {
                    paymentDB.saveCardToUser(user.getId(), userCard);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("card", userCard); // save user card to session
                System.out.println("card is " + userCard.getnumber() + " cvc is " + userCard.getcvc() + " date is " + userCard.getdate());
            }
            request.getRequestDispatcher("success.jsp").include(request, response); // direct to booking successful page

        }
    }
}
