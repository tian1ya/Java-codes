package dateStructure.dsPlay.dsa.algrithem.AboutList;

import dateStructure.chapt02.linkedList.Node;

public class MergeTwoLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;


        ListNode resNode = new ListNode();
        ListNode dummyHead = resNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummyHead.next = l1;
                dummyHead = dummyHead.next;
                l1 = l1.next;
            } else {
                dummyHead.next = l2;
                dummyHead = dummyHead.next;
                l2 = l2.next;
            }
        }

        if (l2 != null)
            dummyHead.next = l2;

        if (l1 != null)
            dummyHead.next = l1;
        return resNode.next;
    }

    public static ListNode mergeTwoListsCur(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;


        if (l1.val < l2.val) {
            l1.next = mergeTwoListsCur(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsCur(l1, l2.next);
            return l2;
        }
    }


        public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        ListNode node11 = new ListNode(1);
        ListNode node33 = new ListNode(3);
        ListNode node44 = new ListNode(4);
        ListNode node45 = new ListNode(6);
        ListNode node46 = new ListNode(7);


        node11.next = node33;
        node33.next = node44;
//        node44.next = node45;
//        node45.next = node46;

        ListNode listNode111 = new ListNode(1);
        ListNode listNode112 = new ListNode(2);

//        ListNode x = mergeTwoLists(listNode111, listNode112);
//        System.out.println(x);
        ListNode x1 = mergeTwoListsCur(listNode112, listNode111);
//        ListNode x = mergeTwoLists(node1, node11);
        System.out.println(x1);
    }
}
