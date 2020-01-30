package com.letscodethemup.utils;

/**
 * DatabaseUtils.java
 * Purpose: POJO to set Database parameters like username, password, hostname, port,
 *          database name and backup file location
 *
 * @author Megha Rastogi
 * @version 1.0 1/30/2020
 */
public class DatabaseUtils {

    private String user = "dummy";
    private String pass = "dummy";
    private String host = "localhost";
    private int port = 3306;
    private String databaseName = "books";
    private String backupPath = "/Users/Megha/temp/backup.sql";

    /**
     * Gets the username.
     *
     * @return username as string
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets the password.
     *
     * @return password as string
     */
    public String getPass() {
        return pass;
    }

    /**
     * Gets the hostname.
     *
     * @return hostname as string
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets the port.
     *
     * @return port as integer
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the database name.
     *
     * @return database name as string
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Gets the backup file path.
     *
     * @return backup file path as string
     */
    public String getBackupPath() {
        return backupPath;
    }
}
