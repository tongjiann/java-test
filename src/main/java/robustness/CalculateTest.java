package robustness;

import java.math.BigDecimal;

public class CalculateTest {
    public static void main(String[] args) {
        bigDecimalTest();
    }

    /**
     * 输出：1.2345677999999999929769955997471697628498077392578125
     * <p>BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常</p>
     */
    private static void bigDecimalTest() {
        double doubleValue =1.2345678D;
        BigDecimal bigDecimal = new BigDecimal(doubleValue);
        BigDecimal bigDecimal2 = new BigDecimal("1.2345678");
        System.out.println(bigDecimal);
        System.out.println(bigDecimal2);
    }
}
