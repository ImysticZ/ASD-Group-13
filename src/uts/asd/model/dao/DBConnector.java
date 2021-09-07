package uts.asd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector extends DB {
    public DBConnector(){
        try{
            System.out.println("Connecting to SQL database...");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, dbuser, dbpass);
            System.out.println("Conneciton Success");
        } catch (SQLException e){
            System.out.println("Cannot create database connection");
			e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println("Cannot create database connection");
			e.printStackTrace();
        }
    }

    public Connection openConnection() {
        return this.conn;
    }

    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
