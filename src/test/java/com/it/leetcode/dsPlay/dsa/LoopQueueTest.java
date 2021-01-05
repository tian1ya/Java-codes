package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.queue.LoopQueue;
import org.junit.Test;

public class LoopQueueTest {

    LoopQueue<Double> queue = new LoopQueue<>(2);

    @Test
    public void should_insert_elements() {
        for (int i = 0; i < 5; i++) {
            queue.enqueue((double) i);
            System.out.println(queue);
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.dequeue());
            System.out.println(queue);
        }
    }

}
