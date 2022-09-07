package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Objects;

public class HasCycle {

    public static boolean hasCycle(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) return false;

        ListNode p1 = head;
        ListNode p2 = head.next;

        while (p1 != p2) {

            if (Objects.isNull(p1) || Objects.isNull(p2) || Objects.isNull(p2.next)) return false;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);

        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

//        node5.next = node2;

        System.out.println(hasCycle(node1));
    }
}
