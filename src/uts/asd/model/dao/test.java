package uts.asd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import uts.asd.model.*;

public class test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://bc2dyro2kdvcc2jmmd9e-mysql.services.clever-cloud.com:3306/bc2dyro2kdvcc2jmmd9e";
        String dbuser = "usws9urc96uqn2aw";
        String dbpass = "aOuX54759girLCF7QIkY";
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection(url, dbuser, dbpass);
            System.out.println("DB WOKRS");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
