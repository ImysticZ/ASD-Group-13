package uts.asd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.dao.*;

public class AddEnquiryServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String question = request.getParameter("question");
        String reply = "";

        EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");

        try {
            manager.addEnquiry(question, reply, 1011);
            request.getRequestDispatcher("enquirySubmitted.jsp").include(request, response);
               
        } catch (SQLException ex) {
            Logger.getLogger(AddEnquiryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
