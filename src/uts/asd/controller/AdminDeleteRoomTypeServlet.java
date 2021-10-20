package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.dao.AdminDBManager;

public class AdminDeleteRoomTypeServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();

        String id = request.getParameter("id");

        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        System.out.println(session.toString());

        
        if(id==null) {
            // null error
            session.setAttribute("roomtypemsg", "ERROR: Field is null");
            request.getRequestDispatcher("admin_roomtype_management.jsp").include(request, response);
            System.out.println("NULLFIELD");
        }
        else if (id.isEmpty()) {
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
                manager.deleteRoomType(Integer.parseInt(id));;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute("roomtypemsg", "RoomType has been deleted");
            request.getRequestDispatcher("admin_roomtype_management.jsp").include(request, response);
        }


    }
}