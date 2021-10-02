package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.asd.model.Room;
import uts.asd.model.dao.AdminDBManager;
import uts.asd.model.dao.DBConnector;
import uts.asd.model.dao.DBManager;

public class AdminRoomManagementServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String roomNumber = request.getParameter("roomnumber");
        String roomType = request.getParameter("type");
        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        ArrayList<Room> roomList = null;
        System.out.println(session.toString());

        try {
            roomList = manager.fetchAllRooms();
            
        } catch (SQLException | NullPointerException ex) {

        }

        if(roomNumber == null || roomNumber.isEmpty()) {    // room num is null
            if(roomType == null || roomType.isEmpty()) {    // roomnum is null, roomtype is null
                session.setAttribute("roomList", roomList);
                request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
            } 
            else {  // roomnum is null, roomtype is not null
                try {
                    roomList = manager.fetchRoomBySuite(roomType);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                session.setAttribute("roomList", roomList);
                request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
            }
        }
        else { // room num is not null
            if(roomType == null || roomType.isEmpty()) {    // roomnum is not null, roomtype is null
                try {
                    roomList = manager.fetchRoomByNumber(Integer.parseInt(roomNumber));
                } catch (NumberFormatException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                session.setAttribute("roomList", roomList);
                request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
            } 
            else {  // roomnum is not null, roomtype is not null
                try {
                    roomList = manager.fetchRooms(Integer.parseInt(roomNumber), roomType);
                } catch (NumberFormatException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                session.setAttribute("roomList", roomList);
                request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
            }
        }
        
    }


}