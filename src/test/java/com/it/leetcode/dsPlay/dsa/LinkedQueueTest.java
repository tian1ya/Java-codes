package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.queue.LinkedListQueue;
import dateStructure.dsPlay.dsa.queue.LoopQueue;
import org.junit.Test;

public class LinkedQueueTest {

    LinkedListQueue<Double> queue = new LinkedListQueue<>();

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
