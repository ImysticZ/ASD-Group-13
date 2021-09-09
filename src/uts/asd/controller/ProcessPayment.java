package uts.asd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.asd.model.*;

public class ProcessPayment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user= session.getAttribute("User")!=null ? (User) session.getAttribute("User") : 
        new User(1, "Berat", "Appak" , "appak123@gmail.com" , "045768" , "password" , "street" , "C" );
        int cardNo= Integer.parseInt(request.getParameter("card"));
        int cvc= Integer.parseInt(request.getParameter("cvc"));
        String date= request.getParameter("date");        
        CreditCard card= new CreditCard(1, cardNo, cvc, date);
        if (request.getParameter("save")!=null) {
        }
        session.setAttribute("card", card);
        Payment payment= new Payment(1, 1, 1);
        session.setAttribute("date", request.getParameter("date")); //date type cannot be resolved to a variable
        request.getRequestDispatcher("success.jsp").include(request, response);
    }
}
