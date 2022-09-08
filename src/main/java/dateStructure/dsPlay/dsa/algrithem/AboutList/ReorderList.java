package dateStructure.dsPlay.dsa.algrithem.AboutList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ReorderList {

    public static void reorderList(ListNode head) {
        if (Objects.isNull(head.next) || Objects.isNull(head.next.next)) return;

        ArrayList<ListNode> nodes = new ArrayList<>();

        ListNode dummyList = head;
        while (Objects.nonNull(dummyList)) {
            nodes.add(dummyList);
            dummyList = dummyList.next;
        }

        int startIndex = 0;
        int endIndex = nodes.size()-1;

        while (startIndex < endIndex) {
            ListNode tempNext = nodes.get(startIndex).next;

            nodes.get(startIndex).next = nodes.get(endIndex);
            nodes.get(endIndex).next = tempNext;
            startIndex++;
            endIndex--;
        }
        nodes.get(nodes.size()/2).next = null;

        head = nodes.get(0).next;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node11 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node22 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(6);

        node1.next = node11;
//        node11.next = node2;
//        node2.next = node22;
//        node22.next = node3;
//        node3.next = node4;

        System.out.println(node1);
        reorderList(node1);
        System.out.println(node1);

    }

}
