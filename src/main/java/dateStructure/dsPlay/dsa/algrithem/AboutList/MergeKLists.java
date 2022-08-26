package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.Arrays;
import java.util.Objects;

public class MergeKLists {

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyHead = new ListNode();
        ListNode head = dummyHead;

        if (lists.length <= 0) return null;

        while (Arrays.stream(lists).anyMatch(Objects::nonNull)) {
            int smallValue = Integer.MAX_VALUE;
            for (ListNode node : lists) {
                if (Objects.nonNull(node) && node.val < smallValue) {
                    smallValue = node.val;
                }
            }

            ListNode node = null;
            int index = 0;
            for (index = 0; index < lists.length; index++) {
                node = lists[index];
                if (Objects.nonNull(node) && node.val == smallValue) {
                    node = node.next;
                    break;
                }
            }
            lists[index] = node;

            dummyHead.next = new ListNode(smallValue);
            dummyHead = dummyHead.next;
        }


        return head.next;
    }

    public static ListNode mergeKListsV2(ListNode[] lists) {
        ListNode dummyHead = new ListNode();
        ListNode head = dummyHead;

        if (lists.length <= 0) return null;

        while (Arrays.stream(lists).anyMatch(Objects::nonNull)) {

            int smallIndex = 0;
            int smallValue = Integer.MAX_VALUE;
            for (int index = 0; index < lists.length; index++) {
                ListNode node = lists[index];
                if (Objects.nonNull(node) && node.val < smallValue) {
                    smallValue = node.val;
                    smallIndex = index;
                }
            }

            lists[smallIndex] = lists[smallIndex].next;

            dummyHead.next = new ListNode(smallValue);
            dummyHead = dummyHead.next;
        }


        return head.next;
    }

    public static void mergeKListsV3Inner(ListNode dummyHead, ListNode[] lists) {
        if (Arrays.stream(lists).allMatch(Objects::isNull)) return;

        int smallIndex = 0;
        int smallValue = Integer.MAX_VALUE;
        for (int index = 0; index < lists.length; index++) {
            ListNode node = lists[index];
            if (Objects.nonNull(node) && node.val < smallValue) {
                smallValue = node.val;
                smallIndex = index;
            }
        }

        lists[smallIndex] = lists[smallIndex].next;

        dummyHead.next = new ListNode(smallValue);
        dummyHead = dummyHead.next;

        mergeKListsV3Inner(dummyHead, lists);
    }

    public static ListNode mergeKListsV3(ListNode[] lists) {

        if (lists.length <= 0) return null;

        ListNode dummyHead = new ListNode(),head = dummyHead;

        mergeKListsV3Inner(dummyHead, lists);

        return head.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;

        ListNode node11 = new ListNode(1);
        ListNode node33 = new ListNode(3);
        ListNode node44 = new ListNode(4);
        node11.next = node33;
        node33.next = node44;

        ListNode node45 = new ListNode(2);
        ListNode node46 = new ListNode(3);
        node45.next = node46;

        System.out.println(node1);
        System.out.println(node11);
        System.out.println(node45);

        System.out.println(mergeKListsV3(new ListNode[]{node1, node11, node45}));
    }
}
