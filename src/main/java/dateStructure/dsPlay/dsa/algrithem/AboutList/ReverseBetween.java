package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Objects;

public class ReverseBetween {

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (Objects.isNull(head) || left == right) return head;

        ListNode dummyHeadTemp = new ListNode(0, head);
        ListNode dummyHead = dummyHeadTemp;

        int index = 0;
        // remainNode 指向开始排序前一个 node
        ListNode preNode = new ListNode();
        ListNode endNode = new ListNode();
        while (index < right) {

            if (left - 1 == index) {
                preNode = dummyHead;
            }

            if (right - 1 == index) {
                endNode = dummyHead.next;
            }
            dummyHead = dummyHead.next;
            index++;
        }
        ListNode sortBeginNode = preNode.next;
        ListNode remainNodes = endNode.next;

        preNode.next = null;
        endNode.next = null;


        ListNode listNode = reverseList(sortBeginNode);

        preNode.next = listNode;

        sortBeginNode.next = remainNodes;

        return dummyHeadTemp.next;
    }

    static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode reverseBetweenV2(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node22 = new ListNode(4);
        ListNode node3 = new ListNode(5);
//        ListNode node4 = new ListNode(6);
//        ListNode node5 = new ListNode(6);
        node1.next = node11;
        node11.next = node2;
        node2.next = node22;
        node22.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        System.out.println(node1);
        System.out.println(reverseBetweenV2(node1, 2, 4));
//        System.out.println(reverseBetween(node1, 1, 2));
//        System.out.println(reverseList(node1, 1));
    }
}
