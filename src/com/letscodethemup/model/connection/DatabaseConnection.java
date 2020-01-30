package com.letscodethemup.model.connection;

import com.letscodethemup.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection.java
 * Purpose: Establish connection with the MySQL database
 *
 * @author Megha Rastogi
 * @version 1.0 1/30/2020
 */
public class DatabaseConnection {
    DatabaseUtils databaseUtils;
    private String url;
    private String user;
    private String pass;

    /**
     * Constructor to instantiate this class and initialize required objects .
     */
    public DatabaseConnection() {
        databaseUtils = new DatabaseUtils();
        url = "jdbc:mysql://"+ databaseUtils.getHost()+":"+databaseUtils.getPort()+"/"+databaseUtils.getDatabaseName();
        user = databaseUtils.getUser();
        pass = databaseUtils.getPass();
    }

    /**
     * Method to establish the connection with the database
     *
     * @return Connection object
     */
    public Connection getConnection(){
        try{
            return DriverManager.getConnection(url, user, pass);
        }catch (SQLException e){
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
