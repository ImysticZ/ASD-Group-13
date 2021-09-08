package uts.asd.model;

import java.io.Serializable;
import java.time.LocalDate;

public class CreditCard implements Serializable {

    private int cardID;
    private int number;
    private int cvc;
    private LocalDate date;

    //constructor

    public CreditCard(int cardID, int num, int cvc, LocalDate date) {
        this.cardID= cardID;
        this.number= num;
        this.cvc= cvc;
        this.date= date;
    }

    //getters

    private int getcardID() {
        return cardID;
    }

    private int getnumber() {
        return number;
    }

    private int getcvc() {
        return cvc;
    }

    private LocalDate getdate() {
        return date;
    }

    //setters

    private void setcardID(int cardID) {
        this.cardID= cardID;
    }

    private void setnumber(int num) {
        this.number= num;
    }

    private void setcvc(int cvc) {
        this.cvc= cvc;
    }

    private void setdate(LocalDate date) {
        this.date= date;
    }
}