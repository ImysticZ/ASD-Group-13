package uts.asd.model.dao;

import uts.asd.model.*;
import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    private Statement st;

    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    //USER DAO METHODS
    public void addUser(String firstname, String lastname, String email, String phone, String password, String address, String type) throws SQLException {
        String query = "insert into USER (FIRST_NAME, LAST_NAME, EMAIL, PHONE, PASSWORD, ADDRESS, TYPE)" + "values (' "+ firstname + " ',' "+  lastname + "','+ email +','+ phone +','+ password +','+ address +', 'c')";
        st.executeUpdate(query);
    }

    public User findUserByEmail(String email) throws SQLException {
        String fetch = "select * from USER where " + "EMAIL = email";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String userEmail = rs.getString(3);
            if (userEmail.equals(email)) {
                return new User(rs);
            }
        }
        return null;
    }

}
