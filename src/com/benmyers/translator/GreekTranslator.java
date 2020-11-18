package com.benmyers.translator;

import com.benmyers.Flag;
import com.benmyers.translator.Translator;

import java.util.Collection;
import java.util.Collections;
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
        String[] words = input.split(" ");
        ArrayList<String> translatedWords = new ArrayList<String>();

        System.out.println("?");

        for(String word: words) {
            if(word == " ") continue;
            String translatedWord = (String) dictionary.get(word);
            if(!flags.contains(Flag.ALTERNATIVE_ENGLISH) && translatedWord != null) {
                String[] chunks = translatedWord.split("/");
                Pattern p = Pattern.compile("\\(.*\\)");
                Matcher m = p.matcher(translatedWord);

                if(m.find() && chunks.length > 1) {
                    translatedWord = chunks[0] + " "+ m.group(0);
                } else {
                    translatedWord = chunks[0];
                }

            }

            //if(translatedWord == null) translatedWord = word + "*";

            System.out.println("-");
            if(translatedWord == null) {

                System.out.println("--");

                String candidate = null;
                int matchLen = word.length() - 1;

                while(matchLen > 2) {
                    candidate = (String) dictionary.get(word.substring(0, matchLen));
                    System.out.println(candidate);
                    if(candidate != null) break;
                }

                if(candidate == null) translatedWord = candidate + "?";
                else translatedWord = word + "*";

            }

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

    @Override
    public String[] alignAndTranslate(String input) {
        String[] words = input.split(" ");
        ArrayList<String> translatedWords = new ArrayList<String>();

        for(String word: words) {
            if(word == " ") continue;
            String translatedWord = (String) dictionary.get(word);
            if(!flags.contains(Flag.ALTERNATIVE_ENGLISH) && translatedWord != null) {
                String[] chunks = translatedWord.split("/");
                Pattern p = Pattern.compile("\\(.*\\)");
                Matcher m = p.matcher(translatedWord);

                if(m.find() && chunks.length > 1) {
                    translatedWord = chunks[0] + " " + m.group(0);
                } else {
                    translatedWord = chunks[0];
                }

            }

            if(translatedWord == null) {

                String candidate = null;
                int matchLen = word.length() - 1;

                while(matchLen > 1) {

                    for(Object key: Collections.list(dictionary.keys())) {
                        String keyString = key.toString();

                        if(matchLen >= keyString.length()) continue;
                        if(keyString.substring(0, matchLen).equals(word.substring(0, matchLen))) {
                            candidate = (String) dictionary.get(key);
                        }
                    }

                    if(candidate != null) break;
                    matchLen -= 1;
                }

                if(candidate != null) translatedWord = candidate + "?";
                else translatedWord = word + "*";

            }

            if(translatedWord == null) translatedWord = word + "*";

            translatedWords.add(translatedWord);
        }

        String alignedInput = "", alignedOutput = "";

        for(int i = 0; i < translatedWords.size() && i < words.length; i++) {
            String inputWord = words[i], outputWord = translatedWords.get(i);
            if(inputWord.length() > outputWord.length()) {
                alignedInput += inputWord;
                while(outputWord.length() < inputWord.length()) outputWord += " ";
                alignedOutput += outputWord;
            } else if(inputWord.length() < outputWord.length()) {
                alignedOutput += outputWord;
                while(inputWord.length() < outputWord.length()) inputWord += " ";
                alignedInput += inputWord;
            } else {
                alignedOutput += outputWord;
                alignedInput += inputWord;
            }

            alignedInput += " "; alignedOutput += " ";
        }

        alignedInput = alignedInput.substring(0, alignedInput.length() - 1);
        alignedOutput = alignedOutput.substring(0, alignedOutput.length() - 1);

        String[] toReturn = {alignedInput, alignedOutput};

        System.out.println(toReturn[1]);

        return toReturn;
    }
}
