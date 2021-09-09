package uts.asd.model;

import java.io.Serializable;

public class Room implements Serializable{
    
    private int roomId;
    private int roomTypeId;
    private boolean available;  


    public Room() {

    }

    public Room(int roomId, int roomTypeId, boolean available) {
        this.roomId = roomId;
        this.roomTypeId = roomTypeId;
        this.available = available;
    }

    /**
     * @return int return the roomId
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    /**
     * @return int return the roomTypeId
     */
    public int getRoomTypeId() {
        return roomTypeId;
    }

    /**
     * @param roomTypeId the roomTypeId to set
     */
    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    /**
     * @return boolean return the availability
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the availability to set
     */
    public void setAvailability(boolean available) {
        this.available = available;
    }

}
