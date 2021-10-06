import static org.junit.jupiter.api.Assertions.*;
import uts.asd.model.*;
import uts.asd.model.dao.*;
import org.junit.jupiter.api.*;
import java.time.temporal.ChronoUnit;
import java.time.*;

public class ClarenceTest {

    static RoomDBManager manager;
    static DBConnector db;

    @BeforeAll
    public static void setup() throws Exception {
        db = new DBConnector();
        manager = new RoomDBManager(db.openConnection());
    }

    @Test
    public void roomTypeTest() {
        int roomTypeID = 1;
        double cost = 99.99;
        int numBeds = 2;
        String suite = "Small";
        String description = "A small room";
        RoomType roomType = new RoomType(roomTypeID, cost, numBeds, suite, description);
        assertEquals(roomType.getRoomTypeId(), roomTypeID);
        assertEquals(roomType.getCost(), cost);
        assertEquals(roomType.getNumBeds(), numBeds);
        assertEquals(roomType.getSuite(), suite);
        assertEquals(roomType.getDescription(), description);
    }

    @Test
    public void bookingTest() {
        int bookingID = 123;
        int userID = 15;
        int roomID = 150;
        String startingDate = "05/10/2021";
        String endingDate = "10/10/2021";
        String status = "Booked";
        Boolean paid = false;
        double totalCost = 99.99;
        Booking booking = new Booking(bookingID, userID, roomID, startingDate, endingDate, status, paid, totalCost);
        assertEquals(booking.getBookingID(), bookingID);
        assertEquals(booking.getUserID(), userID);
        assertEquals(booking.getRoomID(), roomID);
        assertEquals(booking.getStatus(), status);
        assertEquals(booking.getPaid(), paid);
        assertEquals(booking.getTotalCost(), totalCost);
    }

    @Test
    public void differenceInDaysTest() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        assertEquals(ChronoUnit.DAYS.between(today, tomorrow), 1);
    }

    @Test
    public void differenceInDaysTest2() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(34);
        assertEquals(ChronoUnit.DAYS.between(today, tomorrow), 34);
    }

    @Test
    public void findBookingByID() throws Exception {
        int bookingID = 15;
        Booking booking = manager.findBookingByID(bookingID);
        assertEquals(booking.getBookingID(), bookingID);
        assertEquals(booking.getRoomID(), 152);
        assertEquals(booking.getTotalCost(), 1049.93);
    }

    @AfterAll
    public static void end() throws Exception {
        db.closeConnection();
    }

}