import by.spelling.conversion.converter.lacink.NarkamLacinkConverter;
import by.spelling.conversion.converter.lacink.TaraskLacinkConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NarkamLacinkConverterTest {

    private static final NarkamLacinkConverter converter = new NarkamLacinkConverter();

    @Test
    public void test() {
        assertEquals("nia toj źjezd da Iŭja", converter.convert("не той з'езд да Іўя"));
    }

}
