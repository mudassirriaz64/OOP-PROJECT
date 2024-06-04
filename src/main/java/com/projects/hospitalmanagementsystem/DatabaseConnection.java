package com.projects.hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital";
    private static final String USER = "root";
    private static final String PASSWORD = "MudasiR@719";

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
