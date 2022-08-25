package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.LinkedList;
import java.util.Objects;

public class AddTwoNumbers {
    public static ListNode reverseNodes(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode nextNode = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextNode;
        }

        return prev;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reversedL1 = reverseNodes(l1);
        ListNode reversedL2 = reverseNodes(l2);

        ListNode dummyNode = new ListNode();
        ListNode tempNode = dummyNode;
        int flag = 0;

        while (reversedL1 != null && reversedL2 != null) {
            int sum = reversedL1.val + reversedL2.val;
            tempNode.next = new ListNode((sum + flag) % 10);
            tempNode = tempNode.next;
            flag = (sum + flag) / 10;
            reversedL1 = reversedL1.next;
            reversedL2 = reversedL2.next;
        }

        if (reversedL1 != null) {
            ListNode dummyTempHead = reversedL1;
            while (flag > 0 && dummyTempHead != null) {
                int originalVal = dummyTempHead.val + flag;
                dummyTempHead.val = originalVal % 10;
                flag = originalVal / 10;
                dummyTempHead = dummyTempHead.next;
            }

            tempNode.next = reversedL1;
        }

        if (reversedL2 != null) {
            ListNode dummyTempHead = reversedL2;
            while (flag > 0 && dummyTempHead != null) {
                int originalVal = dummyTempHead.val + flag;
                dummyTempHead.val = originalVal % 10;
                flag = originalVal / 10;
                dummyTempHead = dummyTempHead.next;
            }
            tempNode.next = reversedL2;
        }

        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }


        if (flag > 0) {
            tempNode.next = new ListNode(1);
        }

        return reverseNodes(dummyNode.next);
    }


    public static ListNode addTwoNumbersNoReverse(ListNode l1, ListNode l2) {
        LinkedList<Integer> l1Stack = new LinkedList<>();
        LinkedList<Integer> l2Stack = new LinkedList<>();

        while (l1 != null) {
            l1Stack.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            l2Stack.push(l2.val);
            l2 = l2.next;
        }

        int flag = 0;
        ListNode dummyHead = null;
        while (!l1Stack.isEmpty() || !l2Stack.isEmpty() || flag != 0) {
            int valSum = (!l1Stack.isEmpty() ? l1Stack.pop() : 0) + (!l2Stack.isEmpty() ? l2Stack.pop() : 0) + flag;
            int result = valSum % 10;
            flag = valSum / 10;

            ListNode dummyHeadTemp = new ListNode(result);
            dummyHeadTemp.next = dummyHead;
            dummyHead = dummyHeadTemp;
        }
        return dummyHead;
    }

    public static ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) return l2;
        if (Objects.isNull(l2)) return l1;

        int flag = 0;
        ListNode dummyHead = new ListNode();
        ListNode head = dummyHead;
        while (Objects.nonNull(l1) && Objects.nonNull(l2)) {
            int l1Value = l1.val;
            int l2Value = l2.val;

            l1 = l1.next;
            l2 = l2.next;

            int currentValue = (l1Value + l2Value + flag) % 10;

            head.next = new ListNode(currentValue);

            head = head.next;

            flag = (l1Value + l2Value + flag) / 10;
        }

        ListNode remainList = Objects.isNull(l1) ? l2 : l1;

        while (Objects.nonNull(remainList)) {
            int val = remainList.val;
            int currentValue = (flag + val) % 10;
            head.next = new ListNode(currentValue);

            head = head.next;

            flag = (flag + val) / 10;
            remainList = remainList.next;
        }

        if (flag > 0) {
            head.next = new ListNode(flag);
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
//        ListNode node1 = new ListNode(9);
//        ListNode node11 = new ListNode(4);
//        ListNode node2 = new ListNode(4);
//        ListNode node22 = new ListNode(3);
//        node1.next = node11;
//        node11.next = node2;
//        node2.next = node22;
//
//        ListNode node12 = new ListNode(5);
//        ListNode node122 = new ListNode(6);
//        ListNode node222 = new ListNode(4);
//
//        node12.next = node122;
//        node122.next = node222;

//        System.out.println(node1);
//        System.out.println(node12);
//        System.out.println(addTwoNumbersV2(node1, node12));

//        ListNode listNode = new ListNode(1);
//        ListNode listNode2 = new ListNode(9);
//        ListNode listNode22 = new ListNode(9);
//        listNode2.next = listNode22;
//        System.out.println(addTwoNumbersNoReverse(listNode, listNode2));

        //-------------------

        ListNode node1 = new ListNode(9);
        ListNode node11 = new ListNode(9);
        ListNode node2 = new ListNode(9);
        ListNode node22 = new ListNode(9);

        ListNode node3 = new ListNode(9);
        ListNode node33 = new ListNode(9);
        ListNode node44 = new ListNode(9);

        node1.next = node11;
        node11.next = node2;
        node2.next = node22;

        node22.next = node3;
        node3.next = node33;
        node33.next = node44;

        ListNode node5 = new ListNode(9);
        ListNode node55 = new ListNode(9);
        ListNode node6 = new ListNode(9);
        ListNode node66 = new ListNode(9);

        node5.next = node55;
        node55.next = node6;
        node6.next = node66;

//        System.out.println(reverseNodes(node1));
//        System.out.println(reverseNodes(node12));
        System.out.println(node1);
        System.out.println(node5);
        System.out.println(addTwoNumbersV2(node1, node5));

    }
}
