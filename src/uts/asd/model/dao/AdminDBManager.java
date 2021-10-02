package uts.asd.model.dao;

import uts.asd.model.*;
import java.sql.*;
import java.util.ArrayList;

public class AdminDBManager {

    private Statement st;

    public AdminDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    /*
        Admin User Management
     */
    // Fetch all Users by first name
    public ArrayList<User> fetchUsersFirstName(String fname) throws SQLException {
        ResultSet rs = st.executeQuery("select * from USER WHERE TRIM(FIRST_NAME)= '"+fname+"'");
        ArrayList<User> temp = new ArrayList<User>();
        System.out.println(st.toString());

        while(rs.next()) {
            
            temp.add(new User(rs));
        }
        return temp;
    }

    // Fetch every single users
    public ArrayList<User> fetchAllUsers() throws SQLException {
        ResultSet rs = st.executeQuery("select * from USER");
        ArrayList<User> temp = new ArrayList<User>();

        while(rs.next()) {
            temp.add(new User(rs));
        }
        return temp;
    }

    /*
        Admin Room management
     */
    // Fetch every single room
    public ArrayList<Room> fetchAllRooms() throws SQLException {
        ResultSet rs = st.executeQuery("select * from Room");
        ArrayList<Room> temp = new ArrayList<Room>();

        while(rs.next()) {
            int roomId = rs.getInt(1);
            int roomTypeId = rs.getInt(2);
            temp.add(new Room(roomId, roomTypeId));
        }

        for(Room room : temp) {
            boolean isAvailable = checkRoomAvailability(room.getRoomId());
            room.setAvailability(!isAvailable);
        }
        return temp;
    }


    // Helper function to check room availability
    private boolean checkRoomAvailability(int id) throws SQLException {
        ResultSet rset = st.executeQuery("select * from Booking where RoomID =" + id);
        while(rset.next()) {
            Date startDate = rset.getDate(4);
            Date endDate = rset.getDate(5);
            long millis=System.currentTimeMillis();  
            Date currentDate=new Date(millis);  
            if(currentDate.after(startDate) && currentDate.before(endDate)) {
                return true;
            }
        }
        return false;
    }

    // Helper function to get room type id
    private int checkRTypeID(String suite) throws SQLException {
        ResultSet rs = st.executeQuery("select * from RoomType where Suite = '"+suite+"'");
        while(rs.next())
            return rs.getInt(1);
        return 0;
    }

    // Get room suite name
    public String getSuite(int id) throws SQLException {
        ResultSet rs = st.executeQuery("select * from RoomType where RoomTypeID = "+id);
        while(rs.next()) 
            return rs.getString(4);
        return "/!\\  Unknown Type  /!\\";
    }
}
