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
        String query = "insert into USER (FIRST_NAME, LAST_NAME, EMAIL, PHONE, PASSWORD, ADDRESS, TYPE)" + "values (' "+ firstname + " ',' "+  lastname + "',' "+ email + " ',' "+ phone + " ',' "+ password +"',' "+ address + " ', '"+ type + "')";
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

    // Fetch all Users by email
    public ArrayList<User> fetchUsersEmail(String email) throws SQLException {
        ResultSet rs = st.executeQuery("select * from USER WHERE TRIM(EMAIL)= '"+email+"'");
        ArrayList<User> temp = new ArrayList<>();

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

    public void updateFirstName(int id, String fname) throws SQLException {
        st.executeUpdate("UPDATE USER SET FIRST_NAME = '" + fname + "' WHERE ID = " + id);
    }

    public void updateLastName(int id, String lname) throws SQLException {
        st.executeUpdate("UPDATE USER SET LAST_NAME = '" + lname + "' WHERE ID = " + id);
    }

    public void updatePhone(int id, String phone) throws SQLException {
        st.executeUpdate("UPDATE USER SET PHONE = '" + phone + "' WHERE ID = " + id);
    }

    public void updatePassword(int id, String password) throws SQLException {
        st.executeUpdate("UPDATE USER SET PASSWORD = '" + password + "' WHERE ID = " + id);
    }

    public void updateAddress(int id, String address) throws SQLException {
        st.executeUpdate("UPDATE USER SET ADDRESS = '" + address + "' WHERE ID = " + id);
    }

    public void updateType(int id, String type) throws SQLException {
        st.executeUpdate("UPDATE USER SET TYPE = '" + type + "' WHERE ID = " + id);
    }

    public User findUserByID(int id) throws SQLException {
        String fetch = "SELECT * FROM USER WHERE ID = " + id;
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            int userID = rs.getInt(1);
            if (userID == id) {
                return new User(rs);
            }
        }
        return null;
    }

    public void updateEmail(int id, String email) throws SQLException {
        st.executeUpdate("UPDATE USER SET EMAIL = '" + email + "' WHERE ID = " + id);
    }

    public void deleteUser(int id) throws SQLException {
        st.executeUpdate("DELETE FROM USER WHERE ID =" + id);
    }
}
