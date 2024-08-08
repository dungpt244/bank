/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp.admin;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    private JFrame mainFrame;
    private JTable accountTable;
    private DefaultTableModel model;

    public Test() {
        // Create main frame
        mainFrame = new JFrame("Danh sách tài khoản");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 400);
        mainFrame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue
        mainFrame.setLayout(new BorderLayout());

        // Create table model with sample data
        String[] columnNames = {"Acct #", "Description", "Acct Type", "Statement"};
        Object[][] data = fetchSampleAccountData();

        // Create table with data
        model = new DefaultTableModel(data, columnNames);
        accountTable = new JTable(model);
        accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountTable.setDefaultEditor(Object.class, null); // Disable editing

        // Add right-click context menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem infoItem = new JMenuItem("Show Info");
        infoItem.addActionListener(e -> showAccountInfo());
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

        // Delete Button
        JButton deleteButton = new JButton("Xóa");
        deleteButton.setBackground(new Color(220, 20, 60)); // Crimson
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteSelectedAccount());
        mainFrame.add(deleteButton, BorderLayout.SOUTH);

        // Set frame visibility
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private Object[][] fetchSampleAccountData() {
        // Sample data for the table
        return new Object[][]{
            {"1001", "Cash", "Asset", "Balance Sheet"},
            {"1002", "Accounts Receivable", "Asset", "Balance Sheet"},
            {"1003", "Inventory", "Asset", "Balance Sheet"},
            {"1004", "Fixed Assets", "Asset", "Balance Sheet"},
            {"1005", "Prepaid Expenses", "Asset", "Balance Sheet"},
            {"2001", "Accounts Payable", "Liability", "Balance Sheet"},
            {"2002", "Taxes Payable", "Liability", "Balance Sheet"},
            {"2003", "Wages Payable", "Liability", "Balance Sheet"},
            {"3001", "Common Stock", "Equity", "Balance Sheet"},
            {"4001", "Sales Revenue", "Revenue", "Income Statement"},
            {"5001", "Cost of Goods Sold", "Expense", "Income Statement"},
        };
    }

    private void showAccountInfo() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow != -1) {
            String acctNum = (String) accountTable.getValueAt(selectedRow, 0);
            String description = (String) accountTable.getValueAt(selectedRow, 1);
            JOptionPane.showMessageDialog(mainFrame, "Tài khoản: " + acctNum + "\nMô tả: " + description);
        }
    }

    private void deleteSelectedAccount() {
        int selectedRow = accountTable.getSelectedRow();
        if (selectedRow != -1) {
            String acctNum = (String) accountTable.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(mainFrame, "Bạn có chắc chắn muốn xóa tài khoản " + acctNum + "?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Remove from the table
                model.removeRow(selectedRow);

                // Optional: Remove from database
                deleteAccountFromDatabase(acctNum);
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Vui lòng chọn một tài khoản để xóa.");
        }
    }

    private void deleteAccountFromDatabase(String acctNum) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM accounts WHERE id = ?"; // Adjust as necessary
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, acctNum);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Lỗi khi xóa tài khoản từ cơ sở dữ liệu.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Test::new);
    }
}
