package dateStructure.dsPlay.dsa.algrithem.AboutTree;

import java.util.Objects;

public class ConvertBST {
    public static Integer sum = 0;

    public static TreeNode convertBST(TreeNode root) {
        if (Objects.isNull(root)) return root;
        traverse(root);
        return root;
    }

    public static void traverse(TreeNode node) {
        if (node == null) return;
        traverse(node.right);

        sum += node.val;
        node.val = sum;
        traverse(node.left);
    }

    public static void main(String[] args) {
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

        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(0, null, treeNode3);

        TreeNode treeNode = convertBST(treeNode4);
        System.out.println("");
    }
}
