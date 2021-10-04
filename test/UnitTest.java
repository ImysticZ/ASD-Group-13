
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import uts.asd.model.*;

public class UnitTest {
    
    // METHODS BELOW

    /**
     * @param customerID
     * @throws SQLException
     * @return card details from customer/user id
     */
    public static String cardFromID(int customerID) throws SQLException{
        String url = "jdbc:mysql://bc2dyro2kdvcc2jmmd9e-mysql.services.clever-cloud.com:3306/bc2dyro2kdvcc2jmmd9e";
        String dbuser = "usws9urc96uqn2aw";
        String dbpass = "aOuX54759girLCF7QIkY";
        String result = "";
        Connection connection=DriverManager.getConnection(url, dbuser, dbpass);
        String query = "SELECT * from Card INNER JOIN CUSTOMER on Card.CardID = CUSTOMER.CardID WHERE CUSTOMER.ID= " + 1028;
        try (Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(query); // store in resultSet
            while (rs.next()) {
                int id = rs.getInt(1);
                String idString = String.valueOf(id);
                String card = rs.getString(2);
                String cvc = rs.getString(3);
                result = idString + ", " + card + ", " + cvc;
            }
            return result;
        } 
    }

    // UNIT TESTS BELOW

    @Test
    public void testCardFromID() throws SQLException
    {
        assertEquals("47, 1232343234545455, 112", cardFromID(1028));
    }
}
