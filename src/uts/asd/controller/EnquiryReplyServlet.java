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

public class EnquiryReplyServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();

        String id = request.getParameter("id");
        String question = request.getParameter("question");
        String reply = request.getParameter("reply");
        String userID = request.getParameter("UserID");
        
        EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");

        if(validator.checkEmpty(reply, "a")){
            session.setAttribute("enquiryID", id);
            session.setAttribute("enquiryEmptyErr", "Please fill in your reply.");
            request.getRequestDispatcher("enquiryReply.jsp").include(request, response);
        }else{
            try {
                manager.updateEnquiry(Integer.parseInt(id), question, reply, true, Integer.parseInt(userID));
                request.getRequestDispatcher("staffEnquiry.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EnquiryReplyServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        validator.clear(session);
    }

}
