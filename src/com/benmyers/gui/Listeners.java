package com.benmyers.gui;

import com.benmyers.translator.Translator;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Listeners{

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

    public static ActionListener saveLogButtonListener(JTextArea logTextArea, JPanel parent) {
        return e -> Events.saveLog(logTextArea, parent);
    }

    public static ActionListener lockCheckButtonListener(JCheckBox lockCheckBox, JTextArea outputTextArea) {
        return e -> Events.toggleOutputLock(lockCheckBox, outputTextArea);
    }

    public static DocumentListener inputTextUpdateListener(JTextArea inputTextArea, JTextArea outputTextArea, JCheckBox autotranslateCheckBox, Translator translator) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                onUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                onUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {}

            private void onUpdate() {
                if(autotranslateCheckBox.isSelected()) Events.translate(inputTextArea, outputTextArea, translator);
            }
        };
    }
}
