package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.dao.AdminDBManager;

public class AdminUpdateRoomsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();

        String min = request.getParameter("lowerbound");
        String max = request.getParameter("upperbound");
        String typeId = request.getParameter("roomtype");

        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        System.out.println(session.toString());

        
        if(min == null || max == null || typeId == null) {
            // null error
            session.setAttribute("updateroommsg", "ERROR: Field is null");
            
            System.out.println("NULLFIELD");
        }
        else if (min.isEmpty() || max.isEmpty() || typeId.isEmpty()) {
            // empty field error
            session.setAttribute("updateroommsg", "ERROR: Empty field/s");
            request.getRequestDispatcher("admin_update_rooms.jsp").include(request, response);
            System.out.println("EMPTYFIELD");
        }
        else if (Integer.parseInt(min) > Integer.parseInt(max)) {
            // min over max
            session.setAttribute("updateroommsg", "ERROR: Lower bound must be less than upper bound");
            request.getRequestDispatcher("admin_update_rooms.jsp").include(request, response);
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
                manager.updateRoomRange(Integer.parseInt(min), Integer.parseInt(max), Integer.parseInt(typeId));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            session.setAttribute("updateroommsg", "Room has been added");
            request.getRequestDispatcher("admin_create_room.jsp").include(request, response);
        }


    }
}