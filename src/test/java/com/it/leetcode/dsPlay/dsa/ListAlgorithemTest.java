package com.it.leetcode.dsPlay.dsa;

import dateStructure.dsPlay.dsa.algrithem.DeleteAllVal;
import dateStructure.dsPlay.dsa.algrithem.ListNode;
import org.junit.Assert;
import org.junit.Test;

public class ListAlgorithemTest {
    DeleteAllVal allVal = new DeleteAllVal();

    @Test
    public void should_delete_middle_ele() {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;

        ListNode node3 = new ListNode(3);
        node2.next = node3;

        ListNode node4 = new ListNode(4);
        node3.next = node4;

        ListNode node5 = new ListNode(4);
        node4.next = node5;

        ListNode middle2Nodes = node1;
        ListNode node = allVal.removeElements(middle2Nodes, 4);

        Assert.assertEquals(node.val, 1);
        Assert.assertEquals(node.next.val, 2);
        Assert.assertEquals(node.next.next.val, 3);
        Assert.assertEquals(node.next.next.next, null);
    }

    @Test
    public void should_delete_tail_ele() {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;

        ListNode node3 = new ListNode(4);
        node2.next = node3;

        ListNode node4 = new ListNode(4);
        node3.next = node4;

        ListNode node5 = new ListNode(3);
        node4.next = node5;

        ListNode middle2Nodes = node1;
        ListNode node = allVal.removeElements(middle2Nodes, 4);

        Assert.assertEquals(node.val, 1);
        Assert.assertEquals(node.next.val, 2);
        Assert.assertEquals(node.next.next.val, 3);
        Assert.assertEquals(node.next.next.next, null);
    }

    @Test
    public void should_delete_head_ele() {

        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(2);
        node1.next = node2;

        ListNode node3 = new ListNode(1);
        node2.next = node3;

        ListNode node4 = new ListNode(4);
        node3.next = node4;

        ListNode node5 = new ListNode(3);
        node4.next = node5;

        ListNode middle2Nodes = node1;
        ListNode node = allVal.removeElements(middle2Nodes, 2);

        Assert.assertEquals(node.val, 1);
        Assert.assertEquals(node.next.val, 4);
        Assert.assertEquals(node.next.next.val, 3);
        Assert.assertEquals(node.next.next.next, null);
    }

    @Test
    public void should_ok_when_given_1_ele() {

        ListNode node1 = new ListNode(1);
        ListNode node = allVal.removeElements(node1, 1);

        Assert.assertEquals(node, null);
    }


    @Test
    public void should_delete_many_ele() {

        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(2);
        node1.next = node2;

        ListNode node3 = new ListNode(1);
        node2.next = node3;

        ListNode node4 = new ListNode(4);
        node3.next = node4;

        ListNode node11 = new ListNode(2);
        node4.next = node11;
        ListNode node22 = new ListNode(2);
        node11.next = node22;



        ListNode node5 = new ListNode(3);
        node22.next = node5;

        ListNode middle2Nodes = node1;
        ListNode node = allVal.removeElements(middle2Nodes, 2);

        Assert.assertEquals(node.val, 1);
        Assert.assertEquals(node.next.val, 4);
        Assert.assertEquals(node.next.next.next, null);
    }

}
