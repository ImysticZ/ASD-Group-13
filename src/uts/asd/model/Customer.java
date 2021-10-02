package uts.asd.model;

import java.sql.*;
import java.io.Serializable;

public class Customer implements Serializable{

    private int id;
    private int card_id;

    public Customer(int id, int card_id){
        this.id = id;
        this.card_id = card_id;
    }

    public Customer(ResultSet rs){
        try{
            this.id = rs.getInt(1);
            this.card_id = rs.getInt(2);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() == null ? "Unable to load customer" : "New customer object created");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardID() {
        return card_id;
    }

    public void setRole(int card_id){
        this.card_id = card_id;
    }
}