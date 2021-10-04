package uts.asd.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.*;
import uts.asd.model.dao.*;
import java.util.*;

public class RoomFilterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int num = -1;

        if (!request.getParameter("quantity").equals("")) {
            num = Integer.parseInt(request.getParameter("quantity"));
        }
        String sort = request.getParameter("sort");

        RoomDBManager manager = (RoomDBManager) session.getAttribute("room");
        try {
            if (num == -1) {
                if (sort.equals("ascending")) {
                    ArrayList<RoomType> rooms = manager.listRoomAscending();
                    session.setAttribute("rooms", rooms);
                    request.getRequestDispatcher("viewRoom.jsp").include(request, response);
                }
                else {
                    ArrayList<RoomType> rooms = manager.listRoomDescending();
                    session.setAttribute("rooms", rooms);
                    request.getRequestDispatcher("viewRoom.jsp").include(request, response);
                }
            }

            else {
                if (sort.equals("ascending")) {
                    ArrayList<RoomType> rooms = manager.listRoomByBedsAscending(num);
                    session.setAttribute("rooms", rooms);
                    request.getRequestDispatcher("viewRoom.jsp").include(request, response);
                }
                else {
                    ArrayList<RoomType> rooms = manager.listRoomByBedsDescending(num);
                    session.setAttribute("rooms", rooms);
                    request.getRequestDispatcher("viewRoom.jsp").include(request, response);
                }
            }
        }
        catch (Exception e) {
            
        }
    }
}