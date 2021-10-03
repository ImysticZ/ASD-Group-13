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

public class AdminDeleteRoomsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String min = request.getParameter("lowerbound");
        String max = request.getParameter("upperbound");

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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            session.setAttribute("msg", "Rooms deleted");
            request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
        }


    }
}