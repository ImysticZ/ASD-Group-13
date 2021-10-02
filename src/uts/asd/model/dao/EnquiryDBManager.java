package uts.asd.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import uts.asd.model.Enquiry;

public class EnquiryDBManager {
    
    private Statement st;

    public EnquiryDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    //Create
    public void addEnquiry(String question, String reply, int userID) throws SQLException {
        st.executeUpdate("INSERT INTO Enquiry (Question, Reply, Resolved, UserID) "
                + "VALUES ('" + question + "','" + reply + "', " + false + ")");
    }

    //Read
    public Enquiry findEnquiryByID(int ID) throws SQLException {
        String query = "SELECT * FROM Enquiry WHERE EnquiryID = " + ID + "";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int enquiryID = rs.getInt(1);

            if (ID == enquiryID) {
                String question = rs.getString(2);
                String reply = rs.getString(3);
                boolean status = rs.getBoolean(4);
                int userID = rs.getInt(5);

                return new Enquiry(enquiryID, question, reply, status, userID);
            }
        }
        return null;
    }

    public ArrayList<Enquiry> filterEnquiries(boolean resolved) throws SQLException {
        String query = "SELECT * FROM Enquiry WHERE Resolved = " + resolved + "";
        ResultSet rs = st.executeQuery(query);
        ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();

        while (rs.next()) {
            int enquiryID = rs.getInt(1);
            String question = rs.getString(2);
            String reply = rs.getString(3);
            boolean status = rs.getBoolean(4);
            int userID = rs.getInt(5);

            enquiries.add(new Enquiry(enquiryID, question, reply, status, userID));
        }
        return enquiries;
    }

    
    //Update
    public void updateEnquiry(int enquiryID, String question, String reply, boolean status, int userID) throws SQLException {
        st.executeUpdate("UPDATE Enquiry SET Question='" + question + "', Reply= '" + reply + "', Resolved= " + status
                + ", UserID=" + userID + " WHERE EnquiryID= " + enquiryID);
    }

    //Delete
    public void deleteEnquiry(int enquiryID) throws SQLException {
        st.executeUpdate("DELETE FROM Enquiry WHERE EnquiryID=" + enquiryID);
    }

    //Fetch all
    public ArrayList<Enquiry> fetchAll() throws SQLException {
        String query = "select * from Enquiry";
        ResultSet rs = st.executeQuery(query);
        ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();

        while (rs.next()) {
            int enquiryID = rs.getInt(1);
            String question = rs.getString(2);
            String reply = rs.getString(3);
            boolean status = rs.getBoolean(4);
            int userID = rs.getInt(5);

            enquiries.add(new Enquiry(enquiryID, question, reply, status, userID));
        }
        return enquiries;
    }
    
}
