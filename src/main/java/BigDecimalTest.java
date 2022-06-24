import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-06-06 16:48
 */
public class BigDecimalTest {
    @Test
    public void zeroTest() {
        System.out.println(BigDecimal.ZERO.compareTo(BigDecimal.ONE));
    }
}
