package thread.chapt1.chapt4;

import java.util.concurrent.TimeUnit;

/*
    wait 和 notify 并不是Thread 特有的方法，在Object 中每一个类都有这个方法
 */
public class EventClient {

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();

        new Thread(() -> {
            for (;;){
                // 提交任务几乎是没有任何延迟的，线程启动起了，eventQueue 很快就会满
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();


        new Thread(() -> {
            for (;;){
                eventQueue.take();

                try {
                    // 获取一个Event，的周期是 5s
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();


    }
}
