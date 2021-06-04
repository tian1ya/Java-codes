package dateStructure.dsPlay.dsa.algrithem.AboutList;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    ListNode(int x) {
        val = x;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
