package com.letscodethemup.model.connection;

import com.letscodethemup.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    DatabaseUtils databaseUtils;
    private String url;
    private String user;
    private String pass;

    public DatabaseConnection() {
        databaseUtils = new DatabaseUtils();
        url = "jdbc:mysql://"+ databaseUtils.getHost()+":"+databaseUtils.getPort()+"/"+databaseUtils.getDatabaseName();
        user = databaseUtils.getUser();
        pass = databaseUtils.getPass();
    }

    public Connection getConnection(){
        try{
            return DriverManager.getConnection(url, user, pass);
        }catch (SQLException e){
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
