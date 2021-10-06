package uts.asd.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentSuccess extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); //retrieve session
        session.removeAttribute("cardErr");  //clear the server side error message variable
        request.getRequestDispatcher("/index.jsp").include(request, response); //direct to home page
    }  
}
