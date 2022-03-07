import by.spelling.conversion.converter.tarask.NarkamTaraskConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NarkamTaraskConverterTest {

    private static final  NarkamTaraskConverter converter = new NarkamTaraskConverter();

    @Test
    public void testTransformEIntoJa() {
        assertEquals("Ня там.", converter.convert("Не там."));
        assertEquals("Бяз назвы", converter.convert("Без назвы"));
        assertEquals("Ня бойка", converter.convert("Не бойка"));
    }

    @Test
    public void testE() {
        assertEquals("Эўропа", converter.convert("Еўропа"));
    }

}
