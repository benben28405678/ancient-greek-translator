package com.benmyers;

import java.util.Dictionary;
import java.util.ArrayList;

public class GreekTranslator extends Translator {

    public GreekTranslator(Dictionary dictionary) {
        super.dictionary = dictionary;
    }

    @Override
    public String translate(String input) {
        String[] words = input.split("[ .;:!]");
        ArrayList<String> translatedWords = new ArrayList<String>();

        for(String word: words) {
            if(word == " ") continue;
            String translatedWord = (String) dictionary.get(word);
            if(translatedWord == null) translatedWord = word + "*";
            translatedWords.add(translatedWord);
        }

        String output = "";

        for(String word: translatedWords) {
            output += word + " ";
        }

        output = output.substring(0, output.length() - 1);

        return output;
    }
}
