package com.benmyers;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;

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

                // Nouns
                String nounStem = null;
                if(!grk.contains(".") && !grk.contains(",") && grk.length() >= 4) {
                    if (grk.indexOf("ος") == grk.length() - 2 || grk.indexOf("οι") == grk.length() - 2 || grk.indexOf("ον") == grk.length() - 2 || grk.indexOf("ου") == grk.length() - 2 || grk.indexOf("ων") == grk.length() - 2)
                        nounStem = grk.substring(0, grk.length() - 2);
                    if (grk.indexOf("ω") == grk.length() - 1 || grk.indexOf("ε") == grk.length() - 1 || grk.indexOf("α") == grk.length() - 1)
                        nounStem = grk.substring(0, grk.length() - 1);
                    if (grk.indexOf("ους") == grk.length() - 3 || grk.indexOf("οις") == grk.length() - 3)
                        nounStem = grk.substring(0, grk.length() - 3);
                }

                if(!flags.contains(Flag.USING_ACCENTS)) {
                    grk = AccentRemover.removeAccents(grk);
                }

                // Verbs
                if(grk.indexOf(".ω") >= 0) {
                    String stem = grk.replaceAll("\\..", "");
                    // α contract
                    if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("α")) {
                        stem = stem.substring(0, stem.length() - 1);
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
                    else if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("ε")) {
                        stem = stem.substring(0, stem.length() - 1);
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
                    else if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("ο")) {
                        stem = stem.substring(0, stem.length() - 1);
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
                        dictionary.put(stem + "όν", eng + " (acc m+n s)");
                        dictionary.put(stem + "οῦ", eng + " (gen m+n s)");
                        dictionary.put(stem + "ῶ", eng + " (dat m+n s)");
                        dictionary.put(stem + "έ", eng + " (voc s)");
                        dictionary.put(stem + "οί", eng + " (nom m pl)");
                        dictionary.put(stem + "αί", eng + " (nom f pl)");
                        dictionary.put(stem + "ά", eng + " (nom+acc n pl)");
                        dictionary.put(stem + "ούς", eng + " (acc m pl)");
                        dictionary.put(stem + "ᾱς", eng + " (acc f pl)");
                        dictionary.put(stem + "ῶν", eng + " (gen pl)");
                        dictionary.put(stem + "οῖς", eng + " (dat m+n pl)");
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
                        dictionary.put(stem + "ον", eng + " (acc m+n s)");
                        dictionary.put(stem + "ου", eng + " (gen m+n s)");
                        dictionary.put(stem + "ω", eng + " (dat m+n s)");
                        dictionary.put(stem + "ε", eng + " (voc s)");
                        dictionary.put(stem + "οι", eng + " (nom m pl)");
                        dictionary.put(stem + "αι", eng + " (nom f pl)");
                        dictionary.put(stem + "α", eng + " (nom+acc n pl)");
                        dictionary.put(stem + "ους", eng + " (acc m pl)");
                        dictionary.put(stem + "ας", eng + " (acc f pl)");
                        dictionary.put(stem + "ων", eng + " (gen pl)");
                        dictionary.put(stem + "οις", eng + " (dat m+n pl)");
                        dictionary.put(stem + "αις", eng + " (dat f pl)");
                    }
                    continue;
                }

                switch(input) {
                    case ENG: dictionary.put(eng, grk); break;
                    case GRK: dictionary.put(grk, eng); break;
                    default: break;
                }

                if(nounStem != null) {
                    if(!flags.contains(Flag.USING_ACCENTS)) nounStem = AccentRemover.removeAccents(nounStem);

                    // 2A
                    dictionary.put(nounStem + "ος", eng + " (2a nom s)");
                    dictionary.put(nounStem + "οι", eng + " (2a nom pl)");
                    dictionary.put(nounStem + "ον", eng + " (2a acc s)");
                    dictionary.put(nounStem + "ους", eng + " (2a acc pl)");
                    dictionary.put(nounStem + "ου", eng + " (2a gen s)");
                    dictionary.put(nounStem + "ων", eng + " (2a gen pl)");
                    dictionary.put(nounStem + "ω", eng + " (2a dat s)");
                    dictionary.put(nounStem + "οις", eng + " (2a dat pl)");
                    dictionary.put(nounStem + "ε", eng + " (2a voc)");

                    // 2B
                    dictionary.put(nounStem + "ον", eng + " (2a nom/acc s)");
                    dictionary.put(nounStem + "α", eng + " (2a nom/acc pl)");
                    dictionary.put(nounStem + "ου", eng + " (2a gen s)");
                    dictionary.put(nounStem + "ων", eng + " (2a gen pl)");
                    dictionary.put(nounStem + "ω", eng + " (2a dat s)");
                    dictionary.put(nounStem + "οις", eng + " (2a dat pl)");
                }

            }
            sc.close();
            /*for (Enumeration e = dictionary.keys(); e.hasMoreElements();) {
                Object f = e.nextElement();
                System.out.println(f);
                System.out.println(((String)f).indexOf("ος"));
                System.out.println(dictionary.get(f));
            }*/
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
