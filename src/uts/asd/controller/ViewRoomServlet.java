package uts.asd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.*;
import uts.asd.model.dao.*;

public class ViewRoomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RoomDBManager manager = (RoomDBManager) session.getAttribute("room");
        try {
            int roomId = Integer.parseInt(request.getParameter("roomid"));
            RoomType room = manager.findRoomById(roomId);
            session.setAttribute("theroom", room);
            request.getRequestDispatcher("room.jsp").include(request, response);
        }
        catch (Exception e) {
            request.getRequestDispatcher("viewRoom.jsp").include(request, response);
        }
    }
}