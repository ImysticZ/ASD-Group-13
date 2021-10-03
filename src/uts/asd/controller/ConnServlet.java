package uts.asd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.asd.model.dao.*;

public class ConnServlet extends HttpServlet {
    private DBConnector db;
    private DBManager manager;
    private PaymentDB paymentDB;
    private Connection conn;
    private RoomDBManager room;
    private EnquiryDBManager enquiryManager;
    
    @Override //Create and instance of DBConnector for the deployment session
    public void init(){
        try {
            db = new DBConnector();
            System.out.println("Instance of DBConnector created");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("oh no");
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override //Add the DBConnector, DBManager, Connection instances to the session
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        conn = db.openConnection();
        try {
            room = new RoomDBManager(conn);
            manager = new DBManager(conn);
            enquiryManager = new EnquiryDBManager(conn);
            paymentDB= new PaymentDB(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //export the DB manager to the view-session (JSPs)
        session.setAttribute("room", room);
        session.setAttribute("manager", manager);
        session.setAttribute("enquiryManager", enquiryManager);
        session.setAttribute("paymentDB", paymentDB);
    }

    @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
