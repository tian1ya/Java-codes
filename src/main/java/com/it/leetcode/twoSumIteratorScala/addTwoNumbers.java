package com.it.leetcode.twoSumIteratorScala;

/*

    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    示例：
       输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
       输出：7 -> 0 -> 8
       原因：342 + 465 = 807

    Node 可以按照先进后出的方式连接
 */


import java.util.List;

public class addTwoNumbers {
    public static void main(String[] args) {
        com.it.leetcode.twoSumIteratorScala.Nodes nodes1 = new com.it.leetcode.twoSumIteratorScala.Nodes();
        nodes1.add(3).add(4).add(2);

        com.it.leetcode.twoSumIteratorScala.Nodes nodes2 = new com.it.leetcode.twoSumIteratorScala.Nodes();
        nodes2.add(4).add(6).add(5);

        List sum = com.it.leetcode.twoSumIteratorScala.Nodes.sum(nodes1, nodes2);
        String s = Nodes.printSum(sum);
        System.out.println(s);
    }
}
