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

}
