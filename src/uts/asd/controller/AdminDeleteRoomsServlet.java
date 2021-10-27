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

public class AdminDeleteRoomsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // ADMIN VIBE CHECK
        User currentUser = (User) session.getAttribute("user");
        String userType = (currentUser == null) ? "" : currentUser.getType();
        if(userType == null || !userType.equals("a")) return;

        String min = StringEscapeUtils.unescapeHtml4(request.getParameter("lowerbound").trim());
        String max = StringEscapeUtils.unescapeHtml4(request.getParameter("upperbound").trim());

        AdminDBManager manager = (AdminDBManager) session.getAttribute("adminmngr");
        System.out.println(session.toString());

        if(min == null || max == null) {
            request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
        }
        else if (min.isEmpty() || max.isEmpty()) {
            request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
        }
        else {
            try {
                manager.deleteRoomRange(Integer.parseInt(min), Integer.parseInt(max));
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }
            ArrayList<Room> roomList = null;
            try {
                roomList = manager.fetchAllRooms();
            } catch (SQLException | NullPointerException ex) {

            }
            session.setAttribute("roomList", roomList);

            session.setAttribute("roommsg", "Rooms deleted");
            request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
        }


    }
}