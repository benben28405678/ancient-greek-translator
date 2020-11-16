package com.benmyers.gui;

import javax.swing.*;
import java.util.Arrays;

public class MainForm {

    private JPanel mainPanel;
    private JLabel titleLabel;
    protected JComboBox<String> translationModeBox;
    private JLabel outputLabel;
    private JPanel inputPanel;
    private JTextArea inputTextArea;
    private JButton translateAndLogButton;
    private JCheckBox autotranslateCheckBox;
    private JButton translateButton;
    private JScrollPane inputScrollPane;
    private JTabbedPane tabPane;
    private JPanel translatorTabPanel;
    private JPanel dictionaryTabPanel;
    private JPanel outputPanel;
    private JTextArea outputTextArea;
    private JTextArea logTextArea;
    private JButton clearButton;
    private JButton saveToFileButton;
    private JPanel logPanel;
    private JLabel logLabel;
    private JCheckBox enableLoggingCheckBox;

    public MainForm() {

        for (String s : Arrays.asList("Greek > English", "English > Greek")) translationModeBox.addItem(s);

        translateButton.addActionListener(Listeners.translateButtonListener(inputTextArea, outputTextArea, logTextArea, enableLoggingCheckBox));
        translateAndLogButton.addActionListener(Listeners.translateAndLogButtonListener(inputTextArea, outputTextArea, logTextArea));
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Ancient Greek Translator");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
