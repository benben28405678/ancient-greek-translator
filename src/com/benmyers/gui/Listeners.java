package com.benmyers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Listeners {

    public static ActionListener translateButtonListener(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea, JCheckBox enableLoggingCheckBox) {
        return e -> {

            if(enableLoggingCheckBox.isSelected()) {
                Events.translate(inputTextArea, outputTextArea, logTextArea);
            } else {
                Events.translate(inputTextArea, outputTextArea);
            }
        };
    }

    public static ActionListener translateAndLogButtonListener(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea) {
        return e -> Events.translateAndLog(inputTextArea, outputTextArea, logTextArea);
    }
}
