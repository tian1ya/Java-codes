package dateStructure.dsPlay.dsa.algrithem.AboutList;

public class SwapPairs {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode tempHead = dummyHead;

        while (tempHead.next != null && tempHead.next.next != null) {
            ListNode nextNode = tempHead.next;
            ListNode finalNode = tempHead.next.next;
            ListNode followingNodes = finalNode.next;

            tempHead.next = finalNode;
            finalNode.next = nextNode;
            nextNode.next = followingNodes;

            tempHead = tempHead.next.next;
        }
        return dummyHead.next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node22 = new ListNode(6);
//        ListNode node3 = new ListNode(7);
        node1.next = node11;
        node11.next = node2;
        node2.next = node22;
//        node22.next = node3;

        System.out.println(node1);
        System.out.println(swapPairs(node1));
    }
}
