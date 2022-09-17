package dateStructure.dsPlay.dsa.algrithem.AboutTree;

import java.util.Objects;

public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        // base case
        if (root == null) return true;
        // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    public static void main(String[] args) {
//
//        TreeNode treeNode3 = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(1, null, treeNode3);
//        TreeNode treeNode0 = new TreeNode(1);
//        TreeNode treeNode9 = new TreeNode(1, treeNode0, treeNode2);
//
//        TreeNode treeNode8 = new TreeNode(8);
//        TreeNode treeNode7 = new TreeNode(7, null, treeNode8);
//        TreeNode treeNode15 = new TreeNode(5);
//        TreeNode treeNode20 = new TreeNode(6, treeNode15, treeNode7);
//
//        TreeNode treeNode4 = new TreeNode(4, treeNode9, treeNode20);

        // 输入是一个二叉树
//        TreeNode treeNode9 = new TreeNode(1);
//        TreeNode treeNode20 = new TreeNode(3);
//        TreeNode treeNode4 = new TreeNode(2, treeNode9, treeNode20);

//        TreeNode treeNode9 = new TreeNode(1);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode20 = new TreeNode(4, treeNode3, treeNode6);
//
//        TreeNode treeNode4 = new TreeNode(5, treeNode9, treeNode20);

//        TreeNode treeNode9 = new TreeNode(4);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode6 = new TreeNode(7);
//        TreeNode treeNode20 = new TreeNode(6, treeNode3, treeNode6);
//
//        TreeNode treeNode4 = new TreeNode(5, treeNode9, treeNode20);

//        TreeNode treeNode9 = new TreeNode(2147483647);
//        TreeNode treeNode4 = new TreeNode(-2147483648, null, treeNode9);

        TreeNode treeNode9 = new TreeNode(-2147483648);
        TreeNode treeNode4 = new TreeNode(2147483647, null, treeNode9);

        IsValidBST bst = new IsValidBST();
        boolean bstValidBST = bst.isValidBST(treeNode4);
        System.out.println(bstValidBST);
    }
}
