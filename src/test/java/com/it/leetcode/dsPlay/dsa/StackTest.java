package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.stack.ArrayStack;
import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    ArrayStack<Double> stack = new ArrayStack<>();

    @Test
    public void should_insert_elements() {
        for (int i = 0; i < 5; i++) {
            stack.push((double) i);
        }

        Assert.assertEquals(stack.toString(),
                "Stack: [0.0, 1.0, 2.0, 3.0, 4.0] top");

        for (int i = 0; i < 5; i++) {
            System.out.print(stack.pop() + ", ");
        }
    }

}
