import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import uts.asd.model.*;
import uts.asd.model.dao.*;
import java.sql.*;

public class GyounTest {

    @Test
    public void addEnquiryTest(){
        
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

}
