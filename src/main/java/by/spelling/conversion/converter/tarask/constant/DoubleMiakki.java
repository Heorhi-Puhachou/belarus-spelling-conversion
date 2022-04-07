package by.spelling.conversion.converter.tarask.constant;

import java.util.ArrayList;

public class DoubleMiakki {
    private static DoubleMiakki single_instance = null;


    private ArrayList<String> doubleMiakki;


    private DoubleMiakki() {
        doubleMiakki = new ArrayList<>();
        doubleMiakki.add("с");
        doubleMiakki.add("ц");
        doubleMiakki.add("н");
        doubleMiakki.add("з");
        doubleMiakki.add("л");
    }

    public static DoubleMiakki getInstance() {
        if (single_instance == null)
            single_instance = new DoubleMiakki();

        return single_instance;
    }

    public static ArrayList<String> getDoubleMiakki() {
        return getInstance().doubleMiakki;
    }
}
