package com.benmyers;

import com.benmyers.gui.LookAndFeelManager;
import com.benmyers.gui.MainForm;
import com.benmyers.translator.GreekTranslator;
import com.benmyers.translator.Translator;

import java.util.ArrayList;

public class Main {

    private static GreekDictionaryInterpreter dictionaryInterpreter;
    private static Translator translator;

    private static MainForm mainForm;

    public static void main(String[] args) {

        System.out.println("Ancient Greek Translator");

        ArrayList<Flag> flags = new ArrayList<Flag>();
        flags.add(Flag.ALTERNATIVE_ENGLISH);

        dictionaryInterpreter = new GreekDictionaryInterpreter(new ArrayList<Flag>());
        translator = new GreekTranslator(dictionaryInterpreter.getDictionary(), flags);

        LookAndFeelManager.setLookAndFeel();

        MainForm.main(dictionaryInterpreter, translator);
    }
}
