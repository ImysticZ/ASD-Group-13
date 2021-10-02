package uts.asd.model;
import java.io.*;

public class RoomType implements Serializable {
    private double cost;
    private int numBeds, roomTypeId;
    private String suite, description;

    public RoomType(int roomTypeId, double cost, int numBeds, String suite, String description) {
        this.roomTypeId = roomTypeId;
        this.cost = cost;
        this.numBeds = numBeds;
        this.suite = suite;
        this.description = description;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public double getCost() {
        return cost;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public String getSuite() {
        return suite;
    }

    public String getDescription() {
        return description;
    }
}
