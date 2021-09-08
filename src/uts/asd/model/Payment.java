package uts.asd.model;

import java.io.Serializable;

public class Payment implements Serializable{
        private int paymentID;
        private int cardID;
        private int bookingID;
    

        //constructor

        public Payment(int paymentID, int cardID, int bookingID) {
            this.paymentID= paymentID;
            this.cardID= cardID;
            this.bookingID= bookingID;
        }

        //setters
        
        public void setpaymentID(int paymentID) {
            this.paymentID= paymentID;
        }
        
        public void setbookingID(int bookingID) {
            this.bookingID= bookingID;
        }
        
        public void setcardID(int cardID) {
            this.cardID= cardID;
        }

        //getters

        public int getpaymentID() {
            return paymentID;
        }

        public int getcardID() {
            return cardID;
        }

        public int getbookingID() {
            return bookingID;
        }
        
        
}
