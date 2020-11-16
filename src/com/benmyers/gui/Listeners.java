package com.benmyers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Listeners {

    public static ActionListener translateButtonListener(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea, JCheckBox enableLoggingCheckBox) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(enableLoggingCheckBox.isSelected()) {
                    Events.translate(inputTextArea, outputTextArea, logTextArea);
                } else {
                    Events.translate(inputTextArea, outputTextArea);
                }
            }
        };
    }
}
