package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.HashSet;
import java.util.Objects;

public class GetIntersectionNode {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        int headALength = 0, headBLength = 0;
        ListNode headATemp = headA, headBTemp = headB;
        while (headATemp != null) {
            headALength++;
            headATemp = headATemp.next;
        }
        while (headBTemp != null) {
            headBLength++;
            headBTemp = headBTemp.next;
        }

        if (headALength == 0 || headBLength == 0) {
            return null;
        }


        headATemp = headA;
        for (int i = 0; i < headALength; i++) {
            headBTemp = headB;
            for (int j = 0; j < headBLength; j++) {

                if (Objects.equals(headATemp, headBTemp))
                    return headATemp;

                if (headBTemp.next == null) break;
                headBTemp = headBTemp.next;
            }

            if (headATemp.next == null) break;
            headATemp = headATemp.next;
        }
        return null;
    }


    public static ListNode getIntersectionNodeHashTable(ListNode headA, ListNode headB) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode headATemp = headA;
        while (headATemp!= null) {
            visited.add(headATemp);
            headATemp = headATemp.next;
        }

        ListNode headBTemp = headB;
        while (headBTemp!=null) {
            if (visited.contains(headBTemp))
                return headBTemp;
            headBTemp = headBTemp.next;
        }
        return null;
    }

    /*
        https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
        设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
        当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；同样地，当访问 B 链表的指针访问到链表尾部时，
        令它从链表 A 的头部开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
        如果不存在交点，那么 a + b = b + a，以下实现代码中 l1 和 l2 会同时为 null，从而退出循环。
     */
    public static ListNode getIntersectionNodeDoublePointer(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode ptrA = headA;
        ListNode ptrB = headB;
        while (ptrA != ptrB) {
            ptrA = ptrA == null ? headB : ptrA.next;
            ptrB = ptrB == null ? headA : ptrB.next;
        }
        return ptrA;
    }


        public static void main(String[] args) {
        ListNode listNode4 = new ListNode(5);
        ListNode listNode3 = new ListNode(4, listNode4);
        ListNode listNode2 = new ListNode(8, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        ListNode listNode = new ListNode(4, listNode1);

        ListNode listNode13 = new ListNode(1, listNode2);
        ListNode listNode12 = new ListNode(0, listNode13);
        ListNode listNode11 = new ListNode(5, listNode12);

        System.out.println(getIntersectionNodeDoublePointer(listNode, listNode11));
    }
}
