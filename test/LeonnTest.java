
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import uts.asd.model.*;
import uts.asd.model.dao.PaymentDB;

public class LeonnTest {
    String url = "jdbc:mysql://bc2dyro2kdvcc2jmmd9e-mysql.services.clever-cloud.com:3306/bc2dyro2kdvcc2jmmd9e";
    String dbuser = "usws9urc96uqn2aw";
    String dbpass = "aOuX54759girLCF7QIkY";

    @Test
    public void returnCardTest() throws SQLException
    {
        int id = 1028;
        String fname = "Louis";
        String lname = "Lui";
        String email = "test2@test.com";
        String phone = "0421891583";
        String pass = "pass";
        String address = "12 Ogre Street";
        String type = "c";

        User user = new User(id, fname, lname, email, phone, pass, address, type);
        Connection connection=DriverManager.getConnection(url, dbuser, dbpass);
        PaymentDB paymentDB = new PaymentDB(connection);

        assertEquals(47, paymentDB.returnCard(user).getcardID());
        assertNotEquals(48, paymentDB.returnCard(user).getcardID());
    }

    @Test
    public void userTypeTest() {
        String userTypeCustomer = "c";

        User userType = new User(1, "First Name", "Last Name", "Email", "Phone", "Password", "Address", userTypeCustomer);
        
        assertEquals(userType.getType(), userTypeCustomer);
    }
}
