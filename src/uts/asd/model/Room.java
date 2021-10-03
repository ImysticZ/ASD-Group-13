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

    public Room(int roomId, int roomTypeId) {
        this.roomId = roomId;
        this.roomTypeId = roomTypeId;
        this.availability = false;
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

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
