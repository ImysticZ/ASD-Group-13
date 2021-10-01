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
        HttpSession session = request.getSession();
        // retrieve paymentDB from session
        PaymentDB paymentDB = (PaymentDB) session.getAttribute("paymentDB");

        User user = session.getAttribute("user") != null ? (User) session.getAttribute("user") : null; // retrieve user
                                                                                                       // from session
                                                                                                       // if exists
        // also retrieve Booking from session
        Card card = session.getAttribute("card") != null ? (Card) session.getAttribute("card") : null; // retrieve card
                                                                                                       // from session
                                                                                                       // if exists
        // validator.validatecvc(request.getParameter("cvc"));
        // System.out.println("Is the cvc valid" + validator.validatecvc(cvc));
        // System.out.println("validation for cvc is successful");
        String cardNo = request.getParameter("card").trim();
        String cvc = request.getParameter("cvc").trim();
        String date = request.getParameter("date");
        System.out.println("The cardNo is " + cardNo + " The cvc is " + cvc + " The date is " + date);
        
        //regex to check user input

        if (!cardNo.matches("^[0-9]{16}")) {
            System.out.println("*Invalid card Number, must be 16 digits*");
            session.setAttribute("cardErr", "*Invalid card Number, must be 16 digits*");
            request.getRequestDispatcher("payments.jsp").include(request, response);
        } else if (!cvc.matches("^[0-9]{3}")) {
            System.out.println("*Invalid cvc Number, must be 3 digits*");
            session.setAttribute("cardErr", "*Invalid cvc Number, must be 3 digits*");
            request.getRequestDispatcher("payments.jsp").include(request, response);
        } else {
            System.out.println("Input were validated");
            if (request.getParameter("save")!=null) { //save credit card to user database
                System.out.println("Scream and shout and let it all out"); // if user clicked 'save' 
                //should save user card 
            }
            try {
                paymentDB.saveCard(cardNo, cvc, date);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            request.getRequestDispatcher("success.jsp").include(request, response);

        }
        // if (request.getParameter("save")!=null) { //save credit card to user database
        // if user clicked 'save'
        // paymentDB.saveCard(cardNo, cvc, date);
        // card= paymentDB.returnCard(user);
        // }
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // }session.setAttribute("card",card); // save credit card session
        // make payment
        // paymentDB.makePayment(bookingID, card.getcardID());
        // session.setAttribute("date", request.getParameter("date")); //date type
        // cannot be resolved to a variable
    }
}
