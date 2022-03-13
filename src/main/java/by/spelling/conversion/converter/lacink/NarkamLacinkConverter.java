package by.spelling.conversion.converter.lacink;


import by.spelling.conversion.converter.BaseConverter;
import by.spelling.conversion.converter.tarask.NarkamTaraskConverter;

public class NarkamLacinkConverter extends BaseConverter {
    public String convert(String narkam) {
        if (narkam == null || narkam.isEmpty()) {
            return narkam;
        }
        String tarask = (new NarkamTaraskConverter()).convert(narkam);
        String lacink = (new TaraskLacinkConverter()).convert(tarask);

        return lacink;
    }
}
