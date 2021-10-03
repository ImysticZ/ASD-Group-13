package uts.asd.model;

import java.io.Serializable;

public class Card implements Serializable {
    private int cardID;
    private int number;
    private int cvc;
    private String date;

    public Card(int ID, int num, int cvc, String date) {
        cardID = ID;
        number = num;
        this.cvc = cvc;
        this.date = date;
    }
    // getters

    public int getcardID() {
        return cardID;
    }

    public int getnumber() {
        return number;
    }

    public int getcvc() {
        return cvc;
    }

    public String getdate() {
        return date;
    }

    // setters

    public void setcardID(int cardID) {
        this.cardID = cardID;
    }

    public void setnumber(int num) {
        this.number = num;
    }

    public void setcvc(int cvc) {
        this.cvc = cvc;
    }

    public void setdate(String date) {
        this.date = date;
    }

}
