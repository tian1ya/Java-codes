package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.queue.ArrayQueue;
import org.junit.Assert;
import org.junit.Test;

public class QueueTest {

    ArrayQueue<Double> queue = new ArrayQueue<>();

    @Test
    public void should_insert_elements() {
        for (int i = 0; i < 5; i++) {
            queue.enqueue((double) i);
        }

        Assert.assertEquals(queue.toString(),
                "Queue: front [0.0, 1.0, 2.0, 3.0, 4.0] tail");

        for (int i = 0; i < 5; i++) {
            System.out.print(queue.dequeue() + ", ");
        }
    }

}
