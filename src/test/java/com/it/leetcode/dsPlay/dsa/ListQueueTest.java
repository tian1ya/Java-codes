package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.queue.ArrayQueue;
import dateStructure.dsPlay.dsa.queue.ListQueue;
import org.junit.Assert;
import org.junit.Test;

public class ListQueueTest {

    ListQueue<Double> queue = new ListQueue<>();

    @Test
    public void should_insert_elements() {

        for (int i = 0; i < 5; i++) {
            queue.enqueue((double) i);
            System.out.println(queue);
        }

        System.out.println(queue.getFront());
        System.out.println(queue.dequeue());

        queue.enqueue((double) 5);


        System.out.println(queue.getSize());

        System.out.println(queue);
    }

}
