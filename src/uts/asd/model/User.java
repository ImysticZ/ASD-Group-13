package uts.asd.model;

import java.sql.*;
import java.io.Serializable;

public class User implements Serializable{

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
    private String password;
    private String address;
    private String type;

    public User(int id, String firstName, String lastName, String email, String phoneNum, String password, String address, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
        this.address = address;
        this.type = type;
    }

    public User(ResultSet rs) { //uses an sql result set's rows to fill in User attributes
        try {
            this.id = rs.getInt(1);
            this.firstName = rs.getString(2);
            this.lastName = rs.getString(3);
            this.email = rs.getString(4);
            this.phoneNum = rs.getString(5);
            this.password = rs.getString(6);
            this.address = rs.getString(7);
            this.type = rs.getString(8);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() == null ? "Unable to load user" : "New user object created");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
