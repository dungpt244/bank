/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp;

/**
 *
 * @author MINH
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn {
    private static final String URL = "jdbc:mysql://localhost:3306/mybank?zeroDateTimeBehavior=CONVERT_TO_NULL"; // Replace with your database URL
    private static final String USER = "admin"; // Replace with your database username
    private static final String PASSWORD = "1234"; // Replace with your database password

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to the database established successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection to the database failed.");
            e.printStackTrace();
        }
    }
}

class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mybank"; // Change to your database name
    private static final String USER = "admin"; // Your database username
    private static final String PASSWORD = "1234"; // Your database password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

