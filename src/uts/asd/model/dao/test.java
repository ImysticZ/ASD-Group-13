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
            String query = "SELECT * from Card INNER JOIN CUSTOMER on Card.CardID = CUSTOMER.CardID WHERE CUSTOMER.ID= " + 1028;
            try (Statement st = connection.createStatement()){
                ResultSet rs = st.executeQuery(query); // store in resultSet
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String card = rs.getString(2);
                    String cvc = rs.getString(3);
                    System.out.println(id + ", " + card + ", " + cvc);
                }
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
