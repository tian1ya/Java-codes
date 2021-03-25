package thread.b;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    多个读锁之间是不需要互斥的(读操作不会改变数据，如果上了锁，反而会影响效率)，
    写锁和写锁之间需要互斥，也就是说，如果只是读数据，就可以多个线程同时读，
    但是如果你要写数据，就必须互斥，

    三个线程读数据，三个线程写数据
 */
public class ReentrantReadWriterLockTest {
    private Object data = null;

    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void get() {
        // 读上锁
        rwLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 准备数据");
            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName() + " 读出来的数据 " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }


    public void put(Object data) {
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 准备写数据");

            Thread.sleep(2000);
            this.data = data;

            System.out.println(Thread.currentThread().getName() + " 写入数据: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }


    public static void main(String[] args) {
        final ReentrantReadWriterLockTest readWrite = new ReentrantReadWriterLockTest();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                readWrite.get();
            }).start();


            new Thread(() -> {
                readWrite.put(new Random().nextInt(8));
            }).start();
        }
    }
}
