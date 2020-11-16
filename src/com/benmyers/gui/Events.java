package com.benmyers.gui;

import javax.swing.*;

public class Events {

    public static void translate(JTextArea inputTextArea, JTextArea outputTextArea) {
        outputTextArea.setText(inputTextArea.getText());
    }

    public static void translate(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea) {
        if(outputTextArea.getText() == "" || outputTextArea.getText() == null) {
            translate(inputTextArea, outputTextArea);
        } else {
            logTextArea.setText(logTextArea.getText() + "\n" + outputTextArea.getText());
            outputTextArea.setText(inputTextArea.getText());
        }

    }

    public static void translateAndLog() {
    }
}
