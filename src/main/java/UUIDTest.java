import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

public class UUIDTest {

    @Test
    public void testExpception() {
        for (int i = 0; i < 22; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("VALUES ('");
            stringBuilder.append(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
            stringBuilder.append("',");
            System.out.println(stringBuilder.toString());
        }
    }

    @Test
    public void testUUIDCreator() {
        for (int i = 0; i < 30; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
            stringBuilder.append("\n");
            System.out.println(stringBuilder.toString());
        }
    }
}
