import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import uts.asd.model.*;
import uts.asd.model.dao.DBConnector;
import uts.asd.model.dao.testPaymentDB;

public class BeratTest {
    private testPaymentDB testPayment;
    DBConnector dbConn;
    Connection conn;

    public BeratTest() throws ClassNotFoundException, SQLException {
        dbConn = new DBConnector();
    }

    @Test
    public void testAutoFillPayment() {
        int cardID= 142;
        String cardNo= "4922019218374937";
        String cvc= "879";
        String date= "2023-09-15";
        Card card= new Card(cardID, cardNo, cvc, date);
        assertEquals(cardNo, card.getnumber());
        assertEquals(cvc, card.getcvc());
        assertEquals(date, card.getdate());
    }

    @Test
    public void testCheckOutPatron() throws SQLException {
        conn = dbConn.openConnection();
        testPayment = new testPaymentDB(conn);
        Booking expectedBooking= null; //The expected Booking object
        Booking actualBooking= null; //The actual Booking object
        expectedBooking = new Booking(31, 1012, 2, "2021-10-14", "2021-10-21", "Booked", true, 199.50); //create the actual expected booking
        expectedBooking.setStatus("Checked Out"); //set the Status of the EXPECTED BOOKING to 'Checked Out'
        testPayment.testCheckInPatron("Checked Out"); //update the ACTUAL BOOKING status to 'Checked out' using DB Operations
        actualBooking = testPayment.returnBooking(); //Return the ACTUAL BOOKING to actual booking object
        assertEquals(expectedBooking.getStatus(), actualBooking.getStatus()); //Check whether expected status and actual status are the same ('Checked Out')
        dbConn.closeConnection();
    }

    @Test
    public void testFetchBookings() throws SQLException {
        conn = dbConn.openConnection();
        testPayment = new testPaymentDB(conn);
        ArrayList<Booking> allBooking = null; //instantiate Array of Bookings
        allBooking = testPayment.fetchBooking(); //fetches All Bookings in database and store in allBooking
        int noBookings = testPayment.countBookings(); //stores the number of Bookings in database 
        assertEquals(noBookings, allBooking.size()); //Check whether the number of booking is the same as the size of the Booking ArrayList
        dbConn.closeConnection();
    }
}
