package com.annmweru.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public  static final String url = "jdbc:postgresql://localhost:5432/library_db";
    public  static final String user = "annah";
    public  static final String password = "user@123";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Critical failure: Unable to establish database connection.", e);
        }
    }
}


