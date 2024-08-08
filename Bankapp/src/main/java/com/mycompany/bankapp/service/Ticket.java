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

public class Ticket {
    private JFrame frame;

    public Ticket(int index) {
        // Create main frame
        frame = new JFrame("Boarding Pass");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // First Boarding Pass
        JPanel pass1 = createBoardingPassPanel("JOHN DOE", "AC 2505", "New Delhi D E L", "Los Angeles K L A X", "07/05/2018", "5A", "K18", "03:30");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(pass1, gbc);

        // Second Boarding Pass
        JPanel pass2 = createBoardingPassPanel("JOHN DOE", "AC 2505", "New Delhi D E L", "Los Angeles K L A X", "07/05/2018", "5A", "K18", "03:30");
        gbc.gridx = 1;
        frame.add(pass2, gbc);

        // Set frame visibility
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createBoardingPassPanel(String passengerName, String flightNumber, String from, String to, String date, String seat, String gate, String boardingTime) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        panel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name of passenger: " + passengerName);
        JLabel flightLabel = new JLabel("Flight No: " + flightNumber);
        JLabel fromLabel = new JLabel("From: " + from);
        JLabel toLabel = new JLabel("To: " + to);
        JLabel dateLabel = new JLabel("Date: " + date);
        JLabel seatLabel = new JLabel("Seat: " + seat);
        JLabel gateLabel = new JLabel("GATE: " + gate);
        JLabel boardingTimeLabel = new JLabel("BOARDING TIME: " + boardingTime);

        panel.add(nameLabel);
        panel.add(flightLabel);
        panel.add(fromLabel);
        panel.add(toLabel);
        panel.add(dateLabel);
        panel.add(seatLabel);
        panel.add(gateLabel);
        panel.add(boardingTimeLabel);

        return panel;
    }
}
