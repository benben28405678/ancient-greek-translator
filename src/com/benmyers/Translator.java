package com.benmyers;

import java.util.Dictionary;

public abstract class Translator {

    protected Dictionary dictionary;

    public abstract String translate(String input);

}
