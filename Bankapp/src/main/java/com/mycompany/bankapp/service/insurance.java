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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class insurance {
    private JFrame insuranceFrame;
    private int userId; // ID of the current user

    public insurance(int userId) {
        this.userId = userId;
        initializeUI();
    }

    private void initializeUI() {
        insuranceFrame = new JFrame("Bank Insurance Page");
        insuranceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        insuranceFrame.setSize(600, 400);
        insuranceFrame.setLayout(new BorderLayout());

        // User information panel
        JPanel userInfoPanel = createUserInfoPanel();
        insuranceFrame.add(userInfoPanel, BorderLayout.NORTH);

        // Services panel
        JPanel servicesPanel = createServicesPanel();
        insuranceFrame.add(servicesPanel, BorderLayout.CENTER);

        insuranceFrame.setLocationRelativeTo(null);
        insuranceFrame.setVisible(true);
    }

    private JPanel createUserInfoPanel() {
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new GridLayout(3, 2));

        // Fetch user data
        UserData userData = fetchUserData(userId);

        userInfoPanel.add(new JLabel("User ID: " + userData.getId()));
        userInfoPanel.add(new JLabel("Full Name: " + userData.getFullName()));
        userInfoPanel.add(new JLabel("Email: " + userData.getEmail()));
        userInfoPanel.add(new JLabel("Phone Number: " + userData.getPhoneNumber()));

        return userInfoPanel;
    }

    private UserData fetchUserData(int userId) {
        UserData userData = null;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT firstname, lastname, email, phonenumber FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phonenumber");

                userData = new UserData(userId, firstName, lastName, email, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in production code
        }
        return userData;
    }

    private JPanel createServicesPanel() {
        JPanel servicesPanel = new JPanel();
        servicesPanel.setLayout(new BoxLayout(servicesPanel, BoxLayout.Y_AXIS));

        // Fetch services data
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT name, type, price, info1, info2 FROM service WHERE users = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int price = resultSet.getInt("price");
                String info1 = resultSet.getString("info1");
                String info2 = resultSet.getString("info2");

                // Create a panel for each service
                JPanel servicePanel = new JPanel();
                servicePanel.setBorder(BorderFactory.createTitledBorder(name));
                servicePanel.setLayout(new GridLayout(4, 1));
                servicePanel.add(new JLabel("Type: " + type));
                servicePanel.add(new JLabel("Price: $" + price));
                servicePanel.add(new JLabel("Info 1: " + info1));
                servicePanel.add(new JLabel("Info 2: " + info2));

                servicesPanel.add(servicePanel);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in production code
        }

        return servicesPanel;
    }

    public static void main(String[] args) {
        // Example usage: pass the user ID you want to display
        SwingUtilities.invokeLater(() -> new insurance(1)); // Replace 1 with the actual user ID
    }

    // Helper class to hold user data
    private static class UserData {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;

        public UserData(int id, String firstName, String lastName, String email, String phoneNumber) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }
}

