package com.benmyers.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Listeners {

    public static ActionListener translateButtonListener(JTextArea inputTextArea, JLabel outputLabel, JLabel logLabel, JCheckBox enableLoggingCheckBox) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(enableLoggingCheckBox.isSelected()) {
                    Events.translate(inputTextArea, outputLabel, logLabel);
                } else {
                    Events.translate(inputTextArea, outputLabel);
                }
            }
        };
    }
}
