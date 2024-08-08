/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankapp;
import com.mycompany.bankapp.admin.Admin; // Importing Locked class
import com.mycompany.bankapp.service.Ticket;
import com.mycompany.bankapp.service.Bill;
import com.mycompany.bankapp.service.Card;
import com.mycompany.bankapp.service.Tuition;
import com.mycompany.bankapp.service.insurance;
/**
 *
 * @author MINH
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class Bankapp {
    public static void main(String[] args) {
        new Signin();
    }
    int userID;
    public Bankapp(int userid) {
        userID=userid;
        // Create main frame
        JFrame mainFrame = new JFrame("Main Screen");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 700);
        mainFrame.setLayout(new BorderLayout());

        // Set background color
        mainFrame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Top panel (web header)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0, 58, 102)); // Steel Blue
        topPanel.setPreferredSize(new Dimension(800, 50));

        JLabel logoLabel = new JLabel(new ImageIcon("./picture/icon.jpg"), SwingConstants.LEFT); // Replace with your logo path
        logoLabel.setPreferredSize(new Dimension(100, 50)); // Set a preferred size for the logo

        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightTopPanel.setBackground(new Color(0, 58, 102)); // Steel Blue
        JButton transferButton = new JButton(new ImageIcon("path/to/transfer_icon.png")); // Replace with your icon path
        JButton notificationButton = new JButton(new ImageIcon("path/to/notification_icon.png")); // Replace with your icon path
        JButton accountButton = new JButton(new ImageIcon("path/to/account_icon.png")); // Replace with your icon path

        // Style buttons
        styleButton(transferButton);
        styleButton(notificationButton);
        styleButton(accountButton);

        // Add action listeners to buttons
        transferButton.addActionListener(e -> new Tranfer());
        notificationButton.addActionListener(e -> new Note());
        accountButton.addActionListener(e -> new Account());

        rightTopPanel.add(transferButton);
        rightTopPanel.add(notificationButton);
        rightTopPanel.add(accountButton);

        topPanel.add(logoLabel, BorderLayout.WEST);
        topPanel.add(rightTopPanel, BorderLayout.EAST);

        // Main panel with left and right parts
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left part with scrollable content
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(240, 248, 255)); // Alice Blue
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Table
        String[] columnNames = {"Column 1", "Column 2"};
        Object[][] data = {{"Row 1", "Data 1"}, {"Row 2", "Data 2"}};
        JTable table = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(100, 100));
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34))); // Forest Green
        leftPanel.add(tableScrollPane);

        // Descriptive text
        JLabel descriptiveLabel = new JLabel("Some descriptive text goes here.");
        descriptiveLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        leftPanel.add(descriptiveLabel);

        // Two images
        JLabel imageLabel1 = new JLabel(new ImageIcon("path/to/image1.png")); // Replace with your image path
        JLabel imageLabel2 = new JLabel(new ImageIcon("path/to/image2.png")); // Replace with your image path

        // Adjust size of images to fit the panel
        imageLabel1.setPreferredSize(new Dimension(300, 200));
        imageLabel2.setPreferredSize(new Dimension(300, 200));

        leftPanel.add(imageLabel1);
        leftPanel.add(imageLabel2);

        // Scrollable panel
        JScrollPane scrollPane = new JScrollPane(leftPanel);
        scrollPane.setPreferredSize(new Dimension(400, 550)); // Set preferred size for the scroll pane
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34))); // Forest Green

        // Right part with grid of buttons (2/3 size)
        JPanel gridPanel = new JPanel(new GridLayout(4, 3));
        gridPanel.setBackground(new Color(240, 248, 255)); // Alice Blue

        // Button labels
        String[] buttonLabels = {
            "Tài khoản", "Chuyển khoản", "Dịch vụ thẻ",
            "Tiền mạng", "Tiền điện", "Tiền nước",
            "Phí chung cư", "Bảo hiểm", "Học phí",
            "Vé tàu", "Vé xe", "Vé máy bay"
        };

        // Create and add buttons
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            styleButton(button);
            final int index = i; // To use in the action listener
            button.addActionListener(e -> handleButtonClick(index));
            gridPanel.add(button);
        }

        // Add components to the main panel
        mainPanel.add(scrollPane, BorderLayout.WEST);
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        // Add top and main panels to the main frame
        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(mainPanel, BorderLayout.CENTER);

        // Set frame visibility
        mainFrame.setLocationRelativeTo(null); // Center the frame
        mainFrame.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180)); // Steel Blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false); // Remove button border
    }

    private void handleButtonClick(int index) {
        switch (index) {
            case 0: new Account(); break; // Tài khoản
            case 1: new Tranfer(); break; // Chuyển khoản
            case 2: new Card(userID); break; // Dịch vụ thẻ
            case 3: new Bill(1); break; // Tiền mạng
            case 4: new Bill(2); break; // Tiền điện
            case 5: new Bill(3); break; // Tiền nước
            case 6: new Bill(4); break; // Phí chung cư
            case 7: new insurance(userID); break; // Bảo hiểm
            case 8: new Tuition(); break; // Học phí
            case 9: new Ticket(1); break; // Vé tàu
            case 10: new Ticket(2); break; // Vé xe
            case 11: new Ticket(3); break; // Vé máy bay
            default: break;
        }
    }
}
