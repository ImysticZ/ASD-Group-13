package uts.asd.model;

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
