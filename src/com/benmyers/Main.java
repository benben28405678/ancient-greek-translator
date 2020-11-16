package com.benmyers;

import com.benmyers.gui.LookAndFeelManager;
import com.benmyers.gui.MainForm;

import java.util.ArrayList;

public class Main {

    private static GreekDictionaryInterpreter dictionaryInterpreter;
    private static Translator translator;

    private static MainForm mainForm;

    public static void main(String[] args) {

        dictionaryInterpreter = new GreekDictionaryInterpreter(Language.GRK, new ArrayList<Flag>());
        translator = new GreekTranslator(dictionaryInterpreter.getDictionary(), new ArrayList<Flag>());

        LookAndFeelManager.setLookAndFeel();

        MainForm.main(dictionaryInterpreter, translator);
    }
}
