package uts.asd.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uts.asd.model.Booking;

public class testPaymentDB {
    private Statement st; // statement object

    public testPaymentDB(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
    
    public void testCheckInPatron(String status) throws SQLException {
        String sql = "UPDATE Booking SET Status = '" + status + "' WHERE BookingID = 31";
        st.executeUpdate(sql);
    }
    
    public Booking returnBooking() throws SQLException {
        String sql = "SELECT * FROM Booking WHERE BookingID=31";
        ResultSet rs= st.executeQuery(sql);
        rs.next();
        return new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getDouble(8));
    }

    public ArrayList<Booking> fetchBooking() throws SQLException {
        String query = "SELECT * from Booking";
        ResultSet rs = st.executeQuery(query); // store in resultSet
        ArrayList<Booking> allBooking = new ArrayList<Booking>();
        while (rs.next()) {
            allBooking.add(new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getDouble(8)));
        }
        return allBooking;
    }

    public int countBookings() throws SQLException {
        String sql= "SELECT COUNT(BookingID) FROM Booking";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        return rs.getInt(1);
    }
}
