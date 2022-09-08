package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.ArrayList;
import java.util.Objects;

public class IsPalindrome {
    /**
     * 回文链表: 正想和反向遍历，序列一样的链表
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (Objects.isNull(head)) return false;
        if (Objects.nonNull(head) && Objects.nonNull(head.next) && Objects.isNull(head.next.next))
            return head.val == head.next.val;

        ArrayList<ListNode> nodes = new ArrayList<>();

        ListNode dummyNodes = head;
        while (Objects.nonNull(dummyNodes)) {
            nodes.add(dummyNodes);
            dummyNodes = dummyNodes.next;
        }

        int starIndex = 0;
        int endIndex = nodes.size() - 1;
        while (starIndex < endIndex) {
            if (nodes.get(starIndex).val != nodes.get(endIndex).val) return false;
            starIndex++;
            endIndex--;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(0);
        ListNode node5 = new ListNode(5);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(node0);
        System.out.println(isPalindrome(node0));
    }
}
