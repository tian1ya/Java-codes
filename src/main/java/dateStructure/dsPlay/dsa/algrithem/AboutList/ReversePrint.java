package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.ArrayList;

public class ReversePrint {
    public static int[] reversePrint(ListNode head) {
        ListNode dummyHead = head;
        ArrayList<Integer> list = new ArrayList<>();

        while (dummyHead != null) {
            list.add(dummyHead.val);
            dummyHead = dummyHead.next;
        }
        int eleSize = list.size();
        int[] ints = new int[eleSize];

        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(list.size() - i - 1);
        }

        return ints;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        reversePrint(node1);

    }
}
