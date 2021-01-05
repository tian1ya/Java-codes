package dateStructure.dsPlay.dsa.algrithem;

/*
    递归本质： 将原来的问题，转化为更小的同一个问题

    链表具有天然的递归

    所有的操作都是可以使用递归完成，如增删改查

    双链表，在链表2端进行操作，每一个节点2个指针，分别指向前和后节点

    循环双向列表

    数组实现列表，每一个位置还存储指向下个元素的index。

 */
public class DeleteAllValRecursionV3 {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head; // 画图理解
    }
}
