package thread.chapt1.pools;

import thread.chapt1.pools.RunnableQueue;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }


    @Override
    public void run() {
        while (running && ! Thread.currentThread().isInterrupted()) {
            Runnable task = null;
            try {
                task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
                break;
            }

        }
    }

    public void stop() {
        this.running = false;
    }



}

