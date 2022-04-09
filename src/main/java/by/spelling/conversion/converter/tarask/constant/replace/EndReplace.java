package by.spelling.conversion.converter.tarask.constant.replace;

import by.spelling.conversion.util.ReplacePair;

import java.util.ArrayList;

public class EndReplace {
    private static EndReplace single_instance = null;


    private ArrayList<ReplacePair> endReplace;


    private EndReplace() {
        this.endReplace = new ArrayList<>();
        endReplace.add(new ReplacePair("метр", "метар"));
        endReplace.add(new ReplacePair("літр", "літар"));

        //калегіум - калегіюм
        endReplace.add(new ReplacePair("іум", "іюм"));

        //кампендыум - кампендыюм
        endReplace.add(new ReplacePair("ыум", "ыюм"));
    }

    public static EndReplace getInstance() {
        if (single_instance == null)
            single_instance = new EndReplace();

        return single_instance;
    }

    public static ArrayList<ReplacePair> getEndReplaces() {
        return getInstance().endReplace;
    }
}
