package by.spelling.conversion.converter.tarask.constant.replace;

import by.spelling.conversion.util.ReplacePair;

import java.util.ArrayList;

public class StartReplace {
    private static StartReplace single_instance = null;


    private ArrayList<ReplacePair> startReplace;


    private StartReplace() {
        this.startReplace = new ArrayList<>();
        startReplace.add(new ReplacePair("бельгі", "бэльгі"));
        startReplace.add(new ReplacePair("ірланд", "ірлянд"));
        startReplace.add(new ReplacePair("люксембург", "люксэмбург"));
        startReplace.add(new ReplacePair("нідэрланд", "нідэрлянд"));
        startReplace.add(new ReplacePair("швейцар", "швайцар"));
        startReplace.add(new ReplacePair("швецы", "швэцы"));
        startReplace.add(new ReplacePair("амерык", "амэрык"));

        startReplace.add(new ReplacePair("інструмент", "інструмэнт"));
        startReplace.add(new ReplacePair("шаблон", "шаблён"));
    }

    public static StartReplace getInstance() {
        if (single_instance == null)
            single_instance = new StartReplace();

        return single_instance;
    }

    public static ArrayList<ReplacePair> getStartReplaces() {
        return getInstance().startReplace;
    }
}
