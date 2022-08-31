package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Objects;
import java.util.function.BiFunction;

public class ReverseKGroup {

    public static ListNode reverseList(ListNode head) {

        ListNode preNode = null, nextNode, currentNode = head;

        while (Objects.nonNull(currentNode)) {
            nextNode = currentNode.next;

            currentNode.next = preNode;
            preNode = currentNode;

            currentNode = nextNode;
        }
        return preNode;
    }

    public static ListNode reverseListV2(ListNode head) {
        return Objects.isNull(head) ? head : reverseListV2Inner(null, head);
    }

    public static ListNode reverseListV2Inner(ListNode preNode, ListNode curNode) {
        if (curNode.next == null) {
            curNode.next = preNode;
            return curNode;
        }

        ListNode nextNode = curNode.next;
        curNode.next = preNode;
        return reverseListV2Inner(curNode, nextNode);
    }


    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode();
        ListNode pre = dummyHead;

        ListNode start = head, end = head;

        while (Objects.nonNull(end)) {
            // end 先前走 k 步
            for (int i = 0; i < k - 1 && Objects.nonNull(end); i++) end = end.next;

            if (Objects.isNull(end)) break;

            // 断开，找到当前排序的那段 list
            ListNode next = end.next;
            end.next = null;

            // 排序当前 list 段
            ListNode reverseList = reverseList(start);
            pre.next = reverseList;

            // 此时 start 指向 reverseList 的tail
            start.next = next;

            pre = start;
            start = start.next;
            end = start;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);


        ListNode node11 = new ListNode(4);
        ListNode node33 = new ListNode(5);

        ListNode node44 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node11;

        node11.next = node33;
//        node33.next = node44;

        System.out.println(node1);
        System.out.println(reverseKGroup(node1, 1));
//        System.out.println(reverseListV2(node1));
    }
}
