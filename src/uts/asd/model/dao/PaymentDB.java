package uts.asd.model.dao;

import uts.asd.model.*;
import java.sql.*;

public class PaymentDB {
    private Statement st; // statement object

    public PaymentDB(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    // Payment DAO METHODS

    // To save Credit Card
    public void saveCard(String cardNo, String cvc, String date) throws SQLException {
        String query = "insert into CARD (Card_Number, CVC, Expiry)" + "values ('" + Integer.parseInt(cardNo) + "', "
                + Integer.parseInt(cvc) + ", '" + date + "')";
        st.executeUpdate(query);
    }

    // To create payment when user completes transaction
    public void makePayment(int bookingID, int cardID) throws SQLException {
        String query = "insert into Payment (BookingID, cardID)" + "values (" + bookingID + ", " + cardID + ")";
        st.executeQuery(query);
    }

    // Return credit card information of a user
    public CreditCard returnCard(User user) throws SQLException {
        String query = "SELECT * from inner join User on Card.CardID = Customer.CardID where Customer.UserID= "
                + user.getId();
        ResultSet rs = st.executeQuery(query); // store in resultSet
        CreditCard card = null;
        while (rs.next()) {
            card = new CreditCard(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
        }
        return card;
    }
}
