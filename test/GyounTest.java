import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import uts.asd.model.*;
import uts.asd.model.dao.*;
import java.sql.*;
import java.util.ArrayList;

public class GyounTest {
    DBConnector dbConn;
    Connection conn;

    GyounTest() throws ClassNotFoundException, SQLException {
        dbConn = new DBConnector();
    }

    @Test
    public void enquiryTest(){
        int enquiryID = 200;
        String question = "How do I book a room?";
        String reply = "Idk";
        boolean resolved = true;
        int userID = 1012;
        Enquiry enquiry = new Enquiry(enquiryID, question, reply, resolved, userID);
        assertEquals(enquiry.getEnquiryID(), enquiryID);
        assertEquals(enquiry.getQuestion(), question);
        assertEquals(enquiry.getReply(), reply);
        assertEquals(enquiry.getResolved(), resolved);
        assertEquals(enquiry.getUserID(), userID);
        
    }

    @Test
    public void findEnquiryByIDTest() throws SQLException{
        conn = dbConn.openConnection();
        EnquiryDBManager manager = new EnquiryDBManager(conn);
        Enquiry enquiry = manager.findEnquiryByID(33);
        assertEquals(enquiry.getEnquiryID(), 33);
        dbConn.closeConnection();
    }

    @Test
    public void fetchAllEnquiriesTest() throws SQLException{
        conn = dbConn.openConnection();
        EnquiryDBManager manager = new EnquiryDBManager(conn);
        ArrayList<Enquiry> enquiries = manager.fetchAll();
        assertNotNull(enquiries);
        dbConn.closeConnection();
    }

}
