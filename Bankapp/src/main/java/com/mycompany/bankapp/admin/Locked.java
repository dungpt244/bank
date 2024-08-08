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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Locked {
    private JFrame mainFrame;
    private JTable accountTable;

    public Locked() {
        // Create main frame
        mainFrame = new JFrame("Danh sách tài khoản");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue
        mainFrame.setLayout(new BorderLayout());

        // Create table model with data from the database
        String[] columnNames = {"Acct #", "Description", "Acct Type", "Statement"};
        Object[][] data = fetchAccountData();

        // Create table with data
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        accountTable = new JTable(model);
        accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountTable.setDefaultEditor(Object.class, null); // Disable editing

        // Add right-click context menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem infoItem = new JMenuItem("Show Info");
        infoItem.addActionListener(e -> {
            int selectedRow = accountTable.getSelectedRow();
            if (selectedRow != -1) {
                String acctNum = (String) accountTable.getValueAt(selectedRow, 0);
                String description = (String) accountTable.getValueAt(selectedRow, 1);
                JOptionPane.showMessageDialog(mainFrame, "Tài khoản: " + acctNum + "\nMô tả: " + description);
            }
        });
        popupMenu.add(infoItem);

        accountTable.setComponentPopupMenu(popupMenu);

        // Add mouse listener for right-click
        accountTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    int row = accountTable.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        accountTable.setRowSelectionInterval(row, row);
                    }
                }
            }
        });

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(accountTable);
        mainFrame.add(scrollPane, BorderLayout.CENTER);

        // Close Button
        JButton closeButton = new JButton("Đóng");
        closeButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> mainFrame.dispose());
        mainFrame.add(closeButton, BorderLayout.SOUTH);

        // Set frame visibility
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private Object[][] fetchAccountData() {
        // Fetch account data from the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT id, description, type, statement FROM accounts WHERE locked = 1"; // Adjust as necessary
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Create a dynamic list to hold data
            DefaultTableModel model = new DefaultTableModel(new String[]{"Acct #", "Description", "Acct Type", "Statement"}, 0);
            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getString("id"),
                    resultSet.getString("description"),
                    resultSet.getString("type"),
                    resultSet.getString("statement")
                });
            }
            return model.getDataVector().toArray(new Object[0][]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Object[][]{{"Error fetching data", "", "", ""}}; // Return error message
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Locked::new);
    }
}
class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mybank"; // Change to your database name
    private static final String USER = "admin"; // Your database username
    private static final String PASSWORD = "1234"; // Your database password

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
