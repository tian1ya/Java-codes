package thread.b;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockTest {

    private static ReentrantLock rooms = new ReentrantLock();
    static Condition waitCigaretteSet = rooms.newCondition();
    static Condition waitTakeoutSet = rooms.newCondition();
    /*
        每个条件变量都会内部维护一个队列，存放调用 await 存放下得线程
     */


    private static boolean hasCig = false;
    private static boolean ahsTakeout = false;

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 5; i++) {
//            int ii = i;
//            new Thread(() -> {
//                try {
//                    rooms.lock();
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                } finally {
//                    rooms.unlock();
//                }
//            }, "Thread: " + ii).start();
//        }

//        System.out.println("aaa");

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            rooms.lock();
            try {
                System.out.println(name + " 有烟么？" + hasCig);
                while (!hasCig) {
                    System.out.println(name + " 没有烟，先歇会");
                    try {
                        waitCigaretteSet.await();
                        /*
                            挂起当前线程，到条件 waitCigaretteSet，线程阻塞释放 lock 锁
                            其他线程在调  waitCigaretteSet.singal 得时候，线程从条件变量队列中弹出，重新竞争锁
                         */
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 这里挑出 while 可能是给了烟，并将 waitCigaretteSet 中的线程唤醒
                // 也可能是调了 这个线程的 interrupt 方法导致出了锁终止
                System.out.println(name + " 有烟么？" + hasCig);
                if (hasCig) {
                    System.out.println(name + " 可以开始干活了");
                } else {
                    System.out.println(name + " 没法干活");
                }
            } finally {
                rooms.unlock();
            }
        }, "南哥").start();

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            rooms.lock();
            try {
                System.out.println(name + " 外卖到了么" + ahsTakeout);
                while (!ahsTakeout) {
                    System.out.println(name + " 没有外卖，先休息");
                    try {
                        waitTakeoutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(name + " 外卖到了么" + ahsTakeout);
                if (ahsTakeout) {
                    System.out.println(name + " 外卖到了");
                } else {
                    System.out.println(name + " 外卖到了");
                }

            }finally {
                rooms.unlock();
            }
        }, "小女").start();


        new Thread(() -> {
            String name = Thread.currentThread().getName();
            rooms.lock();
            try {
                System.out.println(name + " 送外卖的");
                ahsTakeout = true;
                waitTakeoutSet.signalAll();
            } finally {
                rooms.unlock();
            }
        }, "送外卖的").start();

        new Thread(() -> {
            String name = Thread.currentThread().getName();
            rooms.lock();
            try {
                System.out.println(name + " 送烟的");
                hasCig = true;
                waitCigaretteSet.signalAll();
            } finally {
                rooms.unlock();
            }
        }, "送烟的").start();
    }
}
