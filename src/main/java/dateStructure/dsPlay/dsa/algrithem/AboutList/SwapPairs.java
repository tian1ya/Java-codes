package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Objects;

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

    public static ListNode swapPairsV2(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) return head;
        ListNode dummyHead = new ListNode();
        ListNode returnHead = dummyHead;

        while (Objects.nonNull(head)) {

            if (Objects.isNull(head.next)) {
                // 奇数长度链表
                dummyHead.next = head;
                return returnHead.next;
            }

            // 暂存节点
            ListNode currentNode = head;
            ListNode nextNode = head.next;
            ListNode tailNode = head.next.next;

            // 断开链接
            currentNode.next = null;
            nextNode.next = null;

            // 链接新链
            dummyHead.next = nextNode;
            nextNode.next = currentNode;
            dummyHead = currentNode;

            // 原链，往后走2个节点
            head = tailNode;
        }
        return returnHead.next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(2);
        ListNode node2 = new ListNode(3);
//        ListNode node22 = new ListNode(4);
//        ListNode node3 = new ListNode(7);
        node1.next = node11;
        node11.next = node2;
//        node2.next = node22;
//        node22.next = node3;

        System.out.println(node1);
        System.out.println(swapPairsV2(node1));
    }
}
