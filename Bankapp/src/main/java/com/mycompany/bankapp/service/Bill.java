/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp.service;

import javax.swing.JFrame;

/**
 *
 * @author MINH
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;

public class Bill {
    public Bill(int index) {
        switch (index) {
            case 1:
                new WifiFee();
                break;
            case 2:
                new ElectricityBill();
                break;
            case 3:
                new WaterBill();
                break;
            case 4:
                new ApartmentFee();
                break;
        }
    }

   
}

class ElectricityBill {
    public ElectricityBill() {
        JFrame frame = new JFrame("Electricity Bill");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}

class WaterBill {
    public WaterBill() {
        JFrame frame = new JFrame("Water Bill");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ApartmentFee {
    public ApartmentFee() {
        JFrame frame = new JFrame("Apartment Fee");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}

class WifiFee {
    public WifiFee() {
        JFrame frame = new JFrame("Wifi Fee");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
