package com.mycompany.bankapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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

public class Signup {
    public Signup() {
        // Create frame
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpFrame.setSize(400, 600);
        signUpFrame.setLayout(null);
        
        // Set background color
        signUpFrame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Create components
        JLabel iconLabel = new JLabel(new ImageIcon("./picture/icon.jpg")); // Add your icon path here
        iconLabel.setBounds(150, 20, 100, 100); // Position and size for the icon

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 130, 100, 30);
        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(150, 130, 200, 30);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 170, 100, 30);
        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(150, 170, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 210, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 210, 200, 30);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 250, 100, 30);
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setBounds(150, 250, 80, 30);
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(230, 250, 80, 30);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JLabel idNumberLabel = new JLabel("ID Number:");
        idNumberLabel.setBounds(50, 290, 100, 30);
        JTextField idNumberField = new JTextField();
        idNumberField.setBounds(150, 290, 200, 30);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(50, 330, 100, 30);
        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(150, 330, 200, 30);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 370, 100, 30);
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 370, 200, 30);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 410, 100, 30);
        JTextField addressField = new JTextField();
        addressField.setBounds(150, 410, 200, 30);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(150, 460, 100, 30);
        signUpButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);

        // Sign in link
        JLabel signInLabel = new JLabel("Already have an account? ");
        signInLabel.setBounds(50, 500, 200, 30);
        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(230, 500, 80, 30);
        signInButton.setBorderPainted(false);
        signInButton.setContentAreaFilled(false);
        signInButton.setForeground(new Color(30, 144, 255)); // Dodger Blue

        // Add components to frame
        signUpFrame.add(iconLabel);
        signUpFrame.add(firstNameLabel);
        signUpFrame.add(firstNameField);
        signUpFrame.add(lastNameLabel);
        signUpFrame.add(lastNameField);
        signUpFrame.add(passwordLabel);
        signUpFrame.add(passwordField);
        signUpFrame.add(genderLabel);
        signUpFrame.add(maleButton);
        signUpFrame.add(femaleButton);
        signUpFrame.add(idNumberLabel);
        signUpFrame.add(idNumberField);
        signUpFrame.add(phoneNumberLabel);
        signUpFrame.add(phoneNumberField);
        signUpFrame.add(emailLabel);
        signUpFrame.add(emailField);
        signUpFrame.add(addressLabel);
        signUpFrame.add(addressField);
        signUpFrame.add(signUpButton);
        signUpFrame.add(signInLabel);
        signUpFrame.add(signInButton);

        // Action listener for sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String password = new String(passwordField.getPassword());
                int gender = maleButton.isSelected() ? 1 : 0; // Assuming 1 for male, 0 for female
                int idNumber = Integer.parseInt(idNumberField.getText());
                int phoneNumber = Integer.parseInt(phoneNumberField.getText());
                String email = emailField.getText();
                String address = addressField.getText();
                java.util.Date date = new java.util.Date();
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

                // Insert data into the database
                try (Connection connection = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO users (firstname, lastname, password, gender, idnumber, phonenumber, email, date, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setString(3, password);
                    statement.setInt(4, gender);
                    statement.setInt(5, idNumber);
                    statement.setInt(6, phoneNumber);
                    statement.setString(7, email);
                    statement.setTimestamp(8, sqlDate);
                    statement.setString(9, address);

                    // Execute the insert
                    int affectedRows = statement.executeUpdate();
                    if (affectedRows > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                long id = generatedKeys.getLong(1);
                                JOptionPane.showMessageDialog(null, "Sign Up Successful! Your User ID is: " + id);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Sign Up Failed: " + ex.getMessage());
                }
            }
        });

        // Action listener for sign-in button
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open sign-in screen
                new Signin();
                signUpFrame.dispose();
            }
        });

        // Set frame visibility
        signUpFrame.setLocationRelativeTo(null); // Center the frame
        signUpFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Signup();
    }
}




