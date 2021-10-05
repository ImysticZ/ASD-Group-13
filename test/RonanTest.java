import static org.junit.jupiter.api.Assertions.*;
import uts.asd.model.*;
import uts.asd.model.dao.*;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;

public class RonanTest {
    DBConnector dbConn;
    Connection conn;

    RonanTest() throws ClassNotFoundException, SQLException {
        dbConn = new DBConnector();
        conn = dbConn.openConnection();
    }




    @Test
    public void dbTest() {
        boolean err = false;
        AdminDBManager db = null;
        try {
            db = new AdminDBManager(conn);
        } catch (SQLException e) {
            err = true;
        }
        if(db == null) err = true;
        assertEquals(false, err);
    }

    @Test
    public void fetchUserTest() {
        boolean err = false;
        AdminDBManager db = null;
        ArrayList<User> uList = null;
        try {
            db = new AdminDBManager(conn);
        } catch (SQLException e) {
            err = true;
        }
        if(db == null) err = true;
        try {
            uList = db.fetchAllUsers();
        } catch (SQLException e) {
            err = true;
        }
        if(uList == null) err = true;
        assertEquals(false, err);
    }

    @Test
    public void userTest() {
        Random rng = new Random();
        int id = 123123;
        String firstName = "TestFirstName" + rng.nextInt(1000);
        String lastName = "TestLastName" + rng.nextInt(1000);
        String email = "TestEmail@funny.com";
        String phoneNum = "+" + rng.nextInt(1000000000);
        String password = "password" + rng.nextInt(1000);
        String address = "addresssss" + rng.nextInt(10);
        String type = "c";
        User user = new User(id, firstName, lastName, email, phoneNum, password, address, type);
        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(phoneNum, user.getPhoneNum());
        assertEquals(password, user.getPassword());
        assertEquals(address, user.getAddress());
        assertEquals(type, user.getType());
    }

    @Test
    public void userDBTest() {
        String firstName = "TestFirstName";
        String lastName = "TestLastName";
        String email = "TestEmail@funny.com";
        String phoneNum = "+45453453453";
        String password = "password";
        String address = "addresssss";
        String type = "c";
        boolean err = false;
        AdminDBManager db = null;
        ArrayList<User> uList = null;

        try {
            db = new AdminDBManager(conn);
        } catch (SQLException e) {
            err = true;
        }
        if(db == null) err = true;

        try {
            db.addUser(firstName, lastName, email, phoneNum, password, address, type);
        } catch (SQLException e) {
            err = true;
        }
        try {
            uList = db.fetchUsersFirstName(firstName);
        } catch (SQLException e) {
            err = true;
        }
        User user = uList.get(0);

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(phoneNum, user.getPhoneNum());
        assertEquals(password, user.getPassword());
        assertEquals(address, user.getAddress());
        assertEquals(type, user.getType());

        try {
            db.deleteUser(user.getId());
        } catch (SQLException e) {
            err = true;
        }
        uList = null;
        try {
            uList = db.fetchUsersFirstName(firstName);
        } catch (SQLException e) {
            err = true;
        }
        assertTrue(uList.isEmpty());
        assertEquals(false, err);
    }

    @Test
    public void roomDBTest() {
        int id = 123456789;
        boolean err = false;
        AdminDBManager db = null;
        ArrayList<Room> rList = null;

        try {
            db = new AdminDBManager(conn);
        } catch (SQLException e) {
            err = true;
        }
        if(db == null) err = true;
        ArrayList<RoomType> types = null;
        try {
            types = db.getRoomTypes();
        } catch (SQLException e1) {
            err = true;
        }
        RoomType rtype = types.get(0);
        int type = rtype.getRoomTypeId();

        try {
            db.addRoomRange(id, id, type);;
        } catch (SQLException e) {
            err = true;
        }
        try {
            rList = db.fetchRoomByNumber(id);
        } catch (SQLException e) {
            err = true;
        }
        Room room = rList.get(0);

        assertEquals(id, room.getRoomId());
        assertEquals(type, room.getRoomTypeId());

        try {
            db.deleteRoomRange(room.getRoomId(), room.getRoomId());
        } catch (SQLException e) {
            err = true;
        }
        rList = null;
        try {
            rList = db.fetchRoomByNumber(id);
        } catch (SQLException e) {
            err = true;
        }
        assertTrue(rList.isEmpty());
        assertEquals(false, err);
    }

    @Test
    public void roomTypeDBTest() {
        boolean err = false;
        AdminDBManager db = null;
        ArrayList<RoomType> rTypes = null;

        try {
            db = new AdminDBManager(new DBConnector().openConnection());
        } catch (ClassNotFoundException e) {
            err = true;
        } catch (SQLException e) {
            err = true;
        }

        try {
            rTypes = db.getRoomTypes();
        } catch (SQLException e) {
            err = true;
        }
        assertFalse(rTypes.isEmpty());
        assertEquals(false, err);

        for(int i = 0; i < rTypes.size(); i++) {
            String suite = rTypes.get(i).getSuite();
            int id = -1;
            try {
                id = db.checkRTypeID(suite);
            } catch (SQLException e) {
                err = true;
            }
            assertEquals(rTypes.get(i).getRoomTypeId(), id);
        }

        for(int i = 0; i < rTypes.size(); i++) {
            int id = rTypes.get(i).getRoomTypeId();
            String type = null;
            try {
                type = db.getSuite(id);
            } catch (SQLException e) {
                err = true;
            }
            assertEquals(rTypes.get(i).getSuite(), type);
        }
        assertEquals(false, err);
    }
}
