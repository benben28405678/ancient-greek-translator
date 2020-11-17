package com.benmyers.gui;

import javax.swing.*;

public class LookAndFeelManager {

    private static final String LOOK_AND_FEEL_CLASS_NAME = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    public static void setLookAndFeel() {

        try {
            UIManager.setLookAndFeel(LOOK_AND_FEEL_CLASS_NAME); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
