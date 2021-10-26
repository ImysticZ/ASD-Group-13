package uts.asd.model.dao;

import uts.asd.model.*;
import java.sql.*;
import java.util.ArrayList;
public class PaymentDB {
    private PreparedStatement ps; // statement object
    Connection con;

    public PaymentDB(Connection conn) throws SQLException {
        con = conn;
    }
    
    // Payment DAO METHODS
    //saves user card for payment of booking
    public void saveCard(String cardNo, String cvc, String date) throws SQLException {
        String query = "INSERT INTO Card (Card_Number, cvc, Expiry) VALUES(?, ?, ?)";
        ps = con.prepareStatement(query);
        ps.setString(1, cardNo);
        ps.setString(2, cvc);
        ps.setString(3, date);
        ps.executeUpdate();
    }
    
    public Card returnLastCard() throws SQLException {
        String query="SELECT * FROM Card WHERE CardID= (SELECT MAX(CardID) FROM Card)";
        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        Card card= null;
        while (rs.next()) {
            card = new Card(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        return card;
    }


    // To create payment when user completes transaction
    public void makePayment(int bookingID, int cardID) throws SQLException {
        String query = "INSERT INTO Payment (BookingID, cardId) VALUES (?, ?)";
        ps = con.prepareStatement(query);
        ps.setInt(1, bookingID);
        ps.setInt(2, cardID);
        ps.executeUpdate();
    }

    // Saves card details to user account
    public void saveCardToUser(int userID, Card card) throws SQLException {
        String query="UPDATE CUSTOMER SET CardID= ? WHERE ID = ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, card.getcardID());
        ps.setInt(2, userID);
        ps.executeUpdate();
    }

    // Return credit card information of a user
    public Card returnCard(User user) throws SQLException {
        String query = "SELECT * from Card INNER JOIN CUSTOMER on Card.CardID = CUSTOMER.CardID WHERE CUSTOMER.ID= ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, user.getId());
        ResultSet rs = ps.executeQuery(); // store in resultSet
        Card card = null;
        while (rs.next()) {
            card = new Card(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        return card;
    }

    public ArrayList<Booking> fetchBooking() throws SQLException {
        String query = "SELECT * from Booking";
        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery(); // store in resultSet
        ArrayList<Booking> allBooking = new ArrayList<Booking>();
        while (rs.next()) {
            allBooking.add(new Booking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getDouble(8)));
        }
        return allBooking;
    }

    public void updateBooking(Booking booking, String status) throws SQLException {
        String query = "UPDATE Booking SET Status = ? WHERE BookingID = ?";
        ps = con.prepareStatement(query);
        ps.setString(1, status);
        ps.setInt(2, booking.getBookingID());
        ps.executeUpdate();
    }

    public Card authenticateCustomer(int id, String card) throws SQLException { 
        String query= "select * from CUSTOMER WHERE TRIM(ID)= ? AND TRIM(CardID)= ?";
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.setString(2, card);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String userID = rs.getString(1);
            String cardNo = rs.getString(2);
            if (userID.replaceAll("\\s+","").equalsIgnoreCase(userID.replaceAll("\\s+","")) && cardNo.replaceAll("\\s+","").equalsIgnoreCase(cardNo.replaceAll("\\s+",""))) {
                System.out.println("customer verified");
                return new Card(rs);
            }
        }
        return null;
    }
}
