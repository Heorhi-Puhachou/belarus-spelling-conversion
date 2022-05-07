import by.spelling.conversion.converter.tarask.NarkamTaraskConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NarkamTaraskConverterTest {

    private static final NarkamTaraskConverter converter = new NarkamTaraskConverter();

    @Test
    public void testTransformEIntoJa() {
        assertEquals("Ня там.", converter.convert("Не там."));
        assertEquals("Бяз назвы", converter.convert("Без назвы"));
        assertEquals("Ня бойка", converter.convert("Не бойка"));
    }

    @Test
    public void testNoChange() {
        assertEquals("аплікацыя", converter.convert("аплікацыя"));
        assertEquals("Пугачова", converter.convert("Пугачова"));
    }

    @Test
    public void testEnding() {
        assertEquals("кампендыюм", converter.convert("кампендыум"));
        assertEquals("калегіюм", converter.convert("калегіум"));
    }


    @Test
    public void testDoublesMZ() {
        assertEquals("вясельле", converter.convert("вяселле"));
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

    @Test
    public void testZ() {
        assertEquals("празь яе", converter.convert("праз яе"));
        assertEquals("зь сямі", converter.convert("з сямі"));
    }

    @Test
    public void testMkkZnk() {
        assertEquals("зьеў", converter.convert("з'еў"));
        assertEquals("зьезд", converter.convert("з'езд"));
    }

    @Test
    public void testSS() {
        assertEquals("сьмяесься", converter.convert("смяешся"));
    }

}
