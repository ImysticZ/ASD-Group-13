package uts.asd.model.dao;

import uts.asd.model.*;
import java.sql.*;
import java.util.ArrayList;
public class PaymentDB {
    private Statement st; // statement object

    public PaymentDB(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
    
    // Payment DAO METHODS
    //saves user card for payment of booking
    public void saveCard(String cardNo, String cvc, String date) throws SQLException {
        String query = "INSERT INTO Card (Card_Number, cvc, Expiry) VALUES('" + cardNo + "', '" + cvc + "', '" + date + "')";
        st.execute(query);
    }

    // To create payment when user completes transaction
    public void makePayment(int bookingID, int cardID) throws SQLException {
        String query = "INSERT INTO Payment (BookingID, cardId) VALUES (" + bookingID + ", " + cardID + ")";
        st.execute(query);
    }

    // Saves card to User
    public void saveCardToUser() throws SQLException {
        // st.execute(query);
    }

    // Return credit card information of a user
    public Card returnCard(User user) throws SQLException {
        String query = "SELECT * from inner join User on Card.CardID = Customer.CardID where Customer.UserID= "
                + user.getId();
        ResultSet rs = st.executeQuery(query); // store in resultSet
        Card card = null;
        while (rs.next()) {
            card = new Card(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
        }
        return card;
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

    public void updateBooking(Booking booking, String status) throws SQLException {
        String query = "UPDATE Booking SET Status = '" + status + "' WHERE BookingID =" + booking.getBookingID();
        st.executeUpdate(query);
    }
}
