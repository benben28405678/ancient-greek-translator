package com.benmyers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class GreekDictionaryInterpreter {

    private Dictionary dictionary = new Hashtable();
    private ArrayList<String> verbStems = new ArrayList<String>();

    public GreekDictionaryInterpreter(ArrayList<Flag> flags) {

        try {
            InputStream bytes = Main.class.getClassLoader().getResourceAsStream("dictionary.txt");
            Reader chars = new InputStreamReader(bytes, StandardCharsets.UTF_8);
            BufferedReader lines = new BufferedReader(chars);
            while(true) {
                String data = lines.readLine();

                if (data == null) break;

                if(data.charAt(0) == '#') continue;
                String[] keywords = data.split(" - ");

                String grk = keywords[0];
                String eng = keywords[1];

                if(!flags.contains(Flag.USING_ACCENTS)) {
                    grk = AccentRemover.removeAccents(grk);
                }

                // Active Verbs
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
                            dictionary.put(stem + "α", eng + " (imp s)");
                            dictionary.put(stem + "ᾶτε", eng + " (imp pl)");
                        } else {
                            dictionary.put(stem + "ω", eng + " (1s)");
                            dictionary.put(stem + "ας", eng + " (2s)");
                            dictionary.put(stem + "α", eng + " (3s+imp s)");
                            dictionary.put(stem + "ωμεν", eng + " (1pl)");
                            dictionary.put(stem + "ατε", eng + " (2pl+imp pl)");
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
                            dictionary.put(stem + "εῖτε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "οῦσι", eng + " (3pl)");
                            dictionary.put(stem + "ει", eng + " (imp s)");
                        } else {
                            dictionary.put(stem + "ω", eng + " (1s)");
                            dictionary.put(stem + "εις", eng + " (2s)");
                            dictionary.put(stem + "ει", eng + " (3s+imp s)");
                            dictionary.put(stem + "ουμεν", eng + " (1pl)");
                            dictionary.put(stem + "ειτε", eng + " (2pl+imp pl)");
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
                            dictionary.put(stem + "οῦτε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "οῦσι", eng + " (3pl)");
                            dictionary.put(stem + "ου", eng + " (imp s)");
                        } else {
                            dictionary.put(stem + "ω", eng + " (1s)");
                            dictionary.put(stem + "οις", eng + " (2s)");
                            dictionary.put(stem + "οι", eng + " (3s)");
                            dictionary.put(stem + "ουμεν", eng + " (1pl)");
                            dictionary.put(stem + "ουτε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "ουσι", eng + " (3pl)");
                            dictionary.put(stem + "ου", eng + " (imp s)");
                        }
                    }
                    // Uncontracted
                    else {
                        dictionary.put(stem + "ω", eng + " (1s)");
                        dictionary.put(stem + "εις", eng + " (2s)");
                        dictionary.put(stem + "ει", eng + " (3s)");
                        dictionary.put(stem + "ομεν", eng + " (1pl)");
                        dictionary.put(stem + "ετε", eng + " (2pl+imp pl)");
                        dictionary.put(stem + "ουοι", eng + " (3pl)");
                        dictionary.put(stem + "ουοιν", eng + " (3pl)");
                        dictionary.put(stem + "ε", eng + " (imp s)");
                    }
                    continue;
                }

                // Middle Verbs
                if(grk.indexOf(".ομαι") >= 0) {
                    String stem = grk.replaceAll("\\.....", "");
                    // α contract
                    if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("α")) {
                        stem = stem.substring(0, stem.length() - 1);
                        if(flags.contains(Flag.USING_ACCENTS)) {
                            dictionary.put(stem + "ῶμαι", eng + " (1s)");
                            dictionary.put(stem + "ᾶ", eng + " (2s)");
                            dictionary.put(stem + "ᾶται", eng + " (3s)");
                            dictionary.put(stem + "ῶμεθα", eng + " (1pl)");
                            dictionary.put(stem + "ᾶσθε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "ῶνται", eng + " (3pl)");
                            dictionary.put(stem + "ῶ", eng + " (imp s)");
                        } else {
                            dictionary.put(stem + "ωμαι", eng + " (1s)");
                            dictionary.put(stem + "α", eng + " (2s)");
                            dictionary.put(stem + "αται", eng + " (3s)");
                            dictionary.put(stem + "ωμεθα", eng + " (1pl)");
                            dictionary.put(stem + "ασθε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "ωνται", eng + " (3pl)");
                            dictionary.put(stem + "ω", eng + " (imp s)");
                        }
                    }
                    // ε contract
                    else if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("ε")) {
                        stem = stem.substring(0, stem.length() - 1);
                        if(flags.contains(Flag.USING_ACCENTS)) {
                            dictionary.put(stem + "οῦμαι", eng + " (1s)");
                            dictionary.put(stem + "ῆ", eng + " (2s)");
                            dictionary.put(stem + "εῖται", eng + " (3s)");
                            dictionary.put(stem + "ούμεθα", eng + " (1pl)");
                            dictionary.put(stem + "εῖσθε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "οῦνται", eng + " (3pl)");
                            dictionary.put(stem + "οῦ", eng + " (imp s)");
                        } else {
                            dictionary.put(stem + "ουμαι", eng + " (1s)");
                            dictionary.put(stem + "η", eng + " (2s)");
                            dictionary.put(stem + "ειται", eng + " (3s)");
                            dictionary.put(stem + "ουμεθα", eng + " (1pl)");
                            dictionary.put(stem + "εισθε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "ουνται", eng + " (3pl)");
                            dictionary.put(stem + "ου", eng + " (imp s)");
                        }
                    }
                    // ο contract
                    else if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("ο")) {
                        stem = stem.substring(0, stem.length() - 1);
                        if(flags.contains(Flag.USING_ACCENTS)) {
                            dictionary.put(stem + "οῦμαι", eng + " (1s)");
                            dictionary.put(stem + "οῖ", eng + " (2s)");
                            dictionary.put(stem + "οῦται", eng + " (3s)");
                            dictionary.put(stem + "ούμεθα", eng + " (1pl)");
                            dictionary.put(stem + "οῦσθε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "οῦνται", eng + " (3pl)");
                            dictionary.put(stem + "οῦ", eng + " (imp s)");
                        } else {
                            dictionary.put(stem + "ουμαι", eng + " (1s)");
                            dictionary.put(stem + "οι", eng + " (2s)");
                            dictionary.put(stem + "ουται", eng + " (3s)");
                            dictionary.put(stem + "ουμεθα", eng + " (1pl)");
                            dictionary.put(stem + "ουσθε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "οθνται", eng + " (3pl)");
                            dictionary.put(stem + "ου", eng + " (imp s)");
                        }
                    }
                    // Uncontracted
                    else {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                            dictionary.put(stem + "ομαι", eng + " (1s)");
                            dictionary.put(stem + "η", eng + " (2s)");
                            dictionary.put(stem + "εται", eng + " (3s)");
                            dictionary.put(stem + "όμεθα", eng + " (1pl)");
                            dictionary.put(stem + "εσθε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "ονται", eng + " (3pl)");
                            dictionary.put(stem + "ου", eng + " (imp s)");
                        } else {
                            dictionary.put(stem + "ομαι", eng + " (1s)");
                            dictionary.put(stem + "η", eng + " (2s)");
                            dictionary.put(stem + "εται", eng + " (3s)");
                            dictionary.put(stem + "ομεθα", eng + " (1pl)");
                            dictionary.put(stem + "εσθε", eng + " (2pl+imp pl)");
                            dictionary.put(stem + "ονται", eng + " (3pl)");
                            dictionary.put(stem + "ου", eng + " (imp s)");
                        }
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
                if(grk.indexOf(",ων") >= 0) {

                    String stem = grk.replaceAll(",ων", "");

                    dictionary.put(stem + "ων", eng + " (3 nom m+f s AND gen pl)");
                    dictionary.put(stem + "ονα", eng + " (3 acc m+f s AND nom+acc n pl)");
                    dictionary.put(stem + "ονος", eng + " (3 gen s)");
                    dictionary.put(stem + "ονι", eng + " (3 dat s)");
                    dictionary.put(stem + "ον", eng + " (3 nom+acc+voc n s)");
                    dictionary.put(stem + "ονες", eng + " (3 nom m+f pl)");
                    dictionary.put(stem + "ονας", eng + " (3 acc m+f pl)");
                    dictionary.put(stem + "οσι", eng + " (3 dat pl)");
                    dictionary.put(stem + "οσιν", eng + " (3 dat pl)");
                    
                }

                // Nouns
                if(grk.contains(";")) {
                    if (grk.indexOf(";2a") >= 0) {
                        String stem = grk.replaceAll("ος;2a|ον;2a|ου;2a|ους;2a|ου;2a|ων;2a|ω;2a|οις;2a|ε;2a|οι;2a", "");

                        dictionary.put(stem + "ος", eng + " (2a nom s)");
                        dictionary.put(stem + "οι", eng + " (2a nom pl)");
                        dictionary.put(stem + "ον", eng + " (2a acc s)");
                        dictionary.put(stem + "ους", eng + " (2a acc pl)");
                        dictionary.put(stem + "ου", eng + " (2a gen s)");
                        dictionary.put(stem + "ων", eng + " (2a gen pl)");
                        dictionary.put(stem + "ω", eng + " (2a dat s)");
                        dictionary.put(stem + "οις", eng + " (2a dat pl)");
                        dictionary.put(stem + "ε", eng + " (2a voc)");
                    }
                    else if (grk.indexOf(";2b") >= 0) {
                        String stem = grk.replaceAll("ον;2b|α;2b|ου;2b|ων;2b|ου;2b|ων;2b|ω;2b|οις;2b", "");

                        dictionary.put(stem + "ον", eng + " (2b nom+acc s)");
                        dictionary.put(stem + "α", eng + " (2b nom+acc pl)");
                        dictionary.put(stem + "ου", eng + " (2b gen s)");
                        dictionary.put(stem + "ων", eng + " (2b gen pl)");
                        dictionary.put(stem + "ω", eng + " (2b dat s)");
                        dictionary.put(stem + "οις", eng + " (2b dat pl)");
                    }
                    else if (grk.indexOf(";1a") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("η;1a|ην;1a|ης;1a|η;1a|αι;1a|ας;1a|ων;1a|αις;1a", "");

                            dictionary.put(stem + "η", eng + " (1a nom+dat s)");
                            dictionary.put(stem + "ην", eng + " (1a acc s)");
                            dictionary.put(stem + "ης", eng + " (1a gen s)");
                            dictionary.put(stem + "αι", eng + " (1a nom pl)");
                            dictionary.put(stem + "ας", eng + " (1a acc pl)");
                            dictionary.put(stem + "ων", eng + " (1a gen pl)");
                            dictionary.put(stem + "αις", eng + " (1a dat pl)");
                        }
                    }
                    else if (grk.indexOf(";1b") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("α;1b|αν;1b|ας;1b|α;1b|αι;1b|ας;1b|ων;1b|αις;1b", "");

                            dictionary.put(stem + "α", eng + " (1b nom+dat s)");
                            dictionary.put(stem + "αν", eng + " (1b acc s)");
                            dictionary.put(stem + "ας", eng + " (1b gen s)");
                            dictionary.put(stem + "αι", eng + " (1b nom pl)");
                            dictionary.put(stem + "ας", eng + " (1b acc pl)");
                            dictionary.put(stem + "ων", eng + " (1b gen pl)");
                            dictionary.put(stem + "αις", eng + " (1b dat pl)");
                        }
                    }
                    else if (grk.indexOf(";1c") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("α;1c|αν;1c|ης;1c|η;1c|αι;1c|ας;1c|ων;1c|αις;1c", "");

                            dictionary.put(stem + "α", eng + " (1c nom s)");
                            dictionary.put(stem + "αν", eng + " (1c acc s)");
                            dictionary.put(stem + "ης", eng + " (1c gen s)");
                            dictionary.put(stem + "η", eng + " (1c dat s)");
                            dictionary.put(stem + "αι", eng + " (1c nom pl)");
                            dictionary.put(stem + "ας", eng + " (1c acc pl)");
                            dictionary.put(stem + "ων", eng + " (1c gen pl)");
                            dictionary.put(stem + "αις", eng + " (1c dat pl)");
                        }
                    }
                    else if (grk.indexOf(";1d") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("ης;1d|ην;1d|ου;1d|η;1d|α;1d|αι;1d|ας;1d|ων;1d|αις;1d", "");

                            dictionary.put(stem + "ς", eng + " (1d nom s)");
                            dictionary.put(stem + "ην", eng + " (1d acc s)");
                            dictionary.put(stem + "ου", eng + " (1d gen s)");
                            dictionary.put(stem + "η", eng + " (1d dat s)");
                            dictionary.put(stem + "α", eng + " (1d nom pl)");
                            dictionary.put(stem + "αι", eng + " (1d acc pl)");
                            dictionary.put(stem + "ας", eng + " (1d gen pl)");
                            dictionary.put(stem + "ων", eng + " (1d dat pl)");
                            dictionary.put(stem + "αις", eng + " (1d voc)");
                        }
                    }
                    else if (grk.indexOf(";3a") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll(";3a", "");

                            dictionary.put(stem + "", eng + " (3a nom s)");
                            dictionary.put(stem + "α", eng + " (3a acc s)");
                            dictionary.put(stem + "ος", eng + " (3a gen s)");
                            dictionary.put(stem + "ι", eng + " (3a dat s)");
                            dictionary.put(stem + "ες", eng + " (3a nom s)");
                            dictionary.put(stem + "ας", eng + " (3a acc pl)");
                            dictionary.put(stem + "ων", eng + " (3a gen pl)");
                            dictionary.put(stem + "οι", eng + " (3a dat pl)");
                            dictionary.put(stem + "οιν", eng + " (3a dat pl)");
                        }
                    }
                    else if (grk.indexOf(";3b") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll(";3b", "");

                            dictionary.put(stem + "", eng + " (3b nom+acc s)");
                            dictionary.put(stem + "τος", eng + " (3b gen s)");
                            dictionary.put(stem + "τι", eng + " (3b dat s)");
                            dictionary.put(stem + "τα", eng + " (3b nom+acc pl)");
                            dictionary.put(stem + "ων", eng + " (3b gen pl)");
                            dictionary.put(stem + "ασι", eng + " (3b dat pl)");
                            dictionary.put(stem + "ασιν", eng + " (3b dat pl)");
                        }
                    }
                    else if (grk.indexOf(";3c") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("ος;3c", "");

                            dictionary.put(stem + "ος", eng + " (3c nom+acc s)");
                            dictionary.put(stem + "ους", eng + " (3c gen s)");
                            dictionary.put(stem + "ει", eng + " (3c dat s)");
                            dictionary.put(stem + "η", eng + " (3c nom+acc pl)");
                            dictionary.put(stem + "ων", eng + " (3c gen pl)");
                            dictionary.put(stem + "σι", eng + " (3c dat pl)");
                            dictionary.put(stem + "σιν", eng + " (3c dat pl)");
                        }
                    }
                    else if (grk.indexOf("ις;3e") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("ις;3e", "");

                            dictionary.put(stem + "ις", eng + " (3e nom s)");
                            dictionary.put(stem + "ιν", eng + " (3e acc s)");
                            dictionary.put(stem + "εως", eng + " (3e gen s)");
                            dictionary.put(stem + "ει", eng + " (3e dat s)");
                            dictionary.put(stem + "ι", eng + " (3e voc)");
                            dictionary.put(stem + "εις", eng + " (3e nom+acc pl)");
                            dictionary.put(stem + "εων", eng + " (3e gen pl)");
                            dictionary.put(stem + "εσι", eng + " (3e dat pl)");
                            dictionary.put(stem + "εσιν", eng + " (3e dat pl)");
                        }
                    }
                    else if (grk.indexOf("υς;3e") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("υς;3e", "");

                            dictionary.put(stem + "υς", eng + " (3e nom s)");
                            dictionary.put(stem + "υν", eng + " (3e acc s)");
                            dictionary.put(stem + "εως", eng + " (3e gen s)");
                            dictionary.put(stem + "ει", eng + " (3e dat s)");
                            dictionary.put(stem + "υ", eng + " (3e voc)");
                            dictionary.put(stem + "εις", eng + " (3e nom+acc pl)");
                            dictionary.put(stem + "εων", eng + " (3e gen pl)");
                            dictionary.put(stem + "εσι", eng + " (3e dat pl)");
                            dictionary.put(stem + "εσιν", eng + " (3e dat pl)");
                        }
                    }
                    else if (grk.indexOf(";3f") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("ος;3f", "");

                            dictionary.put(stem + "τυ", eng + " (3f nom+acc s)");
                            dictionary.put(stem + "τεως", eng + " (3f gen s)");
                            dictionary.put(stem + "τει", eng + " (3f dat s)");
                            dictionary.put(stem + "τη", eng + " (3f nom+acc pl)");
                            dictionary.put(stem + "εων", eng + " (3f gen pl)");
                            dictionary.put(stem + "εσι", eng + " (3f dat pl)");
                            dictionary.put(stem + "εσιν", eng + " (3f dat pl)");
                        }
                    }
                    else {
                        grk = grk.replaceAll(";..", "");
                    }
                }

                dictionary.put(grk, eng);
            }
        } catch (Exception e) {
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
