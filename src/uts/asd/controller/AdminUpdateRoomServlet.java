package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.asd.model.Room;
import uts.asd.model.User;
import uts.asd.model.dao.AdminDBManager;
import org.apache.commons.text.StringEscapeUtils;

public class AdminUpdateRoomServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // ADMIN VIBE CHECK
        User currentUser = (User) session.getAttribute("user");
        String userType = (currentUser == null) ? "" : currentUser.getType();
        if(userType == null || !userType.equals("a")) return;

        Validator validator = new Validator();

        String id = StringEscapeUtils.unescapeHtml4(request.getParameter("id").trim());
        String type = StringEscapeUtils.unescapeHtml4(request.getParameter("roomtype").trim());


        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        System.out.println(session.toString());

        
        if(id == null || type == null) {
            // null error
            session.setAttribute("editroommsg", "ERROR: Field is null");
            request.getRequestDispatcher("admin_update_room.jsp").include(request, response);
            System.out.println("NULLFIELD");
        }
        else if (id.isEmpty() || type.isEmpty()) {
            // empty field error
            session.setAttribute("editroommsg", "ERROR: Empty field/s");
            request.getRequestDispatcher("admin_update_room.jsp").include(request, response);
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
                manager.updateRoom(Integer.parseInt(id), Integer.parseInt(type));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ArrayList<Room> roomList = null;
            try {
                roomList = manager.fetchAllRooms();
            } catch (SQLException | NullPointerException ex) {

            }
            session.setAttribute("roomList", roomList);
            session.setAttribute("editroommsg", "Room updated");
            request.getRequestDispatcher("admin_update_room.jsp").include(request, response);
        }


    }
}