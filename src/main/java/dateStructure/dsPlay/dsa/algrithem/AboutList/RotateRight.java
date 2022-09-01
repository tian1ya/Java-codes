package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Objects;

public class RotateRight {

    public static ListNode rotateRight(ListNode head, int k) {
        if (k <= 0 || Objects.isNull(head)) return head;
        int nodeCount = 0;
        ListNode tempHead = head;
        while (Objects.nonNull(tempHead)) {
            tempHead = tempHead.next;
            nodeCount++;
        }

        k = k % nodeCount;
        if (k == 0) return head;

        ListNode end = head, start;

        for (int i = 1; i < nodeCount-k; i++) {
            end = end.next;
        }

        start = end.next;
        end.next = null;

        ListNode dumyStart = start;

        while (Objects.nonNull(dumyStart.next)) dumyStart = dumyStart.next;

        dumyStart.next = head;

        return start;
    }

    public static ListNode rotateRightV2(ListNode head, int k) {
        if (k <= 0 || Objects.isNull(head)) return head;
        int nodeCount = 0;
        ListNode tempHead = head;
        while (Objects.nonNull(tempHead)) {
            tempHead = tempHead.next;
            nodeCount++;
        }

        k = nodeCount - k % nodeCount;
        if (k == nodeCount) return head;

        ListNode end = head, start;

        for (int i = 1; i < nodeCount-k; i++) {
            end = end.next;
        }

        start = end.next;
        end.next = null;

        return start;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node22 = new ListNode(4);
        ListNode node3 = new ListNode(5);

        node1.next = node11;
        node11.next = node2;
        node2.next = node22;
        node22.next = node3;

        System.out.println(node1);
//        System.out.println(rotateRight(node1, 5));
        System.out.println(rotateRightV2(node1, 2));
    }
}
