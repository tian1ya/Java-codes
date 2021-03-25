package thread.b;

        import java.util.concurrent.locks.LockSupport;

public class ParkUnpark {

    /*
        LockSupport.park() 暂停当前线程
        LockSupport.unpark() 恢复某个线程的运行

     */
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("start,,,");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("park...");

            LockSupport.park(); // 对应的状态还是 WAIT 状态

            System.out.println("subThread resume");
        });

        t.start();
        System.out.println("主线程中将子线程重新启动");
        LockSupport.unpark(t);
        /*
            既可以在 park 之前调用，也可以在 park 之后调用
            在 park 之前调用了 unpark，那么在线程未来 unpark 线程

            unpark 可以很精确的唤醒某个线程，而 notify/notifyall 并不能

            wait notify/notifyall 必须配合 object monitor 一起使用，
            而 park unpark 不需要
         */
    }
}
