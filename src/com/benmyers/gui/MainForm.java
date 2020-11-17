package com.benmyers.gui;

import com.benmyers.GreekDictionaryInterpreter;
import com.benmyers.translator.Translator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

    private Translator translator;

    public MainForm(GreekDictionaryInterpreter interpreter, Translator translator) {

        this.translator = translator;

        for (String s : Arrays.asList("Greek > English", "English > Greek")) translationModeBox.addItem(s);

        translateButton.addActionListener(Listeners.translateButtonListener(inputTextArea, outputTextArea, logTextArea, enableLoggingCheckBox, translator));
        translateAndLogButton.addActionListener(Listeners.translateAndLogButtonListener(inputTextArea, outputTextArea, logTextArea, translator));
        clearButton.addActionListener(Listeners.clearLogButtonListener(logTextArea, outputTextArea));
        saveToFileButton.addActionListener(Listeners.saveLogButtonListener(logTextArea, mainPanel));

        inputTextArea.getDocument().addDocumentListener(Listeners.inputTextUpdateListener(inputTextArea, outputTextArea, autotranslateCheckBox, translator));
    }

    public static void main(GreekDictionaryInterpreter interpreter, Translator translator) {

        JFrame frame = new JFrame("Ancient Greek Translator");
        frame.setContentPane(new MainForm(interpreter, translator).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
