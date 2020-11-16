package com.benmyers.gui;

import javax.swing.*;
import java.awt.*;

public class MainForm {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox translationModeBox;
    private JScrollPane liveOutputScrollPane;
    private JPanel liveOutputPanel;
    private JLabel outputLabel;
    private JPanel inputPanel;
    private JTextArea inputTextArea;
    private JButton translateButton;
    private JCheckBox autotranslateCheckBox;
    private JScrollPane loggedOutputPanel;
    private JButton clearLogButton;
    private JButton saveToFileButton;
    private JButton translateButton1;
    private JScrollPane inputScrollPane;
    private JTabbedPane tabPane;
    private JPanel translatorTabPanel;
    private JPanel dictionaryTabPanel;

    public MainForm() {

        translationModeBox.addItem("Greek > English");
        translationModeBox.addItem("English > Greek");
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Ancient Greek Translator");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
