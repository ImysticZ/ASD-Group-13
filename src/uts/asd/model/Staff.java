package uts.asd.model;

import java.sql.*;
import java.io.Serializable;

public class Staff implements Serializable{

    private int id;
    private String role;

    public Staff(int id, String role){
        this.id = id;
        this.role = role;
    }

    public Staff(ResultSet rs){
        try{
            this.id = rs.getInt(1);
            this.role = rs.getString(2);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() == null ? "Unable to load staff" : "New staff object created");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}
