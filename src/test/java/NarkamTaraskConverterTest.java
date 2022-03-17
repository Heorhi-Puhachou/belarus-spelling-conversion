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
    public void testDZ() {
        assertEquals("судзьдзя", converter.convert("суддзя"));
    }

    @Test
    public void testBP() {
        assertEquals("зьбег", converter.convert("збег"));
        assertEquals("сьпеўнік", converter.convert("спеўнік"));
    }

    @Test
    public void testBP2() {
        assertEquals("б'е", converter.convert("б'е"));
        assertEquals("п'е", converter.convert("п'е"));
    }

    @Test
    public void testE() {
        assertEquals("Эўропа", converter.convert("Еўропа"));
    }

}
