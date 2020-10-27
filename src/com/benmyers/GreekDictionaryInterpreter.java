package com.benmyers;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class GreekDictionaryInterpreter {

    private Language keyLanguage;
    private Dictionary dictionary = new Hashtable();

    public GreekDictionaryInterpreter(Language input) {

        try {
            File file = new File("dictionary.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String data = sc.nextLine();
                if(data.charAt(0) == '#') continue;
                String[] keywords = data.split(" - ");
                switch(input) {
                    case ENG: dictionary.put(keywords[1], keywords[0]); break;
                    case GRK: dictionary.put(keywords[0], keywords[1]); break;
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
