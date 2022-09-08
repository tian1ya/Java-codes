package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.HashSet;
import java.util.Objects;

public class GetIntersectionNode {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (Objects.isNull(headA) || Objects.isNull(headB)) return null;

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            p1 = Objects.isNull(p1) ? headB : p1.next;
            p2 = Objects.isNull(p2) ? headA : p2.next;
        }

        return p1;
    }



        public static void main(String[] args) {
        ListNode listNode4 = new ListNode(5);
        ListNode listNode3 = new ListNode(4, listNode4);
        ListNode listNode2 = new ListNode(8, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        ListNode listNode = new ListNode(4, listNode1);

        ListNode listNode13 = new ListNode(1, listNode2);
        ListNode listNode12 = new ListNode(6, listNode13);
        ListNode listNode11 = new ListNode(5, listNode12);

        System.out.println(getIntersectionNode(listNode, listNode11));
    }
}
