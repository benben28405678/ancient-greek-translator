package com.benmyers;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;

public class GreekDictionaryInterpreter {

    private Language keyLanguage;
    private Dictionary dictionary = new Hashtable();
    private ArrayList<String> verbStems = new ArrayList<String>();

    public GreekDictionaryInterpreter(Language input, ArrayList<Flag> flags) {

        try {
            File file = new File("dictionary.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String data = sc.nextLine();
                if(data.charAt(0) == '#') continue;
                String[] keywords = data.split(" - ");

                String grk = keywords[0];
                String eng = keywords[1];

                if(!flags.contains(Flag.USING_ACCENTS)) {
                    grk = AccentRemover.removeAccents(grk);
                }

                if(grk.indexOf(".ω") >= 0) {
                    String stem = grk.replaceAll("\\..", "");
                    dictionary.put(stem + "ω", eng + " (1s)");
                    dictionary.put(stem + "εις", eng + " (2s)");
                    dictionary.put(stem + "ει", eng + " (3s)");
                    dictionary.put(stem + "ομεν", eng + " (1pl)");
                    dictionary.put(stem + "ετε", eng + " (2pl)");
                    dictionary.put(stem + "ουοι", eng + " (3pl)");
                    dictionary.put(stem + "ουοιν", eng + " (3pl)");
                    continue;
                }

                switch(input) {
                    case ENG: dictionary.put(eng, grk); break;
                    case GRK: dictionary.put(grk, eng); break;
                    default: break;
                }

            }
            sc.close();
        } catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public int createDictionary() {

        try {
            File myObj = new File("dictionary.txt");

            if(myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                return 1;
            } else {
                System.out.println("File already exists.");
                return 0;
            }

        } catch(IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
            return 0;
        }

    }

    public Dictionary getDictionary() {
        return dictionary;
    }

}
