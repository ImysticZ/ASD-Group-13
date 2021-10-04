import static org.junit.jupiter.api.Assertions.assertEquals;
import uts.asd.model.*;
import org.junit.jupiter.api.Test;

class ClarenceTest {

    @Test
    void roomTypeTest() {
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

}