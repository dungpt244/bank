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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin {
    private JFrame mainFrame;
    private JList<String> itemList;
    private DefaultListModel<String> listModel;

    public Admin() {
        initializeUI();
    }

    private void initializeUI() {
        // Create main frame
        mainFrame = new JFrame("Admin Panel");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = createHeaderPanel();

        // Menu Panel
        JPanel menuPanel = createMenuPanel();

        // Right Panel
        JPanel rightPanel = createRightPanel();

        // Add panels to main frame
        mainFrame.add(headerPanel, BorderLayout.NORTH);
        mainFrame.add(menuPanel, BorderLayout.WEST);
        mainFrame.add(rightPanel, BorderLayout.CENTER);

        // Set frame visibility
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel Blue
        headerPanel.setPreferredSize(new Dimension(800, 50));

        JLabel headerIcon = new JLabel(new ImageIcon("path/to/your/icon.png")); // Replace with your icon path
        JLabel headerTitle = new JLabel("Admin Control Panel");
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setFont(new Font("Arial", Font.BOLD, 24));

        headerPanel.add(headerIcon);
        headerPanel.add(headerTitle);

        return headerPanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(200, 600));
        menuPanel.setBackground(new Color(240, 248, 255)); // Alice Blue

        JButton dashboardButton = createMenuButton("Dashboard", e -> new Admin());
        JButton lockedButton = createMenuButton("Locked", e -> new Locked());
        JButton settingsButton = createMenuButton("Settings", e -> new Info());

        menuPanel.add(dashboardButton);
        menuPanel.add(lockedButton);
        menuPanel.add(settingsButton);

        return menuPanel;
    }

    private JButton createMenuButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        button.setBackground(new Color(70, 130, 180)); // Steel Blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false); // Remove button border
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.addActionListener(actionListener);
        return button;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());

        // List Model and JList
        listModel = new DefaultListModel<>();
        populateListModel();
        itemList = new JList<>(listModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(itemList);

        // Action Buttons Panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton viewButton = new JButton("View");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        actionPanel.add(viewButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);

        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(actionPanel, BorderLayout.SOUTH);

        // Add mouse listener for double-click
        itemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedItem = itemList.getSelectedValue();
                    if (selectedItem != null) {
                        openAccountSettings(selectedItem);
                    }
                }
            }
        });

        return rightPanel;
    }

    private void populateListModel() {
        // Sample data
        listModel.addElement("Account 1 - ID: 001");
        listModel.addElement("Account 2 - ID: 002");
        listModel.addElement("Account 3 - ID: 003");
        listModel.addElement("Account 4 - ID: 004");
    }

    private void openAccountSettings(String accountInfo) {
        JFrame settingsFrame = new JFrame("Account Settings");
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(400, 300);
        settingsFrame.setLayout(new FlowLayout());

        // Display account information
        settingsFrame.add(new JLabel("Settings for: " + accountInfo));

        // Example settings components
        settingsFrame.add(new JLabel("Change Name:"));
        JTextField nameField = new JTextField(20);
        settingsFrame.add(nameField);

        settingsFrame.add(new JLabel("Change ID:"));
        JTextField idField = new JTextField(20);
        settingsFrame.add(idField);

        JButton saveButton = new JButton("Save");
        settingsFrame.add(saveButton);

        // Action listener for save button
        saveButton.addActionListener(e -> {
            // Save logic here
            JOptionPane.showMessageDialog(settingsFrame, "Settings saved for " + accountInfo);
            settingsFrame.dispose();
        });

        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Admin::new);
    }
}