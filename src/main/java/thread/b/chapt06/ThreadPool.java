package thread.b.chapt06;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {

    static class BlockingQueue<T> {
        private Deque<T> queue = new ArrayDeque<>();

        private ReentrantLock lock = new ReentrantLock();

        private Condition productorFullWaitSet = lock.newCondition();
        private Condition customerEmptyWaitSet = lock.newCondition();

        private int capacity;

        public BlockingQueue(int capacity) {
            this.capacity = capacity;
        }

        public T poll(long timeout, TimeUnit unit) {
            lock.lock();
            try {
                // 转化为 纳秒
                long nanos = unit.toNanos(timeout);
                if (nanos < 0) return null;
                while (queue.isEmpty()) {
                    try {
                        // 返回的是剩余的等待时间
                        //
                        nanos = customerEmptyWaitSet.awaitNanos(nanos);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                T t = queue.removeFirst();
                productorFullWaitSet.signalAll();
                return t;
            } finally {
                lock.unlock();
            }
        }

        public T take() {
            lock.lock();
            try {

                while (queue.isEmpty()) {
                    try {
                        customerEmptyWaitSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                T t = queue.removeFirst();
                productorFullWaitSet.signalAll();
                return t;
            } finally {
                lock.unlock();
            }
        }

        public void put(T ele) {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    try {
                        productorFullWaitSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                queue.addLast(ele);
                customerEmptyWaitSet.signalAll();
            } finally {
                lock.unlock();
            }

        }

        public int size() {
            lock.unlock();
            try {
                return queue.size();
            } finally {
                lock.unlock();
            }
        }

    }

    static class ThreadsPool {

        class Worker extends Thread {
            private Runnable worker;

            public Worker(Runnable worker) {
                this.worker = worker;
            }

            @Override
            public void run() {
//                while (worker != null || (worker = taskQueue.take()) != null) {
                while (worker != null || (worker = taskQueue.poll(timeout, unit)) != null) {
                    try {
                        worker.run();
                    }  catch (Exception e) {

                    } finally {
                        worker = null;
                    }
                }

                // 没大懂这里逻辑
                synchronized (workers) {
                    workers.remove(this);
                }
            }
        }

        private BlockingQueue<Runnable> taskQueue;

        private HashSet<Worker> workers = new HashSet();

        // 核心线程数
        private int coreSize;

        // 获取任务的超时时间
        private long timeout;

        private TimeUnit unit;

        public ThreadsPool(int coreSize, long timeout, TimeUnit unit, int queueCapacity) {
            this.coreSize = coreSize;
            this.timeout = timeout;
            this.unit = unit;
            this.taskQueue = new BlockingQueue<>(queueCapacity);
        }


        public void execute(Runnable task) {
            // 任务没有超过 coreSize 直接交给 worker 对象执行
            // 如果超过，那么假如任务队列执行
            synchronized (workers) {
                if (workers.size() < coreSize) {
                    Worker worker = new Worker(task);
                    workers.add(worker);
                    worker.start();
                    System.out.println("run " + worker);
                } else {
                    System.out.println("put " + task);
                    taskQueue.put(task);
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadsPool pool = new ThreadsPool(2, 1000, TimeUnit.MILLISECONDS, 10);
        for (int i = 0; i < 15; i++) {
            int iy = i;
            pool.execute(() -> {
                System.out.println("task: " + iy);
            });
        }
    }
}
