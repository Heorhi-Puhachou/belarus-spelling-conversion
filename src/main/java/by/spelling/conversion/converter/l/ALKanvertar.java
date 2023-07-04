package by.spelling.conversion.converter.l;


import by.spelling.conversion.converter.BazavyKanvertar;
import by.spelling.conversion.converter.k.AKKanvertar;

public class ALKanvertar extends BazavyKanvertar {
    public String kanvertavać(String tekst) {
        if (tekst == null || tekst.isEmpty()) {
            return tekst;
        }
        String kRadok = (new AKKanvertar()).kanvertavać(tekst);
        String lRadok = (new KLKanvertar()).kanvertavać(kRadok);

        return lRadok;
    }
}
