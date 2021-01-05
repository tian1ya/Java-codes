package dateStructure.dsPlay.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
    请你将所有链表合并到一个升序链表中，返回合并后的链表。

 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val).append("->");

        ListNode tmp = next;

        while (true) {
            sb.append(tmp.val);
            tmp = tmp.next;
            if (tmp != null) {
                sb.append("->");
            } else {
                break;
            }
        }
        return sb.toString();
    }
}

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);

        ListNode curr = dummyHead;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }
        // 完成排序

        while (!pq.isEmpty()) {
            // Retrieves and removes the head of this queue,
            ListNode nextNode = pq.poll();
            curr.next = nextNode;
            curr = curr.next;
            if (nextNode.next != null) {
                // Inserts the specified element into this priority queue.
                pq.add(nextNode.next);
            }
        }
        return dummyHead.next;
    }

    public ListNode mergeKListsV2(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        if (lists.length == 2) {
            return mergeKListsV22(lists[0], lists[1]);
        }

        int mid = lists.length / 2;
        ListNode[] leftNodes = new ListNode[mid];
        ListNode[] rightNodes = new ListNode[lists.length - mid];
        for (int i = 0; i < mid; i++) {
            leftNodes[i] = lists[i];
        }

        for (int i = mid; i < lists.length; i++) {
            rightNodes[i-mid] = lists[i];
        }

        ListNode leftNodeV2 = mergeKListsV2(leftNodes);
        ListNode rightNodeV2 = mergeKListsV2(rightNodes);
        // 返回左右2个拍好序的 listNode，注意这里在开头 只有2个Node 的时候就会去排序的
        return mergeKListsV22(leftNodeV2, rightNodeV2);
    }


    private ListNode mergeKListsV22(ListNode node1, ListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        /*
            node1 和 node2 都均是以及递增的 listNode
         */
        ListNode node;
        if (node1.val < node2.val) {
            /*
                node1.val 是最小的，返回最小值
                然后比较 node1.next 和 node2 进行比较
             */
            node = new ListNode(node1.val);
            node.next = mergeKListsV22(node1.next, node2);
        } else {
            node = new ListNode(node2.val);
            node.next = mergeKListsV22(node2.next, node1);
        }
        // 每次返回都是当前的最小的 node
        return node;
    }

    public static void main(String[] args) {

        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode1 = new ListNode(1, listNode4);


        ListNode listNode25 = new ListNode(4);
        ListNode listNode24 = new ListNode(3, listNode25);
        ListNode listNode21 = new ListNode(1, listNode24);


        ListNode listNode35 = new ListNode(6);
        ListNode listNode34 = new ListNode(2, listNode35);

        System.out.println(listNode1);
        System.out.println(listNode21);
        System.out.println(listNode34);

//        ListNode listNode = new MergeKLists().mergeKLists(new ListNode[]{listNode1, listNode21,  listNode34});
        ListNode listNode = new MergeKLists().mergeKListsV2(new ListNode[]{listNode1, listNode21,listNode34});
        System.out.println(listNode1);
        System.out.println(listNode21);
        System.out.println(listNode34);

        System.out.println(listNode);

    }
}
