package dateStructure.dsPlay.dsa.algrithem.AboutList;

public class MergeInBetween {
    static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode list1DeleteHead = null, list1DeleteTail = null;

        ListNode dummyHead = list1;

        int list1Index = 0;
        while (dummyHead != null) {
            if (list1Index == a-1)
                list1DeleteHead = dummyHead;

            if (list1Index == b)
                list1DeleteTail = dummyHead.next;
            list1Index++;
            dummyHead = dummyHead.next;
        }

        list1DeleteHead.next = list2;
        dummyHead = list2;
        while (dummyHead.next != null) {
            dummyHead = dummyHead.next;
        }
        dummyHead.next = list1DeleteTail;

        return list1;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode node11 = new ListNode(10000001);
        ListNode node22 = new ListNode(20000001);
        ListNode node33 = new ListNode(30000001);

        node11.next = node22;
        node22.next = node33;

        ListNode listNode = mergeInBetween(node0, 3, 4, node22);
        System.out.println("s");
    }
}
