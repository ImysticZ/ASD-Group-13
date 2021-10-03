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

    // Add User
    public void addUser(String firstname, String lastname, String email, String phone, String password, String address, String type) throws SQLException {
        String query = "insert into USER (FIRST_NAME, LAST_NAME, EMAIL, PHONE, PASSWORD, ADDRESS, TYPE)" + "values (' "+ firstname + " ',' "+  lastname + "',' "+ email + " ',' "+ phone + " ',' "+ password +"',' "+ address + " ', '"+type+"')";
        st.executeUpdate(query);
    }

    // Remove User
    public void deleteUser(int id) throws SQLException {
        String query = "delete from USER where ID = " + id;
        st.executeUpdate(query);
    }

    // Update User
    public void updateUser(int id, String firstName, String lastName, String email, String phone, String password, String address, String type) throws SQLException {
        String query = "update USER set FIRST_NAME = '"+firstName+"', LAST_NAME = '"+lastName+"', EMAIL = '"+email+"', PHONE = '"+phone+"', PASSWORD = '"+password+"', ADDRESS = '"+address+"', TYPE = '"+type+"' where ID = "+id;
        st.executeUpdate(query);
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

    // Fetch rooms by suite
    public ArrayList<Room> fetchRoomBySuite(String suite) throws SQLException {
        ArrayList<Room> rooms = fetchAllRooms();
        ArrayList<Room> temp = new ArrayList<Room>();
        int suiteId = checkRTypeID(suite);
        for(Room room : rooms) {
            if(room.getRoomTypeId() == suiteId)
                temp.add(room);
        }
        return temp;
    }

    // Fetch rooms by number
    public ArrayList<Room> fetchRoomByNumber(int id) throws SQLException {
        ArrayList<Room> rooms = fetchAllRooms();
        ArrayList<Room> temp = new ArrayList<Room>();
        for(Room room : rooms) {
            if(room.getRoomId() == id)
                temp.add(room);
        }
        return temp;
    }

    // Fetch rooms by both number and suite
    public ArrayList<Room> fetchRooms(int id, String suite) throws SQLException {
        ResultSet rs = st.executeQuery("select * from Room where RoomID = " + id + " and RoomTypeId = " + checkRTypeID(suite));
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

    // Add room by range
    // Delete room
    // Update room


    /*
        Helper Functions
    */
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
