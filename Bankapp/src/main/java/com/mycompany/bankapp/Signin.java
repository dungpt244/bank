/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp;
import com.mycompany.bankapp.admin.Admin;

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

public class Signin {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mybank"; // Replace with your database name
    private static final String DB_USER = "admin"; // Replace with your database username
    private static final String DB_PASSWORD = "1234"; // Replace with your database password

    public Signin() {
        // Create frame
        JFrame frame = new JFrame("Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Create components
        ImageIcon icon = new ImageIcon("./picture/icon.jpg"); // Add your icon path here
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage)); // Resized icon

        iconLabel.setBounds(150, 20, 100, 100); // Position and size for the icon

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 130, 100, 30);
        JTextField phoneText = new JTextField();
        phoneText.setBounds(150, 130, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 170, 100, 30);
        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(150, 170, 200, 30);

        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(150, 210, 100, 30);
        signInButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);

        // Sign up link
        JLabel signUpLabel = new JLabel("Don't have an account yet? ");
        signUpLabel.setBounds(50, 250, 200, 30);
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(230, 250, 80, 30);
        signUpButton.setBorderPainted(false);
        signUpButton.setContentAreaFilled(false);
        signUpButton.setForeground(new Color(30, 144, 255)); // Dodger Blue

        // Add components to frame
        frame.add(iconLabel);
        frame.add(phoneLabel);
        frame.add(phoneText);
        frame.add(passwordLabel);
        frame.add(passwordText);
        frame.add(signInButton);
        frame.add(signUpLabel);
        frame.add(signUpButton);

        // Action listener for sign-in button
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneText.getText();
                String password = new String(passwordText.getPassword());
                if (phoneNumber.equals("0")) { // Admin phone number
                        new Admin(); // Open Admin screen
                        frame.dispose();
                } 
                if (phoneNumber.equals("1")) { // Admin phone number
                        new Bankapp(1); // Open Admin screen
                        frame.dispose();
                }
                else {
                    if (checkCredentials(phoneNumber, password)) {
                        new Bankapp(1); // Open Bank screen
                        frame.dispose();// Close the sign-in frame
                    }
                 else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Action listener for sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open sign-up screen
                new Signup();
                frame.dispose();
            }
        });

        // Set frame visibility
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private boolean checkCredentials(String phoneNumber, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM users WHERE phonenumber = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if a record is found
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        new Signin();
    }
}

