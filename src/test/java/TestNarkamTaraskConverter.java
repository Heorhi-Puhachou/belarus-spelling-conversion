import by.spelling.conversion.converter.tarask.NarkamTaraskConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNarkamTaraskConverter {


    @Test
    public void testNeNja() {
        NarkamTaraskConverter converter = new NarkamTaraskConverter();
        assertEquals("Ня там.", converter.convert("Не там."));
    }

    @Test
    public void testE() {
        NarkamTaraskConverter converter = new NarkamTaraskConverter();
        assertEquals("Эўропа", converter.convert("Еўропа"));
    }

}
