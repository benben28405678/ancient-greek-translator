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

                    String participleStem = stem.substring(0, stem.length());
                    String imperfectStem = participleStem;

                    if(imperfectStem.charAt(0) == 'α' || imperfectStem.charAt(0) == 'ε' || imperfectStem.charAt(0) == 'ο') {
                        imperfectStem = imperfectStem.substring(1);
                        imperfectStem = "η" + imperfectStem;
                    }
                    else if(imperfectStem.substring(0, 2) == "αι" || imperfectStem.substring(0, 2) == "ει") {
                        imperfectStem = imperfectStem.substring(2);
                        imperfectStem = "η" + imperfectStem;
                    }
                    else if(imperfectStem.charAt(0) == 'ο' || imperfectStem.charAt(0) == 'ω') {
                        imperfectStem = imperfectStem.substring(1);
                        imperfectStem = "ω" + imperfectStem;
                    }
                    else if(imperfectStem.substring(0, 2) == "αυ" || imperfectStem.substring(0, 2) == "ευ") {
                        imperfectStem = imperfectStem.substring(2);
                        imperfectStem = "ηυ" + imperfectStem;
                    }
                    else {
                        imperfectStem = "ε" + imperfectStem;
                    }

                    // α contract
                    if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("α")) {
                        stem = stem.substring(0, stem.length() - 1);

                        dictionary.put(stem + "ω", eng + " (1s)");
                        dictionary.put(stem + "ας", eng + " (2s)");
                        dictionary.put(stem + "α", eng + " (3s+imp s)");
                        dictionary.put(stem + "ωμεν", eng + " (1pl)");
                        dictionary.put(stem + "ατε", eng + " (2pl+imp pl)");
                        dictionary.put(stem + "ωσι", eng + " (3pl)");

                        imperfectStem = imperfectStem.substring(0, imperfectStem.length() - 1);

                        // Imperfect Indicative Active Contract
                        dictionary.put(imperfectStem + "ων", "I was " + eng + "-ing (1s)");
                        dictionary.put(imperfectStem + "ας", "you were " + eng + "-ing (2s)");
                        dictionary.put(imperfectStem + "α", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "ωμεν", "we were " + eng + "-ing (1pl)");
                        dictionary.put(imperfectStem + "ατε", "you all were " + eng + "-ing (2pl)");
                        dictionary.put(imperfectStem + "ων", "they were " + eng + "-ing (3pl)");
                    }
                    // ε contract
                    else if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("ε")) {
                        stem = stem.substring(0, stem.length() - 1);

                        dictionary.put(stem + "ω", eng + " (1s)");
                        dictionary.put(stem + "εις", eng + " (2s)");
                        dictionary.put(stem + "ει", eng + " (3s+imp s)");
                        dictionary.put(stem + "ουμεν", eng + " (1pl)");
                        dictionary.put(stem + "ειτε", eng + " (2pl+imp pl)");
                        dictionary.put(stem + "ουσι", eng + " (3pl)");

                        imperfectStem = imperfectStem.substring(0, imperfectStem.length() - 1);

                        // Imperfect Indicative Active Contract
                        dictionary.put(imperfectStem + "ουν", "I was " + eng + "-ing (1s)");
                        dictionary.put(imperfectStem + "εις", "you were " + eng + "-ing (2s)");
                        dictionary.put(imperfectStem + "ει", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "ουμεν", "we were " + eng + "-ing (1pl)");
                        dictionary.put(imperfectStem + "ειτε", "you all were " + eng + "-ing (2pl)");
                        dictionary.put(imperfectStem + "ουν", "they were " + eng + "-ing (3pl)");
                    }
                    // ο contract
                    else if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("ο")) {
                        stem = stem.substring(0, stem.length() - 1);

                        dictionary.put(stem + "ω", eng + " (1s)");
                        dictionary.put(stem + "οις", eng + " (2s)");
                        dictionary.put(stem + "οι", eng + " (3s)");
                        dictionary.put(stem + "ουμεν", eng + " (1pl)");
                        dictionary.put(stem + "ουτε", eng + " (2pl+imp pl)");
                        dictionary.put(stem + "ουσι", eng + " (3pl)");
                        dictionary.put(stem + "ου", eng + " (imp s)");

                        imperfectStem = imperfectStem.substring(0, imperfectStem.length() - 1);

                        // Imperfect Indicative Active Contract
                        dictionary.put(imperfectStem + "ουν", "I was " + eng + "-ing (1s)");
                        dictionary.put(imperfectStem + "ους", "you were " + eng + "-ing (2s)");
                        dictionary.put(imperfectStem + "ου", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "ουμεν", "we were " + eng + "-ing (1pl)");
                        dictionary.put(imperfectStem + "ουτε", "you all were " + eng + "-ing (2pl)");
                        dictionary.put(imperfectStem + "ουν", "they were " + eng + "-ing (3pl)");
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

                        // Imperfect Indicative Active
                        dictionary.put(imperfectStem + "ον", "I was " + eng + "-ing (1s)");
                        dictionary.put(imperfectStem + "ες", "you were " + eng + "-ing (2s)");
                        dictionary.put(imperfectStem + "ε", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "εν", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "ομεν", "we were " + eng + "-ing (1pl)");
                        dictionary.put(imperfectStem + "ετε", "you all were " + eng + "-ing (2pl)");
                        dictionary.put(imperfectStem + "ον", "they were " + eng + "-ing (3pl)");
                    }

                    // Present Active Participle
                    dictionary.put(participleStem + "ων", eng + "-ing (nom m s)");
                    dictionary.put(participleStem + "οντα", eng + "-ing (acc m s)");
                    dictionary.put(participleStem + "οντος", eng + "-ing (gen m s)");
                    dictionary.put(participleStem + "οντι", eng + "-ing (dat m s)");
                    dictionary.put(participleStem + "οντες", eng + "-ing (nom m pl)");
                    dictionary.put(participleStem + "οντας", eng + "-ing (acc m pl)");
                    dictionary.put(participleStem + "οντων", eng + "-ing (gen m pl)");
                    dictionary.put(participleStem + "ουσι", eng + "-ing (dat m pl)");
                    dictionary.put(participleStem + "ουσιν", eng + "-ing (dat m pl)");
                    dictionary.put(participleStem + "ουσα", eng + "-ing (nom f s)");
                    dictionary.put(participleStem + "ουσαν", eng + "-ing (acc f s)");
                    dictionary.put(participleStem + "ουσης", eng + "-ing (gen f s)");
                    dictionary.put(participleStem + "ουση", eng + "-ing (dat f s)");
                    dictionary.put(participleStem + "ουσαι", eng + "-ing (nom f pl)");
                    dictionary.put(participleStem + "ουσας", eng + "-ing (acc f pl)");
                    dictionary.put(participleStem + "ουσων", eng + "-ing (gen f pl)");
                    dictionary.put(participleStem + "ουσαις", eng + "-ing (dat f pl)");
                    dictionary.put(participleStem + "ον", eng + "-ing (nom+acc n s)");
                    dictionary.put(participleStem + "οντος", eng + "-ing (gen n s)");
                    dictionary.put(participleStem + "οντι", eng + "-ing (dat n s)");
                    dictionary.put(participleStem + "οντα", eng + "-ing (nom+acc n pl)");
                    dictionary.put(participleStem + "οντων", eng + "-ing (gen n pl)");
                    dictionary.put(participleStem + "ουσι", eng + "-ing (dat n pl)");
                    dictionary.put(participleStem + "ουσιν", eng + "-ing (dat n pl)");

                    String futureStem = stem + "σ";

                    String lastTwo = futureStem.substring(futureStem.length() - 2);
                    String lastThree = futureStem.substring(futureStem.length() - 3);

                    if(lastTwo.equals("βσ") || lastTwo.equals("πσ") || lastTwo.equals("φσ")) futureStem = "ψ";
                    else if(lastThree.equals("πτσ")) futureStem = "ψ";
                    else if(lastTwo.equals("γσ") || lastTwo.equals("κσ") || lastTwo.equals("χσ")) futureStem = "ζ";
                    else if(lastThree.equals("οκσ") || lastThree.equals("ττσ")) futureStem = "ζ";
                    else if(lastTwo.equals("δσ") || lastTwo.equals("ζσ") || lastTwo.equals("θσ") || lastTwo.equals("τσ")) futureStem = "σ";
                    else if(lastTwo.equals("ασ") || lastTwo.equals("εσ")) futureStem = "ησ";
                    else if(lastTwo.equals("οσ")) futureStem = "ωσ";

                    // Future Indicative Active
                    dictionary.put(futureStem + "ω", "I shall " + eng + " (1s)");
                    dictionary.put(futureStem + "εις", "You will " + eng + " (2s)");
                    dictionary.put(futureStem + "ει", "He/she/it will " + eng + " (3s)");
                    dictionary.put(futureStem + "ομεν", "We shall " + eng + " (1pl)");
                    dictionary.put(futureStem + "ετε", "You all will " + eng + " (2pl+imp pl)");
                    dictionary.put(futureStem + "ουοι", "They will " + eng + " (3pl)");
                    dictionary.put(futureStem + "ουοιν", "They will " + eng + " (3pl)");

                    continue;
                }

                // Middle Verbs
                if(grk.indexOf(".ομαι") >= 0) {

                    String stem = grk.replaceAll("\\.....", "");

                    String imperfectStem = stem;

                    if(imperfectStem.charAt(0) == 'α' || imperfectStem.charAt(0) == 'ε' || imperfectStem.charAt(0) == 'ο') {
                        imperfectStem = imperfectStem.substring(1);
                        imperfectStem = "η" + imperfectStem;
                    }
                    else if(imperfectStem.substring(0, 2) == "αι" || imperfectStem.substring(0, 2) == "ει") {
                        imperfectStem = imperfectStem.substring(2);
                        imperfectStem = "η" + imperfectStem;
                    }
                    else if(imperfectStem.charAt(0) == 'ο' || imperfectStem.charAt(0) == 'ω') {
                        imperfectStem = imperfectStem.substring(1);
                        imperfectStem = "ω" + imperfectStem;
                    }
                    else if(imperfectStem.substring(0, 2) == "αυ" || imperfectStem.substring(0, 2) == "ευ") {
                        imperfectStem = imperfectStem.substring(2);
                        imperfectStem = "ηυ" + imperfectStem;
                    }
                    else {
                        imperfectStem = "ε" + imperfectStem;
                    }

                    // α contract
                    if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("α")) {
                        stem = stem.substring(0, stem.length() - 1);

                        dictionary.put(stem + "ωμαι", eng + " (1s)");
                        dictionary.put(stem + "α", eng + " (2s)");
                        dictionary.put(stem + "αται", eng + " (3s)");
                        dictionary.put(stem + "ωμεθα", eng + " (1pl)");
                        dictionary.put(stem + "ασθε", eng + " (2pl+imp pl)");
                        dictionary.put(stem + "ωνται", eng + " (3pl)");
                        dictionary.put(stem + "ω", eng + " (imp s)");

                        imperfectStem = imperfectStem.substring(0, imperfectStem.length() - 1);

                        // Imperfect Indicative Middle Contract
                        dictionary.put(imperfectStem + "ωμην", "I was " + eng + "-ing (1s)");
                        dictionary.put(imperfectStem + "ω", "you were " + eng + "-ing (2s)");
                        dictionary.put(imperfectStem + "ατο", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "ωμεθα", "we were " + eng + "-ing (1pl)");
                        dictionary.put(imperfectStem + "ασθε", "you all were " + eng + "-ing (2pl)");
                        dictionary.put(imperfectStem + "ωντο", "they were" + eng + "-ing (3pl)");
                    }
                    // ε contract
                    else if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("ε")) {
                        stem = stem.substring(0, stem.length() - 1);

                        dictionary.put(stem + "ουμαι", eng + " (1s)");
                        dictionary.put(stem + "η", eng + " (2s)");
                        dictionary.put(stem + "ειται", eng + " (3s)");
                        dictionary.put(stem + "ουμεθα", eng + " (1pl)");
                        dictionary.put(stem + "εισθε", eng + " (2pl+imp pl)");
                        dictionary.put(stem + "ουνται", eng + " (3pl)");
                        dictionary.put(stem + "ου", eng + " (imp s)");

                        imperfectStem = imperfectStem.substring(0, imperfectStem.length() - 1);

                        // Imperfect Indicative Middle Contract
                        dictionary.put(imperfectStem + "ουμην", "I was " + eng + "-ing (1s)");
                        dictionary.put(imperfectStem + "ου", "you were " + eng + "-ing (2s)");
                        dictionary.put(imperfectStem + "ειτο", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "ουμεθα", "we were " + eng + "-ing (1pl)");
                        dictionary.put(imperfectStem + "εισθε", "you all were " + eng + "-ing (2pl)");
                        dictionary.put(imperfectStem + "ουντο", "they were " + eng + "-ing (3pl)");
                    }
                    // ο contract
                    else if(AccentRemover.removeAccents(stem.substring(stem.length() - 1)).equals("ο")) {
                        stem = stem.substring(0, stem.length() - 1);

                        dictionary.put(stem + "ουμαι", eng + " (1s)");
                        dictionary.put(stem + "οι", eng + " (2s)");
                        dictionary.put(stem + "ουται", eng + " (3s)");
                        dictionary.put(stem + "ουμεθα", eng + " (1pl)");
                        dictionary.put(stem + "ουσθε", eng + " (2pl+imp pl)");
                        dictionary.put(stem + "οθνται", eng + " (3pl)");
                        dictionary.put(stem + "ου", eng + " (imp s)");

                        imperfectStem = imperfectStem.substring(0, imperfectStem.length() - 1);

                        // Imperfect Indicative Middle Contract
                        dictionary.put(imperfectStem + "ουμην", "I was " + eng + "-ing (1s)");
                        dictionary.put(imperfectStem + "ου", "you were " + eng + "-ing (2s)");
                        dictionary.put(imperfectStem + "ουτο", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "ουμεθα", "we were " + eng + "-ing (1pl)");
                        dictionary.put(imperfectStem + "ουσθε", "you all were " + eng + "-ing (2pl)");
                        dictionary.put(imperfectStem + "ουντο", "they were " + eng + "-ing (3pl)");
                    }
                    // Uncontracted
                    else {

                        dictionary.put(stem + "ομαι", eng + " (1s)");
                        dictionary.put(stem + "η", eng + " (2s)");
                        dictionary.put(stem + "εται", eng + " (3s)");
                        dictionary.put(stem + "ομεθα", eng + " (1pl)");
                        dictionary.put(stem + "εσθε", eng + " (2pl+imp pl)");
                        dictionary.put(stem + "ονται", eng + " (3pl)");
                        dictionary.put(stem + "ου", eng + " (imp s)");

                        // Imperfect Indicative Middle
                        dictionary.put(imperfectStem + "ομην", "I was " + eng + "-ing (1s)");
                        dictionary.put(imperfectStem + "ου", "you were " + eng + "-ing (2s)");
                        dictionary.put(imperfectStem + "ετο", "he/she/it was " + eng + "-ing (3s)");
                        dictionary.put(imperfectStem + "ομεθα", "we were " + eng + "-ing (1pl)");
                        dictionary.put(imperfectStem + "εσθε", "you all were " + eng + "-ing (2pl)");
                        dictionary.put(imperfectStem + "οντο", "they were " + eng + "-ing (3pl)");

                        // Future Indicative Active
                        dictionary.put(stem + "σ" + "ομαι", "I shall " + eng + " (1s)");
                        dictionary.put(stem + "σ" + "η", "You will " + eng + " (2s)");
                        dictionary.put(stem + "σ" + "εται", "He/she/it will " + eng + " (3s)");
                        dictionary.put(stem + "σ" + "ομεθα", "We shall " + eng + " (1pl)");
                        dictionary.put(stem + "σ" + "εσθε", "You all will " + eng + " (2pl+imp pl)");
                        dictionary.put(stem + "σ" + "ονται", "They will " + eng + " (3pl)");
                        dictionary.put(stem + "σ" + "ου", "They will " + eng + " (3pl)");
                    }

                    String participleStem = stem.substring(0, stem.length()) + "ομεν";

                    // Present Middle Participle
                    dictionary.put(participleStem + "ος", eng + "-ing oneself (nom m s)");
                    dictionary.put(participleStem + "ον", eng + "-ing oneself (acc m s AND nom+acc n s)");
                    dictionary.put(participleStem + "ου", eng + "-ing oneself (gen m+n s)");
                    dictionary.put(participleStem + "ω", eng + "-ing oneself (dat m+n s)");
                    dictionary.put(participleStem + "οι", eng + "-ing oneself (nom m pl)");
                    dictionary.put(participleStem + "ους", eng + "-ing oneself (acc m pl)");
                    dictionary.put(participleStem + "ων", eng + "-ing oneself (gen pl)");
                    dictionary.put(participleStem + "οις", eng + "-ing oneself (dat m+n pl)");
                    dictionary.put(participleStem + "η", eng + "-ing oneself (nom+dat f s)");
                    dictionary.put(participleStem + "ην", eng + "-ing oneself (acc f s)");
                    dictionary.put(participleStem + "ης", eng + "-ing oneself (gen f s)");
                    dictionary.put(participleStem + "αι", eng + "-ing oneself (nom f pl)");
                    dictionary.put(participleStem + "ας", eng + "-ing oneself (acc f pl)");
                    dictionary.put(participleStem + "αις", eng + "-ing oneself (dat f pl)");
                    dictionary.put(participleStem + "α", eng + "-ing oneself (nom+acc n pl)");

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
                    else if (grk.indexOf(";3d") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("ης;3d", "");

                            dictionary.put(stem + "ησ", eng + " (3d nom s)");
                            dictionary.put(stem + "η", eng + " (3d acc s)");
                            dictionary.put(stem + "ους", eng + " (3d gen s)");
                            dictionary.put(stem + "ει", eng + " (3d dat s)");
                            dictionary.put(stem + "εις", eng + " (3d nom+acc pl)");
                            dictionary.put(stem + "ων", eng + " (3d gen pl)");
                            dictionary.put(stem + "σι", eng + " (3d dat pl)");
                            dictionary.put(stem + "σιν", eng + " (3d dat pl)");
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
                    else if (grk.indexOf(";3g") >= 0) {
                        if(flags.contains(Flag.USING_ACCENTS)) {
                        } else {
                            String stem = grk.replaceAll("υς;3g", "");

                            dictionary.put(stem + "υς", eng + " (3g nom s)");
                            dictionary.put(stem + "α", eng + " (3g acc s)");
                            dictionary.put(stem + "ως", eng + " (3g gen s)");
                            dictionary.put(stem + "ι", eng + " (3g dat s)");
                            dictionary.put(stem + "ευ", eng + " (3g voc s)");
                            dictionary.put(stem + "ης", eng + " (3g nom pl)");
                            dictionary.put(stem + "ας", eng + " (3g acc pl)");
                            dictionary.put(stem + "ων", eng + " (3g gen pl)");
                            dictionary.put(stem + "υσι", eng + " (3g dat pl)");
                            dictionary.put(stem + "υσιν", eng + " (3g dat pl)");
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
