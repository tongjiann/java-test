import java.util.Locale;
import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("throw new LogicalException(\"未知的错误");
            stringBuilder.append(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
            stringBuilder.append("\");");
            System.out.println(stringBuilder.toString());
        }
    }
}
