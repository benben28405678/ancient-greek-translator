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

                // Verbs
                if(grk.indexOf(".ω") >= 0) {
                    String stem = grk.replaceAll("\\..", "");
                    // α contract
                    if(stem.substring(stem.length() - 1) == "α") {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                            dictionary.put(stem + "ῶ", eng + " (1s)");
                            dictionary.put(stem + "ᾶς", eng + " (2s)");
                            dictionary.put(stem + "ᾶ", eng + " (3s)");
                            dictionary.put(stem + "ῶμεν", eng + " (1pl)");
                            dictionary.put(stem + "ᾶτε", eng + " (2pl)");
                            dictionary.put(stem + "ῶσι", eng + " (3pl)");
                        } else {
                            dictionary.put(stem + "ω", eng + " (1s)");
                            dictionary.put(stem + "ας", eng + " (2s)");
                            dictionary.put(stem + "α", eng + " (3s)");
                            dictionary.put(stem + "ωμεν", eng + " (1pl)");
                            dictionary.put(stem + "ατε", eng + " (2pl)");
                            dictionary.put(stem + "ωσι", eng + " (3pl)");
                        }
                    }
                    // ε contract
                    else if(stem.substring(stem.length() - 1) == "ε") {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                            dictionary.put(stem + "ῶ", eng + " (1s)");
                            dictionary.put(stem + "εῖς", eng + " (2s)");
                            dictionary.put(stem + "εῖ", eng + " (3s)");
                            dictionary.put(stem + "οῦμεν", eng + " (1pl)");
                            dictionary.put(stem + "εῖτε", eng + " (2pl)");
                            dictionary.put(stem + "οῦσι", eng + " (3pl)");
                        } else {
                            dictionary.put(stem + "ω", eng + " (1s)");
                            dictionary.put(stem + "εις", eng + " (2s)");
                            dictionary.put(stem + "ει", eng + " (3s)");
                            dictionary.put(stem + "ουμεν", eng + " (1pl)");
                            dictionary.put(stem + "ειτε", eng + " (2pl)");
                            dictionary.put(stem + "ουσι", eng + " (3pl)");
                        }
                    }
                    // ο contract
                    else if(stem.substring(stem.length() - 1) == "ο") {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                            dictionary.put(stem + "ῶ", eng + " (1s)");
                            dictionary.put(stem + "οῖς", eng + " (2s)");
                            dictionary.put(stem + "οῖ", eng + " (3s)");
                            dictionary.put(stem + "οῦμεν", eng + " (1pl)");
                            dictionary.put(stem + "οῦτε", eng + " (2pl)");
                            dictionary.put(stem + "οῦσι", eng + " (3pl)");
                        } else {
                            dictionary.put(stem + "ω", eng + " (1s)");
                            dictionary.put(stem + "οις", eng + " (2s)");
                            dictionary.put(stem + "οι", eng + " (3s)");
                            dictionary.put(stem + "ουμεν", eng + " (1pl)");
                            dictionary.put(stem + "ουτε", eng + " (2pl)");
                            dictionary.put(stem + "ουσι", eng + " (3pl)");
                        }
                    }
                    // Uncontracted
                    else {
                        dictionary.put(stem + "ω", eng + " (1s)");
                        dictionary.put(stem + "εις", eng + " (2s)");
                        dictionary.put(stem + "ει", eng + " (3s)");
                        dictionary.put(stem + "ομεν", eng + " (1pl)");
                        dictionary.put(stem + "ετε", eng + " (2pl)");
                        dictionary.put(stem + "ουοι", eng + " (3pl)");
                        dictionary.put(stem + "ουοιν", eng + " (3pl)");
                    }
                    continue;
                }

                // Adjectives
                if(grk.indexOf(",ος") >= 0) {
                    String stem = grk.replaceAll(",ος", "");
                    if(flags.contains(Flag.USING_ACCENTS)) {
                        if(stem.substring(stem.length() - 1) == "ι" || stem.substring(stem.length() - 1) == "ρ" || stem.substring(stem.length() - 1) == "ε") {
                            dictionary.put(stem + "ᾱ", eng + " (nom f s)");
                            dictionary.put(stem + "ᾱν", eng + " (acc f s)");
                            dictionary.put(stem + "ᾱς", eng + " (gen f s)");
                            dictionary.put(stem + "α", eng + " (dat f s)");
                        } else {
                            dictionary.put(stem + "ή", eng + " (nom f s)");
                            dictionary.put(stem + "ήν", eng + " (acc f s)");
                            dictionary.put(stem + "ῆς", eng + " (gen f s)");
                            dictionary.put(stem + "ῆ", eng + " (dat f s)");
                        }
                        dictionary.put(stem + "ός", eng + " (nom m s)");
                        dictionary.put(stem + "όν", eng + " (acc m/n s)");
                        dictionary.put(stem + "οῦ", eng + " (gen m/n s)");
                        dictionary.put(stem + "ῶ", eng + " (dat m/n s)");
                        dictionary.put(stem + "έ", eng + " (voc s)");
                        dictionary.put(stem + "οί", eng + " (nom m pl)");
                        dictionary.put(stem + "αί", eng + " (nom f pl)");
                        dictionary.put(stem + "ά", eng + " (nom/acc n pl)");
                        dictionary.put(stem + "ούς", eng + " (acc m pl)");
                        dictionary.put(stem + "ᾱς", eng + " (acc f pl)");
                        dictionary.put(stem + "ῶν", eng + " (gen pl)");
                        dictionary.put(stem + "οῖς", eng + " (dat m/n pl)");
                        dictionary.put(stem + "αῖς", eng + " (dat f pl)");
                    } else {
                        if(stem.substring(stem.length() - 1) == "ι" || stem.substring(stem.length() - 1) == "ρ" || stem.substring(stem.length() - 1) == "ε") {
                            dictionary.put(stem + "α", eng + " (nom f s)");
                            dictionary.put(stem + "αν", eng + " (acc f s)");
                            dictionary.put(stem + "ας", eng + " (gen f s)");
                            dictionary.put(stem + "α", eng + " (dat f s)");
                        } else {
                            dictionary.put(stem + "η", eng + " (nom f s)");
                            dictionary.put(stem + "ην", eng + " (acc f s)");
                            dictionary.put(stem + "ης", eng + " (gen f s)");
                            dictionary.put(stem + "η", eng + " (dat f s)");
                        }
                        dictionary.put(stem + "ος", eng + " (nom m s)");
                        dictionary.put(stem + "ον", eng + " (acc m/n s)");
                        dictionary.put(stem + "ου", eng + " (gen m/n s)");
                        dictionary.put(stem + "ω", eng + " (dat m/n s)");
                        dictionary.put(stem + "ε", eng + " (voc s)");
                        dictionary.put(stem + "οι", eng + " (nom m pl)");
                        dictionary.put(stem + "αι", eng + " (nom f pl)");
                        dictionary.put(stem + "α", eng + " (nom/acc n pl)");
                        dictionary.put(stem + "ους", eng + " (acc m pl)");
                        dictionary.put(stem + "ας", eng + " (acc f pl)");
                        dictionary.put(stem + "ων", eng + " (gen pl)");
                        dictionary.put(stem + "οις", eng + " (dat m/n pl)");
                        dictionary.put(stem + "αις", eng + " (dat f pl)");
                    }
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
