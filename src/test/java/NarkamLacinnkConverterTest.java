import by.spelling.conversion.converter.lacink.NarkamLacinkConverter;
import by.spelling.conversion.converter.tarask.NarkamTaraskConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NarkamLacinnkConverterTest {

    private static final NarkamLacinkConverter converter = new NarkamLacinkConverter();

    @Test
    public void test() {
        assertEquals("Jana j jon ubačyli śviet.", converter.convert("Яна і ён убачылі свет."));
    }
}
