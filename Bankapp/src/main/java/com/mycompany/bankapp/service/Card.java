/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp.service;

/**
 *
 * @author MINH
 */
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Card {
    private JFrame cardFrame;
    private int userId; // ID of the current user

    public Card(int userId) {
        this.userId = userId;
        initializeUI();
    }

    private void initializeUI() {
        cardFrame = new JFrame("User Card");
        cardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cardFrame.setSize(400, 300);
        cardFrame.setLayout(new BorderLayout());

        // Load user data
        UserData userData = fetchUserData(userId);

        // Create card panel
        JPanel cardPanel = createCardPanel(userData);
        cardFrame.add(cardPanel, BorderLayout.CENTER);

        cardFrame.setLocationRelativeTo(null);
        cardFrame.setVisible(true);
    }

    private UserData fetchUserData(int userId) {
        UserData userData = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT firstname, lastname, money, idnumber FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                long money = resultSet.getLong("money");
                int idNumber = resultSet.getInt("idnumber");

                userData = new UserData(firstName, lastName, money, idNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in production code
        }
        return userData;
    }

    private JPanel createCardPanel(UserData userData) {
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Load and add card image
        JLabel cardImage = new JLabel(new ImageIcon("path/to/card/image.png")); // Replace with your card image path
        cardPanel.add(cardImage);

        // Display user information
        JLabel idLabel = new JLabel("User ID: " + userId);
        JLabel nameLabel = new JLabel("Full Name: " + userData.getFullName());
        JLabel moneyLabel = new JLabel("Money: $" + userData.getMoney());
        JLabel cardNumberLabel = new JLabel("Card Number: " + calculateCardNumber(userData.getIdNumber()));

        cardPanel.add(idLabel);
        cardPanel.add(nameLabel);
        cardPanel.add(moneyLabel);
        cardPanel.add(cardNumberLabel);

        return cardPanel;
    }

    private String calculateCardNumber(int idNumber) {
        String reversedId = new StringBuilder(String.valueOf(idNumber)).reverse().toString();
        int cardNumber = idNumber * Integer.parseInt(reversedId);
        return String.valueOf(cardNumber);
    }

    public static void main(String[] args) {
        // Example usage: pass the user ID you want to display
        SwingUtilities.invokeLater(() -> new Card(1)); // Replace 1 with the actual user ID
    }

    // Helper class to hold user data
    private static class UserData {
        private String firstName;
        private String lastName;
        private long money;
        private int idNumber;

        public UserData(String firstName, String lastName, long money, int idNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.money = money;
            this.idNumber = idNumber;
        }

        public String getFullName() {
            return firstName + ", " + lastName;
        }

        public long getMoney() {
            return money;
        }

        public int getIdNumber() {
            return idNumber;
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

