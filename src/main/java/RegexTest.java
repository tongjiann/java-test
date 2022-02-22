import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        regex("1","2");
    }

    public static void regex(String sourceStr, String regexStr) {
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(sourceStr);
        matcher.replaceAll("1");
        System.out.println("pattern:" + pattern);
        System.out.println(matcher.matches());
        String[] split = "th?id=1".split("th\\?id=");
        System.out.println(split);
    }
}
