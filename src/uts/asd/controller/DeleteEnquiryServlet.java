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

public class DeleteEnquiryServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String id = request.getParameter("enquiryID");

        EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");

        try {
            manager.deleteEnquiry(Integer.parseInt(id));
            request.getRequestDispatcher("staffEnquiry.jsp").include(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(DeleteEnquiryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
