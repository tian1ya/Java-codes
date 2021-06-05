package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Arrays;

public class SplitListToParts {
    public static ListNode[] splitListToParts(ListNode root, int k) {
        int nodeLength = 0;
        ListNode dummyHead = root;
        while (dummyHead != null) {
            nodeLength++;
            dummyHead = dummyHead.next;
        }

        int singleEventLength = nodeLength > k ? nodeLength / k : 1;
        int remainder = nodeLength > k ? nodeLength % k : 0;

        dummyHead = root;
        ListNode[] listNodes = new ListNode[k];
        int nodeIndex = 0;
        while (nodeIndex < k) {
            ListNode node = new ListNode();
            ListNode dummyNode = node;
            if (dummyHead != null) {
                for (int j = 0; j < singleEventLength + (nodeIndex < remainder ? 1 : 0); j++) {
                    dummyNode.next = new ListNode(dummyHead.val);
                    dummyHead = dummyHead.next;
                    dummyNode = dummyNode.next;
                }
            }
            listNodes[nodeIndex++] = node.next;
        }
        return listNodes;
    }

    public static ListNode[] splitListToPartsV2(ListNode root, int k) {
        int nodeLength = 0;
        ListNode dummyHead = root;
        while (dummyHead != null) {
            nodeLength++;
            dummyHead = dummyHead.next;
        }

        int singleEventLength = nodeLength > k ? nodeLength / k : 1;
        int remainder = nodeLength > k ? nodeLength % k : 0;

        dummyHead = root;
        ListNode[] listNodes = new ListNode[k];

        for (int i = 0; i < k; i++) {
            ListNode head = dummyHead;
            for (int j = 0; j < singleEventLength + (i < remainder ? 1 : 0)-1; j++) {
                if (dummyHead != null) dummyHead = dummyHead.next;
            }

            if (dummyHead != null) {
                ListNode prev = dummyHead;
                dummyHead = dummyHead.next;
                prev.next = null;
            }
            listNodes[i] = head;
        }
        return listNodes;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        ListNode node9 = new ListNode(9);
        ListNode node10 = new ListNode(10);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = node8;
//        node8.next = node9;
//        node9.next = node10;

        System.out.println(Arrays.toString(splitListToPartsV2(node1, 2)));
    }
}
