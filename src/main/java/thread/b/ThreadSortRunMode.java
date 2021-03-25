package thread.b;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSortRunMode {
    private static boolean t2Runned = false;

    public static void main(String[] args) {
//        me2();

//        WaitNotify notify = new WaitNotify(1, 5);
//        new Thread(() -> notify.print("a", 1, 2)).start();
//        new Thread(() -> notify.print("b", 2, 3)).start();
//        new Thread(() -> notify.print("c", 3, 1)).start();

        AwaitSingnal awaitSingnal = new AwaitSingnal(5);
        Condition a = awaitSingnal.newCondition();
        Condition b = awaitSingnal.newCondition();
        Condition c = awaitSingnal.newCondition();

        new Thread(() -> awaitSingnal.print("a", a, b)).start();
        new Thread(() -> awaitSingnal.print("b", b, c)).start();
        new Thread(() -> awaitSingnal.print("c", c, a)).start();

        // 上面三分 Condition 运行起来之后都先进入自己的等待变量中了
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 需要有一个线程先将一个等待变量唤醒
        awaitSingnal.lock();
        try {
            System.out.println("开始");
            a.signal();
        } finally {
            awaitSingnal.unlock();
        }

    }

    static class AwaitSingnal extends ReentrantLock {
        private int loopNumber;

        public AwaitSingnal(int loopNumber) {
            this.loopNumber = loopNumber;
        }

        public void print(String str, Condition condition, Condition nextCondition) {
            for (int i = 0; i < this.loopNumber; i++) {
                lock();
                try {
                    condition.await();
                    System.out.print(str);
                    nextCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    unlock();
                }
            }
        }
    }

    public static void me() {
        Object lock = new Object();

        Thread t1_ = new Thread(() -> {
            synchronized (lock) {
                while (!t2Runned) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1 ");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 ");
                t2Runned = true;
                lock.notifyAll();
            }
        });

        t1_.start();
        t2.start();
    }

    public static void me2() {
        Thread thread = new Thread(() -> {
            LockSupport.park();
            System.out.println("11");
        });


        Thread thread1 = new Thread(() -> {
            System.out.println("22");
            LockSupport.unpark(thread);
        });

        thread.start();
        thread1.start();
    }

    static class WaitNotify {
        /*
            线程    输出内容  等待标记(表示那个线程可以执行)  下一个标记
              1        a       1                            2
              2        b       2                            3
              3        c       3                            1
         */
        private int runFlag;
        private int loopNumber;

        public WaitNotify(int runFlag, int loopNumber) {
            this.runFlag = runFlag;
            this.loopNumber = loopNumber;
        }

        // 打印方法
        public void print(String str, int waitFlag, int nextFlag) {
            for (int i = 0; i < loopNumber; i++) {
                synchronized (this) {
                    while (runFlag != waitFlag) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(str);
                    runFlag = nextFlag;
                    this.notifyAll();
                }
            }
        }
    }

    public static void m3() {

    }
}
