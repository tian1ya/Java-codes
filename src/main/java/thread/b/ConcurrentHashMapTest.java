package thread.b;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        final ConcurrentHashMap<String, Integer> count = new ConcurrentHashMap<>();

//        final CountDownLatch endLatch = new CountDownLatch(2);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Integer a = count.get("a");
                    if (a == null) {
                        count.put("a", 1);
                    } else {
                        count.put("a", a + 1);
                    }
                }

//                endLatch.countDown();
            }
        };

        Thread thread = new Thread(task);
        Thread thread1 = new Thread(task);

        thread.start();
        thread1.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        try {
//            endLatch.await();
//            System.out.println(count);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        System.out.println(count.get("a"));
    }
}
