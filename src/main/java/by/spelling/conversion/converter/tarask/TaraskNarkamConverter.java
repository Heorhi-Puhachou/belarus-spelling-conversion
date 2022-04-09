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

import static by.spelling.conversion.util.StringUtilCheck.isEngWord;
import static by.spelling.conversion.util.StringUtilCheck.isGalosny;
import static by.spelling.conversion.util.StringUtilGet.getLastSymbol;
import static by.spelling.conversion.util.StringUtilTransform.transformCase;


public class TaraskNarkamConverter extends BaseConverter {

    private Parser parser;

    public TaraskNarkamConverter() {
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
        convertedValue = chekDZ(convertedValue);
        convertedValue = checkZ(convertedValue, next);
        convertedValue = checkNe(convertedValue, next);
        convertedValue = checkBez(convertedValue, next);
        convertedValue = dummyReplace(convertedValue);
        convertedValue = replaceStart(convertedValue);
        convertedValue = replaceEnd(convertedValue);
        convertedValue = chekMZ(convertedValue);
        convertedValue = chekMZForDoubles(convertedValue);
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        return convertedValue;
    }

    // й -> і
    private String checkI(ParsedElement prev, String current, String delimiter) {
        if (prev != null && current.equals("й") && delimiter.equals(" ")) {
            String lastPrevSymbol = getLastSymbol(prev.getWord());
            if (isGalosny(lastPrevSymbol)) {
                return "і";
            }
        }
        return current;
    }

    // зь -> з
    private String checkZ(String current, ParsedElement next) {
        if (current.equals("зь")) {
            return "з";
        }
        return current;
    }

    // ня -> не
    private String checkNe(String current, ParsedElement next) {
        if (current.equals("ня")) {
            return "не";
        }

        return current;
    }

    // без -> бяз
    private String checkBez(String current, ParsedElement next) {
        if (current.equals("бяз")) {
            return "без";
        }
        return current;
    }


    private String chekMZ(String in) {
        for (int i = 0; i < ZmiakcajemyZycny.getZmiakcajemyjaZycnyja().size(); i++) {
            for (int j = 0; j < MiakkajaPara.getMiakkijaPary().size(); j++) {
                String narkam = ZmiakcajemyZycny.getZmiakcajemyjaZycnyja().get(i) + MiakkajaPara.getMiakkijaPary().get(j);
                String tarask = ZmiakcajemyZycny.getZmiakcajemyjaZycnyja().get(i) + "ь" + MiakkajaPara.getMiakkijaPary().get(j);
                in = in.replace(tarask, narkam);
            }
        }
        return in;
    }

    private String chekMZForDoubles(String in) {
        for (int i = 0; i < DoubleMiakki.getDoubleMiakki().size(); i++) {
            for (int j = 0; j < Zmiakchatel.getZmiakcaceli().size(); j++) {
                String narkam = DoubleMiakki.getDoubleMiakki().get(i) + DoubleMiakki.getDoubleMiakki().get(i) + Zmiakchatel.getZmiakcaceli().get(j);
                String tarask = DoubleMiakki.getDoubleMiakki().get(i) + "ь" + DoubleMiakki.getDoubleMiakki().get(i) + Zmiakchatel.getZmiakcaceli().get(j);
                in = in.replace(tarask, narkam);
            }
        }
        return in;
    }

    //дзьдз -> ддз
    private String chekDZ(String in) {
        return in.replace("дзьдз", "ддз");
    }

    private String dummyReplace(String in) {
        for (ReplacePair pair : DummyReplace.getDummyReplaces()) {
            in = in.replace(pair.getTarask(), pair.getNarkam());
        }
        return in;
    }

    private String replaceEnd(String word) {
        for (ReplacePair pair : EndReplace.getEndReplaces()) {
            if (word.endsWith(pair.getTarask())) {
                return word.replace(pair.getTarask(), pair.getNarkam());
            }
        }
        return word;
    }

    private String replaceStart(String word) {
        for (ReplacePair pair : StartReplace.getStartReplaces()) {
            if (word.startsWith(pair.getTarask())) {
                return word.replace(pair.getTarask(), pair.getNarkam());
            }
        }
        return word;
    }
}
