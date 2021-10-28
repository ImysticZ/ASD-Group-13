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
        int id = 1029;
        String fname = "Clarence";
        String lname = "Cheung";
        String email = "clarence@clarence";
        String phone = "0412345678";
        String pass = "urmum";
        String address = "ur mum";
        String type = "c";

        User user = new User(id, fname, lname, email, phone, pass, address, type);

        assertEquals(67, paymentDB.returnCard(user).getcardID());
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
