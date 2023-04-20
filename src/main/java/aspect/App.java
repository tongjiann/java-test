package aspect;

public class App {
    public static void main(String[] args) {
        testCompileTime();
    }

    public static void testCompileTime() {
        Account account = new Account();
        System.out.println("==================");
        account.pay(10);
        account.pay(50);
        System.out.println("==================");
    }
}
