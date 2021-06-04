package dateStructure.dsPlay.dsa.algrithem.AboutList;

public class DeleteNode {

    static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node1 = new ListNode(1);
        ListNode node9 = new ListNode(9);

        node4.next = node5;
        node5.next = node1;
        node1.next = node9;

        deleteNode(node5);
        System.out.println("");
    }
}
