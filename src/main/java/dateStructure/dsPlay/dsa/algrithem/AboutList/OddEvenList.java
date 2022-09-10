package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Objects;

public class OddEvenList {
    public static ListNode oddEvenList(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) return head;

        ListNode oddNode = new ListNode();
        ListNode oddHead = oddNode;

        ListNode eventNode = new ListNode();
        ListNode eventHead = eventNode;

        ListNode tempHead = head;

        int index = 1;
        while (Objects.nonNull(tempHead)) {
            if (index % 2 == 0) {
                eventNode.next = tempHead;
                eventNode = eventNode.next;
            } else {
                oddNode.next = tempHead;
                oddNode = oddNode.next;
            }
            index++;
            tempHead = tempHead.next;
        }
        oddNode.next = null;
        eventNode.next = null;

        oddNode.next = eventHead.next;
        return oddHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node11 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node22 = new ListNode(5);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(7);
        node1.next = node11;
//        node11.next = node2;
//        node2.next = node22;
//        node22.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        System.out.println(node1);
        System.out.println(oddEvenList(node1));
    }
}
