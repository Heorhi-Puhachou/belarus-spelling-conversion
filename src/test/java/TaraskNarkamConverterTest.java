import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;
import by.spelling.conversion.converter.tarask.TaraskNarkamConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaraskNarkamConverterTest {

    private static final TaraskNarkamConverter converter = new TaraskNarkamConverter();

    @Test
    public void test() {
        assertEquals("Не там Еўропа...", converter.convert("Ня там Эўропа..."));
    }

    @Test
    public void testMZ() {
        assertEquals("пасля", converter.convert("пасьля"));
    }

    @Test
    public void testEnding() {
        assertEquals("кампендыум", converter.convert("кампендыюм"));
        assertEquals("калегіум", converter.convert("калегіюм"));
    }

    @Test
    public void testDoublesMZ() {
        assertEquals("вяселле", converter.convert("вясельле"));
    }

    @Test
    public void testNoChange() {
        assertEquals("карыстальнік", converter.convert("карыстальнік"));
    }

    @Test
    public void testDZ() {
        assertEquals("суддзя", converter.convert("судзьдзя"));
    }
}
