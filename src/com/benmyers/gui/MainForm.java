package com.benmyers.gui;

import com.benmyers.GreekDictionaryInterpreter;
import com.benmyers.GreekTranslator;
import com.benmyers.Translator;

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

    public MainForm(GreekDictionaryInterpreter interpreter, Translator translator) {

        for (String s : Arrays.asList("Greek > English", "English > Greek")) translationModeBox.addItem(s);

        translateButton.addActionListener(Listeners.translateButtonListener(inputTextArea, outputTextArea, logTextArea, enableLoggingCheckBox, translator));
        translateAndLogButton.addActionListener(Listeners.translateAndLogButtonListener(inputTextArea, outputTextArea, logTextArea, translator));
    }

    public static void main(GreekDictionaryInterpreter interpreter, Translator translator) {

        JFrame frame = new JFrame("Ancient Greek Translator");
        frame.setContentPane(new MainForm(interpreter, translator).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
