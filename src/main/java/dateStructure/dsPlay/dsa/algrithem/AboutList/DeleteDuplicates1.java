package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Objects;

public class DeleteDuplicates1 {


    public static ListNode deleteDuplicates(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) return head;

        ListNode dummyHead = head;
        ListNode cur = dummyHead;

        while (Objects.nonNull(cur) && Objects.nonNull(cur.next)) {
            int val = cur.val;
            if (val == cur.next.val) {
                while (Objects.nonNull(cur.next) && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummyHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node22 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(6);
        node1.next = node11;
        node11.next = node2;
        node2.next = node22;
        node22.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(node1);
        System.out.println(deleteDuplicates(node1));
    }
}
