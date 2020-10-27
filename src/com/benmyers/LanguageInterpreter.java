package com.benmyers;

public class LanguageInterpreter {

    private static final char[] englishLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] greekLetters = {'α', 'β', 'γ', 'δ', 'ε', 'ζ', 'η', 'θ', 'ι', 'κ', 'λ', 'μ', 'ν', 'ξ', 'ο', 'ρ', 'σ', 'ς', 'τ', 'υ', 'φ', 'χ', 'ψ', 'ω'};

    public static Language determineLanguage(String input) {

        Language detected = null;

        for(char letter: englishLetters) {
            if(input.contains("" + letter)) detected = Language.ENG;
            break;
        }

        for(char letter: greekLetters) {
            if(input.contains("" + letter)) {
                if(detected == null) {
                    detected = Language.GRK;
                    break;
                }
                else {
                    detected = null;
                    break;
                }
            }
        }

        return detected;

    }

}
