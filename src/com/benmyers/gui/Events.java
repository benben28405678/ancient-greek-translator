package com.benmyers.gui;

import javax.swing.*;

public class Events {

    public static void translate(JTextArea inputTextArea, JLabel outputLabel) {
        outputLabel.setText(inputTextArea.getText());
    }

    public static void translate(JTextArea inputTextArea, JLabel outputLabel, JLabel logLabel) {
        if(outputLabel.getText() == "" || outputLabel.getText() == null) {
            translate(inputTextArea, outputLabel);
        } else {
            logLabel.setText(logLabel.getText() + "\n" + outputLabel.getText());
            outputLabel.setText(inputTextArea.getText());
        }

    }

    public static void translateAndLog() {
    }
}
