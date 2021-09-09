package uts.asd.model;

import java.io.Serializable;

public class RoomType implements Serializable {
    
    private int roomTypeId;
    private double costPerDay;
    private int numberOfBeds;
    private String suite;
    private String Description;

    


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
     * @return double return the costPerDay
     */
    public double getCostPerDay() {
        return costPerDay;
    }

    /**
     * @param costPerDay the costPerDay to set
     */
    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    /**
     * @return int return the numberOfBeds
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * @param numberOfBeds the numberOfBeds to set
     */
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * @return String return the suite
     */
    public String getSuite() {
        return suite;
    }

    /**
     * @param suite the suite to set
     */
    public void setSuite(String suite) {
        this.suite = suite;
    }

    /**
     * @return String return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

}
