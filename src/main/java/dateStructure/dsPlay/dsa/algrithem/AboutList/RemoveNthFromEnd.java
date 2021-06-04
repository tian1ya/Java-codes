package dateStructure.dsPlay.dsa.algrithem.AboutList;

public class RemoveNthFromEnd {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        if (head.next == null && n == 1) return null;


        int nodesLength = 0;
        ListNode dummyHead = head;
        while (dummyHead != null) {
            nodesLength++;
            dummyHead = dummyHead.next;
        }

        if (n == nodesLength)
            return head.next;

        if (n == 0) {
            dummyHead = head;
            while (dummyHead.next.next != null) {
                dummyHead = dummyHead.next;
            }
            dummyHead.next = null;
            return head;
        }

        dummyHead = head;
        int deletedIndex = nodesLength - n;

        for (int i = 0; i < deletedIndex-1; i++) {
            dummyHead = dummyHead.next;
        }
        dummyHead.next = dummyHead.next.next;

        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node22 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node11;
        node11.next = node2;
        node2.next = node22;
        node22.next = node3;

        System.out.println(node1);
//        System.out.println(removeNthFromEnd(node1, 0));
//        System.out.println(removeNthFromEnd(node1, 5));
        System.out.println(removeNthFromEnd(node1, 2));
    }
}
