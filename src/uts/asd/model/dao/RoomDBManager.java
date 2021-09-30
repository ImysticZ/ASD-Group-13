package uts.asd.model.dao;

import uts.asd.model.*;
import java.sql.*;
import java.util.ArrayList;

public class RoomDBManager {

    private Statement st;

    public RoomDBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public ArrayList<RoomType> listRooms() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType");
        ArrayList<RoomType> ret = new ArrayList<RoomType>();
        while (rs.next()) {
            ret.add(new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return ret;
    }

    public ArrayList<RoomType> listRoomByBedsAscending(int beds) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType WHERE NumBeds=" + beds + " ORDER BY CostPerDay ASC");
        ArrayList<RoomType> ret = new ArrayList<RoomType>();
        while (rs.next()) {
            ret.add(new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return ret;
    }
    public ArrayList<RoomType> listRoomByBedsDescending(int beds) throws SQLException {
        ResultSet rs = st.executeQuery("SELECT * FROM RoomType WHERE NumBeds=" + beds + " ORDER BY CostPerDay DESC");
        ArrayList<RoomType> ret = new ArrayList<RoomType>();
        while (rs.next()) {
            ret.add(new RoomType(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return ret;
    }


}
