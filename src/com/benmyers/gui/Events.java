package com.benmyers.gui;

import javax.swing.*;

public class Events {

    public static void translate(JTextArea inputTextArea, JTextArea outputTextArea) {
        outputTextArea.setText(inputTextArea.getText());
    }

    public static void translate(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea) {
        if(outputTextArea.getText().equals("") || outputTextArea.getText() == null) {
            translate(inputTextArea, outputTextArea);
        } else {
            if(logTextArea.getText().equals("")) {
                logTextArea.setText(outputTextArea.getText());
                outputTextArea.setText(inputTextArea.getText());
            } else {
                logTextArea.setText(logTextArea.getText() + "\n" + outputTextArea.getText());
                outputTextArea.setText(inputTextArea.getText());
            }
        }
    }

    public static void translateAndLog(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea) {
        outputTextArea.setText(inputTextArea.getText());
        if(logTextArea.getText().equals("")) {
            logTextArea.setText(outputTextArea.getText());
        } else {
            logTextArea.setText(logTextArea.getText() + "\n" + outputTextArea.getText());
        }
    }
}
