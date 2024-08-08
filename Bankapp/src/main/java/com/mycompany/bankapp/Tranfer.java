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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tranfer {
    private JFrame frame;
    private JTextField amountField, recipientAccountField, contentField;
    private JLabel senderIdLabel, senderBalanceLabel;
    private int userId = 1; // Replace with the actual user ID for the sender

    public Tranfer() {
        // Create main frame
        frame = new JFrame("Chuyển tiền trong nước");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Close Button
        JButton closeButton = new JButton("Đóng");
        closeButton.setBounds(10, 10, 100, 30);
        closeButton.setBackground(new Color(70, 130, 180)); // Optional: Set a color to stand out
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        frame.add(closeButton);

        // Action Listener for the close button
        closeButton.addActionListener(e -> frame.dispose());

        // Sender ID Label
        senderIdLabel = new JLabel("Tài khoản nguồn ID: " + userId);
        senderIdLabel.setBounds(50, 60, 300, 30);
        frame.add(senderIdLabel);

        // Sender Balance Label
        senderBalanceLabel = new JLabel("Số dư: " + getCurrentBalance(userId) + " VND");
        senderBalanceLabel.setBounds(50, 100, 300, 30);
        frame.add(senderBalanceLabel);

        // Amount Label and TextField
        JLabel amountLabel = new JLabel("Số tiền:");
        amountLabel.setBounds(50, 140, 100, 30);
        frame.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 140, 200, 30);
        frame.add(amountField);

        // Recipient Account Label and TextField
        JLabel recipientAccountLabel = new JLabel("Tài khoản/thẻ nhận:");
        recipientAccountLabel.setBounds(50, 180, 150, 30);
        frame.add(recipientAccountLabel);

        recipientAccountField = new JTextField();
        recipientAccountField.setBounds(150, 180, 200, 30);
        frame.add(recipientAccountField);

        // Transaction Content Label and TextField
        JLabel contentLabel = new JLabel("Nội dung:");
        contentLabel.setBounds(50, 220, 100, 30);
        frame.add(contentLabel);

        contentField = new JTextField();
        contentField.setBounds(150, 220, 200, 30);
        frame.add(contentField);

        // Transfer Button
        JButton transferButton = new JButton("Chuyển tiền");
        transferButton.setBounds(150, 350, 100, 30);
        transferButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        transferButton.setForeground(Color.WHITE);
        transferButton.setFocusPainted(false);
        frame.add(transferButton);

        // Action listener for the transfer button
        transferButton.addActionListener(e -> executeTransfer());

        // Set frame visibility
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    private double getCurrentBalance(int userId) {
        double balance = 0.0;
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT balance FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    private String getFullName(int userId) {
        String fullName = "";
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT Firstname, Lastname FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                fullName = resultSet.getString("Firstname") + ", " + resultSet.getString("Lastname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullName;
    }

    private void executeTransfer() {
        double amount = Double.parseDouble(amountField.getText());
        String recipientAccount = recipientAccountField.getText();
        String content = contentField.getText();

        // Fetch receiver ID from the recipient account field
        int recipientId = Integer.parseInt(recipientAccount); // Assuming the recipientAccountField contains the ID

        // Fetch sender and receiver names
        String senderName = getFullName(userId);
        String receiverName = getFullName(recipientId);

        // Create a Trade object and execute the trade
        Trade trade = new Trade();
        trade.executeTrade(userId, senderName, recipientId, receiverName, amount, amount); // Adjust as necessary
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tranfer::new);
    }
}


