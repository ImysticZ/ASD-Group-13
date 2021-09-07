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
        st.executeUpdate("INSERT INTO bc2dyro2kdvcc2jmmd9e.\"USER\"(\"FIRST_NAME\", LAST_NAME, EMAIL, PHONE, PASSWORD, ADDRESS, TYPE)" + "VALUES ('" + firstname + "', '" + lastname + "', '" + email + "', '" + phone + "', '" + password + "', '" + address + "', " + type + ")");
    }

    public User findUserByEmail(String email) throws SQLException {
        String fetch = "SELECT * FROM bc2dyro2kdvcc2jmmd9e.\"USER\" WHERE EMAIL = '" + email + "'";
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
