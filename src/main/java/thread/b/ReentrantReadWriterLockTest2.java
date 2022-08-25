package thread.b;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    多个读锁之间是不需要互斥的(读操作不会改变数据，如果上了锁，反而会影响效率)，
    写锁和写锁之间需要互斥，也就是说，如果只是读数据，就可以多个线程同时读，
    但是如果你要写数据，就必须互斥，

    三个线程读数据，三个线程写数据

    读锁不支持条件变量，写锁支持条件变量

    不支持锁升级，同一个线程，读锁的情况下，获取写锁，会导致写锁永久等待
    支持锁降级，同一个线程，在持有写锁的情况下，去获取读锁
 */

public class ReentrantReadWriterLockTest2 {
    private Object data;

    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = rw.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = rw.writeLock();


    public Object read() {
        System.out.println("获取读锁" + new Date());

        readLock.lock();
        try {
            System.out.println("读取" + new Date());
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放读锁" + new Date());
            readLock.unlock();
        }
        return data;
    }

    public void write() {
        System.out.println("获取写锁" + new Date());
        writeLock.lock();
        try {
            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("写入" + new Date());
        } finally {
            System.out.println("释放写锁" + new Date());
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        ReentrantReadWriterLockTest2 container = new ReentrantReadWriterLockTest2();

//        testReadCon(container);
//        testReadWriteCon(container);
        testWriteWriteCon(container);
    }

    private static void testReadCon(ReentrantReadWriterLockTest2 container) {
        // 根据打印出的日志，多此read 之间并没有暂停2s，所以是并发的，没有阻塞
        new Thread(() -> {
            container.read();
        }, "t1").start();

        new Thread(() -> {
            container.read();
        }, "t2").start();
    }

    private static void testReadWriteCon(ReentrantReadWriterLockTest2 container) {
        // 根据打印出的时间，read 和 write 之间由线程阻塞
        new Thread(() -> {
            container.read();
        }, "t1").start();

        new Thread(() -> {
            container.write();
        }, "t2").start();
    }

    private static void testWriteWriteCon(ReentrantReadWriterLockTest2 container) {
        // 根据打印出的时间，write 和 write 之间由线程阻塞, 第二个写，需要在第一个写锁释放才能写
        new Thread(() -> {
            container.write();
        }, "t1").start();

        new Thread(() -> {
            container.write();
        }, "t2").start();
    }
}
