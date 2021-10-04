package uts.asd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.asd.model.dao.AdminDBManager;

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

            session.setAttribute("roommsg", "Rooms deleted");
            request.getRequestDispatcher("admin_room_management.jsp").include(request, response);
        }


    }
}