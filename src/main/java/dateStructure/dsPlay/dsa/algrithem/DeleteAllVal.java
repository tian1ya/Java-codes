package dateStructure.dsPlay.dsa.algrithem;


public class DeleteAllVal {

    public ListNode removeElements(ListNode head, int val) {

        if (head == null) return head;

        if (head.val == val)  {
            // 处理头出现 val 的情况
            return removeElements(head.next, val);
        }

        ListNode dummyHead = head;
        ListNode pointer = head.next;

        /*
            必须保证 pointer 是在 dummyHead 的后面
            否则就会出现 当 pointer=dummyHead，
            就会出现 dummyHead.next = dummyHead;
            的死循环。
         */

        while (pointer != null) {

            if (pointer.val != val) {
                dummyHead.next = pointer;
                dummyHead = dummyHead.next;

            }
            /*
                这里逻辑上可以保证 pointer 一直在 dummyHead的后面
                多线程除外
             */
            pointer = pointer.next;
        }

        dummyHead.next = pointer;
        return head;
    }

}
