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
import java.util.ArrayList;
import uts.asd.model.*;
import uts.asd.model.dao.*;

public class FilterEnquiriesServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Validator validator = new Validator();

        String userID = request.getParameter("UserID");
        String status = request.getParameter("status");

        User user = (User) session.getAttribute("user");
        
        EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");
        if(validator.checkEmpty(status, "a") && user.getType().equals("c")){
            session.setAttribute("filterEmptyErr", "Please select enquiry status.");
            request.getRequestDispatcher("userEnquiry.jsp").include(request, response);
        }else if (validator.checkEmpty(status, "a") && validator.checkEmpty(userID, "a") && user.getType().equals("s")){
            session.setAttribute("filterEmptyErr", "Please select enquiry status or user ID.");
            request.getRequestDispatcher("staffEnquiry.jsp").include(request, response);
        }else{
            try {
                if(status.equals("resolved") && user.getType().equals("c")){
                    ArrayList<Enquiry> enquiries =  manager.filterEnquiries(true, userID);
                    request.setAttribute("filteredEnquiries", enquiries);
                    request.getRequestDispatcher("filteredUserEnquiries.jsp").include(request, response);
                }else if(status.equals("pending") && user.getType().equals("c")){
                    ArrayList<Enquiry> enquiries =  manager.filterEnquiries(false, userID);
                    request.setAttribute("filteredEnquiries", enquiries);
                    request.getRequestDispatcher("filteredUserEnquiries.jsp").include(request, response);
                }else if(status.equals("resolved") && userID.isEmpty()){
                    ArrayList<Enquiry> enquiries =  manager.filterEnquiriesByStatus(true);
                    request.setAttribute("filteredEnquiries", enquiries);
                    request.getRequestDispatcher("filteredStaffEnquiries.jsp").include(request, response);
                }else if(status.equals("pending") && userID.isEmpty()){
                    ArrayList<Enquiry> enquiries =  manager.filterEnquiriesByStatus(false);
                    request.setAttribute("filteredEnquiries", enquiries);
                    request.getRequestDispatcher("filteredStaffEnquiries.jsp").include(request, response);
                }else if(status.equals("resolved") && !userID.isEmpty() && user.getType().equals("s")){
                    ArrayList<Enquiry> enquiries =  manager.filterEnquiries(true, userID);
                    request.setAttribute("filteredEnquiries", enquiries);
                    request.getRequestDispatcher("filteredStaffEnquiries.jsp").include(request, response);
                }else if(status.equals("pending") && !userID.isEmpty() && user.getType().equals("s")){
                    ArrayList<Enquiry> enquiries =  manager.filterEnquiries(false, userID);
                    request.setAttribute("filteredEnquiries", enquiries);
                    request.getRequestDispatcher("filteredStaffEnquiries.jsp").include(request, response);
                }else if(status.isEmpty()){
                    ArrayList<Enquiry> enquiries =  manager.findEnquiriesByID(Integer.parseInt(userID));
                    request.setAttribute("filteredEnquiries", enquiries);
                    request.getRequestDispatcher("filteredStaffEnquiries.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddEnquiryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        validator.clear(session);
    }
}
