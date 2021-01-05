package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.tree.MaxHeap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class HeapTest {

    @Test
    public void should() {
        int n = 1000;
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = heap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i-1] < arr[i]) {
                throw new RuntimeException("Error");
            }
        }
        System.out.println("OK");
    }

    @Test
    public void should_replace_heap_top() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            heap.add(random.nextInt(100));
        }
        heap.add(101);

        Integer replace = heap.replace(201);

        Assert.assertEquals(replace.toString(), "101");
        Assert.assertEquals(heap.findMax().toString(), "201");
    }

    @Test
    public void should_heapify() {
        Random random = new Random();
        Integer[] ints = new Integer[10];

        for (int i = 0; i < 9; i++) {
            ints[i] = random.nextInt(100);
        }
        ints[9] = 101;



        MaxHeap<Integer> heap = new MaxHeap<>(ints);
        Assert.assertEquals(heap.getSize(), 10);
        Assert.assertEquals(heap.findMax().toString(), "101");

        int[] arr2 = new int[10];

        for (int i = 0; i < 10; i++) {
            arr2[i] = heap.extractMax();
        }

        for (int i = 1; i < 10; i++) {
            if (arr2[i-1] < arr2[i]) {
                throw new RuntimeException("Error");
            }
        }
        System.out.println("OK");
    }
}
