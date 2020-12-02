package com.benmyers;

import com.benmyers.gui.LookAndFeelManager;
import com.benmyers.gui.MainForm;
import com.benmyers.interpreter.GreekDictionaryInterpreter;
import com.benmyers.translator.GreekTranslator;
import com.benmyers.translator.Translator;

import java.util.ArrayList;
import java.util.Dictionary;

public class Main {

    private static GreekDictionaryInterpreter dictionaryInterpreter;
    private static Translator translator;

    private static MainForm mainForm;
    private static ArrayList<Flag> flags;

    public static void main(String[] args) {

        System.out.println("Ancient Greek Translator");

        flags = new ArrayList<Flag>();
        flags.add(Flag.ALTERNATIVE_ENGLISH);

        dictionaryInterpreter = new GreekDictionaryInterpreter(new ArrayList<Flag>());
        translator = new GreekTranslator(dictionaryInterpreter.getDictionary(), flags);

        LookAndFeelManager.setLookAndFeel();

        MainForm.main(dictionaryInterpreter, translator);
    }

    public static void modeEngToGrk() {
        dictionaryInterpreter = new GreekDictionaryInterpreter(new ArrayList<Flag>());
        Dictionary greekDictionary = dictionaryInterpreter.getDictionary();
        //dictionaryInterpreter = new EnglishDictionaryInterpreter(dictionaryInterpreter.getDictionary());
        //translator = new EnglishTranslator(dictionaryInterpreter.getDictionary(), flags);
    }

    public static void modeGrkToEng() {
        dictionaryInterpreter = new GreekDictionaryInterpreter(new ArrayList<Flag>());
        translator = new GreekTranslator(dictionaryInterpreter.getDictionary(), flags);
    }
}
