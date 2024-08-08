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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Note {
    private JFrame mainFrame;
    private JTabbedPane tabbedPane;

    public Note() {
        // Create main frame
        mainFrame = new JFrame("Thông tin Vietcombank");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel Blue
        headerPanel.setPreferredSize(new Dimension(600, 50));

        // Header Title
        JLabel headerTitle = new JLabel("Thông tin Vietcombank");
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 24));
        headerTitle.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0)); // Add padding
        headerPanel.add(headerTitle, BorderLayout.WEST);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> mainFrame.dispose());
        backButton.setBorderPainted(false); // Remove button border
        headerPanel.add(backButton, BorderLayout.EAST); // Add back button to header panel

        // Create Tabbed Pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 16));

        // Create Panels for Tabs
        JPanel transactionsPanel = createPanel("Biến động", "transactions");
        JPanel notificationsPanel = createPanel("Thông báo", "notifications");
        JPanel offersPanel = createPanel("Ưu đãi", "offers");

        // Add Tabs to Tabbed Pane
        tabbedPane.addTab("Biến động", transactionsPanel);
        tabbedPane.addTab("Thông báo", notificationsPanel);
        tabbedPane.addTab("Ưu đãi", offersPanel);

        // Add header panel and tabbed pane to main frame
        mainFrame.add(headerPanel, BorderLayout.NORTH);
        mainFrame.add(tabbedPane, BorderLayout.CENTER);

        // Set frame visibility
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private JPanel createPanel(String title, String type) {
        JPanel panel = new JPanel(new BorderLayout());

        // Area for displaying data
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));
//        textArea.setText(loadNotesFromDatabase(type));
        textArea.setText(getSampleData(type));

        // Scroll Pane for the Text Area
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Add Scroll Pane to Panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(new JLabel(title), BorderLayout.NORTH); // Add title to the panel

        return panel;
    }

//    private String loadNotesFromDatabase(String type) {
//        StringBuilder notes = new StringBuilder();
//        String welcomeMessage = "Welcome to SmartBank\n\n";
//        String errorMessage = "Could not load data from the database.\n\n";
//
//        try (Connection connection = DatabaseConnection.getConnection()) {
//            String sql = "SELECT * FROM note WHERE type = ? ORDER BY date DESC"; // Get notes ordered by date
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, type);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                int userId = resultSet.getInt("user");
//                String message = resultSet.getString("mesege");
//                String date = resultSet.getString("date");
//                notes.append(String.format("User ID: %d\n%s\nLúc %s\n\n", userId, message, date));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Print the error stack trace for debugging
//        }
//
//        // If no notes were loaded, return the error message along with the welcome message
//        return notes.length() > 0 ? notes.toString() + welcomeMessage : errorMessage + welcomeMessage + getSampleData(type);
//    }

    private String getSampleData(String type) {
        switch (type) {
            case "transactions":
                return getSampleTransactions();
            case "notifications":
                return getSampleNotifications();
            case "offers":
                return getSampleOffers();
            default:
                return "";
        }
    }

    private String getSampleTransactions() {
        return "18/07/2024 • 10:17\n" +
                "Số dư TK VCB 9949136003 -110,000 VND\n" +
                "Lúc 18-07-2024 10:17:23. Số dư 786,989 VND. Ref\n" +
                "MBVCB.6540178588.671649. PHAM TAN DUNG chuyển tiền. CT từ 9949136003\n" +
                "PHAM TAN DUNG tới 06820046402 LE TRAN CAT TUONG tại TPBANK\n\n" +
                "05/07/2024 • 10:47\n" +
                "Số dư TK VCB 9949136003 -114,000 VND\n" +
                "Lúc 05-07-2024 10:47:38. Số dư 896,989 VND. Ref\n" +
                "MBVCB.6430271711. PHAM TAN DUNG chuyển tiền. CT từ 9949136003\n" +
                "PHAM TAN DUNG tới 1001000278864 PHI THI THUY NHUNG";
    }

    private String getSampleNotifications() {
        return "Notification 1\n" +
                "This is a sample notification message.\n\n" +
                "Notification 2\n" +
                "This is another sample notification message.";
    }

    private String getSampleOffers() {
        return "Offer 1\n" +
                "This is a sample offer message.\n\n" +
                "Offer 2\n" +
                "This is another sample offer message.";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Note::new);
    }
}


