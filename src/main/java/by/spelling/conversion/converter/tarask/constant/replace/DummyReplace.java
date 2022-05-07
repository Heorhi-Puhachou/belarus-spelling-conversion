package by.spelling.conversion.converter.tarask.constant.replace;

import by.spelling.conversion.util.ReplacePair;

import java.util.ArrayList;

public class DummyReplace {
    private static DummyReplace single_instance = null;


    private ArrayList<ReplacePair> dummyReplace;


    private DummyReplace() {
        this.dummyReplace = new ArrayList<>();
        dummyReplace.add(new ReplacePair("амерык", "амэрык"));
        dummyReplace.add(new ReplacePair("анверт", "анвэрт"));
        dummyReplace.add(new ReplacePair("аргумент", "аргумэнт"));
        dummyReplace.add(new ReplacePair("артапед", "артапэд"));
        dummyReplace.add(new ReplacePair("аспект", "аспэкт"));
        dummyReplace.add(new ReplacePair("арфаграф", "артаграф"));
        dummyReplace.add(new ReplacePair("арыстоцель", "арыстотэль"));
        dummyReplace.add(new ReplacePair("валанцёр", "валянтэр"));
        dummyReplace.add(new ReplacePair("версі", "вэрсі"));
        dummyReplace.add(new ReplacePair("вулкан", "вулькан"));
        dummyReplace.add(new ReplacePair("в’етнам", "віетнам"));
        dummyReplace.add(new ReplacePair("гаус", "гаўс"));
        dummyReplace.add(new ReplacePair("Генры", "Гэнры"));
        dummyReplace.add(new ReplacePair("глам", "глям"));
        dummyReplace.add(new ReplacePair("гласар", "глясар"));
        dummyReplace.add(new ReplacePair("візіт", "візыт"));
        dummyReplace.add(new ReplacePair("дакумент", "дакумэнт"));
        dummyReplace.add(new ReplacePair("донья", "доньня"));
        dummyReplace.add(new ReplacePair("еўр", "эўр"));
        dummyReplace.add(new ReplacePair("іерогліф", "герогліф"));
        dummyReplace.add(new ReplacePair("каментарый", "камэнтар"));
        dummyReplace.add(new ReplacePair("каталог", "каталёг"));
        dummyReplace.add(new ReplacePair("класіч", "клясыч"));
        dummyReplace.add(new ReplacePair("клуб", "клюб"));
        dummyReplace.add(new ReplacePair("лагіч", "лягіч"));
        dummyReplace.add(new ReplacePair("лампада", "лямпада"));
        dummyReplace.add(new ReplacePair("лейкацыт", "леўкацыт"));
        dummyReplace.add(new ReplacePair("логік", "лёгік"));
        dummyReplace.add(new ReplacePair("логія", "лёгія"));
        dummyReplace.add(new ReplacePair("мекка", "мэка"));
        dummyReplace.add(new ReplacePair("метад", "мэтад"));
        dummyReplace.add(new ReplacePair("механ", "мэхан"));
        dummyReplace.add(new ReplacePair("мільянер", "мільянэр"));
        dummyReplace.add(new ReplacePair("менеджэр", "мэнэджар"));
        dummyReplace.add(new ReplacePair("музей", "музэй"));
        dummyReplace.add(new ReplacePair("мушкіцёр", "мушкітэр"));
        dummyReplace.add(new ReplacePair("пазіцы", "пазыцы"));
        dummyReplace.add(new ReplacePair("партнёр", "партнэр"));
        dummyReplace.add(new ReplacePair("плутон", "плютон"));
        dummyReplace.add(new ReplacePair("прэзент", "прэзэнт"));
        dummyReplace.add(new ReplacePair("рыдыус", "радыюс"));
        dummyReplace.add(new ReplacePair("рэклам", "рэклям"));
        dummyReplace.add(new ReplacePair("саліцёр", "слітэр"));
        dummyReplace.add(new ReplacePair("сегм", "сэгм"));
        dummyReplace.add(new ReplacePair("сесія", "сэсія"));
        dummyReplace.add(new ReplacePair("сесію", "сэсію"));
        dummyReplace.add(new ReplacePair("сігнал", "сыгнал"));
        dummyReplace.add(new ReplacePair("сістэм", "сыстэм"));
        dummyReplace.add(new ReplacePair("фунікулёр", "фунікулер"));
        dummyReplace.add(new ReplacePair("фальклор", "фальклёр"));
        dummyReplace.add(new ReplacePair("шоу", "шоў"));
        dummyReplace.add(new ReplacePair("элемент", "элемэнт"));
        dummyReplace.add(new ReplacePair("эфект", "эфэкт"));
    }

    public static DummyReplace getInstance() {
        if (single_instance == null)
            single_instance = new DummyReplace();

        return single_instance;
    }

    public static ArrayList<ReplacePair> getDummyReplaces() {
        return getInstance().dummyReplace;
    }
}
