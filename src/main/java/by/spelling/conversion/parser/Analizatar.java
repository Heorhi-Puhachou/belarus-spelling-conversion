package by.spelling.conversion.parser;

import java.util.ArrayList;

import static by.spelling.conversion.util.StringUtilCheck.isWordSymbol;

public class Analizatar {

    public ArrayList<PraanalizavanyElement> parse(String text) {
        ArrayList<PraanalizavanyElement> result = new ArrayList<>();
        char[] chars = text.toCharArray();
        boolean currentDelimiter = true;
        String currentDelimiterValue = "";
        String currentWordValue = "";
        for (int index = 0; index < text.length(); index++) {
            char currentSymbol = chars[index];
            boolean isDelimiter = !isWordSymbol(currentSymbol);
            boolean isLastSymbol = index == text.length() - 1;

            // Чыталі не-слова й працягваем чытаць не-слова
            if (currentDelimiter && isDelimiter) {
                currentDelimiterValue = currentDelimiterValue + currentSymbol;
                if (isLastSymbol) {
                    result.add(new PraanalizavanyElement(currentDelimiterValue, ""));
                }
            }
            // Чыталі не-слова й пачалі чытаць слова
            else if (currentDelimiter) {
                currentDelimiter = false;
                currentWordValue = "" + currentSymbol;
                if (isLastSymbol) {
                    result.add(new PraanalizavanyElement(currentDelimiterValue, currentWordValue));
                }
            }
            // Чыталі слова й працягваем чытаць слова
            else if (!isDelimiter) {
                currentWordValue = currentWordValue + currentSymbol;
                if (isLastSymbol) {
                    result.add(new PraanalizavanyElement(currentDelimiterValue, currentWordValue));
                }
            }
            // Чыталі слова й пачалі чытаць не-слова
            else {
                result.add(new PraanalizavanyElement(currentDelimiterValue, currentWordValue));
                currentDelimiter = true;
                currentDelimiterValue = "" + currentSymbol;
                if (isLastSymbol) {
                    result.add(new PraanalizavanyElement(currentDelimiterValue, ""));
                }
            }
        }
        return result;
    }
}
