package dateStructure.dsPlay.dsa.algrithem.AboutList;

public class DeleteDuplicates {
    // 83
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    public static ListNode deleteDuplicatesV2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyHead = head;
        while (dummyHead != null && dummyHead.next != null) {
            if (dummyHead.val == dummyHead.next.val) {
                dummyHead.next = dummyHead.next.next;
            } else
                dummyHead = dummyHead.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node22 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(4);
        node1.next = node11;
        node11.next = node2;
        node2.next = node22;
        node22.next = node3;
        node3.next = node4;
        node4.next = node5;

//        System.out.println(deleteDuplicates(node1));
        System.out.println(deleteDuplicatesV2(node1));
    }
}
