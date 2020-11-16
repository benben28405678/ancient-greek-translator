package com.benmyers.gui;

import com.benmyers.Translator;

import javax.swing.*;

public class Events {

    public static void translate(JTextArea inputTextArea, JTextArea outputTextArea, Translator translator) {

        String input = inputTextArea.getText();
        String[] output = translator.alignAndTranslate(input);

        outputTextArea.setText(output[0] + "\n" + output[1]);
    }

    public static void translate(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea, Translator translator) {
        if(outputTextArea.getText().equals("")) {
            translate(inputTextArea, outputTextArea, translator);
        } else {
            if(logTextArea.getText().equals("")) {
                logTextArea.setText(outputTextArea.getText());
            } else {
                logTextArea.setText(logTextArea.getText() + "\n" + outputTextArea.getText());
            }
            translate(inputTextArea, outputTextArea, translator);
        }
    }

    public static void translateAndLog(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea, Translator translator) {
        translate(inputTextArea, outputTextArea, translator);
        if(logTextArea.getText().equals("")) {
            logTextArea.setText(outputTextArea.getText());
        } else {
            logTextArea.setText(logTextArea.getText() + "\n" + outputTextArea.getText());
        }
    }

    public static void clearLog(JTextArea log, JTextArea output) {
        log.setText("");
        output.setText("");
    }
}
