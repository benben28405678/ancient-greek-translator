package com.benmyers.gui;

import com.benmyers.Translator;

import java.awt.event.ActionListener;
import javax.swing.*;

public class Listeners {

    public static ActionListener translateButtonListener(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea, JCheckBox enableLoggingCheckBox, Translator translator) {
        return e -> {

            if(enableLoggingCheckBox.isSelected()) {
                Events.translate(inputTextArea, outputTextArea, logTextArea, translator);
            } else {
                Events.translate(inputTextArea, outputTextArea, translator);
            }
        };
    }

    public static ActionListener translateAndLogButtonListener(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea, Translator translator) {
        return e -> Events.translateAndLog(inputTextArea, outputTextArea, logTextArea, translator);
    }

    public static ActionListener clearLogButtonListener(JTextArea logTextArea, JTextArea outputTextArea) {
        return e -> Events.clearLog(logTextArea, outputTextArea);
    }
}
