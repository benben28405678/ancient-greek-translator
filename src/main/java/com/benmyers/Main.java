package com.benmyers;

import com.benmyers.gui.LookAndFeelManager;
import com.benmyers.gui.MainForm;
import com.benmyers.translator.GreekTranslator;
import com.benmyers.translator.Translator;
import com.formdev.flatlaf.FlatDarculaLaf;

import java.util.ArrayList;

public class Main {

    private static GreekDictionaryInterpreter dictionaryInterpreter;
    private static Translator translator;

    private static MainForm mainForm;

    public static void main(String[] args) {

        dictionaryInterpreter = new GreekDictionaryInterpreter(new ArrayList<Flag>());
        translator = new GreekTranslator(dictionaryInterpreter.getDictionary(), new ArrayList<Flag>());

        FlatDarculaLaf.install();

        //LookAndFeelManager.setLookAndFeel();

        MainForm.main(dictionaryInterpreter, translator);
    }
}
