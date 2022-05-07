package by.spelling.conversion.converter.tarask;


import by.spelling.conversion.converter.BaseConverter;
import by.spelling.conversion.converter.tarask.constant.DoubleMiakki;
import by.spelling.conversion.converter.tarask.constant.MiakkajaPara;
import by.spelling.conversion.converter.tarask.constant.ZmiakcajemyZycny;
import by.spelling.conversion.converter.tarask.constant.Zmiakchatel;
import by.spelling.conversion.converter.tarask.constant.replace.DummyReplace;
import by.spelling.conversion.converter.tarask.constant.replace.EndReplace;
import by.spelling.conversion.converter.tarask.constant.replace.StartReplace;
import by.spelling.conversion.parser.ParsedElement;
import by.spelling.conversion.parser.Parser;
import by.spelling.conversion.util.ReplacePair;
import by.spelling.conversion.util.WordCase;

import java.util.ArrayList;

import static by.spelling.conversion.util.StringUtilCheck.*;
import static by.spelling.conversion.util.StringUtilGet.getLastSymbol;
import static by.spelling.conversion.util.StringUtilTransform.transformCase;


public class NarkamTaraskConverter extends BaseConverter {

    private Parser parser;

    public NarkamTaraskConverter() {
        this.parser = new Parser();
    }

    public String convert(String narkam) {

        if (narkam == null || narkam.isEmpty()) {
            return narkam;
        }

        ArrayList<ParsedElement> elements = parser.parse(narkam);

        StringBuilder result = new StringBuilder();

        for (int index = 0; index < elements.size(); index++) {
            ParsedElement current = elements.get(index);
            if (isEngWord(current.getOriginalWord()) || current.getWordCase() == WordCase.OTHER) {
                result.append(current.getDelimiter()).append(current.getOriginalWord());
            } else {
                ParsedElement prev = getPrevElement(elements, index);
                ParsedElement next = getNextElement(elements, index);
                result.append(elements.get(index).getDelimiter()).append(convertElement(prev, current, next));
            }
        }
        return result.toString();
    }

    private ParsedElement getPrevElement(ArrayList<ParsedElement> elements, int index) {
        if (index > 0) {
            return elements.get(index - 1);
        } else {
            return null;
        }
    }

    private ParsedElement getNextElement(ArrayList<ParsedElement> elements, int index) {
        if (index < elements.size() - 1) {
            return elements.get(index + 1);
        } else {
            return null;
        }
    }

    private String convertElement(ParsedElement prev, ParsedElement current, ParsedElement next) {
        String convertedValue = checkI(prev, current.getWord(), current.getDelimiter());
        convertedValue = checkZ(convertedValue, next);
        convertedValue = checkApost(convertedValue);
        convertedValue = checkNe(convertedValue, next);
        convertedValue = checkBez(convertedValue, next);
        convertedValue = dummyReplace(convertedValue);
        convertedValue = replaceStart(convertedValue);
        convertedValue = replaceEnd(convertedValue);
        convertedValue = chekMZ(convertedValue);
        convertedValue = chekMZForDoubles(convertedValue);
        convertedValue = chekDZ(convertedValue);
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        if (!transformCase(current.getWordCase(), current.getWord()).equals(convertedValue)) {
            System.out.println(transformCase(current.getWordCase(), current.getWord()) + " -> " + convertedValue);
        }
        return convertedValue;
    }

    // і -> й
    private String checkI(ParsedElement prev, String current, String delimiter) {
        if (prev != null && current.equals("і") && delimiter.equals(" ")) {
            String lastPrevSymbol = getLastSymbol(prev.getWord());
            if (isGalosny(lastPrevSymbol)) {
                return "й";
            }
        }
        return current;
    }

    // з'езд -> зьезд
    private String checkApost(String current) {
        if (current.contains("з'")) {
            return current.replace("з'","зь");
        }
        return current;
    }

    // з -> зь
    // праз -> празь
    private String checkZ(String current, ParsedElement next) {
        if (next != null
                && next.getDelimiter().equals(" ")
                && current.equals("з")) {
            for (int i = 0; i < MiakkajaPara.getMiakkijaPary().size(); i++) {
                if (next.getWord().startsWith(MiakkajaPara.getMiakkijaPary().get(i))) {
                    return "зь";
                }
            }
            for (int i = 0; i < Zmiakchatel.getZmiakcaceli().size(); i++) {
                if (next.getWord().startsWith(Zmiakchatel.getZmiakcaceli().get(i))) {
                    return "зь";
                }
            }
        }

        if (next != null
                && next.getDelimiter().equals(" ")
                && current.equals("праз")) {
            for (int i = 0; i < MiakkajaPara.getMiakkijaPary().size(); i++) {
                if (next.getWord().startsWith(MiakkajaPara.getMiakkijaPary().get(i))) {
                    return "празь";
                }
            }
            for (int i = 0; i < Zmiakchatel.getZmiakcaceli().size(); i++) {
                if (next.getWord().startsWith(Zmiakchatel.getZmiakcaceli().get(i))) {
                    return "празь";
                }
            }
        }

        return current;
    }

    // не -> ня
    private String checkNe(String current, ParsedElement next) {
        if (current.equals("не")
                && next != null
                && next.getDelimiter().equals(" ")
                && pershySkladPadNaciskam(next.getWord())) {
            return "ня";
        }

        return current;
    }

    // без -> бяз
    private String checkBez(String current, ParsedElement next) {
        if (current.equals("без")
                && next != null
                && next.getDelimiter().equals(" ")
                && pershySkladPadNaciskam(next.getWord())) {
            return "бяз";
        }
        return current;
    }


    private String chekMZ(String in) {
        for (int i = 0; i < ZmiakcajemyZycny.getZmiakcajemyjaZycnyja().size(); i++) {
            for (int j = 0; j < MiakkajaPara.getMiakkijaPary().size(); j++) {
                String narkam = ZmiakcajemyZycny.getZmiakcajemyjaZycnyja().get(i) + MiakkajaPara.getMiakkijaPary().get(j);
                String tarask = ZmiakcajemyZycny.getZmiakcajemyjaZycnyja().get(i) + "ь" + MiakkajaPara.getMiakkijaPary().get(j);
                in = in.replace(narkam, tarask);
            }
        }
        return in;
    }

    private String chekMZForDoubles(String in) {
        for (int i = 0; i < DoubleMiakki.getDoubleMiakki().size(); i++) {
            for (int j = 0; j < Zmiakchatel.getZmiakcaceli().size(); j++) {
                String narkam = DoubleMiakki.getDoubleMiakki().get(i) + DoubleMiakki.getDoubleMiakki().get(i) + Zmiakchatel.getZmiakcaceli().get(j);
                String tarask = DoubleMiakki.getDoubleMiakki().get(i) + "ь" + DoubleMiakki.getDoubleMiakki().get(i) + Zmiakchatel.getZmiakcaceli().get(j);
                in = in.replace(narkam, tarask);
            }
        }
        return in;
    }

    private String chekDZ(String in) {
        for (int j = 0; j < Zmiakchatel.getZmiakcaceli().size(); j++) {
            String narkam = "ддз" + Zmiakchatel.getZmiakcaceli().get(j);
            String tarask = "дзьдз" + Zmiakchatel.getZmiakcaceli().get(j);
            in = in.replace(narkam, tarask);
        }
        return in;
    }

    private String dummyReplace(String in) {
        for (ReplacePair pair : DummyReplace.getDummyReplaces()) {
            in = in.replace(pair.getNarkam(), pair.getTarask());
        }
        return in;
    }

    private String replaceEnd(String word) {
        for (ReplacePair pair : EndReplace.getEndReplaces()) {
            if (word.endsWith(pair.getNarkam())) {
                return word.replace(pair.getNarkam(), pair.getTarask());
            }
        }
        return word;
    }

    private String replaceStart(String word) {
        for (ReplacePair pair : StartReplace.getStartReplaces()) {
            if (word.startsWith(pair.getNarkam())) {
                return word.replace(pair.getNarkam(), pair.getTarask());
            }
        }
        return word;
    }
}
