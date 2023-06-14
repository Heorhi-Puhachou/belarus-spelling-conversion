import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;
import by.spelling.conversion.converter.tarask.NarkamTaraskConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaraskLacinkConverterTest {

    private static final TaraskLacinkConverter converter = new TaraskLacinkConverter();

    @Test
    public void test() {
        assertEquals("Lahienda Zeldy", converter.convert("Лягенда Зэльды"));
    }

}
