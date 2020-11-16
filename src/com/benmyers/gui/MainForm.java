package com.benmyers.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainForm {

    private JLabel titleLabel;
    private JButton clickMeButton;
    private JPanel mainPanel;

    public MainForm() {

        clickMeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clickMeButton.setText("Yay!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
