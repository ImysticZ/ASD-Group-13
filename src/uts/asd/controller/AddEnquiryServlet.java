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
        Validator validator = new Validator();

        String question = request.getParameter("question");
        String reply = "";
        String id = request.getParameter("id");
        
        EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");

        if(validator.checkEmpty(question, "a")){
            session.setAttribute("enquiryEmptyErr", "Please fill in the details of your enquiry");
            request.getRequestDispatcher("addEnquiry.jsp").include(request, response);
        }else{
            try {
                manager.addEnquiry(question, reply, Integer.parseInt(id));
                request.getRequestDispatcher("enquirySubmitted.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AddEnquiryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        validator.clear(session);

    }
    
}
