package uts.asd.model.dao;

import java.sql.Connection;

public abstract class DB {
    String url = "jdbc:mysql://bc2dyro2kdvcc2jmmd9e-mysql.services.clever-cloud.com:3306/bc2dyro2kdvcc2jmmd9e";
    String dbuser = "usws9urc96uqn2aw";
    String dbpass = "aOuX54759girLCF7QIkY";
    String driver = "com.mysql.cj.jdbc.Driver"; //jdbc client driver - built in with NetBeans   
    Connection conn; //connection null-instance to be initialized in sub-classes
}

