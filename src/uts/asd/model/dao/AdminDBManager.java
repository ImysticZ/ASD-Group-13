package uts.asd.model.dao;

import uts.asd.model.*;
import java.sql.*;
import java.util.ArrayList;

public class AdminDBManager {

    private Statement st;

    public AdminDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    /*
        Admin User Management
     */
    // Fetch all Users by first name
    public ArrayList<User> fetchUsersFirstName(String fname) throws SQLException {
        ResultSet rs = st.executeQuery("select * from USER WHERE TRIM(FIRST_NAME)= '"+fname+"'");
        ArrayList<User> temp = new ArrayList<User>();
        System.out.println(st.toString());

        while(rs.next()) {
            
            temp.add(new User(rs));
        }
        return temp;
    }

    // Fetch every single users
    public ArrayList<User> fetchAllUsers() throws SQLException {
        ResultSet rs = st.executeQuery("select * from USER");
        ArrayList<User> temp = new ArrayList<User>();

        while(rs.next()) {
            temp.add(new User(rs));
        }
        return temp;
    }
}
