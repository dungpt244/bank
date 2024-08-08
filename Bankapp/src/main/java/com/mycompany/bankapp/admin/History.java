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
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class History {
    private JFrame mainFrame;
    private JTextArea transactionArea;

    public History() {
        // Create main frame
        mainFrame = new JFrame("Transaction History");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
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
        headerTitle.setBorder(new EmptyBorder(10, 20, 10, 0)); // Add padding
        headerPanel.add(headerTitle, BorderLayout.WEST);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(70, 130, 180)); // Forest Green
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> mainFrame.dispose());
        backButton.setBorderPainted(false); // Remove button border
        headerPanel.add(backButton, BorderLayout.EAST); // Add back button to header panel

        // Transaction Area
        transactionArea = new JTextArea();
        transactionArea.setEditable(false);
        transactionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        transactionArea.setLineWrap(true);
        transactionArea.setWrapStyleWord(true);
        transactionArea.setBackground(new Color(240, 248, 255)); // Alice Blue
        transactionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Sample transaction data
        String transactions = "18/07/2024 • 10:17\n" +
                "Số dư TK VCB 9949136003 -110,000 VND\n" +
                "Lúc 18-07-2024 10:17:23. Số dư 786,989 VND. Ref\n" +
                "MBVCB.6540178588.671649. PHAM TAN DUNG chuyển tiền. CT từ 9949136003\n" +
                "PHAM TAN DUNG tới 06820046402 LE TRAN CAT TUONG tại TPBANK\n\n" +
                "05/07/2024 • 10:47\n" +
                "Số dư TK VCB 9949136003 -114,000 VND\n" +
                "Lúc 05-07-2024 10:47:38. Số dư 896,989 VND. Ref\n" +
                "MBVCB.6430271711. PHAM TAN DUNG chuyển tiền. CT từ 9949136003\n" +
                "PHAM TAN DUNG tới 1001000278864 PHI THI THUY NHUNG";

        transactionArea.setText(transactions);

        // Scroll Pane for the Transaction Area
        JScrollPane scrollPane = new JScrollPane(transactionArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34))); // Border color
        scrollPane.setPreferredSize(new Dimension(580, 300)); // Set preferred size
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar

        // Add components to the main frame
        mainFrame.add(headerPanel, BorderLayout.NORTH);
        mainFrame.add(scrollPane, BorderLayout.CENTER);

        // Set frame visibility
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(History::new);
    }
}
