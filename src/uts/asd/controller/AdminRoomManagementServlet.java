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

public class AdminRoomManagementServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // ADMIN VIBE CHECK
        User currentUser = (User) session.getAttribute("user");
        String userType = (currentUser == null) ? "" : currentUser.getType();
        if(userType == null || !userType.equals("a")) return;

        Validator validator = new Validator();
        String roomNumber = StringEscapeUtils.unescapeHtml4(request.getParameter("roomnumber").trim());
        String roomType = StringEscapeUtils.unescapeHtml4(request.getParameter("type").trim());
        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        ArrayList<Room> roomList = null;
        System.out.println(session.toString());

        try {
            roomList = manager.fetchAllRooms();
            
        } catch (SQLException | NullPointerException ex) {

        }

        if(roomNumber == null || roomNumber.isEmpty()) {    // room num is null
            if(roomType == null || roomType.isEmpty() || roomType.equals("none123123")) {    // roomnum is null, roomtype is null
                session.setAttribute("roomList", roomList);
                request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
            } 
            else {  // roomnum is null, roomtype is not null
                try {
                    roomList = manager.fetchRoomBySuite(roomType);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("roomList", roomList);
                request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
            }
        }
        else { // room num is not null
            if(roomType == null || roomType.isEmpty() || roomType.equals("none123123")) {    // roomnum is not null, roomtype is null
                try {
                    roomList = manager.fetchRoomByNumber(Integer.parseInt(roomNumber));
                } catch (NumberFormatException | SQLException e) {
                    e.printStackTrace();
                }
                session.setAttribute("roomList", roomList);
                request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
            } 
            else {  // roomnum is not null, roomtype is not null
                try {
                    roomList = manager.fetchRooms(Integer.parseInt(roomNumber), roomType);
                } catch (NumberFormatException | SQLException e) {
                    e.printStackTrace();
                }

                session.setAttribute("roomList", roomList);
                request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
            }
        }
        
    }


}