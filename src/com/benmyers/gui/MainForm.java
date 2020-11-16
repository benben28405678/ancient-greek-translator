package com.benmyers.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox translationModeBox;
    private JScrollPane liveOutputScrollPane;
    private JPanel liveOutputPanel;
    private JLabel outputLabel;
    private JPanel inputPanel;
    private JTextArea inputTextArea;
    private JButton translateAndLogButton;
    private JCheckBox autotranslateCheckBox;
    private JScrollPane loggedOutputPanel;
    private JButton clearLogButton;
    private JButton saveToFileButton;
    private JButton translateButton;
    private JScrollPane inputScrollPane;
    private JTabbedPane tabPane;
    private JPanel translatorTabPanel;
    private JPanel dictionaryTabPanel;
    private JLabel logLabel;
    private JCheckBox enableLoggingCheckBox;

    public MainForm() {

        translationModeBox.addItem("Greek > English");
        translationModeBox.addItem("English > Greek");

        translateButton.addActionListener(Listeners.translateButtonListener(inputTextArea, outputLabel, logLabel, enableLoggingCheckBox));
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Ancient Greek Translator");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
