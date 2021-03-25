package thread.b;


public class WaitAndNotify {
   static final Object lock =  new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1 执行。。。");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 执行其他代码");
            }
        }, "t1");

        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 执行。。。");
                try {
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 执行其他代码");
            }
        }, "t2");

        t2.start();

        Thread.sleep(2000);

        System.out.println("main 获得锁。唤醒其他线程");
        synchronized (lock) {
            lock.notify();
//            lock.notifyAll();
        }
    }
}
