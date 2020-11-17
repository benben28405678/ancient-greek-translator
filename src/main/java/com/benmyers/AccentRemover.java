package com.benmyers;

public class AccentRemover {

    public static String removeAccents(String input) {
        input = input.replace('ἀ', 'α');
        input = input.replace('ά', 'α');
        input = input.replace('ἁ', 'α');
        input = input.replace('ᾶ', 'α');
        input = input.replace('ᾳ', 'α');
        input = input.replace('ὰ', 'α');
        input = input.replace('ᾱ', 'α');
        input = input.replace('ᾰ', 'α');
        input = input.replace('ἆ', 'α');
        input = input.replace('ἇ', 'α');
        input = input.replace('ἂ', 'α');
        input = input.replace('ἃ', 'α');
        input = input.replace('ἄ', 'α');
        input = input.replace('ἅ', 'α');

        input = input.replace('ὲ', 'ε');
        input = input.replace('ἒ', 'ε');
        input = input.replace('ἓ', 'ε');
        input = input.replace('έ', 'ε');
        input = input.replace('έ', 'ε');
        input = input.replace('ἐ', 'ε');
        input = input.replace('ἑ', 'ε');
        input = input.replace('ἔ', 'ε');
        input = input.replace('ἕ', 'ε');

        input = input.replace('Ἐ', 'Ε');

        input = input.replace('ἦ', 'η');
        input = input.replace('ἧ', 'η');
        input = input.replace('ῆ', 'η');
        input = input.replace('ῃ', 'η');
        input = input.replace('ὴ', 'η');
        input = input.replace('ἢ', 'η');
        input = input.replace('ἣ', 'η');
        input = input.replace('ή', 'η');
        input = input.replace('ἠ', 'η');
        input = input.replace('ἡ', 'η');
        input = input.replace('ἤ', 'η');
        input = input.replace('ἥ', 'η');

        input = input.replace('ῑ', 'ι');
        input = input.replace('ῐ', 'ι');
        input = input.replace('ἶ', 'ι');
        input = input.replace('ἷ', 'ι');
        input = input.replace('ῖ', 'ι');
        input = input.replace('ἷ', 'ι');
        input = input.replace('ὶ', 'ι');
        input = input.replace('ἲ', 'ι');
        input = input.replace('ἳ', 'ι');
        input = input.replace('ί', 'ι');
        input = input.replace('ϊ', 'ι');
        input = input.replace('ἰ', 'ι');
        input = input.replace('ἱ', 'ι');
        input = input.replace('ἴ', 'ι');
        input = input.replace('ἵ', 'ι');

        input = input.replace('ὸ', 'ο');
        input = input.replace('ὂ', 'ο');
        input = input.replace('ὃ', 'ο');
        input = input.replace('ό', 'ο');
        input = input.replace('ό', 'ο');
        input = input.replace('ὀ', 'ο');
        input = input.replace('ὁ', 'ο');
        input = input.replace('ὄ', 'ο');
        input = input.replace('ὅ', 'ο');

        input = input.replace('ῡ', 'υ');
        input = input.replace('ῠ', 'υ');
        input = input.replace('ὖ', 'υ');
        input = input.replace('ὗ', 'υ');
        input = input.replace('ῦ', 'υ');
        input = input.replace('ὺ', 'υ');
        input = input.replace('ὒ', 'υ');
        input = input.replace('ὓ', 'υ');
        input = input.replace('ύ', 'υ');
        input = input.replace('ϋ', 'υ');
        input = input.replace('ὐ', 'υ');
        input = input.replace('ὑ', 'υ');
        input = input.replace('ὔ', 'υ');
        input = input.replace('ὕ', 'υ');

        input = input.replace('ὦ', 'ω');
        input = input.replace('ὧ', 'ω');
        input = input.replace('ῶ', 'ω');
        input = input.replace('ῳ', 'ω');
        input = input.replace('ὼ', 'ω');
        input = input.replace('ὢ', 'ω');
        input = input.replace('ὣ', 'ω');
        input = input.replace('ώ', 'ω');
        input = input.replace('ὠ', 'ω');
        input = input.replace('ὡ', 'ω');
        input = input.replace('ὤ', 'ω');
        input = input.replace('ὥ', 'ω');

        input = input.replace('ῥ', 'ρ');

        return input;
    }

}
