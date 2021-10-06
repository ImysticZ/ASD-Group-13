import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import uts.asd.model.*;
import uts.asd.model.dao.*;

public class LeonTest {
    static DBConnector db;
    static PaymentDB paymentDB;

    @BeforeAll
    public static void setup() throws Exception {
        db = new DBConnector();
        paymentDB = new PaymentDB(db.openConnection());
    }

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

        assertEquals(52, paymentDB.returnCard(user).getcardID());
        assertNotEquals(48, paymentDB.returnCard(user).getcardID());
    }

    @Test
    public void userTypeTest() {
        String userTypeCustomer = "c";

        User userType = new User(1, "First Name", "Last Name", "Email", "Phone", "Password", "Address", userTypeCustomer);
        
        assertEquals(userType.getType(), userTypeCustomer);
    }

    @AfterAll
    public static void end() throws Exception {
        db.closeConnection();
    }
}
