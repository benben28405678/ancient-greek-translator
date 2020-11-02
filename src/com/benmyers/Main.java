package com.benmyers;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    private static String input;
    private static Language inputLanguage;

    private static GreekDictionaryInterpreter dictionaryInterpreter;
    private static Translator translator;

    private static String repeatCommand = "";

    private static ArrayList<Flag> flags = new ArrayList<Flag>();

    private static final boolean CONCISE_MODE = true;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        br();
        println("-----[ Ancient Greek Translator by Ben Myers ]-----");
        println("                    Version 0.1                    ");
        println("- - - - - - - - - - - - - - - - - - - - - - - - - -");

        if(!CONCISE_MODE) println("[!] Welcome to the Ancient Greek Translator!");

        while(true) {

            flags = new ArrayList<Flag>();

            if(!CONCISE_MODE) println("[!] Enter a Greek or English word or sentence.");
            if(!CONCISE_MODE) br();

            print("> ");
            input = sc.nextLine();

            if(!CONCISE_MODE) br();
            if(!CONCISE_MODE) print("[!] Your input: ");
            if(!CONCISE_MODE) println("\"" + input + "\"");

            Language lang = LanguageInterpreter.determineLanguage(input);

            if (lang == null) {
                println("[x] Error: Could not detect the input language.");
                System.exit(-1);
            } else {

                switch (lang) {
                    case ENG:
                        if(!CONCISE_MODE) println("[!] Input language detected: English");
                        break;
                    case GRK:
                        if(!CONCISE_MODE) println("[!] Input language detected: Greek");
                        break;
                    default:
                        break;
                }

            }

            if(input.contains("!a") || input.contains("!α")) {
                flags.add(Flag.USING_ACCENTS);
                if(!CONCISE_MODE) println("[⚑] Flag: Strict Accents enabled");
                input = input.replaceAll("![aα]", "");
            }

            if(input.contains("!p") || input.contains("!π")) {
                flags.add(Flag.PRONUNCIATION);
                if(!CONCISE_MODE) println("[⚑] Pronunciation enabled");
                input = input.replaceAll("![pπ]", "");
            }

            if(input.contains("!l") || input.contains("!λ")) {
                flags.add(Flag.ALTERNATIVE_ENGLISH);
                if(!CONCISE_MODE) println("[⚑] English Alternatives enabled");
                input = input.replaceAll("![lλ]", "");
            }

            if(input.contains("!s") || input.contains("!σ")) {
                flags.add(Flag.ALIGN);
                if(!CONCISE_MODE) println("[⚑] Align enabled");
                input = input.replaceAll("![sσ]", "");
            }

            dictionaryInterpreter = new GreekDictionaryInterpreter(lang, flags);

            switch (lang) {
                case GRK:
                    translator = new GreekTranslator(dictionaryInterpreter.getDictionary(), flags);
            }

            if (translator != null) {

                if(!CONCISE_MODE) println("[!] Found dictionary with " + translator.dictionary.size() + " total entries.");
                if(!CONCISE_MODE) br();

                if(!CONCISE_MODE) println("[!] Translated output:");

                if(flags.contains(Flag.ALIGN)) {
                    String[] output = translator.alignAndTranslate(input);
                    println("[✓] " + output[0]);
                    println("[✓] " + output[1]);
                } else {
                    String output = translator.translate(input);
                    println("[✓] \"" + output + "\"");
                }

                if(flags.contains(Flag.PRONUNCIATION)) {
                    if(!CONCISE_MODE) println("[!] Pronunciation:");
                    println("[✓] \"" + ((GreekTranslator)translator).pronounce(input) + "\"");
                }
            } else {
                println("[x] A dictionary could not be found.");
                System.exit(-2);
            }

            br();
            if(!CONCISE_MODE) print("[!] Press enter to start again. Type \"e\" or \"ε\" to exit. > ");
            if(!CONCISE_MODE) repeatCommand = sc.nextLine();
            else repeatCommand = "";

            if(repeatCommand.indexOf("e") == 0 || repeatCommand.indexOf("ε") == 0) System.exit(1);

            System.out.print("\033[H\033[2J");
            System.out.flush();

        }

    }

    private static void print(String s) {
        System.out.print(s);
    }

    private static void print(int x) {
        System.out.print(x);
    }

    private static void print(boolean b) {
        System.out.print(b);
    }

    private static void println(String s) {
        print(s + "\n");
    }

    private static void println(int x) {
        print(x + "\n");
    }

    private static void println(boolean b) {
        print(b + "\n");
    }

    private static void br() {
        println("");
    }

}
