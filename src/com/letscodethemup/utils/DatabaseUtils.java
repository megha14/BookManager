package com.letscodethemup.utils;

public class DatabaseUtils {

    private String user = "dummy";
    private String pass = "dummy";
    private String host = "localhost";
    private int port = 3306;
    private String databaseName = "books";
    private String backupPath = "/Users/Megha/temp/backup.sql";

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getBackupPath() {
        return backupPath;
    }
}
