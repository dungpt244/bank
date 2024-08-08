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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trade {
    private JLabel tradeStatusLabel;

    public Trade() {
        // Create frame
        JFrame frame = new JFrame("Trade Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        // Set background color
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Trade status label
        tradeStatusLabel = new JLabel("");
        tradeStatusLabel.setBounds(20, 300, 350, 100);
        tradeStatusLabel.setForeground(Color.RED);
        frame.add(tradeStatusLabel);

        // Set frame visibility
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public void executeTrade(int senderId, String senderName, int receiverId, String receiverName, double sendMoney, double cost) {
        double receiverMoney = sendMoney * 0.9999; // 99.99% of the cost
        double newSenderMoney = sendMoney - cost; // Sender's new balance

        // Transaction timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // Save trade to database
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Insert into trade table
            String tradeSql = "INSERT INTO trade (send, recive, amount, time) VALUES (?, ?, ?, ?)";
            PreparedStatement tradeStatement = connection.prepareStatement(tradeSql);
            tradeStatement.setInt(1, senderId);
            tradeStatement.setInt(2, receiverId);
            tradeStatement.setDouble(3, cost);
            tradeStatement.setString(4, timestamp);
            tradeStatement.executeUpdate();

            // Insert note for sender
            String senderNote = String.format("Lúc %s. Số dư TK %d - %.2f\nSố dư %.2f VND. Ref\nTrade ID: %d. %s chuyển tiền. CT từ %d\n%s tới %d %s tại SMBANK",
                    timestamp, senderId, cost, newSenderMoney, tradeStatement.getGeneratedKeys().getInt(1), senderName, senderId, receiverName, receiverId);
            insertNoteToDatabase(senderId, senderNote, timestamp);

            // Insert note for receiver
            String receiverNote = String.format("Lúc %s. Số dư TK %d + %.2f\nSố dư %.2f VND. Ref\nTrade ID: %d. %s Nhận tiền. NT từ %d\n%s từ %d %s tại SMBANK",
                    timestamp, receiverId, cost, receiverMoney, tradeStatement.getGeneratedKeys().getInt(1), receiverName, senderId, senderName, senderId);
            insertNoteToDatabase(receiverId, receiverNote, timestamp);

            tradeStatusLabel.setText("Trade executed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            tradeStatusLabel.setText("Error executing trade: " + e.getMessage());
        }
    }

    private void insertNoteToDatabase(int userId, String message, String timestamp) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String noteSql = "INSERT INTO note (user, mesege, date) VALUES (?, ?, ?)";
            PreparedStatement noteStatement = connection.prepareStatement(noteSql);
            noteStatement.setInt(1, userId);
            noteStatement.setString(2, message);
            noteStatement.setString(3, timestamp);
            noteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Trade(); // Launch the trade screen
    }
}
