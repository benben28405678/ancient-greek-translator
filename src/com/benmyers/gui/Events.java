package com.benmyers.gui;

import com.benmyers.translator.Translator;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Events extends Component {

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
                logTextArea.setText(logTextArea.getText() + "\n\n" + outputTextArea.getText());
            }
            translate(inputTextArea, outputTextArea, translator);
        }
    }

    public static void translateAndLog(JTextArea inputTextArea, JTextArea outputTextArea, JTextArea logTextArea, Translator translator) {
        translate(inputTextArea, outputTextArea, translator);
        if(logTextArea.getText().equals("")) {
            logTextArea.setText(outputTextArea.getText());
        } else {
            logTextArea.setText(logTextArea.getText() + "\n\n" + outputTextArea.getText());
        }
    }

    public static void clearLog(JTextArea log, JTextArea output) {
        log.setText("");
        output.setText("");
    }

    public static void saveLog(JTextArea n, JPanel parent) {

        final JFileChooser fc = new JFileChooser();

        final JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Save");
        int actionDialog = SaveAs.showOpenDialog(parent);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileName = new File(SaveAs.getSelectedFile() + ".txt");
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(fileName));

            n.write(outFile);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
