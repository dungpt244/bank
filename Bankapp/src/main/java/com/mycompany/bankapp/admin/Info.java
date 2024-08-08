/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp.admin;

/**
 *
 * @author MINH
 */
import javax.swing.*;
import java.awt.*;

public class Info {
    public Info() {
        // Create frame
        JFrame frame = new JFrame("Account Detail");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Create components
        JLabel nameLabel = new JLabel("Name: Dr. Clinton Ackerman");
        nameLabel.setBounds(20, 30, 350, 30);

        JLabel middleLabel = new JLabel("Middle: C");
        middleLabel.setBounds(20, 70, 350, 30);

        JLabel suffixLabel = new JLabel("Suffix: ");
        suffixLabel.setBounds(20, 110, 350, 30);

        JLabel accountIdentifierLabel = new JLabel("Account Identifier: A1000");
        accountIdentifierLabel.setBounds(20, 150, 350, 30);

        JLabel accountPlanLabel = new JLabel("Account Plan: ");
        accountPlanLabel.setBounds(20, 190, 350, 30);

        JLabel countryLabel = new JLabel("Country: ");
        countryLabel.setBounds(20, 230, 350, 30);

        JLabel signatureLabel = new JLabel("Signature Page Display Name: Clinton C Ackerman, DO (609) 394-2331");
        signatureLabel.setBounds(20, 270, 350, 30);

        JLabel accountRecordTypeLabel = new JLabel("Account Record Type: Professional");
        accountRecordTypeLabel.setBounds(20, 310, 350, 30);

        JLabel primaryParentLabel = new JLabel("Primary Parent: Newton Memorial Hospital");
        primaryParentLabel.setBounds(20, 350, 350, 30);

        // Change Password button
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(20, 400, 150, 30);
        changePasswordButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFocusPainted(false);

        // OTP Fields
        JLabel otpLabel = new JLabel("Enter OTP Code:");
        otpLabel.setBounds(20, 450, 150, 30);
        JTextField[] otpFields = new JTextField[6];

        for (int i = 0; i < otpFields.length; i++) {
            otpFields[i] = new JPasswordField(); // Use JPasswordField to hide input
            otpFields[i].setBounds(20 + (i * 50), 490, 40, 30);
            otpFields[i].setHorizontalAlignment(JTextField.CENTER);
            frame.add(otpFields[i]);
        }

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 10, 100, 30);
        backButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        
        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 550, 100, 30);
        saveButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        
        // Add components to frame
        frame.add(nameLabel);
        frame.add(middleLabel);
        frame.add(suffixLabel);
        frame.add(accountIdentifierLabel);
        frame.add(accountPlanLabel);
        frame.add(countryLabel);
        frame.add(signatureLabel);
        frame.add(accountRecordTypeLabel);
        frame.add(primaryParentLabel);
        frame.add(changePasswordButton);
        frame.add(otpLabel);
        frame.add(backButton);
        frame.add(saveButton);

        // Action listener for back button
        backButton.addActionListener(e -> {
            // Close the account detail frame
            frame.dispose();
        });
        
        // Action listener for back button
        saveButton.addActionListener(e -> {
            // Close the account detail frame
            frame.dispose();
        });

        // Set frame visibility
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Info(); // Launch the account detail screen
    }
}


