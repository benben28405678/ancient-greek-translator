package com.benmyers;

import java.util.Dictionary;
import java.util.ArrayList;

public abstract class Translator {

    protected Dictionary dictionary;
    protected ArrayList<Flag> flags;

    public abstract String translate(String input);
    public abstract String[] alignAndTranslate(String input);

}
