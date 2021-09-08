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
        String query = "insert into USER (FIRST_NAME, LAST_NAME, EMAIL, PHONE, PASSWORD, ADDRESS, TYPE)" + "values (' "+ firstname + " ',' "+  lastname + "',' "+ email + " ',' "+ phone + " ',' "+ password +"',' "+ address + " ', 'c')";
        st.executeUpdate(query);
    }

    public User findUserByEmail(String email) throws SQLException { 
        String fetch = "select * from USER where EMAIL LIKE CONCAT('%', '"+email+"', '%')";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String userEmail = rs.getString(4);
            if (userEmail.replaceAll("\\s+","").equalsIgnoreCase(email.replaceAll("\\s+",""))) {
                return new User(rs);
            }
        }
        return null;
    }

    public User authenticateUser(String email, String password) throws SQLException { 
        ResultSet rs = st.executeQuery("select * from USER WHERE TRIM(EMAIL)= '"+email+"'" + " AND TRIM(PASSWORD)= '"+password+"'");

        while (rs.next()) {
            String userEmail = rs.getString(4);
            System.out.println(userEmail+"space"+email);
            String userPw = rs.getString(6);
            System.out.println(userPw+"space"+password);
            if (userEmail.replaceAll("\\s+","").equalsIgnoreCase(email.replaceAll("\\s+","")) && userPw.replaceAll("\\s+","").equalsIgnoreCase(password.replaceAll("\\s+",""))) {
                System.out.println("Success");
                return new User(rs);
            }
        }
        return null;
    }

}
