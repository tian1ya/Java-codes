package thread.beauty.chapt01;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {

        System.out.println("Begin park!");

        Thread thread = new Thread(() -> {
            System.out.println("Child Thread park self");
            LockSupport.park();
            System.out.println("Child Thread park self end");
        });

        thread.start();

        System.out.println("main thead unpark child thread");

        LockSupport.unpark(thread);
    }
}
