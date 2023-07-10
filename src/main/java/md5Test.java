import cn.hutool.crypto.digest.MD5;
import org.junit.Test;

import java.math.BigInteger;

public class md5Test {

    @Test
    public void testMd5Equals() {
        String phoneNumber = null;
        BigInteger phoBI = new BigInteger("1000000000");
        boolean flag = false;
        int cnt = 0;
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        while (phoBI.toString().length() < 11 && !flag) {

            phoneNumber = "1" + phoBI;
            // System.out.println(phoneNumber);
            MD5 md5 = MD5.create();
            byte[] digest = md5.digest(phoneNumber);
            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            if ("b66f54be65198152811a0d87f1ef68a5".contentEquals(sb)) {
                flag = true;
            }
            phoBI = phoBI.add(BigInteger.ONE);
            cnt++;
            if (cnt % 1000000 == 0) {
                end = System.currentTimeMillis();
                System.out.println("尝试了" + cnt + "次，耗时" + (end-start) + "ms");
            }
        }
        System.out.println("=================");
        end = System.currentTimeMillis();
        System.out.println("尝试了" + cnt + "次，耗时" + (end-start) + "ms");
        System.out.println(phoneNumber);
    }


}