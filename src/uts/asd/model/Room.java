package uts.asd.model;
import java.io.*;

public class Room implements Serializable {
    private int roomId, roomTypeId;
    private boolean availability;

    public Room(int roomId, int roomTypeId, boolean availability) {
        this.roomId = roomId;
        this.roomTypeId = roomTypeId;
        this.availability = availability;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public boolean getAvailability() {
        return availability;
    }
}
