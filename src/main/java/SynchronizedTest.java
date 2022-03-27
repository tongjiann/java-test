import org.junit.Test;

/**
 * @author xiwang
 * @date 2022-03-23 14:24
 */
public class SynchronizedTest {

    @Test
    public void testSynchronized() {
        C c1 = new C();
        C c2 = new C();
        Thread thread1 = new Thread(c1, "c1");
        Thread thread2 = new Thread(c2, "c2");
        thread1.start();
        thread2.start();
    }


    class C implements Runnable {
        private int packageId = 0;

        private synchronized int getPackageId() throws InterruptedException {
            packageId++;
            return packageId;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+":packageId:" + getPackageId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
