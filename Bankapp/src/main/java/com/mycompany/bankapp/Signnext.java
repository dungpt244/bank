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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Signnext {
    private JFrame frame;
    private JLabel imageLabel;

    public Signnext() {
        // Create main frame
        frame = new JFrame("Chụp Ảnh Giấy Tờ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Alice Blue

        // Icon (optional)
        JLabel iconLabel = new JLabel(new ImageIcon("path/to/your/icon.png")); // Add your icon path here
        iconLabel.setBounds(150, 20, 100, 100); // Position and size for the icon
        frame.add(iconLabel);

        // Title
        JLabel titleLabel = new JLabel("Vui lòng đưa giấy tờ vào khung");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(50, 130, 300, 30);
        frame.add(titleLabel);

        // CMND/CCCD Label
        JLabel cmndLabel = new JLabel("CMND/CCCD");
        cmndLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cmndLabel.setBounds(50, 170, 100, 30);
        frame.add(cmndLabel);

        // Image Label to display selected image
        imageLabel = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(300, 200));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imageLabel.setBounds(50, 210, 300, 200);
        frame.add(imageLabel);

        // Upload Button
        JButton uploadButton = new JButton("Chọn Ảnh");
        uploadButton.setBounds(150, 420, 100, 30);
        uploadButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        uploadButton.setForeground(Color.WHITE);
        uploadButton.setFocusPainted(false);
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imageLabel.setText(selectedFile.getName()); // Display file name
                    // Here you can add code to display the image if needed
                }
            }
        });
        //Confim button
        JButton confim = new JButton("Confim");
        confim.setBounds(150, 470, 100, 30);
        confim.setBackground(new Color(70, 130, 180)); // Steel Blue
        confim.setForeground(Color.WHITE);
        confim.setFocusPainted(false);
        // Action listener for sign-in button
        confim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close 
                frame.dispose();
            }
        });
        
        frame.add(uploadButton);
        frame.add(confim);
        // Set frame visibility
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signnext::new);
    }
}


