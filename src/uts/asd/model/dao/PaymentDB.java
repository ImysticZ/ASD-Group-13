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
    public Card saveCard(String cardNo, String cvc, String date) throws SQLException {
        String query = "INSERT INTO Card (Card_Number, cvc, Expiry) VALUES('" + cardNo + "', '" + cvc + "', '" + date + "')";
        st.execute(query); 
         System.out.println("Card successfully inserted"); //successful
        query="SELECT * FROM Card WHERE CardID= (SELECT MAX(CardID) FROM Card)";
        ResultSet rs= st.executeQuery(query);
        System.out.println("Card return works"); //successful
        Card card= null;
        while (rs.next()) {
            card = new Card(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        return card;
    }

    // To create payment when user completes transaction
    public void makePayment(int bookingID, int cardID) throws SQLException {
        String query = "INSERT INTO Payment (BookingID, cardId) VALUES (" + bookingID + ", " + cardID + ")";
        st.execute(query);
    }

    // Saves card details to user account
    public void saveCardToUser(int userID, Card card) throws SQLException {
        String query="UPDATE CUSTOMER SET CardID= " + card.getcardID() + " WHERE ID= " + userID;
        st.executeUpdate(query);
        System.out.println("card sucessfully saved to customer record"); //  successful
    }

    // Return credit card information of a user
    public Card returnCard(User user) throws SQLException {
        String query = "SELECT * from Card INNER JOIN CUSTOMER on Card.CardID = CUSTOMER.CardID WHERE CUSTOMER.ID= " + user.getId();
        ResultSet rs = st.executeQuery(query); // store in resultSet
        Card card = null;
        while (rs.next()) {
            card = new Card(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        System.out.println("card sucessfully returned"); //  successful
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

    public Card authenticateCustomer(int id, String card) throws SQLException { 
        ResultSet rs = st.executeQuery("select * from CUSTOMER WHERE TRIM(ID)= '"+id+"'" + " AND TRIM(CardID)= '"+card+"'");

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
