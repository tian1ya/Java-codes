package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Objects;

public class Partition {

    public static ListNode partition(ListNode head, int x) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) return head;

        ListNode dummyHead = head;
        ListNode result = new ListNode();
        ListNode cur = result;

        while (Objects.nonNull(dummyHead)) {
            if (dummyHead.val < x) {
                cur.next = new ListNode(dummyHead.val);
                cur = cur.next;
            }
            dummyHead = dummyHead.next;
        }

        dummyHead = head;
        while (Objects.nonNull(dummyHead)) {
            if (dummyHead.val >= x) {
                cur.next = new ListNode(dummyHead.val);
                cur = cur.next;
            }
            dummyHead = dummyHead.next;
        }

        return result.next;
    }

    public static ListNode partitionV2(ListNode head, int x) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) return head;
        ListNode dummyHead = head;
        ListNode smallList = new ListNode();
        ListNode smallHead = smallList;

        ListNode bigList = new ListNode();
        ListNode bigHead = bigList;

        while (Objects.nonNull(dummyHead)) {

            if (dummyHead.val < x) {
                smallList.next = dummyHead;
                smallList = smallList.next;
            } else {
                bigList.next = dummyHead;
                bigList = bigList.next;
            }
            dummyHead = dummyHead.next;
        }

        bigList.next = null;
        smallList.next = bigHead.next;

        return smallHead.next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node22 = new ListNode(5);
        ListNode node3 = new ListNode(2);
//        ListNode node4 = new ListNode(6);
//        ListNode node5 = new ListNode(6);
        node1.next = node11;
        node11.next = node2;
        node2.next = node22;
        node22.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        System.out.println(node1);
        System.out.println(partitionV2(node1, 3));
    }
}
