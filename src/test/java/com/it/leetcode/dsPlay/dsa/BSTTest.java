package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.tree.BST;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class BSTTest {
    @Test
    public void should_insert_ele() {
        BST<Integer> bst = new BST<>();
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(0);

        Assert.assertEquals(bst.getSize(), 4);
    }

    @Test
    public void should_conotain_ele() {
        BST<Integer> bst = new BST<>();
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(0);

        Assert.assertEquals(bst.contains(1), true);
        Assert.assertEquals(bst.contains(3), true);
        Assert.assertEquals(bst.contains(30), false);
    }

    @Test
    public void pre_order_traversal() {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(4);
        bst.add(2);

        String x = bst.preOrder().toString();
        System.out.println(x);
        Assert.assertTrue(x.contains("capacity=10, size=6"));
        Assert.assertTrue(x.contains("[5, 3, 2, 4, 6, 8, ]"));
    }

    @Test
    public void in_order_traversal() {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(4);
        bst.add(2);

        String x = bst.inOrder().toString();
        System.out.println(x);
        Assert.assertTrue(x.contains("capacity=10, size=6"));
        Assert.assertTrue(x.contains("[3, 2, 4, 5, 6, 8, ]"));
    }

    @Test
    public void after_order_traversal() {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(4);
        bst.add(2);

        String x = bst.postOrder().toString();
        System.out.println(x);
        Assert.assertTrue(x.contains("capacity=10, size=6"));
        Assert.assertTrue(x.contains("[3, 2, 4, 6, 8, 5, ]"));
    }

    @Test
    public void level_order_traversal() {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(4);
        bst.add(2);

        String x = bst.levelOrder().toString();
        Assert.assertTrue(x.contains("capacity=10, size=6"));
        Assert.assertTrue(x.contains("[5, 3, 6, 2, 4, 8, ]"));
    }

    @Test
    public void get_min_test() {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(4);
        bst.add(2);

        Integer minVal = bst.getMin();
        Integer maxVal = bst.getMax();

        Assert.assertEquals(minVal.toString(), String.valueOf(2));
        Assert.assertEquals(maxVal.toString(), String.valueOf(8));
    }

    @Test
    public void remove_min_test() {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(4);
        bst.add(2);


        Assert.assertTrue(bst.levelOrder().toString().contains("[5, 3, 6, 2, 4, 8, ]"));

        Integer minVal = bst.removeMin();
        Integer maxVal = bst.removeMax();

        Assert.assertTrue(bst.levelOrder().toString().contains("[5, 3, 6, 4, ]"));

        Assert.assertEquals(minVal.toString(), String.valueOf(2));
        Assert.assertEquals(maxVal.toString(), String.valueOf(8));
    }

    @Test
    public void remove_many_min_values() {
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 20; i++) {
            bst.add(new Random().nextInt(100));
        }

        for (int i = 0; i < 20; i++) {
            System.out.print(bst.removeMin() + " => ");
        };
    }


    @Test
    public void remove_test() {
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(6);
        bst.add(8);
        bst.add(4);
        bst.add(2);


        Assert.assertTrue(bst.levelOrder().toString().contains("[5, 3, 6, 2, 4, 8, ]"));

        bst.remove(3);
        Assert.assertTrue(bst.levelOrder().toString().contains("[5, 4, 6, 2, 8, ]"));

        bst.remove(2);
        Assert.assertTrue(bst.levelOrder().toString().contains("[5, 4, 6, 8, ]"));
    }

}
