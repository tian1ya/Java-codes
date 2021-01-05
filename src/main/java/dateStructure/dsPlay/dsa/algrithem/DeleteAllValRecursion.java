package dateStructure.dsPlay.dsa.algrithem;

/*
    递归本质： 将原来的问题，转化为更小的同一个问题

 */
public class DeleteAllValRecursion {

    public ListNode removeElements(ListNode head, int val) {

        if (head == null)
            return head;

        if (head.val == val) {
            return removeElements(head.next, val);
        }

        ListNode dummyHead = head;
        ListNode pointer = head.next;

        dummyHead = nextNode(pointer, dummyHead, val);
        dummyHead.next = null;

        return head;
    }

    private ListNode nextNode(ListNode pointer, ListNode dummyHead, int val) {
        if (pointer == null) return dummyHead;

        if (pointer.val != val) {
            dummyHead.next = pointer;
            dummyHead = dummyHead.next;
        }

        return nextNode(pointer.next, dummyHead, val);
    }
}
