package dateStructure.dsPlay.dsa.algrithem.LinkedList;

public class GetKthFromEnd {
    static ListNode getKthFromEnd(ListNode head, int k) {

        int listLength = 0;
        ListNode dummyNode = head;
        while (dummyNode != null) {
            listLength++;
            dummyNode = dummyNode.next;
        }

        dummyNode = head;
        for (int i = 1; i < listLength; i++) {
            if (i == listLength - k)
                return dummyNode.next;
            dummyNode = dummyNode.next;
        }
        return head;
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

        ListNode kthFromEnd = getKthFromEnd(node1, 2);
        System.out.println("");
    }
}
