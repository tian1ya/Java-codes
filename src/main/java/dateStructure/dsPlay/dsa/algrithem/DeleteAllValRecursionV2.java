package dateStructure.dsPlay.dsa.algrithem;

/*
    递归本质： 将原来的问题，转化为更小的同一个问题

 */
public class DeleteAllValRecursionV2 {

    public ListNode removeElements(ListNode head, int val) {

        if (head == null)
            return head;

        ListNode res = removeElements(head.next, val);
        /*
           递归到最深处，返回NULL
         */
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }
    }
}
