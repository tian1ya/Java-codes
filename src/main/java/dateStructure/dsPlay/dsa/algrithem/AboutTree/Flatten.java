package dateStructure.dsPlay.dsa.algrithem.AboutTree;

public class Flatten {
    public static void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        /*
            1、左右子树已经被拉平成一条链表
               如果是遍历的中间，那么经过之前返回的步骤中已经执行了下面的步骤，所以已经拉平
               而如果是递归到最后，那么是经过下面的步骤才拉平的
         */
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node6 = new TreeNode(6);
//        TreeNode node7 = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);
        node3.right = node6;

        TreeNode node1 = new TreeNode(1, node2, node3);

        flatten(node1);
        System.out.println("");
    }
}
