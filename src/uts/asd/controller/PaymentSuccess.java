package uts.asd.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.*;
import uts.asd.model.dao.PaymentDB;

public class PaymentSuccess extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cardNo = request.getParameter("card");
        User user = (User) session.getAttribute("user");
        int userID = user.getId();
        PaymentDB manager = (PaymentDB) session.getAttribute("paymentDB");
        session.removeAttribute("cardErr");
        Card card = null;
        try {
            card = manager.authenticateCustomer(userID, cardNo);
            if (card != null){
                session.setAttribute("card", card);
                request.getRequestDispatcher("/main.jsp").include(request, response);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() == null ? "Error" : "System error " + ex.getMessage());
            request.getRequestDispatcher("/payments.jsp").include(request, response);
        }
    }
    
}
