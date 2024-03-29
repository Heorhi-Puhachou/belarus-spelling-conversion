package by.spelling.conversion.converter.l;


import by.spelling.conversion.converter.BazavyKanvertar;
import by.spelling.conversion.parser.PraanalizavanyElement;
import by.spelling.conversion.parser.Analizatar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static by.spelling.conversion.util.StringUtilCheck.*;
import static by.spelling.conversion.util.StringUtilGet.getLastSymbol;
import static by.spelling.conversion.util.StringUtilTransform.transformCase;

public class KLKanvertar extends BazavyKanvertar {

    private final Analizatar parser;
    private final HashMap<String, String> pairs;
    private final HashMap<String, String> paraMiakkaści;
    private final HashMap<String, String> pairsL;

    public KLKanvertar() {

        this.parser = new Analizatar();
        pairs = new HashMap<>();
        pairs.put("а", "a");
        pairs.put("б", "b");
        pairs.put("в", "v");
        pairs.put("г", "h");
        pairs.put("д", "d");
        pairs.put("е", "je");
        pairs.put("ё", "jo");
        pairs.put("ж", "ž");
        pairs.put("з", "z");
        pairs.put("і", "i");
        pairs.put("й", "j");
        pairs.put("к", "k");
        pairs.put("л", "ł");
        pairs.put("м", "m");
        pairs.put("н", "n");
        pairs.put("о", "o");
        pairs.put("п", "p");
        pairs.put("р", "r");
        pairs.put("с", "s");
        pairs.put("т", "t");
        pairs.put("у", "u");
        pairs.put("ў", "ŭ");
        pairs.put("ф", "f");
        pairs.put("х", "ch");
        pairs.put("ц", "c");
        pairs.put("ч", "č");
        pairs.put("ш", "š");
        pairs.put("ы", "y");
        pairs.put("ь", "");
        pairs.put("э", "e");
        pairs.put("ю", "ju");
        pairs.put("я", "ja");

        paraMiakkaści = new HashMap<>();
        paraMiakkaści.put("ł", "l");
        paraMiakkaści.put("n", "ń");
        paraMiakkaści.put("c", "ć");
        paraMiakkaści.put("s", "ś");
        paraMiakkaści.put("z", "ź");

        pairsL = new HashMap<>();
        pairsL.put("łia", "la");
        pairsL.put("łie", "le");
        pairsL.put("łio", "lo");
        pairsL.put("łiu", "lu");
    }

    public String kanvertavać(String tekst) {
        if (tekst == null || tekst.isEmpty()) {
            return tekst;
        }
        ArrayList<PraanalizavanyElement> elements = parser.parse(tekst);
        StringBuilder result = new StringBuilder();

        for (PraanalizavanyElement current : elements) {
            if (isEngWord(current.getOriginalWord())) {
                result.append(current.getDelimiter()).append(current.getOriginalWord());
            } else {
                result.append(current.getDelimiter()).append(convertElement(current));
            }
        }

        return result.toString();
    }

    private String convertElement(PraanalizavanyElement current) {
        String convertedValue = advancedReplace(current.getWord());
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        return convertedValue;
    }

    private String advancedReplace(String word) {
        String result = "";

        char[] chars = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (isApostraf(chars[i])) {
                //DO NOTHING
            } else if (isNumber(chars[i]) || isEngSymbol(chars[i])) {
                result = result + chars[i];
            } else {
                if (i > 0 && (chars[i] == 'ь')) {
                    result = replaceLastToMiakki(result);
                }

                String symbol = pairs.get("" + chars[i]);
                if (i > 0 && hetaMiakkiHalosny(chars[i])
                        && !isHalosny(chars[i - 1])
                        && chars[i - 1] != 'ь'
                        && chars[i - 1] != 'ў'
                        && !isApostraf(chars[i - 1])) {
                    symbol = symbol.replace("j", "i");
                }
                result = result + symbol;
            }
        }

        return replaceL(result);
    }

    private String replaceL(String word) {
        for (Map.Entry<String, String> entry : pairsL.entrySet()) {
            word = word.replace(entry.getKey(), entry.getValue());
        }
        return word.replace("łi", "li");
    }

    private String replaceLastToMiakki(String word) {
        if (word.length() == 1) {
            return paraMiakkaści.get(word);
        }
        String replacement = paraMiakkaści.get(getLastSymbol(word)) == null ? getLastSymbol(word) : paraMiakkaści.get(getLastSymbol(word));
        return word.substring(0, word.length() - 1) + replacement;
    }
}
