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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        br();
        println("-----[ Ancient Greek Translator by Ben Myers ]-----");
        println("                    Version 0.1                    ");
        println("- - - - - - - - - - - - - - - - - - - - - - - - - -");

        println("[!] Welcome to the Ancient Greek Translator!");

        while(true) {

            println("[!] Enter a Greek or English word or sentence.");
            br();

            print("> ");
            input = sc.nextLine();

            br();
            print("[!] Your input: ");
            println("\"" + input + "\"");

            Language lang = LanguageInterpreter.determineLanguage(input);

            if (lang == null) {
                println("[x] Error: Could not detect the input language.");
                System.exit(-1);
            } else {

                switch (lang) {
                    case ENG:
                        println("[!] Input language detected: English");
                        break;
                    case GRK:
                        println("[!] Input language detected: Greek");
                        break;
                    default:
                        break;
                }

            }

            if(input.contains("!a") || input.contains("!α")) {
                flags.add(Flag.USING_ACCENTS);
                println("[⚑] Flag: Strict Accents enabled");
                input = input.replaceAll("![aα]", "");
            }

            if(input.contains("!p") || input.contains("!π")) {
                flags.add(Flag.PRONUNCIATION);
                println("[⚑] Pronunciation enabled");
                input = input.replaceAll("![pπ]", "");
            }

            if(input.contains("!l") || input.contains("!λ")) {
                flags.add(Flag.ALTERNATIVE_ENGLISH);
                println("[⚑] English Alternatives enabled");
                input = input.replaceAll("![lλ]", "");
            }

            dictionaryInterpreter = new GreekDictionaryInterpreter(lang, flags);

            switch (lang) {
                case GRK:
                    translator = new GreekTranslator(dictionaryInterpreter.getDictionary(), flags);
            }

            if (translator != null) {

                println("[!] Found dictionary with " + translator.dictionary.size() + " total entries.");

                String output = translator.translate(input);
                br();

                println("[!] Translated output:");
                println("[✓] \"" + output + "\"");

                if(flags.contains(Flag.PRONUNCIATION)) {
                    println("[!] Pronunciation:");
                    println("[✓] \"" + ((GreekTranslator)translator).pronounce(input) + "\"");
                }
            } else {
                println("[x] A dictionary could not be found.");
                System.exit(-2);
            }

            br();
            print("[!] Press enter to start again. Type \"e\" or \"ε\" to exit. > ");
            repeatCommand = sc.nextLine();

            if(repeatCommand.contains("e") || repeatCommand.contains("ε")) System.exit(1);

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
