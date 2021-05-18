package thread.b.chapt06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TestAqs {

    // 自定义一个不可重入锁
    static class MyLock implements Lock {
        // lock 中有比较多的方法
        // 而 aqs 已经帮助实现了大部分的方法
        // 值需要写几个重要的方式

        // 独占锁
        /*
            需要写如下的几个方法
            tryAcquire
            tryRelease
            isHeldExclusively
            newCondition
         */
        class MySync extends AbstractQueuedLongSynchronizer {
            @Override
            protected boolean tryAcquire(long arg) {
                if (compareAndSetState(0,1)) {
                    // 状态变为1，拿到了锁
                    // 然后设置当前线程为锁的拥有者
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }

            @Override
            protected boolean tryRelease(long arg) {
                setState(0);
                // 状态变为1，拿到了锁
                // 然后设置当前线程为锁的拥有者
                setExclusiveOwnerThread(null);
                return true;
            }

            @Override
            protected boolean isHeldExclusively() {
                return getState() == 1; // 表示持有所，且上面已经表示了创建的是独占锁的方式
            }

            public Condition newCondition() {
                return new ConditionObject();
            }
        }

        private MySync sync = new MySync();

        @Override // 加锁不成功，进入等待队列
        public void lock() {
            sync.acquire(1);
        }

        @Override // 可打断加锁
        public void lockInterruptibly() throws InterruptedException {
            sync.acquireInterruptibly(1);
        }

        @Override // 一次尝试加锁
        public boolean tryLock() {
            return sync.tryAcquire(1);
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return sync.tryAcquireNanos(1, unit.toNanos(time));
        }

        @Override
        public void unlock() {
            sync.release(1);
        }

        @Override
        public Condition newCondition() {
            return sync.newCondition();
        }
    }


    public static void main(String[] args) {
        MyLock myLock = new MyLock();

        new Thread(() -> {

            long start = 0;
            long end = 0;

            myLock.lock();
            System.out.println("1加锁成功");
            start = System.currentTimeMillis();
//            myLock.lock();
//            System.out.println("加锁成功");
            // 被阻塞主，代码跑不到这里

            try {
                System.out.println("11 locking.....");
                try {
                    System.out.println("休息 5s.. ");
                    Thread.sleep(5000);
                    end = System.currentTimeMillis();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                System.out.println("1 释放锁, 花费时间");
                System.out.println(end - start);
                myLock.unlock();
            }
        }).start();

        new Thread(() -> {
            System.out.println("2 加锁成功");
            myLock.lock();

            try {
                System.out.println("22 locking.....");
            } finally {
                System.out.println("2 释放锁");

                myLock.unlock();
            }
        }).start();
    }


}
