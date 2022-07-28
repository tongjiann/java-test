import org.junit.Test;

public class IntegerTest {

    @Test
    public void divisibleTest() {
        System.out.println(8 / 2);
    }

    @Test
    public void modTest() {
        System.out.println(9 % 2);
    }

    @Test
    public void nullTest(){
        Integer i = null;
        System.out.println(1==i);
    }
}