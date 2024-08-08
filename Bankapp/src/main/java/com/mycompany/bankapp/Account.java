/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp;

/**
 *
 * @author MINH
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private JTextField firstNameField, lastNameField, idNumberField, phoneNumberField, emailField, addressField, otpField;
    private JLabel idField, moneyField;
    private final String DEFAULT_GENDER = "Male";
    private int userId = 1; // Replace with actual user ID

    public Account() {
        // Create frame
        JFrame frame = new JFrame("Account Detail");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Create components
        JLabel idLabel = new JLabel("User ID:");
        idLabel.setBounds(20, 20, 150, 30);
        idField = new JLabel("unknown");
        idField.setBounds(150, 20, 200, 30);
        idField.setForeground(Color.BLACK);

        JLabel moneyLabel = new JLabel("Money:");
        moneyLabel.setBounds(20, 60, 150, 30);
        moneyField = new JLabel("0"); // Default to 0
        moneyField.setBounds(150, 60, 200, 30);
        moneyField.setForeground(Color.BLACK);
        moneyField.setOpaque(true);

        // Create a panel for User ID and Money
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(10, 45, 370, 100);
        topPanel.setBackground(new Color(70, 130, 180)); // Steel Blue
        topPanel.add(idLabel);
        topPanel.add(idField);
        topPanel.add(moneyLabel);
        topPanel.add(moneyField);

        // Move everything down
        int verticalOffset = 40;

        firstNameField = new JTextField("empty");
        firstNameField.setBounds(150, 120 + verticalOffset, 200, 30);
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 120 + verticalOffset, 150, 30);

        lastNameField = new JTextField("empty");
        lastNameField.setBounds(150, 160 + verticalOffset, 200, 30);
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 160 + verticalOffset, 150, 30);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(20, 200 + verticalOffset, 150, 30);
        JLabel genderField = new JLabel(DEFAULT_GENDER);
        genderField.setBounds(150, 200 + verticalOffset, 200, 30);
        genderField.setForeground(Color.BLACK);
        
        idNumberField = new JTextField("unknown");
        idNumberField.setBounds(150, 240 + verticalOffset, 200, 30);
        JLabel idNumberLabel = new JLabel("ID Number:");
        idNumberLabel.setBounds(20, 240 + verticalOffset, 150, 30);

        phoneNumberField = new JTextField("empty");
        phoneNumberField.setBounds(150, 280 + verticalOffset, 200, 30);
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(20, 280 + verticalOffset, 150, 30);

        emailField = new JTextField("empty");
        emailField.setBounds(150, 320 + verticalOffset, 200, 30);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 320 + verticalOffset, 150, 30);

        addressField = new JTextField("empty");
        addressField.setBounds(150, 360 + verticalOffset, 200, 30);
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 360 + verticalOffset, 150, 30);

        otpField = new JTextField("000000"); // Default to six zeros
        otpField.setBounds(150, 400 + verticalOffset, 200, 30);
        JLabel otpLabel = new JLabel("OTP:");
        otpLabel.setBounds(20, 400 + verticalOffset, 150, 30);

        // Change Password button
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(20, 440 + verticalOffset, 150, 30);
        changePasswordButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.addActionListener(e -> showChangePasswordScreen());

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 520 + verticalOffset, 100, 30);
        saveButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> saveAccountDetails());

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 5, 100, 30);
        backButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> frame.dispose());

        // Add components to frame
        frame.add(topPanel);
        frame.add(firstNameLabel);
        frame.add(firstNameField);
        frame.add(lastNameLabel);
        frame.add(lastNameField);
        frame.add(genderLabel);
        frame.add(genderField);
        frame.add(idNumberLabel);
        frame.add(idNumberField);
        frame.add(phoneNumberLabel);
        frame.add(phoneNumberField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(addressLabel);
        frame.add(addressField);
        frame.add(otpLabel);
        frame.add(otpField);
        frame.add(changePasswordButton);
        frame.add(saveButton);
        frame.add(backButton);

        // Set frame visibility
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        // Load account details from the database
        loadAccountDetails();
    }

    private void loadAccountDetails() {
        // Load account details from the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?"; // Change as necessary
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                firstNameField.setText(resultSet.getString("firstname") != null ? resultSet.getString("firstname") : "empty");
                lastNameField.setText(resultSet.getString("lastname") != null ? resultSet.getString("lastname") : "empty");
                idNumberField.setText(resultSet.getString("idnumber") != null ? resultSet.getString("idnumber") : "unknown");
                phoneNumberField.setText(resultSet.getString("phonenumber") != null ? String.valueOf(resultSet.getInt("phonenumber")) : "empty");
                emailField.setText(resultSet.getString("email") != null ? resultSet.getString("email") : "empty");
                addressField.setText(resultSet.getString("andress") != null ? resultSet.getString("andress") : "empty");
                otpField.setText(resultSet.getString("otp") != null ? String.valueOf(resultSet.getInt("otp")) : "000000");
                moneyField.setText(resultSet.getString("money") != null ? String.valueOf(resultSet.getLong("money")) : "0");
            } else {
                // If no user found, set all fields to "empty"
                setAllFieldsToDefault();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setAllFieldsToDefault() {
        firstNameField.setText("empty");
        lastNameField.setText("empty");
        idNumberField.setText("unknown");
        phoneNumberField.setText("empty");
        emailField.setText("empty");
        addressField.setText("empty");
        otpField.setText("000000");
        moneyField.setText("0");
    }

    private void saveAccountDetails() {
        // Save account details to the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE users SET firstname = ?, lastname = ?, idnumber = ?, phonenumber = ?, email = ?, andress = ?, otp = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstNameField.getText().equals("empty") ? null : firstNameField.getText());
            statement.setString(2, lastNameField.getText().equals("empty") ? null : lastNameField.getText());
            statement.setString(3, idNumberField.getText().equals("unknown") ? null : idNumberField.getText());
            statement.setInt(4, Integer.parseInt(phoneNumberField.getText().equals("empty") ? "0" : phoneNumberField.getText()));
            statement.setString(5, emailField.getText().equals("empty") ? null : emailField.getText());
            statement.setString(6, addressField.getText().equals("empty") ? null : addressField.getText());
            statement.setInt(7, Integer.parseInt(otpField.getText().equals("000000") ? "0" : otpField.getText()));
            statement.setInt(8, userId); // Replace with actual user ID
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Account details saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving account details: " + e.getMessage());
        }
    }

    private void showChangePasswordScreen() {
        JFrame changePasswordFrame = new JFrame("Change Password");
        changePasswordFrame.setSize(400, 300);
        changePasswordFrame.setLayout(null);
        changePasswordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changePasswordFrame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        JLabel currentPasswordLabel = new JLabel("Current Password:");
        currentPasswordLabel.setBounds(20, 30, 150, 30);
        JPasswordField currentPasswordField = new JPasswordField();
        currentPasswordField.setBounds(150, 30, 200, 30);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setBounds(20, 70, 150, 30);
        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(150, 70, 200, 30);

        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(20, 110, 150, 30);
        changePasswordButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.addActionListener(e -> {
            String currentPassword = new String(currentPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            changePassword(currentPassword, newPassword);
            changePasswordFrame.dispose();
        });

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(200, 110, 100, 30);
        saveButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> {
            String currentPassword = new String(currentPasswordField.getPassword());
            String newPassword = new String(newPasswordField.getPassword());
            changePassword(currentPassword, newPassword);
            changePasswordFrame.dispose();
        });

        changePasswordFrame.add(currentPasswordLabel);
        changePasswordFrame.add(currentPasswordField);
        changePasswordFrame.add(newPasswordLabel);
        changePasswordFrame.add(newPasswordField);
        changePasswordFrame.add(changePasswordButton);
        changePasswordFrame.add(saveButton);

        changePasswordFrame.setLocationRelativeTo(null);
        changePasswordFrame.setVisible(true);
    }

    private void changePassword(String currentPassword, String newPassword) {
        // Change password logic here
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "UPDATE users SET password = ? WHERE id = ? AND password = ?"; // Change as necessary
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setInt(2, userId); // Replace with actual user ID
            statement.setString(3, currentPassword); // Ensure current password matches
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Password changed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Current password is incorrect.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error changing password: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Account(); // Launch the account detail screen
    }
}
