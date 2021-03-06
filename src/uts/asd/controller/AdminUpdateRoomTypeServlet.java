package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.User;
import uts.asd.model.dao.AdminDBManager;
import org.apache.commons.text.StringEscapeUtils;

public class AdminUpdateRoomTypeServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // ADMIN VIBE CHECK
        User currentUser = (User) session.getAttribute("user");
        String userType = (currentUser == null) ? "" : currentUser.getType();
        if(userType == null || !userType.equals("a")) return;

        Validator validator = new Validator();

        String id = StringEscapeUtils.unescapeHtml4(request.getParameter("id").trim());
        String suite = StringEscapeUtils.unescapeHtml4(request.getParameter("suite").trim());
        String cost = StringEscapeUtils.unescapeHtml4(request.getParameter("cost").trim());
        String numBeds = StringEscapeUtils.unescapeHtml4(request.getParameter("numberofbeds").trim());
        String desc = StringEscapeUtils.unescapeHtml4(request.getParameter("desc").trim());

        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        System.out.println(session.toString());

        
        if(suite == null || cost == null || numBeds == null || desc == null || id == null) {
            // null error
            session.setAttribute("roomtypemsg", "ERROR: Field is null");
            request.getRequestDispatcher("admin_roomtype_management.jsp").include(request, response);
            System.out.println("NULLFIELD");
        }
        else if (suite.isEmpty() || cost.isEmpty() || numBeds.isEmpty() || desc.isEmpty() || id.isEmpty()) {
            // empty field error
            session.setAttribute("roomtypemsg", "ERROR: Empty field/s");
            request.getRequestDispatcher("admin_roomtype_management.jsp").include(request, response);
            System.out.println("EMPTYFIELD");
        }
        /*
        else if (!(validator.validateFirstName(firstName) && validator.validateLastName(lastName) && validator.validateEmail(email) && validator.validatePhone(phone) && validator.validatePassword(password) && validator.validateAddress(address) && validator.validateType(type) )) {
            // validation error
            session.setAttribute("createusermsg", "ERROR: Invalid input!");
            request.getRequestDispatcher("admin_create_user.jsp").include(request, response);
        }
        */
        else {
            // no error
            try {
                manager.updateRoomType(Integer.parseInt(id), Double.parseDouble(cost), Integer.parseInt(numBeds), suite, desc);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            session.setAttribute("roomtypemsg", "RoomType has been updated");
            request.getRequestDispatcher("admin_roomtype_management.jsp").include(request, response);
        }


    }
}