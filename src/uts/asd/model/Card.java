package uts.asd.model;

import java.sql.*;
import java.io.Serializable;

public class Card implements Serializable {
    private int cardID;
    private String number;
    private String cvc;
    private String date;

    public Card(int ID, String num, String cvc, String date) {
        cardID = ID;
        number = num;
        this.cvc = cvc;
        this.date = date;
    }

    public Card(ResultSet rs) { //uses an sql result set's rows to fill in User attributes
        try {
            this.cardID = rs.getInt(1);
            this.number = rs.getString(2);
            this.cvc = rs.getString(3);
            this.date = rs.getString(4);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() == null ? "Unable to load card" : "New card object created");
        }
    }

    // getters

    public int getcardID() {
        return cardID;
    }

    public String getnumber() {
        return number;
    }

    public String getcvc() {
        return cvc;
    }

    public String getdate() {
        return date;
    }

    // setters

    public void setcardID(int cardID) {
        this.cardID = cardID;
    }

    public void setnumber(String num) {
        this.number = num;
    }

    public void setcvc(String cvc) {
        this.cvc = cvc;
    }

    public void setdate(String date) {
        this.date = date;
    }

}
