package uts.asd.model;
import java.io.*;
import java.util.*;
import java.text.*;

public class Booking implements Serializable {
    private int bookingID, userID, roomID;
    private Date startingDate, endingDate;
    private String status;
    private boolean paid;
    private double totalCost;

    public Booking(int bookingID, int userID, int roomID, String startingDate, String endingDate, String status, boolean paid, double totalCost) {
        this.bookingID = bookingID;

        this.userID = userID;
        this.roomID = roomID;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.startingDate = formatter.parse(startingDate);
            this.endingDate = formatter.parse(endingDate);
        }
        catch (Exception e) {
            System.out.print("fk");
        }
        this.status = status;
        this.paid = paid;
        this.totalCost = totalCost;
    }

    public int getBookingID() {
        return bookingID;
    }
    public int getUserID() {
        return userID;
    }

    public int getRoomID() {
        return roomID;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public String getStatus() {
        return status;
    }

    public boolean getPaid() {
        return paid;
    }

    public double getTotalCost() {
        return totalCost;
    }
}

