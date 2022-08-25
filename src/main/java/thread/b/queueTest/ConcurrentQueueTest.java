package thread.b.queueTest;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueueTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
    }
}
