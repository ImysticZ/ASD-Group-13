package uts.asd.model.dao;

import uts.asd.model.*;
import java.sql.*;
import java.util.ArrayList;

public class RoomDBManager {

    private Statement st;
    Connection con;

    public RoomDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
        con = conn;
    }

    public ArrayList<RoomType> listRooms() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType");
        ArrayList<RoomType> ret = new ArrayList<RoomType>();
        while (rs.next()) {
            ret.add(new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return ret;
    }

    public ArrayList<RoomType> listRoomByBedsAscending(int beds) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType WHERE NumBeds=" + beds + " ORDER BY CostPerDay ASC");
        ArrayList<RoomType> ret = new ArrayList<RoomType>();
        while (rs.next()) {
            ret.add(new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return ret;
    }
    public ArrayList<RoomType> listRoomByBedsDescending(int beds) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType WHERE NumBeds=" + beds + " ORDER BY CostPerDay DESC");
        ArrayList<RoomType> ret = new ArrayList<RoomType>();
        while (rs.next()) {
            ret.add(new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return ret;
    }

    public ArrayList<RoomType> listRoomAscending() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType ORDER BY CostPerDay ASC");
        ArrayList<RoomType> ret = new ArrayList<RoomType>();
        while (rs.next()) {
            ret.add(new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return ret;
    }
    public ArrayList<RoomType> listRoomDescending() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType ORDER BY CostPerDay DESC");
        ArrayList<RoomType> ret = new ArrayList<RoomType>();
        while (rs.next()) {
            ret.add(new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return ret;
    }

    public RoomType findRoomById(int id) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType WHERE RoomTypeID=" + id);
        if (rs.next()) {
            RoomType ret = new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            return ret;
        }
        return null;
    }

    public ArrayList<Room> listRoomsByID(int ID) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM Room WHERE RoomTypeID=" + ID);
        ArrayList<Room> ret = new ArrayList<Room>();
        while (rs.next()) {
            ret.add(new Room(rs.getInt(1), rs.getInt(2), true));
        }
        return ret;
    }

    public int addNewBooking(int userID, int roomID, String startingDate, String endingDate, String status, boolean paid, double totalCost) throws SQLException{
        String q = "INSERT INTO Booking(UserID, RoomID, Starting_Date, Ending_Date, Status, Paid, Total_Cost)";
        String v = String.format("VALUES(%s, %s, '%s', '%s', '%s', %s, %s)", userID, roomID, startingDate, endingDate, status, paid, totalCost);
        String[] key = {"BookingID"};
        PreparedStatement ps = con.prepareStatement(q+v, key);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public Booking findBookingByID(int bookingID) throws SQLException{
        ResultSet rs = st.executeQuery("SELECT * FROM Booking WHERE BookingID=" + bookingID);
        rs.next();
        return new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getDouble(8));
    }

    public ArrayList<Booking> listBookingByUserID(int userID) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM Booking WHERE UserID=" + userID);
        ArrayList<Booking> ret = new ArrayList<Booking>();
        while (rs.next()) {
            ret.add(new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getDouble(8)));
        }
        return ret;
    }


}
