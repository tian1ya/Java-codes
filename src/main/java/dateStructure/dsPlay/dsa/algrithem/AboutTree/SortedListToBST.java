package dateStructure.dsPlay.dsa.algrithem.AboutTree;


/*
    给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
    本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 */

public class SortedListToBST {

    ListNode globalNode;

    static public class ListNode {
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
    }


    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /*
        第一种方式，分治
            将给定的有序链表转换为二叉搜索树的第一步是确定根节点。由于我们需要构造出平衡的二叉树，因此比较直观的想法是让根节点左子树中的节点个数与右子树中的节点个数尽可能接近。
            这样一来，左右子树的高度也会非常接近，可以达到高度差绝对值不超过 11 的题目要求。

        如何找出这样的一个根节点呢？我们可以找出链表元素的中位数作为根节点的值。
            这里对于中位数的定义为：如果链表中的元素个数为奇数，那么唯一的中间值为中位数；如果元素个数为偶数，那么唯二的中间值都可以作为中位数，而不是常规定义中二者的平均值。

     */
    public static TreeNode sortedListToBST(ListNode head) {
        return buildTreeFenZhi(head, null);
    }

    private static TreeNode buildTreeFenZhi(ListNode left, ListNode right) {
        if (right == left) return null;

        ListNode midNode= getMiddleNode(left, right);
        TreeNode root = new TreeNode(midNode.val);
        root.left = buildTreeFenZhi(left, midNode);
        root.right = buildTreeFenZhi(midNode.next, right);
        return root;
    }

    private static ListNode getMiddleNode(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;

        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    /*
        由于构造出的二叉搜索树的中序遍历结果就是链表本身，因此我们可以将分治和中序遍历结合起来，减少时间复杂度。
        这样一来，我们其实已经知道了这棵二叉搜索树的结构，并且题目给定了它的中序遍历结果，那么我们只要对其进行中序遍历，就可以还原出整棵二叉搜索树了。
        https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/shou-hua-tu-jie-san-chong-jie-fa-jie-zhu-shu-zu-ku/
     */
    public static TreeNode sortedListToBSTV2(ListNode head) {
        SortedListToBST toBST = new SortedListToBST();
        int length = toBST.getListLength(head);
        return toBST.buildTreeV2(0, length-1);
    }

    private TreeNode buildTreeV2(int left, int right) {
        if (left > right) return null;
        int mid = (left + right + 1) % 2;
        TreeNode treeNode = new TreeNode();
        treeNode.left = buildTreeV2(left, mid);
        treeNode.val = this.globalNode.val;
        this.globalNode = this.globalNode.next;
        treeNode.right = buildTreeV2(mid+1, right);
        return treeNode;
    }

    public int getListLength(ListNode node) {
        int length = 1;
        ListNode temp = node.next;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }
    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(9);
        ListNode listNode3 = new ListNode(5, listNode4);
        ListNode listNode2 = new ListNode(0, listNode3);
        ListNode listNode1 = new ListNode(-3, listNode2);
        ListNode listNode = new ListNode(-10, listNode1);

        TreeNode treeNode = sortedListToBST(listNode);
        System.out.println("a");
    }
}
