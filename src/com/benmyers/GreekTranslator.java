package com.benmyers;

import java.util.Dictionary;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreekTranslator extends Translator {

    public GreekTranslator(Dictionary dictionary, ArrayList<Flag> flags) {
        super.dictionary = dictionary;
        super.flags = flags;
    }

    @Override
    public String translate(String input) {
        String[] words = input.split("[ .;:!]");
        ArrayList<String> translatedWords = new ArrayList<String>();

        for(String word: words) {
            if(word == " ") continue;
            String translatedWord = (String) dictionary.get(word);
            if(!flags.contains(Flag.ALTERNATIVE_ENGLISH) && translatedWord != null) {
                String[] chunks = translatedWord.split("/");
                Pattern p = Pattern.compile("\\(.*\\)");
                Matcher m = p.matcher(chunks[chunks.length - 1]);

                if(m.find() && chunks.length > 1) {
                    translatedWord = chunks[0] + m.group(0);
                } else {
                    translatedWord = chunks[0];
                }

            }
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

    public String pronounce(String input) {

        input = input.replaceAll("Α", "Ah");
        input = input.replaceAll("α", "ah");
        input = input.replaceAll("Β", "B");
        input = input.replaceAll("β", "b");
        input = input.replaceAll("Γ", "G");
        input = input.replaceAll("γ", "g");
        input = input.replaceAll("Δ", "D");
        input = input.replaceAll("δ", "d");
        input = input.replaceAll("Ε", "Eh");
        input = input.replaceAll("ε", "eh");
        input = input.replaceAll("Ζ", "Z");
        input = input.replaceAll("ζ", "z");
        input = input.replaceAll("Η", "Ai");
        input = input.replaceAll("η", "ai");
        input = input.replaceAll("Θ", "T");
        input = input.replaceAll("θ", "t");
        input = input.replaceAll("Ι", "Ih");
        input = input.replaceAll("ι", "ih");
        input = input.replaceAll("Κ", "K");
        input = input.replaceAll("κ", "k");
        input = input.replaceAll("Λ", "L");
        input = input.replaceAll("λ", "l");
        input = input.replaceAll("Μ", "M");
        input = input.replaceAll("μ", "m");
        input = input.replaceAll("Ν", "N");
        input = input.replaceAll("ν", "n");
        input = input.replaceAll("Ξ", "X");
        input = input.replaceAll("ξ", "x");
        input = input.replaceAll("Ο", "O");
        input = input.replaceAll("ο", "o");
        input = input.replaceAll("Π", "P");
        input = input.replaceAll("π", "p");
        input = input.replaceAll("Ρ", "R");
        input = input.replaceAll("ρ", "r");
        input = input.replaceAll("Σ", "S");
        input = input.replaceAll("σ", "s");
        input = input.replaceAll("ς", "s");
        input = input.replaceAll("Τ", "T");
        input = input.replaceAll("τ", "t");
        input = input.replaceAll("Υ", "U");
        input = input.replaceAll("υ", "u");
        input = input.replaceAll("Φ", "P");
        input = input.replaceAll("φ", "p");
        input = input.replaceAll("Χ", "C");
        input = input.replaceAll("χ", "c");
        input = input.replaceAll("Ψ", "Ps");
        input = input.replaceAll("ψ", "ps");
        input = input.replaceAll("Ω", "Aw");
        input = input.replaceAll("ω", "aw");

        return input.substring(0, input.length() - 1);
    }
}
