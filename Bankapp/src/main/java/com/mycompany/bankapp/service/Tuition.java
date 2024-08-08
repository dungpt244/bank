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

public class Tuition {
    private JFrame mainFrame;

    public Tuition() {
        // Create main frame
        mainFrame = new JFrame("Thanh toán học phí");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 500);
        mainFrame.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel Blue
        headerPanel.setPreferredSize(new Dimension(400, 50));

        // Header Title
        JLabel headerTitle = new JLabel("Thanh toán học phí");
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerTitle);

        // Balance Panel
        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel balanceLabel = new JLabel("Số dư: 786,989 VND");
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balancePanel.add(balanceLabel);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form Fields
        formPanel.add(new JLabel("Nhà cung cấp:"));
        JTextField supplierField = new JTextField("Anh ngữ ILA VN");
        formPanel.add(supplierField);

        formPanel.add(new JLabel("Loại giao dịch:"));
        String[] transactionTypes = {"Chọn loại giao dịch", "Học phí", "Khác"};
        JComboBox<String> transactionTypeCombo = new JComboBox<>(transactionTypes);
        formPanel.add(transactionTypeCombo);

        formPanel.add(new JLabel("Mã khách hàng:"));
        JTextField customerIdField = new JTextField();
        formPanel.add(customerIdField);

        formPanel.add(new JLabel("Tên khách hàng:"));
        JTextField customerNameField = new JTextField();
        formPanel.add(customerNameField);

        formPanel.add(new JLabel("Số tiền thanh toán:"));
        JTextField amountField = new JTextField();
        formPanel.add(amountField);

        formPanel.add(new JLabel("Thông tin thêm:"));
        JTextField additionalInfoField = new JTextField();
        formPanel.add(additionalInfoField);

        // Continue Button
        JButton continueButton = new JButton("Tiếp tục");
        continueButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Arial", Font.BOLD, 16));
        continueButton.setPreferredSize(new Dimension(100, 40));

        // Add components to main frame
        mainFrame.add(headerPanel, BorderLayout.NORTH);
        mainFrame.add(balancePanel, BorderLayout.SOUTH);
        mainFrame.add(formPanel, BorderLayout.CENTER);
        mainFrame.add(continueButton, BorderLayout.SOUTH);

        // Set frame visibility
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Tuition::new);
    }
}


