package thread.b;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*

    不支持锁升级，同一个线程，读锁的情况下，获取写锁，会导致写锁永久等待
    支持锁降级，同一个线程，在持有写锁的情况下，去获取读锁
 */

public class ReentrantReadWriterLockTest3 {

    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReentrantReadWriterLockTest3 test3 = new ReentrantReadWriterLockTest3();
        test3.processCachedData();
    }
    void processCachedData() {

        // 加读锁
        rwl.readLock().lock();

        if (!cacheValid) {
            // 缓存失效了
            // Must release read lock before acquiring write lock
            // 读锁释放
            rwl.readLock().unlock();
            // 获取写锁, 获取从写锁 -> 写锁,需要先释放写锁，然后在获取
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid) {
                    data = "hello";
                    cacheValid = true;
                }
                // Downgrade by acquiring read lock before releasing write lock
                // 锁降级，写锁还没释放，获取读锁
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }

        try {
            System.out.println(data);;
        } finally {
            rwl.readLock().unlock();
        }
    }

}
